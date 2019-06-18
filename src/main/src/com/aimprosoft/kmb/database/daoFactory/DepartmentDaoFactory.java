package com.aimprosoft.kmb.database.daoFactory;

import com.aimprosoft.kmb.database.DepartmentDao;
import com.aimprosoft.kmb.database.jdbc.DepartmentDaoJdbc;

public class DepartmentDaoFactory {


    public static DepartmentDao getDao() {
        return new DepartmentDaoJdbc();
    }
}

