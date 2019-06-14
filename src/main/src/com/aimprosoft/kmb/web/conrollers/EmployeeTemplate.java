package com.aimprosoft.kmb.web.conrollers;

import com.aimprosoft.kmb.domain.Employee;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EmployeeTemplate {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Employee extractEmployeeFromRequest(HttpServletRequest req, Employee employee) {
        employee.setFirstName(req.getParameter("firstName"));
        employee.setLastName(req.getParameter("lastName"));
        employee.setEmail(req.getParameter("email"));
        employee.setPhoneNumber(req.getParameter("phoneNumber"));
        try {
            employee.setAge(Integer.parseInt(req.getParameter("age")));
        } catch (NumberFormatException e) {
            System.out.println("Can`t parse to Integer." + e);
        }
        try {
            employee.setEmploymentDate(simpleDateFormat.parse(req.getParameter("employmentDate")));
        } catch (ParseException e) {
            System.out.println("Can`t parse date" + e);
        }
        return employee;
    }
}
