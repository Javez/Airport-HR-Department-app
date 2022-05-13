package javezProject.service;

import javezProject.model.Employee;
import java.util.List;

public interface EmployeeService {
    List<Employee> allEmployees();
    List<Employee> someEmployees(String departmentId);
    void add(Employee employee);
    void delete(Employee employee);
    void edit(Employee employee);
    void saveOrUpdate(Employee newEmployee, int oldEmployeeId);
    Employee getById(int id);
    String getDepartmentName(int depId);

    int employeeCount();
}
