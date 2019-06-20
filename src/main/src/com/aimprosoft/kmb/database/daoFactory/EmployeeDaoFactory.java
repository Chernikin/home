package com.aimprosoft.kmb.database.daoFactory;

import com.aimprosoft.kmb.database.EmployeeDao;
import com.aimprosoft.kmb.database.jdbc.EmployeeDaoJdbc;

public class EmployeeDaoFactory {


    public static EmployeeDao getDao() {
        return new EmployeeDaoJdbc();
    }
}
