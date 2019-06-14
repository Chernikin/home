package com.aimprosoft.kmb.database;

import com.aimprosoft.kmb.database.jdbc.AbstractDaoJdbc;
import com.aimprosoft.kmb.database.jdbc.DepartmentDaoJdbc;
import com.aimprosoft.kmb.database.jdbc.EmployeeDaoJdbc;

public class DaoJdbcFactory {

    public static AbstractDaoJdbc getDaoJdbc(String criteria) {
        if (criteria.equals("DepartmentDaoJdbc")) {
            return new DepartmentDaoJdbc();
        }
        if (criteria.equals("EmployeeDaoJdbc")) {
            return new EmployeeDaoJdbc();
        }
        return null;
    }
}
