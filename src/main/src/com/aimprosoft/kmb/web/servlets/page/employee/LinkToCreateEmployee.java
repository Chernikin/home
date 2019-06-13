package com.aimprosoft.kmb.web.servlets.page.employee;

import com.aimprosoft.kmb.web.conroller.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LinkToCreateEmployee implements Controller {

    @Override
    public void processRequest(HttpServletRequest req, HttpServletResponse resp) {
        final long departmentId = Long.parseLong(req.getParameter("departmentId"));
        req.setAttribute("departmentId", departmentId);
    }
}
