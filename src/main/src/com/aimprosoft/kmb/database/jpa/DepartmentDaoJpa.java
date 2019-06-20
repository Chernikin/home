package com.aimprosoft.kmb.database.jpa;

import com.aimprosoft.kmb.database.DepartmentDao;
import com.aimprosoft.kmb.domain.Department;

import javax.persistence.EntityManager;
import java.util.List;

public class DepartmentDaoJpa implements DepartmentDao {

    @Override
    public void create(Department department) {
        final EntityManager entityManager = JpaEntityManager.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(department);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Department getById(Long id) {
        final EntityManager entityManager = JpaEntityManager.getEntityManager();
        return entityManager.find(Department.class, id);
    }

    @Override
    public Department getByName(String departmentName) {
        final EntityManager entityManager = JpaEntityManager.getEntityManager();
        return entityManager.find(Department.class, departmentName);
    }

    @Override
    public List<Department> getAll() {
        final EntityManager entityManager = JpaEntityManager.getEntityManager();
        return entityManager.createNativeQuery("SELECT * FROM departments", Department.class).getResultList();
    }

    @Override
    public Department update(Department department) {
        final EntityManager entityManager = JpaEntityManager.getEntityManager();
        entityManager.getTransaction().begin();
        final Department updatedDepartment = entityManager.merge(department);
        entityManager.getTransaction().commit();
        entityManager.close();
        return updatedDepartment;
    }

    @Override
    public void deleteById(Long id) {
        final EntityManager entityManager = JpaEntityManager.getEntityManager();
        entityManager.getTransaction().begin();
        final Department department = entityManager.find(Department.class, id);
        entityManager.remove(department);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public boolean isExists(Department department) {
        final EntityManager entityManager = JpaEntityManager.getEntityManager();
        return entityManager.createNativeQuery("SELECT count(id) FROM departments WHERE department_name = ?", Department.class)
                .setParameter(1, department.getDepartmentName()).getResultList() != null;
    }
}
