package javezProject.service;

import javezProject.dao.EmployeeDAO;
import javezProject.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService {
    private EmployeeDAO employeeDAO;

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    @Transactional
    public List<Employee> allEmployees() {
        return employeeDAO.allEmployees();
    }

    @Override
    @Transactional
    public List<Employee> someEmployees(String departmentId) {
        return employeeDAO.someEmployees(departmentId);
    }

    @Override
    @Transactional
    public String getDepartmentName(int depId) {
        return employeeDAO.getDepartmentName(depId);
    }

    @Override
    @Transactional
    public void add(Employee employee) {
        employeeDAO.add(employee);
    }

    @Override
    @Transactional
    public void delete(Employee employee) {
        employeeDAO.delete(employee);
    }

    @Override
    @Transactional
    public void edit(Employee employee) {
        employeeDAO.edit(employee);
    }

    @Override
    @Transactional
    public void saveOrUpdate(Employee newEmployee, int oldEmployeeId) {
        employeeDAO.saveOrUpdate(newEmployee, oldEmployeeId);
    }

    @Override
    @Transactional
    public Employee getById(int id) {
        return employeeDAO.getById(id);
    }

    @Override
    @Transactional
    public int employeeCount() {

        return employeeDAO.employeeCount();
    }
}
