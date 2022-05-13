package javezProject.dao;

import javezProject.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UserDAOImp implements UserDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<User> allUsers(int page) {
        Session session = sessionFactory.getCurrentSession();
        List<User> users = session.createQuery("select * from User", User.class).setFirstResult(10 * (page - 1)).setMaxResults(10).list();
        return users;
    }

    @Override
    public void add(User users) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(users);
    }

    @Override
    public void delete(User users) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(users);
    }

    @Override
    public void edit(User users) {
        Session session = sessionFactory.getCurrentSession();
        session.update(users);
    }

    @Override
    public void saveOrUpdate(User newUser, int oldUserId) {
        Session session = sessionFactory.getCurrentSession();
        if (oldUserId == 0) { // Наш AtomicInteger начинаеться с 1, следовательно controller создает новый фильм со значением id=0
            session.update(newUser);
        } else {
            add(newUser);
        }
    }

    @Override
    public User getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    @Override
    public int userCount() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select count(*) from User", Number.class).getSingleResult().intValue();
    }

    @Override
    public boolean findUser(String nickname, String password) {
        Session session = sessionFactory.getCurrentSession();
        Query query;

        query = session.createQuery("from User where nickname = :nickname and password = :password");
        query.setParameter("nickname", nickname);
        query.setParameter("password", password);

        if(query.list().isEmpty() != true) {
            return true;
        }
        else {
            return false;
        }

    }

}

