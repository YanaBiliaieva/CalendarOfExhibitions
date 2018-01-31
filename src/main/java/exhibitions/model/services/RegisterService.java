package exhibitions.model.services;

import exhibitions.exception.DAOException;
import exhibitions.exception.RegistrationException;
import exhibitions.exception.TransactionException;
import exhibitions.model.dao.DaoFactory;
import exhibitions.model.dao.implementation.UserDaoImpl;
import exhibitions.model.entities.Client;
import exhibitions.model.transactions.TransactionManager;

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

        UserDaoImpl userDaoImpl = DaoFactory.getUserDaoImpl();
        TransactionManager.beginTransaction();
        if(Objects.isNull(userDaoImpl.getByLogin(login))){
            Client client = new Client();
//            client.setName(name);
//            client.setLogin(login);
//            client.setPassword(password);
//            userDaoImpl.createUser(client);
            TransactionManager.endTransaction();
        } else {
            TransactionManager.endTransaction();
            throw new RegistrationException("User already exist");
        }
    }
}
