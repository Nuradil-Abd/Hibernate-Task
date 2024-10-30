package dao;

import entities.Employee;

import java.util.List;

public interface EmployeeDao {
    Employee createEmployee(Employee employee);
    Employee getEmployeeById(Long id);
    List<Employee> getAllEmployees();
    Employee updateEmployee(Long id, Employee employee);
    void deleteEmployee(Long id);
    List<Employee> getAllEmployeesSortedBySalary();

}
