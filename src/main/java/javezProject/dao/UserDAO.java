package javezProject.dao;

import javezProject.model.User;
import java.util.List;

public interface UserDAO {
    List<User> allUsers(int page);
    void add(User users);
    void delete(User users);
    void edit(User users);
    void saveOrUpdate(User newUser, int oldUserId);
    User getById(int id);
    boolean findUser(String nickname, String password);

    int userCount();

}
