package exhibitions.controller.authentification;

import exhibitions.model.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;
import java.util.Objects;

public class Authentication {
    private static Logger logger = Logger.getLogger(Authentication.class);
    private Authentication() {
    }

    public static boolean isUserLogIn(HttpSession session){
        User user = (User) session.getAttribute("user");
        logger.info("Authentication isUserLogIn");
        return Objects.nonNull(user);
    }
    public static boolean isAdmin(HttpSession session) {
        User user = (User) session.getAttribute("user");
        logger.info("Authentication isAdmin");
        return Objects.nonNull(user) && Objects.nonNull(user.getRole()) && user.getRole().equals("ADMIN");
    }
}
