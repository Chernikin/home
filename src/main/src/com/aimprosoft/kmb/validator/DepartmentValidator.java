package com.aimprosoft.kmb.validator;

import com.aimprosoft.kmb.database.DepartmentDao;
import com.aimprosoft.kmb.database.jdbc.DepartmentDaoJdbc;
import com.aimprosoft.kmb.domain.Department;
import com.aimprosoft.kmb.exceptions.RepositoryException;
import org.apache.log4j.Logger;

public class DepartmentValidator implements Validator<Department> {

    private static final int DEP_NAME_MAX_LENGTH = 50;
    private static final int DEP_COMMENTS_MAX_LENGTH = 1000;
    private DepartmentDao departmentDao = new DepartmentDaoJdbc();
    private static Logger logger = Logger.getLogger(DepartmentValidator.class);


    @Override
    public ValidationResult validate(Department department, String updatableName) {
        final ValidationResult validationResult = new ValidationResult();
        if (!validateDepartmentName(department)) {
            validationResult.addError("departmentName", "Department name is not valid. Department name cannot be empty or more than 50 characters.");
        }
        if (!validateDepartmentNameExist(department, updatableName)) {
            validationResult.addError("departmentName", "Department name is not valid. This name is already used!");
        }
        if (!validateComments(department)) {
            validationResult.addError("comments", "Comments is not valid. Comments cannot be more than 1000 characters.");
        }
        if (validationResult.hasError()) {
            validationResult.addError("incorrectDepartmentData", department);
        }
        return validationResult;
    }

    private boolean validateDepartmentName(Department department) {
        final String departmentName = department.getDepartmentName();
        return departmentName != null && !departmentName.isEmpty() && departmentName.length() <= DEP_NAME_MAX_LENGTH;
    }

    private boolean validateDepartmentNameExist(Department department, String updatableName) {
        if (updatableName.equals(department.getDepartmentName())) {
            return true;
        }
        if (!updatableName.equals(department.getDepartmentName())) {
            try {
                return !departmentDao.isExists(department);
            } catch (RepositoryException e) {
                logger.debug("Can`t check department name on exist!");
            }
        }
        return false;
    }

    private boolean validateComments(Department department) {
        return department.getComments().length() <= DEP_COMMENTS_MAX_LENGTH;
    }
}
