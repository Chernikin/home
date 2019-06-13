package com.aimprosoft.kmb.web.servlets.action;

import com.aimprosoft.kmb.exceptions.ApplicationException;
import com.aimprosoft.kmb.exceptions.ValidationException;
import com.aimprosoft.kmb.web.servlets.LinkToUpdateDepartment;
import com.aimprosoft.kmb.web.servlets.LinkToUpdateEmployee;
import com.aimprosoft.kmb.web.servlets.action.department.CreateDepartmentAction;
import com.aimprosoft.kmb.web.servlets.action.department.DeleteDepartmentAction;
import com.aimprosoft.kmb.web.servlets.action.department.UpdateDepartmentAction;
import com.aimprosoft.kmb.web.servlets.action.employee.CreateEmployeeAction;
import com.aimprosoft.kmb.web.servlets.action.employee.DeleteEmployeeAction;
import com.aimprosoft.kmb.web.servlets.action.employee.UpdateEmployeeAction;
import com.aimprosoft.kmb.web.servlets.page.department.ManageDepartmentsPage;
import com.aimprosoft.kmb.web.servlets.page.employee.LinkToCreateEmployee;
import com.aimprosoft.kmb.web.servlets.page.employee.ManageEmployeesPage;
import org.apache.log4j.Logger;

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

    private static Logger logger = Logger.getLogger(GeneralPage.class);
    private Map<String, PageMapping> uriMappings = new HashMap<>();

    @Override
    public void init() {
        uriMappings.put("/", new PageMapping(new ManageDepartmentsPage(), "GET", "/manage-departments-page.jsp", null));
        uriMappings.put("/add-department", new PageMapping(new CreateDepartmentAction(), "POST", "/", "/getQueryForCreate-department-page.jsp"));
        uriMappings.put("/link-to-getQueryForUpdate-department", new PageMapping(new LinkToUpdateDepartment(), "GET", "/getQueryForUpdate-department-page.jsp", null));
        uriMappings.put("/getQueryForUpdate-department", new PageMapping(new UpdateDepartmentAction(), "POST", "/", "/getQueryForUpdate-department-page.jsp"));
        uriMappings.put("/delete-department", new PageMapping(new DeleteDepartmentAction(), "POST", "/", null));
        uriMappings.put("/manage-employees", new PageMapping(new ManageEmployeesPage(), "GET", "/manage-employees-page.jsp", null));
        uriMappings.put("/link-to-getQueryForCreate-employee", new PageMapping(new LinkToCreateEmployee(), "GET", "/getQueryForCreate-employee-page.jsp", null));
        uriMappings.put("/add-employee", new PageMapping(new CreateEmployeeAction(), "POST", "/manage-employees", "/getQueryForCreate-employee-page.jsp"));
        uriMappings.put("/link-to-getQueryForUpdate-employee", new PageMapping(new LinkToUpdateEmployee(), "GET", "/getQueryForUpdate-employee-page.jsp", null));
        uriMappings.put("/getQueryForUpdate-employee", new PageMapping(new UpdateEmployeeAction(), "POST", "/manage-employees", "/getQueryForUpdate-employee-page.jsp"));
        uriMappings.put("/delete-employee", new PageMapping(new DeleteEmployeeAction(), "POST", "/manage-employees", null));
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        final PageMapping mapping = uriMappings.get(req.getRequestURI());

        try {
            mapping.getController().processRequest(req, resp);
            if (mapping.getMethod().equals("GET")) {
                req.getRequestDispatcher(mapping.getJsp()).forward(req, resp);
            }
            if (mapping.getMethod().equals("POST")) {
                getLogicForPost(req, resp, mapping);
            }
        } catch (ValidationException e) {
            logger.debug("Data is not valid!");
            req.setAttribute("errors", e.getErrors());
            req.getRequestDispatcher(mapping.getRedirect()).forward(req, resp);
        } catch (ApplicationException e) {
            logger.error("Can`t establish connection with database.");
            req.setAttribute("exception", e);
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }

    private void getLogicForPost(HttpServletRequest req, HttpServletResponse resp, PageMapping mapping) throws IOException {
        final Object dataForRedirect = req.getAttribute("dataForRedirect");
        if (dataForRedirect != null) {
            resp.sendRedirect(mapping.getJsp() + dataForRedirect);
        } else {
            resp.sendRedirect(mapping.getJsp());
        }
    }
}
