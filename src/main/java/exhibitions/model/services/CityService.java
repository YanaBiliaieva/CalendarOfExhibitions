package exhibitions.model.services;

import exhibitions.model.dao.DaoFactory;
import exhibitions.model.dao.implementation.CityDaoImpl;
import exhibitions.model.entities.City;
import org.apache.log4j.Logger;

import java.util.List;

public class CityService {
    private static CityService cityService = new CityService();
    private Logger logger = Logger.getLogger(CityService.class);

    public static CityService getCityService() {
        return cityService;
    }

    private CityDaoImpl cityDao = DaoFactory.getCityDaoImpl();

    private CityService() {
    }
    public Integer getByName(String name){
        Integer id=cityDao.getByName(name);
        return id;
    }

    public boolean createCity(String cityName) {
        boolean created=cityDao.createCity(cityName);
        return created;
    }

    public List<City> getAllCities() {
        List<City>cities=cityDao.getAllCities();
        if(!cities.isEmpty())return cities;
        else return null;
    }
}
