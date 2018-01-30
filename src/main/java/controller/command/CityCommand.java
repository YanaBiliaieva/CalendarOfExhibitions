package controller.command;

import controller.ConfigurationManager;
import org.apache.log4j.Logger;
import services.CityService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class CityCommand implements Command {
    private Logger logger = Logger.getLogger(CityCommand.class);
    private static CityService cityService = CityService.getCityService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getMethod().equals("POST")) {
            logger.info("In CityCommand execute post");
            String cityName = request.getParameter("city");
            Integer cityId = cityService.getByName(cityName);
            if (Objects.nonNull(cityId)) {
                request.setAttribute("message", "City with such name already exists!");
                request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.halls")).forward(request, response);
            } else {

                boolean created = cityService.createCity(cityName);
                logger.info("Is city created: "+created);
                if (created) {
                    request.setAttribute("message", "City created!");
                    logger.info("Is city created: "+created);

                    request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.halls"))
                            .forward(request,response);
                } else {
                    request.setAttribute("message", "Cannot create city with such name");
                    request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.halls"))
                            .forward(request, response);
                }

            }

        }
    }
}
