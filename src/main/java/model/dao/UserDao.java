package model.dao;

import model.entities.User;

import java.util.List;

public interface UserDao {
    void createUser(String firstname, String lastname, String login, String password, int phone, int balance, String email);

    void createAdmin(String firstname, String lastname, String login, String password, int phone, int balance, String email, int role);

    User getByLogin(String login);

    List<User> getAllUsers();

    void update(User user);

    void delete(int userId);
}
