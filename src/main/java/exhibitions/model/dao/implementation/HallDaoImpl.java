package exhibitions.model.dao.implementation;

import exhibitions.model.dao.HallDao;
import exhibitions.model.entities.Hall;
import org.apache.log4j.Logger;
import exhibitions.transactions.ConnectionWrapper;
import exhibitions.transactions.TransactionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HallDaoImpl implements HallDao {
    private static Logger log = Logger.getLogger(HallDaoImpl.class);
    private static final String GET_BY_ID = "SELECT * FROM halls WHERE id = ?";

    private static final String DELETE_HALL = "DELETE FROM halls WHERE id=?";
    private static final String UPDATE_HALL = "UPDATE halls SET name=?,address=? WHERE id=?";

    private static final String GET_ALL_HALLS = "SELECT id_ha, name FROM halls";
    private static final String CREATE_HALL = "INSERT INTO halls(name, address, fk_id_ci) VALUES (?,?,?)";
    private static final String GET_BY_NAME = "SELECT id_ha FROM halls WHERE name = ?";

    public HallDaoImpl() {
    }

    @Override
    public boolean getByName(String name) {
        ConnectionWrapper con = null;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(GET_BY_NAME);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            Integer idHall = null;
            while (resultSet.next()) {
                idHall = resultSet.getInt("id_ha");
            }
            if (Objects.nonNull(idHall)) {
                return true;
            }
        } catch (SQLException e) {
            log.error("Cannot get Hall by name " + name, e);
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
    public boolean createHall(Hall hall,Integer cityId) {
        ConnectionWrapper con = null;

        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(CREATE_HALL);
            statement.setString(1, hall.getName());
            statement.setString(2, hall.getAddress());
            statement.setInt(3,cityId);
            statement.executeUpdate();
            return true;

        } catch (SQLException e) {
            log.error("Cannot create Hall with name " + hall.getName(), e);
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
        List<Hall> halls = new ArrayList<>();
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(GET_ALL_HALLS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Hall hall = new Hall();
                hall.setId(resultSet.getInt("id_ha"));
                hall.setName(resultSet.getString("name"));
                halls.add(hall);
            }
            if (!halls.isEmpty()) return halls;
        } catch (SQLException e) {
            log.error("Can't find halls", e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                log.error("Can't close connection", e);
            }
        }
        return null;
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
