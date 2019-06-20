package com.aimprosoft.kmb.web.conrollers;

import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.domain.Employee;
import com.aimprosoft.kmb.exceptions.ServiceException;
import com.aimprosoft.kmb.exceptions.ValidationException;
import com.aimprosoft.kmb.service.DepartmentService;
import com.aimprosoft.kmb.validator.EmployeeValidator;
import com.aimprosoft.kmb.validator.ValidationResult;
import com.aimprosoft.kmb.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeTemplate {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final Validator<Employee> validator = new EmployeeValidator();
    private DepartmentService departmentService = new DepartmentService();

    public Employee extractEmployeeFromRequest(HttpServletRequest req, Employee employee) throws ServiceException {

        req.setAttribute("departmentId", req.getParameter("departmentId"));

        employee.setFirstName(req.getParameter("firstName"));
        employee.setLastName(req.getParameter("lastName"));
        employee.setEmail(req.getParameter("email"));
        employee.setPhoneNumber(req.getParameter("phoneNumber"));
        try {
            employee.setAge(Integer.parseInt(req.getParameter("age")));
            employee.setEmploymentDate(simpleDateFormat.parse(req.getParameter("employmentDate")));
        } catch (ParseException | NumberFormatException e) {
            return getInvalidEmployee(req, employee);
        }
        return employee;
    }

    private Employee getInvalidEmployee(HttpServletRequest req, Employee employee) throws ServiceException {
        if (req.getParameter("employeeId") != null) {
            req.setAttribute("employeeId", req.getParameter("employeeId"));
        }

        final List<Department> allDepartments = departmentService.getAll();
        req.setAttribute("allDepartments", allDepartments);

        final ValidationResult validationResult = validator.validate(employee);

        Map<String, Object> parameter = new HashMap<>();
        parameter.put("firstName", req.getParameter("firstName"));
        parameter.put("lastName", req.getParameter("lastName"));
        parameter.put("email", req.getParameter("email"));
        parameter.put("age", req.getParameter("age"));
        parameter.put("phoneNumber", req.getParameter("phoneNumber"));
        try {
            parameter.put("employmentDate", simpleDateFormat.parse(req.getParameter("employmentDate")));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        validationResult.addError("incorrectEmployeeData", parameter);
        throw new ValidationException(validationResult.getError());
    }
}
