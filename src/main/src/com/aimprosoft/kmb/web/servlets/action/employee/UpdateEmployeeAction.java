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
import java.util.List;

public class UpdateEmployeeAction implements Controller {

    private EmployeeService employeeService = new EmployeeService();
    private DepartmentService departmentService = new DepartmentService();
    private EmployeeTemplate employeeTemplate = new EmployeeTemplate();
    private Validator<Employee> validator = new EmployeeValidator();


    @Override
    public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, ServletException, IOException {
        final long employeeId = Long.parseLong(req.getParameter("employeeId"));
        final long departmentId = Long.parseLong(req.getParameter("newDepartmentId"));
        final Employee employee = getEmployee(req, employeeId, departmentId);
        final List<Department> allDepartments = departmentService.getAll();
        final String updatableEmail = req.getParameter("updatableEmail");
        final ValidationResult validationResult = validator.validate(employee, updatableEmail);
        if (validationResult.hasError()) {
            req.setAttribute("errors", validationResult.getErrorMessage());
            req.setAttribute("employee", employee);
            req.setAttribute("allDepartments", allDepartments);
            throw new ValidationException("error");
        }
        final int previousDepId = Integer.parseInt(req.getParameter("departmentId"));
        req.setAttribute("dataForRedirect", "?departmentId=" + previousDepId);
        employeeService.update(employee);
    }

    private Employee getEmployee(HttpServletRequest req, long employeeId, long departmentId) throws ServiceException {
        Department departmentById;
        departmentById = departmentService.getById(departmentId);
        final Employee employeeById = employeeService.getById(employeeId);
        final Employee employee = employeeTemplate.extractEmployeeFromRequest(req, employeeById);
        employee.setDepartment(departmentById);
        return employee;
    }
}
