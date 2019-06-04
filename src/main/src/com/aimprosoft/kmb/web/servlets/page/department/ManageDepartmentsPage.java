package com.aimprosoft.kmb.web.servlets.page.department;

import com.aimprosoft.kmb.conroller.Controller;
import com.aimprosoft.kmb.conroller.ModelAndView;
import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.exceptions.ServiceException;
import com.aimprosoft.kmb.service.DepartmentService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ManageDepartmentsPage implements Controller {

    private DepartmentService departmentService = new DepartmentService();

    @Override
    public ModelAndView processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, ServletException, IOException {

        final List<Department> allDepartments;
        allDepartments = departmentService.getAllDepartments();
        req.setAttribute("allDepartments", allDepartments);

        final ModelAndView modelAndView = new ModelAndView("/manage-departments-page.jsp");
        modelAndView.addModelData("departments", allDepartments);
        return modelAndView;
    }
}
