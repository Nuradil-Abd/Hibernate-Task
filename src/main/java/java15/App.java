package java15;

import config.HibernateConfig;
import dao.DepartmentDao;
import dao.EmployeeDao;
import dao.impl.DepartmentDaoImpl;
import dao.impl.EmployeeDaoImpl;
import entities.Department;
import entities.Employee;


import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
     HibernateConfig.getEntityManagerFactory();
        DepartmentDao departmentDao = new DepartmentDaoImpl();
        EmployeeDao employeeDao = new EmployeeDaoImpl();

// create dep

        departmentDao.createDepartment(new Department("Sallers"));
//create em
        System.out.println(employeeDao.createEmployee(new Employee("Atai", 30000.00)));
// get by id dep
        System.out.println("Dep: " + departmentDao.getDepartmentById(1L));
//get em by id
        System.out.println(employeeDao.getEmployeeById(1L));

// get all deps
        List<Department> allDepartments = departmentDao.getAllDepartments();
        allDepartments.forEach(System.out::println);
 // get all em
        List<Employee> allEmployees = employeeDao.getAllEmployees();
        allEmployees.forEach(System.out::println);

// dep update
        Department updatedDepartment = new Department();
        updatedDepartment.setName("Marketing dep");
        departmentDao.updateDepartment(1L, updatedDepartment);
// em update
        Employee updatedEmployee = new Employee();
        updatedEmployee.setFirstName("Adilet");
        updatedEmployee.setSalary(60000.00);
        employeeDao.updateEmployee(1L, updatedEmployee);


// delete dep
        departmentDao.deleteDepartment(1L);
        // em delete
        employeeDao.deleteEmployee(1L);

// get dep with max em
        Department maxEmployeesDepartment = departmentDao.getDepartmentByMaxEmployees();
        System.out.println("dep with max em: " + maxEmployeesDepartment);

        //get sort
        List<Employee> employees = employeeDao.getAllEmployees();
        employees.forEach(System.out::println);
    }
}
