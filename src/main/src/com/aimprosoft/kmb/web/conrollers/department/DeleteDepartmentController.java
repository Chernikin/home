package com.aimprosoft.kmb.web.conrollers.department;

import com.aimprosoft.kmb.exceptions.ApplicationException;
import com.aimprosoft.kmb.service.DepartmentService;
import com.aimprosoft.kmb.web.conrollers.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteDepartmentController implements Controller {

    private DepartmentService departmentService = new DepartmentService();

    @Override
    public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ApplicationException {
        final long departmentId = Long.parseLong(req.getParameter("departmentId"));
        departmentService.deleteById(departmentId);
    }
}

