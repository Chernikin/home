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


@WebServlet("/create-employee-action")
public class CreateEmployeeActionServlet extends HttpServlet {

    private EmployeeService employeeService = new EmployeeService();
    private DepartmentService departmentService = new DepartmentService();
    private EmployeeTemplate employeeTemplate = new EmployeeTemplate();
    private Validator<Employee> validator = new EmployeeValidator();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Employee employee = getEmployee(req);
        final ValidationResult validationResult = validator.validate(employee);
        if (validationResult.hasError()) {
            processError(req, resp, employee, validationResult);
            return;
        }
        try {
            createEmployee(req, resp, employee);
        } catch (ServiceException e) {
            throw new ServletException(e.getMessage());
        }


    }

    private Employee getEmployee(HttpServletRequest req) {
        final Employee employeeNew = new Employee();
        return employeeTemplate.extractEmployeeFromRequest(req, employeeNew);
    }

    private void processError(HttpServletRequest req, HttpServletResponse resp, Employee employee, ValidationResult validationResult) throws ServletException, IOException {
        final long departmentId = Long.parseLong(req.getParameter("departmentId"));
        req.setAttribute("errors", validationResult.getErrorMessage());
        req.setAttribute("incorrectEmployeeData", employee);
        req.setAttribute("departmentId", departmentId);
        req.getRequestDispatcher("create-employee-page.jsp").forward(req, resp);
    }

    private void createEmployee(HttpServletRequest req, HttpServletResponse resp, Employee employee) throws IOException, ServiceException {
        final Department department;
        final long departmentId = Long.parseLong(req.getParameter("departmentId"));
        try {
            department = departmentService.getDepartmentById(departmentId);
            employee.setDepartment(department);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        employeeService.createEmployee(employee);
        req.setAttribute("successMessage", "Employee successfully created!");
        resp.sendRedirect("manage-employees-page?departmentId=" + departmentId);
    }
}
