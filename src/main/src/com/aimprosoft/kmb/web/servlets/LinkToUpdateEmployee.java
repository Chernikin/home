package com.aimprosoft.kmb.web.servlets;

import com.aimprosoft.kmb.conroller.Controller;
import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.domain.Employee;
import com.aimprosoft.kmb.exceptions.ServiceException;
import com.aimprosoft.kmb.service.DepartmentService;
import com.aimprosoft.kmb.service.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LinkToUpdateEmployee implements Controller {

    private EmployeeService employeeService = new EmployeeService();
    private DepartmentService departmentService = new DepartmentService();

    @Override
    public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        final long employeeId = Long.parseLong(req.getParameter("employeeId"));
        Employee employeeById = employeeService.getById(employeeId);
        final List<Department> allDepartments = departmentService.getAll();
        final Long departmentId = employeeById.getDepartment().getId();
        req.setAttribute("employeeId", employeeId);
        req.setAttribute("departmentId", departmentId);
        req.setAttribute("employee", employeeById);
        req.setAttribute("allDepartments", allDepartments);
    }
}
