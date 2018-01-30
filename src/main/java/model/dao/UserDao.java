package model.dao;

import model.entities.User;

import java.util.List;

public interface UserDao {
    void createUser(User user);

    void createAdmin(User user);

    User getByLogin(String login);

    List<User> getAllUsers();

    void update(User user);

    void delete(int userId);

    boolean findLogin(String login);
}
