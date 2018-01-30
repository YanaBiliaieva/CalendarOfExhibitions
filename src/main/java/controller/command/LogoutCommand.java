package controller.command;

import controller.ConfigurationManager;
import model.entities.Exposition;
import org.apache.log4j.Logger;
import services.ExhibitionsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LogoutCommand implements Command {
    private Logger logger = Logger.getLogger(LogoutCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)  {
        logger.info("In Logout Command execute");
        request.getSession().invalidate();

        try {
            List<Exposition> exhibitions = ExhibitionsService.getExhibitions();
            request.setAttribute("exhibitions", exhibitions);
            request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.index")).forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error("Error while logging out"+e);
        }
    }
}
