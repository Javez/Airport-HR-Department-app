package javezProject.service;

import javezProject.model.User;
import java.util.List;

public interface UserService {

    List<User> allUsers(int page);
    void add(User user);
    void delete(User user);
    void edit(User user);
    void saveOrUpdate(User newUser, int oldUserId);
    User getById(int id);
    int userCount();
    boolean findUser(String nicknameOrEmail, String password);

}
