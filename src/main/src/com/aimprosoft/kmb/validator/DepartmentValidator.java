package com.aimprosoft.kmb.validator;

import com.aimprosoft.kmb.domain.Department;

public class DepartmentValidator implements Validator<Department> {

    public static final int DEP_NAME_MAX_LENGTH = 50;
    public static final int DEP_COMMENTS_MAX_LENGTH = 1000;

    @Override
    public ValidationResult validate(Department department) {
        final ValidationResult validationResult = new ValidationResult();

        if (!validateDepartmentName(department)) {
            validationResult.addErrorMessage("departmentName", "Department name is not valid");
        }
        if (!validateComments(department)) {
            validationResult.addErrorMessage("comments", "Comments is not valid");
        }
        return validationResult;
    }

    private boolean validateDepartmentName(Department department) {
        final String departmentName = department.getDepartmentName();
        return departmentName != null && !departmentName.isEmpty() && departmentName.length() <= DEP_NAME_MAX_LENGTH;
    }

    private boolean validateComments(Department department) {
        return department.getComments().length() <= DEP_COMMENTS_MAX_LENGTH;
    }
}
