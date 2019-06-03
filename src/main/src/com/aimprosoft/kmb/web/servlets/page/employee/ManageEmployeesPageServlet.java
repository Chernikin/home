package com.aimprosoft.kmb.web.servlets.page.employee;

import com.aimprosoft.kmb.conroller.Controller;
import com.aimprosoft.kmb.conroller.ModelAndView;
import com.aimprosoft.kmb.domain.Employee;
import com.aimprosoft.kmb.exceptions.ServiceException;
import com.aimprosoft.kmb.service.EmployeeService;

import javax.naming.ldap.Control;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class ManageEmployeesPageServlet implements Controller {

    private EmployeeService employeeService = new EmployeeService();

    @Override
    public ModelAndView processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, ServletException, IOException {
        final long departmentId = Long.parseLong(req.getParameter("departmentId"));
        final List<Employee> allEmployeesFromDepartment;
        allEmployeesFromDepartment = employeeService.getAllEmployeesFromDepartment(departmentId);
        req.setAttribute("allEmployeesFromDepartment", allEmployeesFromDepartment);
        req.setAttribute("departmentId", departmentId);
        //  req.getRequestDispatcher("manage-employees-page.jsp").forward(req, resp);
        System.out.println("We HERE");
        final ModelAndView modelAndView = new ModelAndView("/manage-employees-page.jsp");
        modelAndView.addModelData("allEmployeesFromDepartment", allEmployeesFromDepartment);
        modelAndView.addModelData("departmentId", departmentId);
        return modelAndView;
    }
}
