package services;

import model.dao.implementation.UserDaoImpl;
import model.dao.DaoFactory;
import exception.DAOException;
import model.entities.User;

import java.sql.SQLException;
import java.util.Objects;

public class LoginService {

    private static LoginService loginService= new LoginService();

    public static LoginService getLoginService(){
        return loginService;
    }

    private LoginService(){

    }

    public boolean verify(String login, String password) throws SQLException, DAOException {

        UserDaoImpl userDaoImpl = DaoFactory.getUserDaoImpl();
        User user = userDaoImpl.getByLogin(login);

        return  (Objects.nonNull(user) && user.getPassword().equals(password));
    }
}
