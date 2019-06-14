package com.aimprosoft.kmb.web.conrollers.employee;

import com.aimprosoft.kmb.web.conrollers.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LinkToCreateEmployeeController implements Controller {

    @Override
    public void processRequest(HttpServletRequest req, HttpServletResponse resp) {
        final long departmentId = Long.parseLong(req.getParameter("departmentId"));
        req.setAttribute("departmentId", departmentId);
    }
}
