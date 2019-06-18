package com.aimprosoft.kmb.database.daoFactory;

import com.aimprosoft.kmb.database.EmployeeDao;
import com.aimprosoft.kmb.database.jdbc.EmployeeDaoJdbc;

public class EmployeeDaoJdbcFactory {

    public static EmployeeDao<Long> getDaoJdbc(){
        return new EmployeeDaoJdbc();
    }
}
