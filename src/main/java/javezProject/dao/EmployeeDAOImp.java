package javezProject.dao;

import javezProject.model.Department;
import javezProject.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class EmployeeDAOImp implements EmployeeDAO {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Employee> allEmployees() {

        Session session = sessionFactory.getCurrentSession();
        String hql = "from Employee ";
        List<Employee> employees = session.createQuery(hql, Employee.class).list();
        return employees;
    }

    @Override
    public List<Employee> someEmployees(String departmentId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Employee where department_id = " + departmentId;
        List<Employee> employees = session.createQuery(hql, Employee.class).list();
        return employees;
    }


    @Override
    public String getDepartmentName(int depId) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Department where id = " + depId + "";

        Department department = session.createQuery(hql, Department.class).getSingleResult();
        if (department.getId() == depId) {
            return department.getName();
        }
        else {
            return "Error";
        }
    }

    @Override
    public void add(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
//        String hql = "from Employee where name = " + employee.getDepartmentName() + "";
//        Department department = session.createQuery(hql, Department.class).getSingleResult();
//        employee.setDepartment(department);
        session.persist(employee);
    }

    @Override
    public void delete(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(employee);
    }

    @Override
    public void edit(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.update(employee);
    }

    @Override
    public void saveOrUpdate(Employee newEmployee, int oldEmployeeId) {
        Session session = sessionFactory.getCurrentSession();
        if (oldEmployeeId == 0) { // Наш AtomicInteger начинаеться с 1, следовательно controller создает новый фильм со значением id=0
            session.update(newEmployee);
        } else {
            add(newEmployee);
        }
    }

    @Override
    public Employee getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Employee.class, id);
    }

    @Override
    public int employeeCount() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count(*) from Employee", Number.class).getSingleResult().intValue();
    }
}

