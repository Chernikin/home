package com.aimprosoft.kmb.database.daoFactory;

import com.aimprosoft.kmb.database.DepartmentDao;
import com.aimprosoft.kmb.database.jdbc.DepartmentDaoJdbc;

public class DepartmentDaoJdbcFactory {


    public static DepartmentDao getDaoJdbc() {
        return new DepartmentDaoJdbc();
    }
}

