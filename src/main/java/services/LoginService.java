package services;

import exception.DAOException;
import model.dao.DaoFactory;
import model.dao.implementation.UserDaoImpl;
import model.entities.User;

import java.sql.SQLException;
import java.util.Objects;

public class LoginService {

    private static LoginService loginService= new LoginService();
    public static LoginService getLoginService(){
        return loginService;
    }
    private UserDaoImpl userDaoImpl = DaoFactory.getUserDaoImpl();

    private LoginService(){

    }

    public User verify(String login, String password) throws SQLException, DAOException {
        User user = userDaoImpl.getByLogin(login);
        if (Objects.nonNull(user) && user.getPassword().equals(password)) return user;
        else return null;
    }
}
