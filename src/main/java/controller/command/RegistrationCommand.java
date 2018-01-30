package controller.command;

import controller.ConfigurationManager;
import exception.RegistrationException;
import org.apache.log4j.Logger;
import services.RegistrationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class RegistrationCommand implements Command {
    private Logger logger = Logger.getLogger(RegistrationCommand.class);
    private RegistrationService regService = RegistrationService.getInstance();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getMethod().equals("GET")) {
            request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.registration"))
                    .forward(request, response);
        } else if (request.getMethod().equals("POST")) {
            logger.info("In Registration Command execute POST");
            String firstname = request.getParameter("firstname");
            logger.debug("request.getParameter(firstname)=" + firstname);

            String lastname = request.getParameter("lastname");
            logger.debug("request.getParameter(lastname)=" + lastname);

            String login = request.getParameter("login");
            logger.debug("request.getParameter(login)=" + login);

            String password = request.getParameter("password");
            logger.debug("request.getParameter(password)=" + password);

            String email = request.getParameter("email");
            logger.debug("request.getParameter(email)=" + email);
            logger.debug("request.getParameter(phone)=" + request.getParameter("phone"));
            String phone = String.valueOf(request.getParameter("phone"));
            logger.debug("request.getParameter(phone)=" + phone);


            if (Objects.isNull(firstname) || Objects.isNull(login) || Objects.isNull(password) || Objects.isNull(email)
                    || Objects.isNull(lastname) || Objects.isNull(phone)) {

                request.setAttribute("errorMessage", "All fields must been not null");
                request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.registration"))
                        .forward(request, response);

            } else {

                boolean alreadyExists = regService.findByLogin(login);
                if (alreadyExists) {
                    try {
                        throw new RegistrationException("User with such login " + login + " already exists ");
                    } catch (RegistrationException e) {
                        String message = "User with such login " + login + " already exists ";
                        logger.debug(message);

                        request.setAttribute("errorMessage", message);
                        request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.registration"))
                                .forward(request, response);
                    }
                } else {
                    regService.registerUser(firstname, lastname, login, password, email, phone);
                    request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.login"))
                            .forward(request, response);
                }

            }

        }
    }
}
