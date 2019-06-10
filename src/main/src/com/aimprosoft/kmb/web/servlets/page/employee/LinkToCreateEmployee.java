package com.aimprosoft.kmb.web.servlets.page.employee;

import com.aimprosoft.kmb.conroller.Controller;
import com.aimprosoft.kmb.exceptions.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LinkToCreateEmployee implements Controller {

    @Override
    public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, ServletException, IOException {
        final long departmentId = Long.parseLong(req.getParameter("departmentId"));
        req.setAttribute("departmentId", departmentId);


        /*final ModelAndView modelAndView = new ModelAndView("/create-employee-page.jsp");
        modelAndView.addModelData("departmentId", departmentId);
        return modelAndView;*/

        /*  req.getRequestDispatcher("create-employee-page.jsp").forward(req, resp);*/
    }
}
