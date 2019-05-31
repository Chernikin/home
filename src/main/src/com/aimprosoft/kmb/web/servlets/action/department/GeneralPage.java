package com.aimprosoft.kmb.web.servlets.action.department;

import com.aimprosoft.kmb.conroller.Controller;
import com.aimprosoft.kmb.conroller.ModelAndView;
import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.exceptions.ServiceException;
import com.aimprosoft.kmb.validator.ValidationResult;
import com.aimprosoft.kmb.web.servlets.action.Action;
import com.aimprosoft.kmb.web.servlets.page.department.ManageDepartmentsPageServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/*")
public class GeneralPage extends HttpServlet {


    private Map<String, Controller> uriMappings = new HashMap<>();


    @Override
    public void init() throws ServletException {
        uriMappings.put("/manage-departments-page.jsp", new ManageDepartmentsPageServlet());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service(req, resp);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        /*uriMappings.put("manage-departments-page", new ManageDepartmentsPageServlet());
        uriMappings.put("Create department", new CreateDepartmentActionServlet());
        */

        final Controller controller = uriMappings.get(req.getRequestURI());

        ModelAndView modelAndView = null;
        try {
            modelAndView = controller.processRequest(req, resp);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        assert modelAndView != null;
        String pageName = modelAndView.getViewName();
        final Map<String, Object> modelData = modelAndView.getModelData();

        prepareRenderData(modelData, req);
        req.getRequestDispatcher(pageName).forward(req, resp);

    }

    private void prepareRenderData(Map<String, Object> modelData, HttpServletRequest req) {
        modelData.forEach((name, value) -> req.setAttribute(name, value));

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
