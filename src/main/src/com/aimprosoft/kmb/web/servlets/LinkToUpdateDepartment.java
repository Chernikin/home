package com.aimprosoft.kmb.web.servlets;

import com.aimprosoft.kmb.conroller.Controller;
import com.aimprosoft.kmb.conroller.ModelAndView;
import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.exceptions.ServiceException;
import com.aimprosoft.kmb.service.DepartmentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LinkToUpdateDepartment implements Controller {

    private DepartmentService departmentService = new DepartmentService();


    @Override
    public ModelAndView processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, ServletException, IOException {
        final long departmentId = Long.parseLong(req.getParameter("departmentId"));
        final Department departmentById;
        departmentById = departmentService.getDepartmentById(departmentId);

        final ModelAndView modelAndView = new ModelAndView("/update-department-page.jsp");
        modelAndView.addModelData("department", departmentById);
        modelAndView.addModelData("departmentId", departmentId);
        return modelAndView;
    }
}
