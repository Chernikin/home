package com.aimprosoft.kmb.database.daoFactory;

import com.aimprosoft.kmb.database.EmployeeDao;
import com.aimprosoft.kmb.database.jdbc.EmployeeDaoJdbc;
import com.aimprosoft.kmb.database.jpa.EmployeeDaoJpa;

public class EmployeeDaoFactory {


    public  enum DaoType{
        jdbc,jpa
    }
    public static EmployeeDao getDao(DaoType daoType) {
        switch (daoType){
            case jdbc:
               return new EmployeeDaoJdbc();
            case jpa:
                return new EmployeeDaoJpa();
        }return null;
    }
}
