package javezProject.service;

import javezProject.dao.UserDAO;
import javezProject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private UserDAO userDAO;

    @Autowired
    public void setFilmDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    @Transactional
    public List<User> allUsers(int page) {
        return userDAO.allUsers(page);
    }

    @Override
    @Transactional
    public void add(User user) {
        userDAO.add(user);
    }

    @Override
    @Transactional
    public void delete(User user) {
        userDAO.delete(user);
    }

    @Override
    @Transactional
    public void edit(User user) {
        userDAO.edit(user);
    }

    @Override
    @Transactional
    public void saveOrUpdate(User newUser, int oldUserId) {
        userDAO.saveOrUpdate(newUser, oldUserId);
    }

    @Override
    @Transactional
    public User getById(int id) {
        return userDAO.getById(id);
    }

    @Override
    @Transactional
    public int userCount() {
        return userDAO.userCount();
    }

    @Override
    @Transactional
    public boolean findUser(String nicknameOrEmail, String password) {

        return userDAO.findUser(nicknameOrEmail, password);
    }
}
