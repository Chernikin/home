package com.aimprosoft.kmb.web.servlets.page.employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/create-employee-page")
public class CreateEmployeePageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final long departmentId = Long.parseLong(req.getParameter("departmentId"));
        req.setAttribute("departmentId", departmentId);
        req.getRequestDispatcher("create-employee-page.jsp").forward(req, resp);
    }
}
