package com.aimprosoft.kmb.web.servlets.action.department;

import com.aimprosoft.kmb.service.DepartmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-department-action")
public class DeleteDepartmentActionServlet extends HttpServlet {

    private DepartmentService departmentService = new DepartmentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final long departmentId = Long.parseLong(req.getParameter("departmentId"));
        departmentService.deleteDepartmentById(departmentId);
        req.setAttribute("successMessage", "Department with id: " + departmentId + " deleted!");
        resp.sendRedirect("manage-departments-page");
    }
}
