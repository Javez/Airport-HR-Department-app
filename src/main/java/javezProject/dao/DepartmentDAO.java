package javezProject.dao;

import javezProject.model.Department;
import java.util.List;

public interface DepartmentDAO {
    List<Department> allDepartments();
    void add(Department departments);
    void delete(Department departments);
    void edit(Department departments);
    void saveOrUpdate(Department newDepartment, int oldDepartmentId);
    Department getById(int id);

    int departmentCount();

//    boolean checkTitle(String title);
}
