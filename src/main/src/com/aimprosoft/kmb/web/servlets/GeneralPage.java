package com.aimprosoft.kmb.web.servlets;

import com.aimprosoft.kmb.exceptions.ApplicationException;
import com.aimprosoft.kmb.exceptions.ValidationException;
import com.aimprosoft.kmb.web.conrollers.department.LinkToUpdateDepartmentController;
import com.aimprosoft.kmb.web.conrollers.employee.LinkToUpdateEmployeeController;
import com.aimprosoft.kmb.web.conrollers.PageMapping;
import com.aimprosoft.kmb.web.conrollers.department.CreateDepartmentController;
import com.aimprosoft.kmb.web.conrollers.department.DeleteDepartmentController;
import com.aimprosoft.kmb.web.conrollers.department.UpdateDepartmentController;
import com.aimprosoft.kmb.web.conrollers.employee.CreateEmployeeController;
import com.aimprosoft.kmb.web.conrollers.employee.DeleteEmployeeController;
import com.aimprosoft.kmb.web.conrollers.employee.UpdateEmployeeController;
import com.aimprosoft.kmb.web.conrollers.department.ManageDepartmentsController;
import com.aimprosoft.kmb.web.conrollers.employee.LinkToCreateEmployeeController;
import com.aimprosoft.kmb.web.conrollers.employee.ManageEmployeesController;
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
        uriMappings.put("/", new PageMapping(new ManageDepartmentsController(), "GET", "/manage-departments-page.jsp", null));
        uriMappings.put("/add-department", new PageMapping(new CreateDepartmentController(), "POST", "/", "/create-department-page.jsp"));
        uriMappings.put("/link-to-update-department", new PageMapping(new LinkToUpdateDepartmentController(), "GET", "/update-department-page.jsp", null));
        uriMappings.put("/update-department", new PageMapping(new UpdateDepartmentController(), "POST", "/", "/update-department-page.jsp"));
        uriMappings.put("/delete-department", new PageMapping(new DeleteDepartmentController(), "POST", "/", null));
        uriMappings.put("/manage-employees", new PageMapping(new ManageEmployeesController(), "GET", "/manage-employees-page.jsp", null));
        uriMappings.put("/link-to-create-employee", new PageMapping(new LinkToCreateEmployeeController(), "GET", "/create-employee-page.jsp", null));
        uriMappings.put("/add-employee", new PageMapping(new CreateEmployeeController(), "POST", "/manage-employees", "/create-employee-page.jsp"));
        uriMappings.put("/link-to-update-employee", new PageMapping(new LinkToUpdateEmployeeController(), "GET", "/update-employee-page.jsp", null));
        uriMappings.put("/update-employee", new PageMapping(new UpdateEmployeeController(), "POST", "/manage-employees", "/update-employee-page.jsp"));
        uriMappings.put("/delete-employee", new PageMapping(new DeleteEmployeeController(), "POST", "/manage-employees", null));
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
            logger.debug("Data for create/update is not valid! Try again.", e);
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
