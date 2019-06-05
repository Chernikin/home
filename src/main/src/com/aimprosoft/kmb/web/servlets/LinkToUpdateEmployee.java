package com.aimprosoft.kmb.web.servlets;

import com.aimprosoft.kmb.conroller.Controller;
import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.domain.Employee;
import com.aimprosoft.kmb.exceptions.ServiceException;
import com.aimprosoft.kmb.service.DepartmentService;
import com.aimprosoft.kmb.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LinkToUpdateEmployee implements Controller {

    private EmployeeService employeeService = new EmployeeService();
    private DepartmentService departmentService = new DepartmentService();

    @Override
    public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, ServletException, IOException {
        final long employeeId = Long.parseLong(req.getParameter("employeeId"));
        Employee employeeById = employeeService.getEmployeeById(employeeId);
        final List<Department> allDepartments = departmentService.getAllDepartments();
        req.setAttribute("employee", employeeById);
        req.setAttribute("allDepartments", allDepartments);

    /*    final ModelAndView modelAndView = new ModelAndView("/update-employee-page.jsp");
        modelAndView.addModelData("employee", employeeById);
        modelAndView.addModelData("allDepartments", allDepartments);
        return modelAndView;
    */
    }
}
