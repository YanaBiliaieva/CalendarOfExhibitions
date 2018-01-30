package controller.command;

import controller.ConfigurationManager;
import model.entities.City;
import model.entities.Hall;
import org.apache.log4j.Logger;
import services.CityService;
import services.HallService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HallsCommand implements Command {
    private Logger logger = Logger.getLogger(HallsCommand.class);
    private HallService hallService = HallService.getHallService();
    private CityService cityService = CityService.getCityService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("In HallsCommand execute method");
        List<City> cities = cityService.getAllCities();
        if (request.getMethod().equals("GET")) {
            logger.info("In HallsCommand execute get");

            request.setAttribute("cities", cities);
            request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.halls"))
                    .forward(request, response);

        } else if (request.getMethod().equals("POST")) {
            logger.info("In HallsCommand execute post");
            Hall hall = new Hall();
            hall.setName(request.getParameter("name"));
            hall.setCity(request.getParameter("cityname"));
            hall.setAddress(request.getParameter("address"));
            logger.info("hall name="+hall.getName());
            logger.info("hall city="+hall.getCity());
            logger.info("hall address="+hall.getAddress());
            boolean alreadyExists = hallService.findHall(hall);
            if (!alreadyExists) {
                logger.info("hall does not exist. creating new hall");
                Integer cityId=cityService.getByName(hall.getCity());
                logger.info("cityId="+cityId);
                boolean created = hallService.createHall(hall,cityId);
                if (created) {
                    request.setAttribute("cities", cities);
                    request.setAttribute("info", "Hall created!");
                    request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.admin"))
                            .forward(request, response);
                } else {
                    request.setAttribute("cities", cities);
                    request.setAttribute("error", "Hall is not created!");
                    request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.halls"))
                            .forward(request, response);
                }
            } else {
                request.setAttribute("cities", cities);
                request.setAttribute("error", "Hall with such name already exists!");
                request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.halls"))
                        .forward(request, response);
            }

        }

    }
}
