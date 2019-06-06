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
        final Department department;
        department = getDepartment(req, departmentId);
        final ValidationResult validationResult = validator.validate(department);
        if (validationResult.hasError()) {
            /*processError(req, resp, department, validationResult);*/
            req.setAttribute("errors", validationResult.getErrorMessage());
            req.setAttribute("department", department);
            throw new ValidationException("error");
        }

        departmentService.update(department);
/*
        final ModelAndView modelAndView = new ModelAndView("/");
        return modelAndView;
    */
    }


    private Department getDepartment(HttpServletRequest req, long departmentId) throws ServiceException {
        final Department departmentById = departmentService.getById(departmentId);
        departmentById.setDepartmentName(req.getParameter("departmentName"));
        departmentById.setComments(req.getParameter("comments"));
        return departmentById;
    }

   /* private void processError(HttpServletRequest req, HttpServletResponse resp, Department department, ValidationResult validationResult) throws ServletException, IOException {
        req.setAttribute("errors", validationResult.getErrorMessage());
        req.setAttribute("department", department);
        req.getRequestDispatcher("update-department-page.jsp").forward(req, resp);
    }

    private void update(HttpServletRequest req, HttpServletResponse resp, long departmentId, Department department) throws IOException, ServiceException {
        departmentService.update(department);
        req.setAttribute("successMessage", "Department with id: " + departmentId + " updated!");
        resp.sendRedirect("manage-departments-page");
    }*/
}
