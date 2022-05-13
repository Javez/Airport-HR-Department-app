package javezProject.dao;

import javezProject.model.Department;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class DepartmentDAOImp implements  DepartmentDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Department> allDepartments() {
        Session session = sessionFactory.getCurrentSession();
        List<Department> departments = session.createQuery("from Department", Department.class).list();
        return departments;
    }

    @Override
    public void add(Department department) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(department);
    }

    @Override
    public void delete(Department department) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(department);
    }

    @Override
    public void edit(Department department) {
        Session session = sessionFactory.getCurrentSession();
        session.update(department);
    }

    @Override
    public void saveOrUpdate(Department newDepartment, int oldDepartmentId) {
        Session session = sessionFactory.getCurrentSession();
        if (oldDepartmentId == 0) { // Наш AtomicInteger начинаеться с 1, следовательно controller создает новый фильм со значением id=0
            session.update(newDepartment);
        } else {
            add(newDepartment);
        }
    }

    @Override
    public Department getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Department.class, id);
    }

    @Override
    public int departmentCount() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count(*) from Department", Number.class).getSingleResult().intValue();
    }
}
