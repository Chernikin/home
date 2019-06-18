package com.aimprosoft.kmb.web.conrollers.department;

import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.exceptions.ApplicationException;
import com.aimprosoft.kmb.service.DepartmentService;
import com.aimprosoft.kmb.web.conrollers.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateDepartmentController implements Controller {

    private DepartmentService departmentService = new DepartmentService();

    @Override
    public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ApplicationException {
        final long departmentId = Long.parseLong(req.getParameter("departmentId"));
        req.setAttribute("departmentId", departmentId);
        final Department department = getDepartment(req, departmentId);
        departmentService.update(department);
    }

    private Department getDepartment(HttpServletRequest req, Long departmentId) throws ApplicationException {
        final Department departmentById = departmentService.getById(departmentId);
        departmentById.setDepartmentName(req.getParameter("departmentName"));
        departmentById.setComments(req.getParameter("comments"));
        return departmentById;
    }
}
