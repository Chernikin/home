package com.aimprosoft.kmb.web.servlets;

import com.aimprosoft.kmb.conroller.Controller;
import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.exceptions.ServiceException;
import com.aimprosoft.kmb.service.DepartmentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LinkToUpdateDepartment implements Controller {

    private DepartmentService departmentService = new DepartmentService();


    @Override
    public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        final long departmentId = Long.parseLong(req.getParameter("departmentId"));
        final Department departmentById = departmentService.getById(departmentId);
        req.setAttribute("department", departmentById);
        req.setAttribute("departmentId", departmentId);
    }
}
