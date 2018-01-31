package exhibitions.controller.command;

import exhibitions.controller.ConfigurationManager;
import exhibitions.model.entities.Exposition;
import exhibitions.model.entities.Hall;
import org.apache.log4j.Logger;
import exhibitions.services.ExhibitionsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CreateExhibitionCommand implements Command {
    private ExhibitionsService exhibitionsService = ExhibitionsService.getExhibitionsService();
    private Logger logger = Logger.getLogger(CreateExhibitionCommand.class);


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getMethod().equals("GET")) {
            logger.info("in Create Exhibition Command execute GET!");
            List<Hall> halls = exhibitionsService.getAllHalls();
            request.setAttribute("halls", halls);
            request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.createexhibition"))
                    .forward(request, response);
        } else if (request.getMethod().equals("POST")) {
            logger.info("in Create Exhibition Command  execute POST!");
            Exposition exposition = new Exposition();
            exposition.setTheme(request.getParameter("theme"));
            logger.info(" theme=" + exposition.getTheme());
            exposition.setDescription(request.getParameter("description"));
            logger.info("description=" + exposition.getDescription());

            exposition.setDateStart(parseDate(request.getParameter("dateStart")));
            logger.info(" dateStart=" + exposition.getDateStart());

            exposition.setDateEnd(parseDate(request.getParameter("dateEnd")));
            logger.info(" dateEnd=" + exposition.getDateEnd());

            exposition.setPrice(Integer.valueOf(request.getParameter("price")));
            logger.info(" price=" + exposition.getPrice());
            exposition.setTicketsAvailable(Integer.valueOf(request.getParameter("ticketsAvailable")));
            logger.info(" ticketsAvailable=" + exposition.getTicketsAvailable());
            Integer hallId = Integer.valueOf(request.getParameter("hallid"));

            logger.info(" Integer hallId=" + hallId);
            boolean created = exhibitionsService.createExposition(exposition, hallId);
            logger.info("Is exhibition created=" + created);
            if (created) {
                request.setAttribute("info", "Exhibition created");
                request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.admin"))
                        .forward(request, response);
            } else {
                request.setAttribute("error", "Cannot create exhibition");
                request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.createexhibition"))
                        .forward(request, response);
            }

        }
        return null;
    }

    private Date parseDate(String date) {
        logger.info("String date to parse=" + date);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String dateNumbers = date.replace("-", "");
        logger.info("dateNumbers=" + dateNumbers);
        Date parsed = null;
        try {
            parsed = format.parse(dateNumbers);
            logger.info("Date parsed ==" + parsed);
            java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());
            logger.info("sqlDate ==" + sqlDate);
            return sqlDate;
        } catch (ParseException e) {
            logger.error("Cannot parse date" + date);
        }
        return null;

    }
}
