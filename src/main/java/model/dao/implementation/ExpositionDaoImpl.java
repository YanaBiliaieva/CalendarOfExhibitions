package model.dao.implementation;

import model.dao.ExpositionDao;
import model.entities.Exposition;
import org.apache.log4j.Logger;
import transactions.ConnectionWrapper;
import transactions.TransactionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpositionDaoImpl implements ExpositionDao {
    private static Logger log = Logger.getLogger(ExpositionDaoImpl.class);
    private static final String GET_BY_ID = "SELECT * FROM expositions WHERE id_ex = ?";
    private static final String CREATE_EXPOSITION = "INSERT INTO expositions(theme, date_start, date_end, description, fk_id_ha, price) VALUES (?,?,?,?,?,?)";
    private static final String DELETE_EXPOSITION = "DELETE FROM expositions WHERE id_ex=?";
    private static final String UPDATE_EXPOSITION = "UPDATE expositions SET theme=?,date_start=? date_end=?, description=?, fk_id_ha=?, price=? WHERE id_ex=?";
    private static final String GET_ALL_EXPOSITION = "SELECT * FROM expositions";

    @Override
    public void createExposition(String theme, Date start, Date end, String description, int fk_id_ha, int price) {
        ConnectionWrapper con = null;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(CREATE_EXPOSITION);
            statement.setString(1, theme);
            statement.setDate(2, start);
            statement.setDate(3, end);
            statement.setString(4, description);
            statement.setInt(5, fk_id_ha);
            statement.setInt(6, price);
            statement.executeQuery();
        } catch (SQLException e) {
            log.error("Cannot create Exposition with theme " + theme, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }
    }

    @Override
    public Exposition getById(int id) {
        ConnectionWrapper con = null;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(GET_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Exposition exposition = new Exposition();
                exposition.setTheme(resultSet.getString("theme"));
                exposition.setDateStart(resultSet.getDate("date_start"));
                exposition.setDateEnd(resultSet.getDate("date_end"));
                exposition.setDescription(resultSet.getString("description"));
                exposition.setHallId(resultSet.getInt("fk_id_ha"));
                exposition.setPrice(resultSet.getInt("price"));
                exposition.setId(id);
                return exposition;
            }
        } catch (SQLException e) {
            log.error("Cannot get exposition by id " + id, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }
        return null;
    }

    @Override
    public List<Exposition> getAllExpositions() {
        ConnectionWrapper con = null;
        ResultSet resultSet = null;
        Map<Integer, Exposition> expositionMap = null;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(GET_ALL_EXPOSITION);
            expositionMap = new HashMap<>();
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_ex");
                Exposition exposition = new Exposition();
                exposition.setId(id);
                exposition.setTheme(resultSet.getString("theme"));
                exposition.setDateStart(resultSet.getDate("date_start"));
                exposition.setDateEnd(resultSet.getDate("date_end"));
                exposition.setDescription(resultSet.getString("description"));
                exposition.setHallId(resultSet.getInt("fk_id_ha"));
                exposition.setPrice(resultSet.getInt("price"));
                expositionMap.put(id, exposition);
            }
        } catch (SQLException e) {
            log.error("Can't find expositions", e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                log.error("Can't close result set", e);
            }
            try {
                con.close();
            } catch (SQLException e) {
                log.error("Can't close connection", e);
            }
        }
        return new ArrayList<>(expositionMap.values());
    }

    @Override
    public void update(Exposition exposition) {
        ConnectionWrapper con = null;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(UPDATE_EXPOSITION);
            statement.setString(1, exposition.getTheme());
            statement.setDate(2, exposition.getDateStart());
            statement.setDate(3, exposition.getDateEnd());
            statement.setString(4, exposition.getDescription());
            statement.setInt(5, exposition.getHallId());
            statement.setInt(6, exposition.getPrice());
            statement.setInt(7, exposition.getId());
            statement.executeQuery();

        } catch (SQLException e) {
            log.error("Cannot update Hall with id=" + exposition.getId(), e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }
    }

    @Override
    public void delete(int id) {
        ConnectionWrapper con = null;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(DELETE_EXPOSITION);
            statement.setInt(1, id);
            statement.executeQuery();
        } catch (SQLException e) {
            log.error("Cannot delete exposition with id" + id, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }
    }
}
