package controller.command;

import controller.ConfigurationManager;
import model.entities.Exposition;
import org.apache.log4j.Logger;
import services.ExhibitionsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExpositionsCommand implements Command {
    private Logger logger = Logger.getLogger(ExpositionsCommand.class);
    private ExhibitionsService exhibitionsService = ExhibitionsService.getExhibitionsService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        logger.info("in ExpositionsCommand execute!!!!");

        if (request.getMethod().equals("GET")) {
            logger.info("in ExpositionsCommand execute GET!");

            try {
                request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.exposition")).forward(request, response);
            } catch (ServletException | IOException e) {
                logger.error(e);
            }

        } else if (request.getMethod().equals("POST")) {
            logger.info("in ExpositionsCommand execute POST!");
            Integer expositionId;
            try {
                expositionId = Integer.valueOf(request.getParameter("expositionId"));
            } finally {
                logger.debug("int expositionId===" + request.getParameter("expositionId"));
            }

            Exposition exposition = exhibitionsService.getExposition(expositionId);
            logger.debug("request.setAttribute exposition==" + exposition.toString());
            request.setAttribute("exposition", exposition);

            try {
                request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.exposition")).forward(request, response);
            } catch (ServletException | IOException e) {
                logger.error(e);
            }
        }

    }
}
