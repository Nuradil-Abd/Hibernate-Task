package dao.impl;

import config.HibernateConfig;
import dao.DepartmentDao;
import entities.Department;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao {


    private final EntityManager em = HibernateConfig.getEntityManagerFactory().createEntityManager();


    @Override
    public Department createDepartment(Department department) {
        EntityTransaction transaction = em.getTransaction();
        try{
            transaction.begin();
            em.persist(department);
            transaction.commit();
            return department;
        }catch (Exception e){
            if(transaction.isActive()){
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public Department getDepartmentById(Long id) {
        return em.find(Department.class, id);
    }

    @Override
    public List<Department> getAllDepartments() {
        String query = "select d from Department d";
        TypedQuery<Department> query1 = em.createQuery(query, Department.class);
        return query1.getResultList();
    }

    @Override
    public Department updateDepartment(Long id, Department updatedDepartment) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Department target = em.find(Department.class, id);
            if (target != null) {
                target.setName(updatedDepartment.getName());
                target.setMaxEmployeeNumber(updatedDepartment.getMaxEmployeeNumber());
                em.merge(target);
            }
            transaction.commit();
            return target;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public void deleteDepartment(Long id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Department department = em.find(Department.class, id);
            if (department != null) {
                em.remove(department);
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
    public Department getDepartmentByMaxEmployees() {
        String query = "select d from Department d where size(d.maxEmployeeNumber) = (" +
                       "select max(size(dep.maxEmployeeNumber)) from Department dep)";
        TypedQuery<Department> query1 = em.createQuery(query, Department.class);
        query1.setMaxResults(1);
        List<Department> result = query1.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }
}
