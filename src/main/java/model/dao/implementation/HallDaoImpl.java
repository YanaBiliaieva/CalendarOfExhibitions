package model.dao.implementation;

import model.dao.HallDao;
import model.entities.Hall;
import org.apache.log4j.Logger;
import transactions.ConnectionWrapper;
import transactions.TransactionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HallDaoImpl implements HallDao {
    private static Logger log = Logger.getLogger(HallDaoImpl.class);
    private static final String GET_BY_ID = "SELECT * FROM halls WHERE id = ?";
    private static final String CREATE_HALL = "INSERT INTO halls(name, address) VALUES (?,?)";
    private static final String DELETE_HALL = "DELETE FROM halls WHERE id=?";
    private static final String UPDATE_HALL = "UPDATE halls SET name=?,address=? WHERE id=?";
    private static final String GET_ALL_HALLS = "SELECT * FROM halls";

    public HallDaoImpl() {
    }

    @Override
    public void createHall(String name, String address) {
        ConnectionWrapper con = null;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(CREATE_HALL);
            statement.setString(1, name);
            statement.setString(2, address);
            statement.executeQuery();

        } catch (SQLException e) {
            log.error("Cannot create Hall with name " + name, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }
    }

    @Override
    public Hall getById(int id) {
        ConnectionWrapper con = null;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(GET_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Hall hall = new Hall();
                hall.setName(resultSet.getString("name"));
                hall.setAddress(resultSet.getString("address"));
                hall.setId(id);
                return hall;
            }
        } catch (SQLException e) {
            log.error("Cannot get Hall by id " + id, e);
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
    public List<Hall> getAllHalls() {
        ConnectionWrapper con = null;
        ResultSet resultSet = null;
        Map<Integer, Hall> hallById = null;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(GET_ALL_HALLS);
            hallById = new HashMap<>();
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Hall hall = new Hall();
                hall.setId(id);
                hall.setName(resultSet.getString("name"));
                hall.setAddress(resultSet.getString("address"));
                hallById.put(id, hall);
            }
        } catch (SQLException e) {
            log.error("Can't find halls", e);
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
        return new ArrayList<>(hallById.values());
    }

    @Override
    public void update(Hall hall) {
        ConnectionWrapper con = null;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(UPDATE_HALL);
            statement.setString(1, hall.getName());
            statement.setString(2, hall.getAddress());
            statement.setInt(3, hall.getId());
            statement.executeQuery();

        } catch (SQLException e) {
            log.error("Cannot update Hall with id=" + hall.getId(), e);
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
            PreparedStatement statement = con.preparedStatement(DELETE_HALL);
            statement.setInt(1, id);
            statement.executeQuery();
        } catch (SQLException e) {
            log.error("Cannot delete hall with id" + id, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }
    }
}
