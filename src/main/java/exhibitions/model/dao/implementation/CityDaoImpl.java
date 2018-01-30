package exhibitions.model.dao.implementation;

import exhibitions.model.dao.CityDao;
import exhibitions.model.entities.City;
import exhibitions.transactions.ConnectionWrapper;
import exhibitions.transactions.TransactionManager;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CityDaoImpl implements CityDao {
    private static Logger log = Logger.getLogger(CityDaoImpl.class);
    private static final String GET_BY_NAME = "SELECT id_ci FROM cities c WHERE name=?";
    private static final String CREATE_CITY = "INSERT INTO cities (name) VALUES (?)";
    private static final String CET_ALL_CITIES = "SELECT* FROM cities";

    @Override
    public List<City> getAllCities() {
        ConnectionWrapper con = null;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(CET_ALL_CITIES);
            ResultSet resultSet = statement.executeQuery();
            List<City> cities = new ArrayList<>();
            while (resultSet.next()) {
                City city = new City();
                city.setId(resultSet.getInt("id_ci"));
                city.setName(resultSet.getString("name"));
                cities.add(city);
            }
            return cities;

        } catch (SQLException e) {
            log.error("Cannot query all cities");
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }
        return null;
    }

    @Override
    public boolean createCity(String cityName) {

        ConnectionWrapper con = null;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(CREATE_CITY);
            statement.setString(1, cityName);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            log.error("Cannot create City with name " + cityName, e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }
        return false;
    }


    @Override
    public Integer getByName(String name) {
        ConnectionWrapper con = null;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(GET_BY_NAME);
            log.info("con.preparedStatement GET_BY_NAME for name="+name);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            Integer idCity = null;
            while (resultSet.next()) {
                idCity = resultSet.getInt("id_ci");
            }
            if (Objects.nonNull(idCity)) {
                return idCity;
            }
        } catch (SQLException e) {
            log.error("Cannot get city by name " + name, e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }
        return null;
    }


}
