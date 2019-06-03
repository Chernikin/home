package com.aimprosoft.kmb.web.servlets.action.department;

import com.aimprosoft.kmb.conroller.Controller;
import com.aimprosoft.kmb.conroller.ModelAndView;
import com.aimprosoft.kmb.exceptions.ServiceException;
import com.aimprosoft.kmb.exceptions.ValidationException;
import com.aimprosoft.kmb.web.servlets.action.PageMapping;
import com.aimprosoft.kmb.web.servlets.page.department.ManageDepartmentsPage;
import com.aimprosoft.kmb.web.servlets.page.employee.ManageEmployeesPageServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/general-page")
public class GeneralPage extends HttpServlet {


    private Map<String, PageMapping> uriMappings = new HashMap<>();


    @Override
    public void init() throws ServletException {
        uriMappings.put("/", new PageMapping(new ManageDepartmentsPage(), "GET", "/manage-departments-page.jsp", null));
        uriMappings.put("/add", new PageMapping(new CreateDepartmentAction(), "POST", "/create-department-page.jsp", "/"));
//        uriMappings.put("/add",);
//        uriMappings.put("/manage-employees-page.jsp", new ManageEmployeesPageServlet());

    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        final PageMapping mapping = uriMappings.get(req.getRequestURI());

        ModelAndView modelAndView = null;
        try {
            modelAndView = mapping.getController().processRequest(req, resp);
        } catch (ValidationException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        /*assert modelAndView != null;
        final String pageUrl = modelAndView.getViewName();*/
        final Map<String, Object> modelData = modelAndView.getModelData();
        prepareRenderData(modelData, req);
        req.getRequestDispatcher(mapping.getJsp()).forward(req, resp);

    }

    private void prepareRenderData(Map<String, Object> modelData, HttpServletRequest req) {
        modelData.forEach((name, value) -> req.setAttribute(name, value));
    }

}
