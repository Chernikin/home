package com.aimprosoft.kmb.database.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaEntityManager {

    private static EntityManagerFactory entityManager = Persistence.createEntityManagerFactory("com.aimprosoft.kmb");

    public static EntityManager getEntityManager(){
        return entityManager.createEntityManager();
    }
}
