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

public class ExhibitionsCommand implements Command {
    private Logger logger = Logger.getLogger(ExhibitionsCommand.class);

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("in ExhibitionsCommand execute!!!!");
        List<Exposition> exhibitions = ExhibitionsService.getExhibitions();
        if (request.getMethod().equals("GET")) {
            logger.info("in ExhibitionsCommand execute GET!");

            request.setAttribute("exhibitions", exhibitions);
            request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.index")).forward(request, response);


        }else if (request.getMethod().equals("POST")) {
            logger.info("in ExhibitionsCommand execute POST!");
            exhibitions = ExhibitionsService.getExhibitions();
            request.setAttribute("exhibitions", exhibitions);
            request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.index"))
                    .forward(request, response);
        }
        return null;
    }
}
