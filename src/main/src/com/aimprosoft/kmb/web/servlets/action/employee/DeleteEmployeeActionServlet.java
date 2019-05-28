package com.aimprosoft.kmb.web.servlets.action.employee;

import com.aimprosoft.kmb.exceptions.ServiceException;
import com.aimprosoft.kmb.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/delete-employee-action")
public class DeleteEmployeeActionServlet extends HttpServlet {

    private EmployeeService employeeService = new EmployeeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final long employeeId = Long.parseLong(req.getParameter("employeeId"));
        final long departmentId = Long.parseLong(req.getParameter("departmentId"));
        try {
            employeeService.deleteEmployee(employeeId);
        } catch (ServiceException e) {
            throw new ServletException(e.getMessage());
        }
        req.setAttribute("successMessage", "Employee with id: " + employeeId + " delete!");
        resp.sendRedirect("manage-employees-page?departmentId=" + departmentId);
    }
}
