package com.aimprosoft.kmb.validator;

import com.aimprosoft.kmb.database.EmployeeDao;
import com.aimprosoft.kmb.database.jdbc.EmployeeDaoJDBC;
import com.aimprosoft.kmb.domain.Employee;
import com.aimprosoft.kmb.exceptions.RepositoryException;
import com.aimprosoft.kmb.web.servlets.action.department.GeneralPage;
import org.apache.log4j.Logger;

import java.util.Date;

public class EmployeeValidator implements Validator<Employee> {

    private static final int MAX_FIRST_NAME_LENGTH = 25;
    private static final int MAX_LAST_NAME_LENGTH = 30;
    private static final int MAX_EMAIL_LENGTH = 50;
    private static final int MAX_AGE = 99;
    private static final int MAX_PHONE_NUMBER_LENGTH = 13;
    private static final int DEP_NOT_VALID_NUMBER = 0;
    private EmployeeDao employeeDao = new EmployeeDaoJDBC();
    private static Logger logger = Logger.getLogger(EmployeeValidator.class);


    @Override
    public ValidationResult validate(Employee employee) {
        final ValidationResult validationResult = new ValidationResult();
        if (!validateFirstName(employee)) {
            validationResult.addErrorMessage("firstName", "First name is not valid. First name cannot be empty or more than 25 characters.");
        }
        if (!validateLastName(employee)) {
            validationResult.addErrorMessage("lastName", "Last name is not valid. Last name cannot be empty or more than 30 characters.");
        }
        if (!validateEmail(employee)) {
            validationResult.addErrorMessage("email", "E-mail is not valid. E-mail cannot be empty or more than 50 characters. Must contain '@' and '.' .");
        }
        if (!validateEmailExist(employee)) {
            validationResult.addErrorMessage("email", "E-mail is not valid. This e-mail is already used!");
        }
        if (!validateAge(employee)) {
            validationResult.addErrorMessage("age", "Age is not valid. Age cannot be less than 18 and more than 99 years old.");
        }
        if (!validatePhoneNumber(employee)) {
            validationResult.addErrorMessage("phoneNumber", "Phone number is not valid. Phone number cannot be more than 13 digits.");
        }
        if (!validateEmploymentDate(employee)) {
            validationResult.addErrorMessage("employmentDate", "Employment date is not valid. Date cannot be empty.");
        }
       /* if (!validateDepartmentId(employee)) {
            validationResult.addErrorMessage("departmentId", "Department id is not valid");
        }*/
        return validationResult;
    }

    private boolean validateFirstName(Employee employee) {
        final String firstName = employee.getFirstName();
        return firstName != null && !firstName.isEmpty() && firstName.length() <= MAX_FIRST_NAME_LENGTH;
    }

    private boolean validateLastName(Employee employee) {
        final String lastName = employee.getLastName();
        return lastName != null && !lastName.isEmpty() && lastName.length() <= MAX_LAST_NAME_LENGTH;
    }

    private boolean validateEmail(Employee employee) {
        final String email = employee.getEmail();
        return email != null && !email.isEmpty() && email.contains("@") && email.contains(".") && email.length() <= MAX_EMAIL_LENGTH;
    }

    private boolean validateEmailExist(Employee employee) {
        try {
            return !employeeDao.isExists(employee);
        } catch (RepositoryException e) {
            logger.error("Can`t check email on exist!");
        }
        return false;
    }

    private boolean validateAge(Employee employee) {
        final int age = employee.getAge();
        return age >= 18 && age <= MAX_AGE;
    }

    private boolean validatePhoneNumber(Employee employee) {
        final String phoneNumber = employee.getPhoneNumber();
        return phoneNumber.length() <= MAX_PHONE_NUMBER_LENGTH;
    }

    private boolean validateEmploymentDate(Employee employee) {
        final Date employmentDate = employee.getEmploymentDate();
        return employmentDate != null;
    }

    private boolean validateDepartmentId(Employee employee) {
        final long departmentId = employee.getDepartment().getId();
        return departmentId > DEP_NOT_VALID_NUMBER;
    }
}
