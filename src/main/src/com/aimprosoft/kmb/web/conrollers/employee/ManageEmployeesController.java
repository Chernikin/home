package com.aimprosoft.kmb.web.conrollers.employee;

import com.aimprosoft.kmb.domain.Employee;
import com.aimprosoft.kmb.exceptions.ApplicationException;
import com.aimprosoft.kmb.service.EmployeeService;
import com.aimprosoft.kmb.web.conrollers.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class ManageEmployeesController implements Controller {

    private EmployeeService employeeService = new EmployeeService();

    @Override
    public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ApplicationException {

        final long departmentId = Long.parseLong(req.getParameter("departmentId"));
        final List<Employee> allEmployeesFromDepartment = employeeService.getAllEmployeesFromDepartment(departmentId);
        req.setAttribute("allEmployeesFromDepartment", allEmployeesFromDepartment);
        req.setAttribute("departmentId", departmentId);
    }
}
