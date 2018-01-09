package services;

import model.dao.ClientDao;
import model.dao.DaoFactory;
import model.entities.Client;
import exception.DAOException;

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

        ClientDao clientDao = DaoFactory.getClientDao();
        Client client = clientDao.getByLogin(login);

        return  (Objects.nonNull(client) && client.getPassword().equals(password));
    }
}
