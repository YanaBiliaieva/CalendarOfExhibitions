package exhibitions.model.services;

import exhibitions.exception.HallsException;
import exhibitions.model.dao.DaoFactory;
import exhibitions.model.dao.implementation.HallDaoImpl;
import exhibitions.model.dao.implementation.TicketsDaoImpl;
import exhibitions.model.entities.Exposition;
import exhibitions.model.dao.implementation.ExpositionDaoImpl;
import exhibitions.model.entities.Hall;
import org.apache.log4j.Logger;

import java.util.List;

public class ExhibitionsService {
    private static ExhibitionsService exhibitionsService = new ExhibitionsService();
    private static ExpositionDaoImpl expositionDao = DaoFactory.getExpositionDaoImpl();
    private static TicketsDaoImpl ticketsDao = DaoFactory.getTicketsDaoImpl();
    private static HallDaoImpl hallDao = DaoFactory.getHallDaoImpl();
    private Logger logger = Logger.getLogger(ExhibitionsService.class);

    private ExhibitionsService() {
    }

    public static ExhibitionsService getExhibitionsService() {
        expositionDao = new ExpositionDaoImpl();
        return exhibitionsService;
    }

    public static List<Exposition> getExhibitions() {
        List<Exposition> ex = expositionDao.getAllExpositions();
        return ex;
    }

    public static Exposition getExposition(Integer expId) {
        Exposition exposition = expositionDao.getById(expId);
        return exposition;
    }

    public boolean createExposition(Exposition exposition, Integer hallId) {
        logger.info("In ExhibitionsService createExpositionWithTickets method");
        boolean expositionCreated = expositionDao.createExpositionWithTickets(exposition, hallId);
        logger.debug("expositionCreated="+expositionCreated);

        return expositionCreated;
    }

    public List<Hall> getAllHalls() {
        List<Hall> halls = hallDao.getAllHalls();
        if (!halls.isEmpty()) return halls;
        else try {
            throw new HallsException("Cannot get all halls");
        } catch (HallsException e) {
            logger.error(e);
        }
        return null;
    }
}
