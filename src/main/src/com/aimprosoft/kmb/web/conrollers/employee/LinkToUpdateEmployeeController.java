package com.aimprosoft.kmb.web.conrollers.employee;

import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.domain.Employee;
import com.aimprosoft.kmb.exceptions.ApplicationException;
import com.aimprosoft.kmb.service.DepartmentService;
import com.aimprosoft.kmb.service.EmployeeService;
import com.aimprosoft.kmb.web.conrollers.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class LinkToUpdateEmployeeController implements Controller {

    private EmployeeService employeeService = new EmployeeService();
    private DepartmentService departmentService = new DepartmentService();

    @Override
    public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ApplicationException {
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
