package dao.impl;

import config.HibernateConfig;
import dao.EmployeeDao;
import entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    private final EntityManager em = HibernateConfig.getEntityManagerFactory().createEntityManager();
    @Override
    public Employee createEmployee(Employee employee) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(employee);
            transaction.commit();
            return employee;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return em.find(Employee.class, id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        String query = "select e from Employee e";
        TypedQuery<Employee> query1 = em.createQuery(query, Employee.class);
        return query1.getResultList();
    }

    @Override
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Employee target = em.find(Employee.class, id);
            if (target != null) {
                target.setFirstName(updatedEmployee.getFirstName());
                target.setSalary(updatedEmployee.getSalary());
                em.merge(target);
            }
            transaction.commit();
            return target;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException();
        }
    }



    @Override
    public void deleteEmployee(Long id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Employee employee = em.find(Employee.class, id);
            if (employee != null) {
                em.remove(employee);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public List<Employee> getAllEmployeesSortedBySalary() {
        String jpql = "select e from Employee e order by e.salary asc";
        TypedQuery<Employee> query = em.createQuery(jpql, Employee.class);
        return query.getResultList();
    }
}
