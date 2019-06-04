package com.aimprosoft.kmb.web.servlets.page.employee;

import com.aimprosoft.kmb.conroller.Controller;
import com.aimprosoft.kmb.conroller.ModelAndView;
import com.aimprosoft.kmb.exceptions.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CreateEmployeePageServlet implements Controller {

    @Override
    public ModelAndView processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, ServletException, IOException {
        final long departmentId = Long.parseLong(req.getParameter("departmentId"));

        final ModelAndView modelAndView = new ModelAndView("/create-employee-page.jsp");
        modelAndView.addModelData("departmentId", departmentId);
        return modelAndView;

        /*  req.getRequestDispatcher("create-employee-page.jsp").forward(req, resp);*/
    }
}
