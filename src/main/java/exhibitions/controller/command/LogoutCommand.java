package exhibitions.controller.command;

import exhibitions.controller.ConfigurationManager;
import exhibitions.model.entities.Exposition;
import org.apache.log4j.Logger;
import exhibitions.model.services.ExhibitionsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LogoutCommand implements Command {
    private Logger logger = Logger.getLogger(LogoutCommand.class);
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response)  {
        logger.info("In Logout Command execute");
        request.getSession().invalidate();

        try {
            List<Exposition> exhibitions = ExhibitionsService.getExhibitions();
            request.setAttribute("exhibitions", exhibitions);
            request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.index")).forward(request, response);
        } catch (ServletException | IOException e) {
            logger.error("Error while logging out"+e);
        }return null;
    }
}
