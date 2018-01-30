package exhibitions.services;


import exhibitions.model.dao.DaoFactory;
import exhibitions.model.dao.implementation.UserDaoImpl;
import exhibitions.model.entities.User;
import org.apache.log4j.Logger;

public class RegistrationService {
    private Logger logger = Logger.getLogger(RegistrationService.class);
    private static RegistrationService registrationService = new RegistrationService();
    private static UserDaoImpl userDao = DaoFactory.getUserDaoImpl();

    private RegistrationService() {

    }

    public static RegistrationService getInstance() {
        return registrationService;
    }

    public boolean findByLogin(String login) {
        return userDao.findLogin(login);
    }

    public void registerUser(String firstname, String lastname, String login, String password, String email, String phone) {
        User user = new User();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setLogin(login);
        user.setPassword(password);
        user.setEmail(email);
        user.setPhone(phone);
        user.setBalance(0);
        userDao.createUser(user);
        logger.debug("User with login " + login + " is registered");

    }
}
