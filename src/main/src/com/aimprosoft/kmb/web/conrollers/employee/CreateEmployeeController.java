package com.aimprosoft.kmb.web.conrollers.employee;

import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.domain.Employee;
import com.aimprosoft.kmb.exceptions.ApplicationException;
import com.aimprosoft.kmb.exceptions.ServiceException;
import com.aimprosoft.kmb.service.DepartmentService;
import com.aimprosoft.kmb.service.EmployeeService;
import com.aimprosoft.kmb.web.conrollers.Controller;
import com.aimprosoft.kmb.web.conrollers.EmployeeTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CreateEmployeeController implements Controller {

    private EmployeeService employeeService = new EmployeeService();
    private DepartmentService departmentService = new DepartmentService();
    private EmployeeTemplate employeeTemplate = new EmployeeTemplate();

    @Override
    public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ApplicationException {
        final long departmentId = Long.parseLong(req.getParameter("departmentId"));
        Department department = departmentService.getById(departmentId);
        final Employee employee = getEmployee(req);
        employee.setDepartment(department);
        req.setAttribute("departmentId", departmentId);
        req.setAttribute("dataForRedirect", "?departmentId=" + departmentId);
        employeeService.create(employee);

    }

    private Employee getEmployee(HttpServletRequest req) throws ServiceException {
        final Employee employeeNew = new Employee();
        return employeeTemplate.extractEmployeeFromRequest(req, employeeNew);
    }
}
