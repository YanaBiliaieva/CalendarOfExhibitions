package model.dao.implementation;

import model.dao.UserDao;
import model.entities.User;
import org.apache.log4j.Logger;
import transactions.ConnectionWrapper;
import transactions.TransactionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {
    private static Logger log = Logger.getLogger(UserDaoImpl.class);
    private static final String GET_BY_LOGIN = "SELECT first_name, login, password, ro.name FROM users us LEFT JOIN role ro On us.fk_ro=ro.id_ro WHERE login = ?";
    private static final String CREATE_USER = "INSERT INTO users(first_name, last_name,login , password, phone, balance,email) VALUES (?,?,?,?,?,?,?)";
    private static final String CREATE_ADMIN = "INSERT INTO users(first_name, last_name, login, password, phone, balance,email,role) VALUES (?,?,?,?,?,?,?,?)";
    private static final String DELETE_USER = "DELETE FROM users WHERE id=?";
    private static final String UPDATE_USER = "UPDATE users SET first_name=?,last_name=?,login=?,email=?,password=?, phone=?, balance=?,email=?,role=? WHERE id=?";
    private static final String GET_ALL_USERS = "SELECT first_name, last_name, login, password, phone, balance,email,ro.name" +
            " FROM users us LEFT JOIN role ro On us.fk_ro=ro.id_ro WHERE role =1";

    public UserDaoImpl() {
    }
    @Override
    public User getByLogin(String login) {
        ConnectionWrapper con = null;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(GET_BY_LOGIN);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setFirstname(resultSet.getString("first_name"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(resultSet.getString("ro.name"));
                return user;
            }
        } catch (SQLException e) {
            log.error("Cannot get User by login"+login, e);

        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }
        return null;
    }
    @Override
    public List<User> getAllUsers() {
        ConnectionWrapper con = null;
        ResultSet resultSet = null;
        Map<Integer, User> userById = null;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(GET_ALL_USERS);
            userById = new HashMap<>();
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                User user = new User();
                user.setUserId(id);
                user.setFirstname(resultSet.getString("first_name"));
                user.setLastname(resultSet.getString("last_name"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setPhone(resultSet.getInt("phone"));
                user.setBalance(resultSet.getInt("balance"));
                user.setRole(resultSet.getString("ro.name"));
                user.setEmail(resultSet.getString("email"));
                userById.put(id, user);
            }
        } catch (SQLException e) {
            log.error("Can't find all users", e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                log.error("Can't close result set", e);
            }
            try {
                con.close();
            } catch (SQLException e) {
                log.error("Can't close connection", e);
            }
        }
        return new ArrayList<>(userById.values());
    }

    @Override
    public void createUser(String firstname, String lastname, String login, String password, int phone, int balance, String email) {
        ConnectionWrapper con = null;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(CREATE_USER);
            statement.setString(1, firstname);
            statement.setString(2, lastname);
            statement.setString(3, login);
            statement.setString(4, password);
            statement.setInt(5, phone);
            statement.setInt(6, balance);
            statement.setString(7, email);
            statement.executeQuery();

        } catch (SQLException e) {
            log.error("Cannot create User", e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }

    }
    @Override
    public void createAdmin(String firstname, String lastname, String login, String password, int phone, int balance, String email ) {
        ConnectionWrapper con = null;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(CREATE_ADMIN);
            statement.setString(1, firstname);
            statement.setString(2, lastname);
            statement.setString(3, login);
            statement.setString(4, password);
            statement.setInt(5, phone);
            statement.setInt(6, balance);
            statement.setString(7, email);
            statement.setInt(8, 2);
            statement.executeQuery();

        } catch (SQLException e) {
            log.error("Cannot create Admin", e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }

    }
    @Override
    public void update(User user) {
        ConnectionWrapper con = null;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(UPDATE_USER);
            statement.setString(1, user.getFirstname());
            statement.setString(2, user.getLastname());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setInt(5, user.getPhone());
            statement.setInt(6, user.getBalance());
            statement.setString(7, user.getEmail());
            if(user.getRole().equals("ADMIN"))statement.setInt(8, 2);
            else statement.setInt(8, 1);
            statement.setInt(9, user.getUserId());
            statement.executeQuery();

        } catch (SQLException e) {
            log.error("Cannot update User with id=" + user.getUserId(), e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }
    }
    @Override
    public void delete(int userId) {
        ConnectionWrapper con = null;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(DELETE_USER);
            statement.setInt(1, userId);
            statement.executeQuery();
        } catch (SQLException e) {
            log.error("Cannot delete user with id" + userId, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }

    }
}
