package com.aimprosoft.kmb.web.servlets.action.department;

import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.exceptions.ServiceException;
import com.aimprosoft.kmb.service.DepartmentService;
import com.aimprosoft.kmb.validator.DepartmentValidator;
import com.aimprosoft.kmb.validator.ValidationResult;
import com.aimprosoft.kmb.validator.Validator;
import com.aimprosoft.kmb.web.servlets.action.Action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateDepartmentAction implements Action {
    private DepartmentService departmentService = new DepartmentService();
    private Validator<Department> validator = new DepartmentValidator();


    @Override
    public void handle(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, ServletException, IOException {
        final Department department = getDepartment(req);
        final ValidationResult validationResult = validator.validate(department);
        /*departmentService.createDepartment(department);*/
        if (validationResult.hasError()) {
            processError(req, resp, department, validationResult);
            return;
        }
        createDepartment(req, resp, department);
    }

    private Department getDepartment(HttpServletRequest req) {
        final Department department = new Department();
        department.setDepartmentName(req.getParameter("departmentName"));
        department.setComments(req.getParameter("comments"));
        return department;
    }

    private void processError(HttpServletRequest req, HttpServletResponse resp, Department department, ValidationResult validationResult) throws ServletException, IOException {
        req.setAttribute("errors", validationResult.getErrorMessage());
        req.setAttribute("incorrectDepartmentData", department);
        req.getRequestDispatcher("create-department-page.jsp").forward(req, resp);
    }

    private void createDepartment(HttpServletRequest req, HttpServletResponse resp, Department department) throws IOException, ServiceException {
        departmentService.createDepartment(department);
        req.setAttribute("successMessage", "Department successfully created!");
        resp.sendRedirect("manage-departments-page");
    }
}
