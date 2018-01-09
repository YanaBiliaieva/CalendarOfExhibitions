package services;

import model.dao.ClientDao;
import model.dao.DaoFactory;
import model.entities.Client;
import exception.DAOException;
import exception.RegistrationException;
import exception.TransactionException;
import transactions.TransactionManager;

import java.sql.SQLException;
import java.util.Objects;

public class RegisterService {

    private static RegisterService loginService= new RegisterService();

    public static RegisterService getLoginService(){
        return loginService;
    }

    private static RegisterService registerService = new RegisterService();

    private RegisterService(){
    }

    public static RegisterService getRegisterService() {
        return registerService;
    }

    public void register(String name, String login, String password) throws RegistrationException, SQLException, DAOException, TransactionException {

        ClientDao clientDao = DaoFactory.getClientDao();
        TransactionManager.beginTransaction();
        if(Objects.isNull(clientDao.getByLogin(login))){
            Client client = new Client();
            client.setName(name);
            client.setLogin(login);
            client.setPassword(password);
            clientDao.insert(client);
            TransactionManager.endTransaction();
        } else {
            TransactionManager.endTransaction();
            throw new RegistrationException("User already exist");
        }
    }
}
