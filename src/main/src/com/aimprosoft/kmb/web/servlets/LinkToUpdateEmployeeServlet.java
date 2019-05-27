package com.aimprosoft.kmb.web.servlets;

import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.domain.Employee;
import com.aimprosoft.kmb.exceptions.ServiceException;
import com.aimprosoft.kmb.service.DepartmentService;
import com.aimprosoft.kmb.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/link-to-update-employee")
public class LinkToUpdateEmployeeServlet extends HttpServlet {

    private EmployeeService employeeService = new EmployeeService();
    private DepartmentService departmentService = new DepartmentService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final long employeeId = Long.parseLong(req.getParameter("employeeId"));
        final Employee employeeById;
        try {
            employeeById = employeeService.getEmployeeById(employeeId);
            req.setAttribute("employee", employeeById);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        final List<Department> allDepartments;
        try {
            allDepartments = departmentService.getAllDepartments();
            req.setAttribute("allDepartments", allDepartments);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("update-employee-page.jsp").forward(req, resp);
    }
}
