package controller.command;

import org.apache.log4j.Logger;
import exception.DAOException;
import services.LoginService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class LoginCommand implements Command {
    private Logger logger = Logger.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        logger.debug("String login = "+login);
        logger.debug("String password = "+password);
        String page=null;
        if (Objects.isNull(login) || Objects.isNull(password)) {
            request.getSession().setAttribute("nullCredentials", "Cannot login");
            //request.getRequestDispatcher(FactoryCommand.ERROR);
        }else {

            LoginService loginService = LoginService.getLoginService();
            try {
                if (loginService.verify(login, password)) {
                    logger.debug("login and password match with database ");
                    HttpSession session = request.getSession();
                    session.setAttribute("loggedUser", login);
                    page=FactoryCommand.EXHIBITIONS;

                }else {
                    request.setAttribute("errorMessageLogin", "Login incorrect");
                    request.getRequestDispatcher(FactoryCommand.LOGIN).forward(request, response);
                }
            } catch (SQLException | DAOException e) {
                logger.error("Error while verifying login"+e);
            }
        }

        return page;
    }
}
