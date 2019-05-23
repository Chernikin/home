package com.aimprosoft.kmb.service;

import com.aimprosoft.kmb.domain.Department;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

public class DepartmentServiceTest {

    @Ignore
    @Test
    public void returnDepartmentIdIfDepartmentSavedInData() {
        final DepartmentService departmentService = new DepartmentService();
        final Department department = new Department();
        department.setDepartmentName("Test name");
        department.setComments("Test comments");
        final long departmentId = departmentService.createDepartment(department);
        assertTrue(departmentId > 1);
    }

    @Ignore
    @Test
    public void returnDepartmentIfDepartmentUpdated() {
        final DepartmentService departmentService = new DepartmentService();
        final Department departmentById = departmentService.getDepartmentById(3);
        departmentById.setDepartmentName("NEW TEST NAME");
        final Department department = departmentService.updateDepartment(departmentById);
        assertNotNull(department);
    }

    @Ignore
    @Test
    public void returnNullIfDepartmentDeleteFromData() {
        final DepartmentService departmentService = new DepartmentService();
        departmentService.deleteDepartmentById(3);
    }

}
