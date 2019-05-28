package com.aimprosoft.kmb.web.servlets.action.employee;

import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.domain.Employee;
import com.aimprosoft.kmb.exceptions.ServiceException;
import com.aimprosoft.kmb.service.DepartmentService;
import com.aimprosoft.kmb.service.EmployeeService;
import com.aimprosoft.kmb.validator.EmployeeValidator;
import com.aimprosoft.kmb.validator.ValidationResult;
import com.aimprosoft.kmb.validator.Validator;
import com.aimprosoft.kmb.web.servlets.EmployeeTemplate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update-employee-action")
public class UpdateEmployeeActionServlet extends HttpServlet {

    private EmployeeService employeeService = new EmployeeService();
    private DepartmentService departmentService = new DepartmentService();
    private EmployeeTemplate employeeTemplate = new EmployeeTemplate();
    private Validator<Employee> validator = new EmployeeValidator();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final long employeeId = Long.parseLong(req.getParameter("employeeId"));
        final long departmentId = Long.parseLong(req.getParameter("departmentId"));
        final Employee employee;
        try {
            employee = getEmployee(req, employeeId, departmentId);
            final ValidationResult validationResult = validator.validate(employee);
            if (validationResult.hasError()) {
                processError(req, resp, employee, validationResult);
                return;
            }
            updateEmployee(req, resp, employee, departmentId);
        } catch (ServiceException e) {
            throw new ServletException(e.getMessage());
        }
    }

    private Employee getEmployee(HttpServletRequest req, long employeeId, long departmentId) throws ServiceException {
        Department departmentById;
        departmentById = departmentService.getDepartmentById(departmentId);
        final Employee employeeById = employeeService.getEmployeeById(employeeId);
        final Employee employee = employeeTemplate.extractEmployeeFromRequest(req, employeeById);
        employee.setDepartment(departmentById);
        return employee;
    }

    private void processError(HttpServletRequest req, HttpServletResponse resp, Employee employee, ValidationResult validationResult) throws ServletException, IOException {
        req.setAttribute("errors", validationResult.getErrorMessage());
        req.setAttribute("employee", employee);
        req.getRequestDispatcher("update-employee-page.jsp").forward(req, resp);
    }

    private void updateEmployee(HttpServletRequest req, HttpServletResponse resp, Employee employee, long departmentId) throws ServiceException, IOException {
        employeeService.updateEmployee(employee);
        req.setAttribute("successMessage", "Employee with id: " + employee.getId() + " updated!");
        resp.sendRedirect("manage-employees-page?departmentId=" + departmentId);
    }

}
