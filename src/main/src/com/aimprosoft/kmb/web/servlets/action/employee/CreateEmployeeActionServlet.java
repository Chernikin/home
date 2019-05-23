package com.aimprosoft.kmb.web.servlets.action.employee;

import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.domain.Employee;
import com.aimprosoft.kmb.service.DepartmentService;
import com.aimprosoft.kmb.service.EmployeeService;
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Employee employeeNew = new Employee();
        final Employee employee = employeeTemplate.extractEmployeeFromRequest(req, employeeNew);
        final Department department = departmentService.getDepartmentById(Long.parseLong(req.getParameter("departmentId")));
        employee.setDepartment(department);
        final long employeeId = employeeService.createEmployee(employee);
        if (employeeId != -1) {
            req.setAttribute("successMessage", "Employee successfully created!");
        } else {
            req.setAttribute("errorMessage", "Creation employee failed!");
        }
        resp.sendRedirect("manage-employees-page?departmentId=" + department.getId());
    }
}
