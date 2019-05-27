package com.aimprosoft.kmb.web.servlets.page.department;

import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.exceptions.ServiceException;
import com.aimprosoft.kmb.service.DepartmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/manage-departments-page")
public class ManageDepartmentsPageServlet extends HttpServlet {

    private DepartmentService departmentService = new DepartmentService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final List<Department> allDepartments;
        try {
            allDepartments = departmentService.getAllDepartments();
            req.setAttribute("allDepartments", allDepartments);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("manage-departments-page.jsp").forward(req, resp);
    }
}
