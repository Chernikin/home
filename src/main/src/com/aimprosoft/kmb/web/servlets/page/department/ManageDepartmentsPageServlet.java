package com.aimprosoft.kmb.web.servlets.page.department;

import com.aimprosoft.kmb.conroller.Controller;
import com.aimprosoft.kmb.conroller.ModelAndView;
import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.exceptions.ServiceException;
import com.aimprosoft.kmb.service.DepartmentService;
import com.aimprosoft.kmb.web.servlets.action.Action;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/manage-departments-page")
public class ManageDepartmentsPageServlet implements Controller {

    private DepartmentService departmentService = new DepartmentService();

   /* public void handle(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, ServletException, IOException {
        final List<Department> allDepartments;
        allDepartments = departmentService.getAllDepartments();
        req.setAttribute("allDepartments", allDepartments);
        req.getRequestDispatcher("manage-departments-page.jsp").forward(req, resp);
    }*/

    @Override
    public ModelAndView processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, ServletException, IOException {

        final List<Department> allDepartments;
        allDepartments = departmentService.getAllDepartments();
        req.setAttribute("allDepartments", allDepartments);
        //req.getRequestDispatcher("manage-departments-page.jsp").forward(req, resp);

        final ModelAndView modelAndView = new ModelAndView("/manage-departments-page.jsp");

        modelAndView.addMapping("departments", allDepartments);
        return modelAndView;
    }
}
