package com.aimprosoft.kmb.web.servlets.action.department;

import com.aimprosoft.kmb.conroller.ModelAndView;
import com.aimprosoft.kmb.exceptions.ServiceException;
import com.aimprosoft.kmb.exceptions.ValidationException;
import com.aimprosoft.kmb.web.servlets.LinkToUpdateDepartment;
import com.aimprosoft.kmb.web.servlets.LinkToUpdateEmployee;
import com.aimprosoft.kmb.web.servlets.action.PageMapping;
import com.aimprosoft.kmb.web.servlets.action.employee.CreateEmployeeAction;
import com.aimprosoft.kmb.web.servlets.action.employee.DeleteEmployeeAction;
import com.aimprosoft.kmb.web.servlets.action.employee.UpdateEmployeeAction;
import com.aimprosoft.kmb.web.servlets.page.department.ManageDepartmentsPage;
import com.aimprosoft.kmb.web.servlets.page.employee.ManageEmployeesPage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/")
public class GeneralPage extends HttpServlet {


    private Map<String, PageMapping> uriMappings = new HashMap<>();


    @Override
    public void init() {
        uriMappings.put("/", new PageMapping(new ManageDepartmentsPage(), "GET", "/manage-departments-page.jsp", null));
        uriMappings.put("/add-department", new PageMapping(new CreateDepartmentAction(), "POST", "/create-department-page.jsp", "/"));
        uriMappings.put("/link-to-update-department", new PageMapping(new LinkToUpdateDepartment(), "GET", "/update-department-page.jsp", ""));
        uriMappings.put("/update-department", new PageMapping(new UpdateDepartmentAction(), "POST", "", "/"));
        uriMappings.put("/delete-department", new PageMapping(new DeleteDepartmentAction(), "GET", "", "/"));
        uriMappings.put("/manage-employees", new PageMapping(new ManageEmployeesPage(), "GET", "/manage-employees-page.jsp", null));
        uriMappings.put("/add-employee", new PageMapping(new CreateEmployeeAction(), "POST", "", "/manage-employees"));
        uriMappings.put("/link-to-update-employee", new PageMapping(new LinkToUpdateEmployee(), "GET", "/update-employee-page.jsp", ""));
        uriMappings.put("/update-employee", new PageMapping(new UpdateEmployeeAction(), "POST", "", "/manage-employees"));
        uriMappings.put("/delete-employee", new PageMapping(new DeleteEmployeeAction(), "GET", "", "/manage-employees"));

    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        final PageMapping mapping = uriMappings.get(req.getRequestURI());

        ModelAndView modelAndView = null;
        try {
            modelAndView = mapping.getController().processRequest(req, resp);
        } catch (ValidationException e) {
            throw new ServletException(e.getMessage());
        } catch (ServiceException e) {
            throw new ServletException(e.getMessage());
        }
        final String viewName = modelAndView.getViewName();
        final Map<String, Object> modelData = modelAndView.getModelData();
        prepareRenderData(modelData, req);
        modelData.get("departmentId");


//NEED TO CHANGE

        req.getRequestDispatcher(viewName).forward(req, resp);
/*
        if (mapping.getRedirect() != null) {
            resp.sendRedirect(mapping.getRedirect());
        } else {
            req.getRequestDispatcher(mapping.getJsp()).forward(req, resp);
        }*/
    }

    private void prepareRenderData(Map<String, Object> modelData, HttpServletRequest req) {
        modelData.forEach((name, value) -> req.setAttribute(name, value));
    }

}
