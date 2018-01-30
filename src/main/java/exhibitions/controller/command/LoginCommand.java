package exhibitions.controller.command;

import exhibitions.controller.ConfigurationManager;
import exhibitions.exception.DAOException;
import exhibitions.model.entities.Exposition;
import exhibitions.model.entities.User;
import org.apache.log4j.Logger;
import exhibitions.services.ExhibitionsService;
import exhibitions.services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import static exhibitions.controller.command.FactoryCommand.ERROR;

public class LoginCommand implements Command {
    private Logger logger = Logger.getLogger(LoginCommand.class);
    private LoginService loginService = LoginService.getLoginService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("In LoginCommand execute method");
        if (request.getMethod().equals("GET")) {
            logger.info("In LoginCommand execute get");
            try {
                request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.login"))
                        .forward(request, response);
            } catch (ServletException | IOException e) {
                logger.error("Cannot go to login page" + e);
            }
        } else if (request.getMethod().equals("POST")) {
            String login = request.getParameter("login");
            String password = request.getParameter("password");

            logger.debug("String login = " + login);
            logger.debug("String password = " + password);

            if (Objects.isNull(login) || Objects.isNull(password)) {
                request.getSession().setAttribute("nullCredentials", "Cannot login");
                try {
                    response.sendRedirect(ERROR);
                } catch (IOException e) {
                    logger.error("Cannot go to error page" + e);
                }
            } else {
                try {
                    User user = loginService.verify(login, password);
                    if (Objects.isNull(user)) {
                        request.setAttribute("login_incorrect", true);
                        logger.debug("login_incorrect");
                        try {
                            request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.login"))
                                    .forward(request, response);
                        } catch (ServletException | IOException e) {
                            logger.error("Cannot go to login page" + e);
                        }
                    } else {
                        request.getSession().setAttribute("user", user);
                        logger.debug("User with login" + user.getLogin() + " logged in.");
                        try {
                            List<Exposition> exhibitions = ExhibitionsService.getExhibitions();
                            request.setAttribute("exhibitions", exhibitions);
                            request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.index"))
                                    .forward(request, response);
                            return ConfigurationManager.getProperty("path.page.index");
                        } catch (ServletException | IOException e) {
                            logger.error("Cannot go to index page" + e);
                        }
                    }
                } catch (SQLException | DAOException e) {
                    logger.error("Error while verifying login" + e);
                }
            }
        }
        return null;
    }
}
