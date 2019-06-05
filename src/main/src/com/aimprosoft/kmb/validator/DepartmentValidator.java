package com.aimprosoft.kmb.validator;

import com.aimprosoft.kmb.database.DepartmentDao;
import com.aimprosoft.kmb.database.jdbc.DepartmentDaoJDBC;
import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.exceptions.RepositoryException;

public class DepartmentValidator implements Validator<Department> {

    private static final int DEP_NAME_MAX_LENGTH = 50;
    private static final int DEP_COMMENTS_MAX_LENGTH = 1000;
    private DepartmentDao departmentDao = new DepartmentDaoJDBC();

    @Override
    public ValidationResult validate(Department department) {
        final ValidationResult validationResult = new ValidationResult();

        if (!validateDepartmentName(department)) {
            validationResult.addErrorMessage("departmentName", "Department name is not valid. Department name cannot be empty or more than 50 characters.");
        }
        if (!validateDepartmentNameExist(department)) {
            validationResult.addErrorMessage("departmentName", "Department name is not valid. This name is already used!");
        }
        if (!validateComments(department)) {
            validationResult.addErrorMessage("comments", "Comments is not valid. Comments cannot be more than 1000 characters.");
        }
        return validationResult;
    }

    private boolean validateDepartmentName(Department department) {
        final String departmentName = department.getDepartmentName();
        return departmentName != null && !departmentName.isEmpty() && departmentName.length() <= DEP_NAME_MAX_LENGTH;
    }

    private boolean validateDepartmentNameExist(Department department) {
        try {
            return !departmentDao.isExists(department);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean validateComments(Department department) {
        return department.getComments().length() <= DEP_COMMENTS_MAX_LENGTH;
    }
}
