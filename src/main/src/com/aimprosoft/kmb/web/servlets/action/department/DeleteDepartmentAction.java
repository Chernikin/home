package com.aimprosoft.kmb.web.servlets.action.department;

import com.aimprosoft.kmb.conroller.Controller;
import com.aimprosoft.kmb.exceptions.ServiceException;
import com.aimprosoft.kmb.service.DepartmentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteDepartmentAction implements Controller {

    private DepartmentService departmentService = new DepartmentService();

    @Override
    public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        final long departmentId = Long.parseLong(req.getParameter("departmentId"));
        departmentService.deleteById(departmentId);
    }
}

