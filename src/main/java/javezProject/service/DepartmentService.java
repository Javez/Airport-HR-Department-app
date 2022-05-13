package javezProject.service;

import javezProject.model.Department;
import java.util.List;

public interface DepartmentService {
    List<Department> allDepartments();
    void add(Department department);
    void delete(Department department);
    void edit(Department department);
    void saveOrUpdate(Department newDepartment, int oldDepartmentId);
    Department getById(int id);

    int departmentCount();
}
