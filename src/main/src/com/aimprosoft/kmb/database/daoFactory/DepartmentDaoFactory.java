package com.aimprosoft.kmb.database.daoFactory;

import com.aimprosoft.kmb.database.DepartmentDao;
import com.aimprosoft.kmb.database.jdbc.DepartmentDaoJdbc;
import com.aimprosoft.kmb.database.jpa.DepartmentDaoJpa;

public class DepartmentDaoFactory {

    public enum DaoType {
        jdbc, jpa
    }

    public static DepartmentDao getDao(DaoType daoType) {
        switch (daoType) {
            case jdbc:
                return new DepartmentDaoJdbc();
            case jpa:
                return new DepartmentDaoJpa();
        }return null;
    }
}

