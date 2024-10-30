package dao;

import entities.Department;

import java.util.List;

public interface DepartmentDao {
    Department createDepartment(Department department);
    Department getDepartmentById(Long id);
    List<Department> getAllDepartments();
    Department updateDepartment(Long id, Department updatedDepartment);
    void deleteDepartment(Long id);
    Department getDepartmentByMaxEmployees();
}
