package services;

import model.dao.DaoFactory;
import model.dao.implementation.HallDaoImpl;
import model.entities.Hall;
import org.apache.log4j.Logger;

public class HallService {
    private static HallService hallService = new HallService();
    private Logger logger = Logger.getLogger(HallService.class);

    public static HallService getHallService() {
        return hallService;
    }

    private HallDaoImpl hallDao = DaoFactory.getHallDaoImpl();

    private HallService() {
    }
    public boolean findHall(Hall hall){
        boolean found=hallDao.getByName(hall.getName());
        return found;
    }
    public boolean createHall(Hall hall, Integer cityId) {
        boolean created=hallDao.createHall(hall,cityId);
        return created;
    }
}
