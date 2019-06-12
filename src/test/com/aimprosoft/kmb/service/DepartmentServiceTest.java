package com.aimprosoft.kmb.service;

import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.exceptions.ServiceException;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

public class DepartmentServiceTest {

    @Ignore
    @Test
    public void returnDepartmentIdIfDepartmentSavedInData() throws ServiceException {
        final DepartmentService departmentService = new DepartmentService();
        final Department department = new Department();
        department.setDepartmentName("TestNewItems name");
        department.setComments("TestNewItems comments");
        departmentService.create(department);
    }

    @Ignore
    @Test
    public void returnDepartmentIfDepartmentUpdated() throws ServiceException {
        final DepartmentService departmentService = new DepartmentService();
        final Department departmentById = departmentService.getById(3L);
        departmentById.setDepartmentName("NEW TEST NAME");
        final Department department = departmentService.update(departmentById);
        assertNotNull(department);
    }

    @Ignore
    @Test
    public void returnNullIfDepartmentDeleteFromData() throws ServiceException {
        final DepartmentService departmentService = new DepartmentService();
        departmentService.deleteById(3L);
    }


}
