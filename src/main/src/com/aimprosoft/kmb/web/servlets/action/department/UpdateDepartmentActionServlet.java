package com.aimprosoft.kmb.web.servlets.action.department;

import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.exceptions.ServiceException;
import com.aimprosoft.kmb.service.DepartmentService;
import com.aimprosoft.kmb.validator.DepartmentValidator;
import com.aimprosoft.kmb.validator.ValidationResult;
import com.aimprosoft.kmb.validator.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update-department-action")
public class UpdateDepartmentActionServlet extends HttpServlet {

    private DepartmentService departmentService = new DepartmentService();
    private Validator<Department> validator = new DepartmentValidator();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final long departmentId = Long.parseLong(req.getParameter("departmentId"));
        final Department department;
        try {
            department = getDepartment(req, departmentId);
            final ValidationResult validationResult = validator.validate(department);
            if (validationResult.hasError()) {
                processError(req, resp, department, validationResult);
                return;
            }
            updateDepartment(req, resp, departmentId, department);
        } catch (ServiceException e) {
            throw new ServletException(e.getMessage());
        }

    }

    private Department getDepartment(HttpServletRequest req, long departmentId) throws ServiceException {
        final Department departmentById = departmentService.getDepartmentById(departmentId);
        departmentById.setDepartmentName(req.getParameter("departmentName"));
        departmentById.setComments(req.getParameter("comments"));
        return departmentById;
    }

    private void processError(HttpServletRequest req, HttpServletResponse resp, Department department, ValidationResult validationResult) throws ServletException, IOException {
        req.setAttribute("errors", validationResult.getErrorMessage());
        req.setAttribute("department", department);
        req.getRequestDispatcher("update-department-page.jsp").forward(req, resp);
    }

    private void updateDepartment(HttpServletRequest req, HttpServletResponse resp, long departmentId, Department department) throws IOException, ServiceException {
        departmentService.updateDepartment(department);
        req.setAttribute("successMessage", "Department with id: " + departmentId + " updated!");
        resp.sendRedirect("manage-departments-page");
    }
}
