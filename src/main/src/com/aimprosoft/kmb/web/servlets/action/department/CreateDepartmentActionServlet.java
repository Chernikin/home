package com.aimprosoft.kmb.web.servlets.action.department;

import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.service.DepartmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/create-department-action")
public class CreateDepartmentActionServlet extends HttpServlet {

    private DepartmentService departmentService = new DepartmentService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final Department department = new Department();
        department.setDepartmentName(req.getParameter("departmentName"));
        department.setComments(req.getParameter("comments"));
        final long departmentId = departmentService.createDepartment(department);
        if (departmentId != -1) {
            req.setAttribute("successMessage", "Department successfully created!");
        } else {
            req.setAttribute("errorMessage", "Creation department failed!");
        }
        resp.sendRedirect("manage-departments-page");
    }
}
