package com.aimprosoft.kmb.web.servlets.action.department;

import com.aimprosoft.kmb.conroller.Controller;
import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.exceptions.ServiceException;
import com.aimprosoft.kmb.exceptions.ValidationException;
import com.aimprosoft.kmb.service.DepartmentService;
import com.aimprosoft.kmb.validator.DepartmentValidator;
import com.aimprosoft.kmb.validator.ValidationResult;
import com.aimprosoft.kmb.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateDepartmentAction implements Controller {

    private DepartmentService departmentService = new DepartmentService();
    private Validator<Department> validator = new DepartmentValidator();

    @Override
    public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, ServletException, IOException {
        final long departmentId = Long.parseLong(req.getParameter("departmentId"));
        final Department department = getDepartment(req, departmentId);
        final String updatableName = req.getParameter("updatableName");
        final ValidationResult validationResult = validator.validate(department, updatableName);
        if (validationResult.hasError()) {
            req.setAttribute("errors", validationResult.getErrorMessage());
            req.setAttribute("department", department);
            throw new ValidationException("error");
        }
        departmentService.update(department);
    }

    private Department getDepartment(HttpServletRequest req, long departmentId) throws ServiceException {
        final Department departmentById = departmentService.getById(departmentId);
        departmentById.setDepartmentName(req.getParameter("departmentName"));
        departmentById.setComments(req.getParameter("comments"));
        return departmentById;
    }
}
