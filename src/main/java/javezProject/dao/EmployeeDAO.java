package javezProject.dao;

import javezProject.model.Employee;
import java.util.List;

public interface EmployeeDAO {
    List<Employee> allEmployees();
    List<Employee> someEmployees(String departmentId);
    void add(Employee employees);
    void delete(Employee employees);
    void edit(Employee employees);
    void saveOrUpdate(Employee newEmployee, int oldEmployeeId);
    Employee getById(int id);
    String getDepartmentName(int depId);

    int employeeCount();

//    boolean checkTitle(String title);
}
