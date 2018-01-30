package model.dao.implementation;

import model.dao.UserDao;
import model.entities.User;
import org.apache.log4j.Logger;
import transactions.ConnectionWrapper;
import transactions.TransactionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserDaoImpl implements UserDao {
    private static Logger log = Logger.getLogger(UserDaoImpl.class);
    private static final String GET_BY_LOGIN = "SELECT id_us, first_name, login, password, phone, last_name,balance, email," +
            " ro.name FROM users us LEFT JOIN roles ro On us.fk_role=ro.id_ro WHERE login = ?";
    private static final String CREATE_USER = "INSERT INTO users(first_name, last_name,login , password, phone, balance,email) VALUES (?,?,?,?,?,?,?)";
    private static final String CREATE_ADMIN = "INSERT INTO users(first_name, last_name, login, password, phone, balance,email,role) VALUES (?,?,?,?,?,?,?,?)";
    private static final String DELETE_USER = "DELETE FROM users WHERE id=?";
    private static final String UPDATE_USER = "UPDATE users SET first_name=?,last_name=?,login=?,email=?,password=?, phone=?, balance=?,email=?,role=? WHERE id=?";
    private static final String GET_ALL_USERS = "SELECT first_name, last_name, login, password, phone, balance,email,ro.name" +
            " FROM users us LEFT JOIN role ro On us.fk_ro=ro.id_ro WHERE role =1";
    private static final String UPDATE_USER_BALANCE = "UPDATE users SET balance=? WHERE id_us=?";
    private static final String FIND_LOGIN = "SELECT login FROM users WHERE login=?";
    private static final String GET_USER_BALANCE="SELECT balance FROM users WHERE id_us=?";
    public UserDaoImpl() {
    }

    @Override
    public boolean findLogin(String login) {
        ConnectionWrapper con = null;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(FIND_LOGIN);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String resultLogin = resultSet.getString("login");
                return Objects.equals(resultLogin, login);
            }
        } catch (SQLException e) {
            log.error("Cannot get User by login" + login, e);

        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }
        return false;
    }

    public void changeBalance(Integer userId, Integer ticketsPrice) {
        ConnectionWrapper con = null;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(GET_USER_BALANCE);
            statement.setInt(1,userId);
            ResultSet resultSet=statement.executeQuery();
            Integer newBalance=null;
            while (resultSet.next()){
                newBalance=resultSet.getInt("balance");
            }
            if(newBalance!=null){
                statement = con.preparedStatement(UPDATE_USER_BALANCE);
                statement.setInt(1, ticketsPrice);
                statement.setInt(2, userId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            log.error("Cannot get change Balance for user with id" + userId, e);

        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }

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
                user.setUserId(resultSet.getInt("id_us"));
                user.setFirstname(resultSet.getString("first_name"));
                user.setLastname(resultSet.getString("last_name"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setPhone(resultSet.getString("phone"));
                user.setBalance(resultSet.getInt("balance"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(resultSet.getString("ro.name"));
                return user;
            }
        } catch (SQLException e) {
            log.error("Cannot get User by login" + login, e);

        } finally {
            try {
                if (con != null) {
                    con.close();
                }
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
                user.setPhone(resultSet.getString("phone"));
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
    public void createUser(User user) {
        ConnectionWrapper con = null;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(CREATE_USER);
            statement.setString(1, user.getFirstname());
            statement.setString(2, user.getLastname());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getPhone());
            statement.setInt(6, user.getBalance());
            statement.setString(7, user.getEmail());
            statement.executeUpdate();

        } catch (SQLException e) {
            log.error("Cannot create User", e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }

    }

    @Override
    public void createAdmin(User user) {
        ConnectionWrapper con = null;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(CREATE_ADMIN);
            statement.setString(1, user.getFirstname());
            statement.setString(2, user.getLastname());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getPhone());
            statement.setInt(6, user.getBalance());
            statement.setString(7, user.getEmail());
            statement.setInt(8, 2);
            statement.executeQuery();

        } catch (SQLException e) {
            log.error("Cannot create Admin", e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
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
            statement.setString(5, user.getPhone());
            statement.setInt(6, user.getBalance());
            statement.setString(7, user.getEmail());
            if (user.getRole().equals("ADMIN")) statement.setInt(8, 2);
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
