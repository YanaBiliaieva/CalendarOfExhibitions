package controller.command;

import controller.ConfigurationManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminPanelCommand implements Command {
    private Logger logger = Logger.getLogger(AdminPanelCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getMethod().equals("GET")) {
            logger.info("In AdminPanelCommand execute GET");
            request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.admin")).forward(request, response);
        }else if (request.getMethod().equals("POST")){
            logger.info("In AdminPanelCommand execute POST");
            request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.error")).forward(request, response);
        }
    }
}
