package com.aimprosoft.kmb.database.jpa;

import com.aimprosoft.kmb.database.EmployeeDao;
import com.aimprosoft.kmb.domain.Employee;

import javax.persistence.EntityManager;
import java.util.List;

public class EmployeeDaoJpa implements EmployeeDao {

    @Override
    public void create(Employee employee) {
        final EntityManager entityManager = JpaEntityManager.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Employee getById(Long id) {
        final EntityManager entityManager = JpaEntityManager.getEntityManager();
        return entityManager.find(Employee.class, id);
    }

    @Override
    public Employee getByEmail(String email) {
        final EntityManager entityManager = JpaEntityManager.getEntityManager();
        return entityManager.find(Employee.class, email);
    }

    @Override
    public List<Employee> getAll() {
        final EntityManager entityManager = JpaEntityManager.getEntityManager();
        return entityManager.createNativeQuery("SELECT * FROM employees", Employee.class).getResultList();
    }

    @Override
    public List<Employee> getAllFromDepartment(Long id) {
        final EntityManager entityManager = JpaEntityManager.getEntityManager();
        return entityManager.createNativeQuery("SELECT * FROM employees JOIN departments ON employees.department_id = departments.id WHERE department_id = ?"
                , Employee.class).setParameter(1, id).getResultList();
    }

    @Override
    public Employee update(Employee employee) {
        final EntityManager entityManager = JpaEntityManager.getEntityManager();
        entityManager.getTransaction().begin();
        final Employee updatedEmployee = entityManager.merge(employee);
        entityManager.getTransaction().commit();
        entityManager.close();
        return updatedEmployee;
    }

    @Override
    public void deleteById(Long id) {
        final EntityManager entityManager = JpaEntityManager.getEntityManager();
        entityManager.getTransaction().begin();
        final Employee employee = entityManager.find(Employee.class, id);
        entityManager.remove(employee);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteAllFromDepartment(Long id) {
        final EntityManager entityManager = JpaEntityManager.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createNativeQuery("DELETE FROM employees WHERE department_id = ?", Employee.class).setParameter(1, id);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public boolean isExists(Employee employee) {
        final EntityManager entityManager = JpaEntityManager.getEntityManager();
        return entityManager.createNativeQuery("SELECT count(id) FROM employees WHERE email = ?", Employee.class)
                .setParameter(1, employee.getEmail()).getResultList() != null;
    }
}
