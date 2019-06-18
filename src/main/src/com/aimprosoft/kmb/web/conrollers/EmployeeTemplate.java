package com.aimprosoft.kmb.web.conrollers;

import com.aimprosoft.kmb.domain.Employee;
import com.aimprosoft.kmb.exceptions.ApplicationException;
import com.aimprosoft.kmb.validator.ValidationResult;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EmployeeTemplate {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private ValidationResult validationResult = new ValidationResult();

    public Employee extractEmployeeFromRequest(HttpServletRequest req, Employee employee) throws ApplicationException {
        employee.setFirstName(req.getParameter("firstName"));
        employee.setLastName(req.getParameter("lastName"));
        employee.setEmail(req.getParameter("email"));
        employee.setPhoneNumber(req.getParameter("phoneNumber"));
//        try {
//            employee.setAge(Integer.parseInt(req.getParameter("age")));
//        } catch (NumberFormatException e) {
//            validationResult.addError("age", "Age can`t contains a char.");
//        }
        try {
            employee.setEmploymentDate(simpleDateFormat.parse(req.getParameter("employmentDate")));
        } catch (ParseException e) {
            throw new ApplicationException("Can`t parse to data", e);
        }
        return employee;
    }
}
