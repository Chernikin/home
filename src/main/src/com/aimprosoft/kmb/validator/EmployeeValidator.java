package com.aimprosoft.kmb.validator;

import com.aimprosoft.kmb.domain.Employee;

import java.util.Date;

public class EmployeeValidator implements Validator<Employee> {

    public static final int MAX_FIRST_NAME_LENGTH = 25;
    public static final int MAX_LAST_NAME_LENGTH = 30;
    public static final int MAX_EMAIL_LENGTH = 50;
    public static final int MAX_AGE_LENGTH = 3;
    public static final int MAX_AGE = 105;
    public static final int MAX_PHONE_NUMBER_LENGTH = 13;

    @Override
    public ValidationResult validate(Employee employee) {
        final ValidationResult validationResult = new ValidationResult();
        if (!validateFirstName(employee)) {
            validationResult.addErrorMessage("firstName", "First name is not valid");
        }
        if (!validateLastName(employee)) {
            validationResult.addErrorMessage("lastName", "Last name is not valid");
        }
        if (!validateEmail(employee)) {
            validationResult.addErrorMessage("email", "e-mail is not valid");
        }
        if (!validateAge(employee)) {
            validationResult.addErrorMessage("age", "Age is not valid");
        }
        if (!validatePhoneNumber(employee)) {
            validationResult.addErrorMessage("phoneNumber", "Phone number is not valid");
        }
        if (!validateEmploymentDate(employee)) {
            validationResult.addErrorMessage("employmentDate", "Employment date is not valid");
        }
        return validationResult;
    }

    public boolean validateFirstName(Employee employee) {
        final String firstName = employee.getFirstName();
        return firstName != null && firstName.length() <= MAX_FIRST_NAME_LENGTH;
    }

    public boolean validateLastName(Employee employee) {
        final String lastName = employee.getLastName();
        return lastName != null && lastName.length() <= MAX_LAST_NAME_LENGTH;
    }

    public boolean validateEmail(Employee employee) {
        final String email = employee.getEmail();
        return email != null && email.length() <= MAX_EMAIL_LENGTH;
    }

    public boolean validateAge(Employee employee) {
        final int age = employee.getAge();
        return String.valueOf(age).length() <= MAX_AGE_LENGTH && age <= MAX_AGE;
    }

    public boolean validatePhoneNumber(Employee employee) {
        final String phoneNumber = employee.getPhoneNumber();
        return phoneNumber.length() <= MAX_PHONE_NUMBER_LENGTH;
    }

    public boolean validateEmploymentDate(Employee employee) {
        final Date employmentDate = employee.getEmploymentDate();
        return employmentDate != null;
    }
}
