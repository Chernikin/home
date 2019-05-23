package com.aimprosoft.kmb.web.servlets.action.department;

import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.service.DepartmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update-department-action")
public class UpdateDepartmentActionServlet extends HttpServlet {

    private DepartmentService departmentService = new DepartmentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final long departmentId = Long.parseLong(req.getParameter("departmentId"));
        final Department departmentById = departmentService.getDepartmentById(departmentId);
        departmentById.setDepartmentName(req.getParameter("departmentName"));
        departmentById.setComments(req.getParameter("comments"));
        departmentService.updateDepartment(departmentById);
        req.setAttribute("successMessage", "Department with id: " + departmentId + " updated!");
        resp.sendRedirect("manage-departments-page");
    }
}
