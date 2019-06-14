package com.aimprosoft.kmb.web.conrollers.department;

import com.aimprosoft.kmb.web.conrollers.Controller;
import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.exceptions.ServiceException;
import com.aimprosoft.kmb.service.DepartmentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ManageDepartmentsController implements Controller {

    private DepartmentService departmentService = new DepartmentService();

    @Override
    public void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {

        final List<Department> allDepartments;
        allDepartments = departmentService.getAll();
        req.setAttribute("allDepartments", allDepartments);


    }
}
