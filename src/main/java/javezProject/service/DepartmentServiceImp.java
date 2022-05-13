package javezProject.service;

import javezProject.dao.DepartmentDAO;
import javezProject.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class DepartmentServiceImp implements DepartmentService {
    private DepartmentDAO departmentDAO;

    @Autowired
    public void setDepartmentDAO(DepartmentDAO departmentDAO) {
        this.departmentDAO = departmentDAO;
    }

    @Override
    @Transactional
    public List<Department> allDepartments() {
        return departmentDAO.allDepartments();
    }

    @Override
    @Transactional
    public void add(Department department) {
        departmentDAO.add(department);
    }

    @Override
    @Transactional
    public void delete(Department department) {
        departmentDAO.delete(department);
    }

    @Override
    @Transactional
    public void edit(Department department) {
        departmentDAO.edit(department);
    }

    @Override
    @Transactional
    public void saveOrUpdate(Department newDepartment, int oldDepartmentId) {
        departmentDAO.saveOrUpdate(newDepartment, oldDepartmentId);
    }

    @Override
    @Transactional
    public Department getById(int id) {
        return departmentDAO.getById(id);
    }

    @Override
    @Transactional
    public int departmentCount() {
        return departmentDAO.departmentCount();
    }
}
