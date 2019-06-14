package com.aimprosoft.kmb.web.conrollers.department;

import com.aimprosoft.kmb.web.conrollers.Controller;
import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.exceptions.ServiceException;
import com.aimprosoft.kmb.service.DepartmentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateDepartmentController implements Controller {
    private DepartmentService departmentService = new DepartmentService();

    @Override
    public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        final Department department = getDepartment(req);
        departmentService.create(department);
    }


    private Department getDepartment(HttpServletRequest req) {
        final Department department = new Department();
        department.setDepartmentName(req.getParameter("departmentName"));
        department.setComments(req.getParameter("comments"));
        return department;
    }
}
