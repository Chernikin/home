package com.aimprosoft.kmb.web.servlets.action.employee;

import com.aimprosoft.kmb.conroller.Controller;
import com.aimprosoft.kmb.exceptions.ServiceException;
import com.aimprosoft.kmb.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DeleteEmployeeAction implements Controller {

    private EmployeeService employeeService = new EmployeeService();

    @Override
    public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, ServletException, IOException {
        final long employeeId = Long.parseLong(req.getParameter("employeeId"));
        final long departmentId = Long.parseLong(req.getParameter("departmentId"));
        req.setAttribute("successMessage", "Employee with id: " + employeeId + " delete!");
        req.setAttribute("departmentId", departmentId);
        employeeService.deleteById(employeeId);

      /*  final ModelAndView modelAndView = new ModelAndView("/manage-employees");
        modelAndView.addModelData("successMessage", "Employee with id: " + employeeId + " delete!");
        modelAndView.addModelData("departmentId", departmentId);
        return modelAndView;*/

        /*resp.sendRedirect("manage-employees-page?departmentId=" + departmentId);*/
    }
}
