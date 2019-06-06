package com.aimprosoft.kmb.web.servlets.action.department;

import com.aimprosoft.kmb.conroller.Controller;
import com.aimprosoft.kmb.exceptions.ServiceException;
import com.aimprosoft.kmb.service.DepartmentService;
import com.aimprosoft.kmb.service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteDepartmentAction implements Controller {

    private DepartmentService departmentService = new DepartmentService();
    private EmployeeService employeeService = new EmployeeService();

    @Override
    public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServiceException, ServletException, IOException {

        final long departmentId = Long.parseLong(req.getParameter("departmentId"));
        employeeService.deleteAllFromDepartment(departmentId);
        departmentService.deleteById(departmentId);

    /*    final ModelAndView modelAndView = new ModelAndView("/");
        modelAndView.addModelData("successMessage", "Department with id: " + departmentId + " deleted!");
        return modelAndView;
    */
    }
}

