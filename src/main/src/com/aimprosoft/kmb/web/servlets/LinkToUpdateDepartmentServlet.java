package com.aimprosoft.kmb.web.servlets;

import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.exceptions.ServiceException;
import com.aimprosoft.kmb.service.DepartmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/link-to-update-department")
public class LinkToUpdateDepartmentServlet extends HttpServlet {

    private DepartmentService departmentService = new DepartmentService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final long departmentId = Long.parseLong(req.getParameter("departmentId"));
        final Department departmentById;
        try {
            departmentById = departmentService.getDepartmentById(departmentId);
            req.setAttribute("department", departmentById);
        } catch (ServiceException e) {
            throw new ServletException(e.getMessage());
        }
        req.getRequestDispatcher("update-department-page.jsp").forward(req, resp);
    }
}
