package model.dao;

import model.entities.City;

import java.util.List;

public interface CityDao {
    Integer getByName(String name);

    boolean createCity(String cityName);

    List<City> getAllCities();
}
