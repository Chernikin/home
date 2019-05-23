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

@WebServlet("/update-employee-action")
public class UpdateEmployeeActionServlet extends HttpServlet {

    private EmployeeService employeeService = new EmployeeService();
    private DepartmentService departmentService = new DepartmentService();
    private EmployeeTemplate employeeTemplate = new EmployeeTemplate();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final long employeeId = Long.parseLong(req.getParameter("employeeId"));
        final long departmentId = Long.parseLong(req.getParameter("departmentId"));
        final Department departmentById = departmentService.getDepartmentById(departmentId);
        final Employee employeeById = employeeService.getEmployeeById(employeeId);
        final Employee employee = employeeTemplate.extractEmployeeFromRequest(req, employeeById);
        employee.setDepartment(departmentById);
        employeeService.updateEmployee(employee);
        resp.sendRedirect("manage-employees-page?departmentId=" + departmentId);
    }
}
