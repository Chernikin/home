package com.aimprosoft.kmb.web.servlets.action.department;

import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.exceptions.ServiceException;
import com.aimprosoft.kmb.validator.ValidationResult;
import com.aimprosoft.kmb.web.servlets.action.Action;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/*")
public class CREATE extends HttpServlet {

    Map<String, Action> mapping = new HashMap<>();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Action action = mapping.get(req.getRequestURI());

        try {
            action.handle(req, resp);
        } catch (ServiceException e) {
            e.printStackTrace();
        }


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

   /* private void createDepartment(HttpServletRequest req, HttpServletResponse resp, Department department) throws IOException, ServiceException {
        departmentService.createDepartment(department);
        req.setAttribute("successMessage", "Department successfully created!");
        resp.sendRedirect("manage-departments-page");
    }*/
}
