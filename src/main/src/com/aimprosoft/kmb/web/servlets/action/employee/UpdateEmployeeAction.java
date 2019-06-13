package com.aimprosoft.kmb.web.servlets.action.employee;

import com.aimprosoft.kmb.web.conroller.Controller;
import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.domain.Employee;
import com.aimprosoft.kmb.exceptions.ServiceException;
import com.aimprosoft.kmb.service.DepartmentService;
import com.aimprosoft.kmb.service.EmployeeService;
import com.aimprosoft.kmb.web.servlets.EmployeeTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UpdateEmployeeAction implements Controller {

    private EmployeeService employeeService = new EmployeeService();
    private DepartmentService departmentService = new DepartmentService();
    private EmployeeTemplate employeeTemplate = new EmployeeTemplate();

    @Override
    public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        final long employeeId = Long.parseLong(req.getParameter("employeeId"));
        final long departmentId = Long.parseLong(req.getParameter("newDepartmentId"));
        final Employee employee = getEmployee(req, employeeId, departmentId);

        final List<Department> allDepartments = departmentService.getAll();
        req.setAttribute("allDepartments", allDepartments);

        req.setAttribute("employeeId", employeeId);
        final int previousDepId = Integer.parseInt(req.getParameter("departmentId"));
        req.setAttribute("departmentId", previousDepId);
        req.setAttribute("dataForRedirect", "?departmentId=" + previousDepId);
        employeeService.update(employee);
    }

    private Employee getEmployee(HttpServletRequest req, long employeeId, long departmentId) throws ServiceException {
        Department departmentById;
        departmentById = departmentService.getById(departmentId);
        final Employee employeeById = employeeService.getById(employeeId);
        final Employee employee = employeeTemplate.extractEmployeeFromRequest(req, employeeById);
        employee.setDepartment(departmentById);
        return employee;
    }
}
