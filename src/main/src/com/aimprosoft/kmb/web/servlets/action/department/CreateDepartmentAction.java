package com.aimprosoft.kmb.web.servlets.action.department;

import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.exceptions.ServiceException;
import com.aimprosoft.kmb.service.DepartmentService;
import com.aimprosoft.kmb.validator.ValidationResult;
import com.aimprosoft.kmb.web.servlets.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateDepartmentAction implements Action {
    private DepartmentService departmentService = new DepartmentService();

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        final Department department = getDepartment(req);
        final ValidationResult validationResult = validator.validate(department);
        departmentService.createDepartment();
        if (validationResult.hasError()) {
            processError(req, resp, department, validationResult);
            return;
        }
            createDepartment(req, resp, department);
    }
}
