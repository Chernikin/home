package com.aimprosoft.kmb.web.servlets.action.employee;

import com.aimprosoft.kmb.conroller.Controller;
import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.domain.Employee;
import com.aimprosoft.kmb.exceptions.ServiceException;
import com.aimprosoft.kmb.exceptions.ValidationException;
import com.aimprosoft.kmb.service.DepartmentService;
import com.aimprosoft.kmb.service.EmployeeService;
import com.aimprosoft.kmb.validator.EmployeeValidator;
import com.aimprosoft.kmb.validator.ValidationResult;
import com.aimprosoft.kmb.validator.Validator;
import com.aimprosoft.kmb.web.servlets.EmployeeTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CreateEmployeeAction implements Controller {

    private EmployeeService employeeService = new EmployeeService();
    private DepartmentService departmentService = new DepartmentService();
    private EmployeeTemplate employeeTemplate = new EmployeeTemplate();
    private Validator<Employee> validator = new EmployeeValidator();

    @Override
    public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, ServletException, IOException {
        final Employee employee = getEmployee(req);

        final long departmentId = Long.parseLong(req.getParameter("departmentId"));
        Department department = departmentService.getById(departmentId);
        employee.setDepartment(department);
        String updatableEmail = "validateEmail";
        final ValidationResult validationResult = validator.validate(employee, updatableEmail);
        if (validationResult.hasError()) {
            req.setAttribute("errors", validationResult.getError());
            req.setAttribute("incorrectEmployeeData", employee);
            req.setAttribute("departmentId", departmentId);
            throw new ValidationException("error");
        }

        req.setAttribute("dataForRedirect", "?departmentId=" + departmentId);
        employeeService.create(employee);

    }

    private Employee getEmployee(HttpServletRequest req) {
        final Employee employeeNew = new Employee();
        return employeeTemplate.extractEmployeeFromRequest(req, employeeNew);
    }
}
