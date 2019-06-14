package com.aimprosoft.kmb.web.conrollers.employee;

import com.aimprosoft.kmb.web.conrollers.Controller;
import com.aimprosoft.kmb.exceptions.ServiceException;
import com.aimprosoft.kmb.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DeleteEmployeeController implements Controller {

    private EmployeeService employeeService = new EmployeeService();

    @Override
    public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        final long employeeId = Long.parseLong(req.getParameter("employeeId"));
        final long departmentId = Long.parseLong(req.getParameter("departmentId"));
        req.setAttribute("dataForRedirect", "?departmentId=" + departmentId);
        employeeService.deleteById(employeeId);
    }
}
