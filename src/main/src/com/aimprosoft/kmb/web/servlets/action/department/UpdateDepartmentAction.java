package com.aimprosoft.kmb.web.servlets.action.department;

import com.aimprosoft.kmb.web.conroller.Controller;
import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.exceptions.ServiceException;
import com.aimprosoft.kmb.service.DepartmentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateDepartmentAction implements Controller {

    private DepartmentService departmentService = new DepartmentService();

    @Override
    public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        final long departmentId = Long.parseLong(req.getParameter("departmentId"));
        req.setAttribute("departmentId", departmentId);
        final Department department = getDepartment(req, departmentId);
        departmentService.update(department);
    }

    private Department getDepartment(HttpServletRequest req, Long departmentId) throws ServiceException {
        final Department departmentById = departmentService.getById(departmentId);
        departmentById.setDepartmentName(req.getParameter("departmentName"));
        departmentById.setComments(req.getParameter("comments"));
        return departmentById;
    }
}
