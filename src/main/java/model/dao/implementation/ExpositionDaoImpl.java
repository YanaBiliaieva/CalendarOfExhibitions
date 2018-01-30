package model.dao.implementation;

import model.dao.ExpositionDao;
import model.entities.Exposition;
import org.apache.log4j.Logger;
import transactions.ConnectionWrapper;
import transactions.TransactionManager;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExpositionDaoImpl implements ExpositionDao {
    private static Logger logger = Logger.getLogger(ExpositionDaoImpl.class);
    private static final String GET_BY_ID = "SELECT theme, date_start,date_end, description, price, ha.name, ha.address, " +
            "ci.name, tickets FROM expositions ex LEFT JOIN halls ha ON ex.fk_id_ha=ha.id_ha LEFT JOIN cities ci " +
            "ON ha.fk_id_ci = ci.id_ci WHERE id_ex = ?";
    private static final String CREATE_EXPOSITION = "INSERT INTO expositions(theme, date_start, date_end, description, " +
            "fk_id_ha, price, tickets ) VALUES (?,?,?,?,?,?,?)";
    private static final String DELETE_EXPOSITION = "DELETE FROM expositions WHERE id_ex=?";
    private static final String UPDATE_EXPOSITION = "UPDATE expositions SET theme=?,date_start=? date_end=?, description=?, fk_id_ha=?, price=? WHERE id_ex=?";
    private static final String GET_ALL_EXPOSITION = "SELECT id_ex, theme, date_start,date_end, description, price, " +
            "ha.name, ha.address, ci.name, tickets FROM expositions ex LEFT JOIN halls ha ON ex.fk_id_ha=ha.id_ha " +
            "LEFT JOIN cities ci ON ha.fk_id_ci = ci.id_ci;";
    private static final String REMOVE_EXPOSITION_TICKETS = "UPDATE expositions SET tickets=? WHERE id_ex=?";
    private static final String GET_PRICE_BY_ID = "SELECT price FROM expositions WHERE id_ex = ?";
    private static final String CREATE_TICKETS = "INSERT INTO tickets (number, fk_id_ex) VALUES (?,LAST_INSERT_ID())";
    private static final String GET_EXHIBITION_ID = "SELECT fk_id_ex FROM tickets WHERE id_ti=LAST_INSERT_ID()";
    private static final String CREATE_TICKETS_WITH_EX_ID = "INSERT INTO tickets (number, fk_id_ex) VALUES (?,?)";
    private static final String GET_AVAILABLE_TICKETS_NUMBER = "SELECT tickets FROM expositions WHERE id_ex = ?";
    public ExpositionDaoImpl() {
    }

    @Override
    public boolean createExpositionWithTickets(Exposition exposition, Integer hallId) {
        ConnectionWrapper con = null;
        boolean created = false;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(CREATE_EXPOSITION);
            statement.setString(1, exposition.getTheme());
            statement.setDate(2, (Date) exposition.getDateStart());
            statement.setDate(3, (Date) exposition.getDateEnd());
            statement.setString(4, exposition.getDescription());
            statement.setInt(5, hallId);
            statement.setInt(6, exposition.getPrice());
            statement.setInt(7, exposition.getTicketsAvailable());
            statement.executeUpdate();
            logger.info(" before statement = con.preparedStatement(CREATE_TICKETS)");
            statement = con.preparedStatement(CREATE_TICKETS);
            logger.info(" after statement = con.preparedStatement(CREATE_TICKETS)");



            String number = TicketsDaoImpl.generateNumber(exposition, 1);

            statement.setString(1, number);
            statement.executeUpdate();
            logger.info("First ticket number created " + number);
            statement = con.preparedStatement(GET_EXHIBITION_ID);
            ResultSet resultSet = statement.executeQuery();
            Integer expositionId = null;
            while (resultSet.next()){
                 expositionId = resultSet.getInt("fk_id_ex");
                logger.info("expositionId " + expositionId);
            }


            statement = con.preparedStatement(CREATE_TICKETS_WITH_EX_ID);
            if(exposition.getTicketsAvailable()>1){
                for (int i = 2; i < exposition.getTicketsAvailable(); i++) {
                    String number2 = TicketsDaoImpl.generateNumber(exposition, i);
                    logger.info("Ticket number created " + number2);
                    statement.setString(1, number2);
                    statement.setInt(2, expositionId);
                    statement.executeUpdate();
                    logger.debug("Ticket for exposition with id " + expositionId + " created");
                    created = true;
                }
            }created = true;
        } catch (SQLException e) {
            logger.error("Cannot create Exposition with theme " + exposition.getTheme(), e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                logger.error("Cannot close connection", e);
            }
        }
        return created;
    }

    public Integer getPriceById(Integer expositionId) {
        ConnectionWrapper con = null;
        Integer price = null;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(GET_PRICE_BY_ID);
            statement.setInt(1, expositionId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                price = resultSet.getInt("price");
                logger.info("Price of exposition with id " + expositionId + " price" + price);
            }

        } catch (SQLException e) {
            logger.error("Cannot get price from exposition with id " + expositionId, e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                logger.error("Cannot close connection", e);
            }
        }
        return price;
    }

    public boolean removeTickets(Integer expositionId, int boughtTickets) {
        ConnectionWrapper con = null;
        boolean removed = false;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(REMOVE_EXPOSITION_TICKETS);
            statement.setInt(1, boughtTickets);
            statement.setInt(2, expositionId);
            statement.executeUpdate();
            removed = true;
        } catch (SQLException e) {
            logger.error("Cannot remove tickets from exposition with id " + expositionId, e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                logger.error("Cannot close connection", e);
            }
        }
        return removed;
    }


    @Override
    public Exposition getById(Integer id) {
        ConnectionWrapper con = null;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(GET_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            //theme, date_start,date_end, description, price, ha.name, ha.address, ci.name, tickets
            if (resultSet.next()) {
                Exposition exposition = new Exposition();
                logger.info(resultSet.getString("theme"));
                exposition.setTheme(resultSet.getString("theme"));
                logger.info(resultSet.getDate("date_start"));
                exposition.setDateStart(resultSet.getDate("date_start"));
                exposition.setDateEnd(resultSet.getDate("date_end"));
                exposition.setDescription(resultSet.getString("description"));
                exposition.setPrice(resultSet.getInt("price"));
                exposition.setHallName(resultSet.getString("ha.name"));
                exposition.setHallAddress(resultSet.getString("ha.address"));
                exposition.setHallCity(resultSet.getString("ci.name"));
                exposition.setTicketsAvailable(resultSet.getInt("tickets"));
                exposition.setId(id);
                return exposition;
            }
        } catch (SQLException e) {
            logger.error("Cannot get exposition by id " + id, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                logger.error("Cannot close connection", e);
            }
        }
        return null;
    }

    @Override
    public List<Exposition> getAllExpositions() {
        ConnectionWrapper con = null;
        ResultSet resultSet = null;

        Integer counter = 0;
        List<Exposition> list = new ArrayList<>();
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(GET_ALL_EXPOSITION);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Exposition ex = new Exposition();
                ex.setId(resultSet.getInt("id_ex"));
                ex.setTheme(resultSet.getString("theme"));
                ex.setDateStart((resultSet.getDate("date_start")));
                ex.setDateEnd(resultSet.getDate("date_end"));
                ex.setDescription(resultSet.getString("description"));
                ex.setPrice(resultSet.getInt("price"));
                ex.setHallName(resultSet.getString("ha.name"));
                ex.setHallAddress(resultSet.getString("ha.address"));
                ex.setHallCity(resultSet.getString("ci.name"));
                ex.setTicketsAvailable(resultSet.getInt("tickets"));
                list.add(ex);
            }
        } catch (SQLException e) {
            logger.error("Can't find expositions", e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error("Can't close result set", e);
            }
            try {
                con.close();
            } catch (SQLException e) {
                logger.error("Can't close connection", e);
            }
        }
        return list;
    }

    @Override
    public void update(Exposition exposition) {
        ConnectionWrapper con = null;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(UPDATE_EXPOSITION);
            statement.setString(1, exposition.getTheme());
            statement.setDate(2, (Date) exposition.getDateStart());
            statement.setDate(3, (Date) exposition.getDateEnd());
            statement.setString(4, exposition.getDescription());
            //TODO rest columns
            //statement.setInt(5, exposition.getHallId());
            statement.setInt(6, exposition.getPrice());
            statement.setInt(7, exposition.getId());
            statement.executeQuery();

        } catch (SQLException e) {
            logger.error("Cannot update Hall with id=" + exposition.getId(), e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                logger.error("Cannot close connection", e);
            }
        }
    }

    @Override
    public void delete(Integer id) {
        ConnectionWrapper con = null;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(DELETE_EXPOSITION);
            statement.setInt(1, id);
            statement.executeQuery();
        } catch (SQLException e) {
            logger.error("Cannot delete exposition with id" + id, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                logger.error("Cannot close connection", e);
            }
        }
    }

    @Override
    public Integer getNumberOfTickets(Integer expositionId) {
        ConnectionWrapper con = null;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(GET_AVAILABLE_TICKETS_NUMBER);
            statement.setInt(1, expositionId);
            ResultSet resultSet=statement.executeQuery();
            Integer tickets=null;
            while (resultSet.next()){
                tickets=resultSet.getInt("tickets");
                return tickets;
            }
        } catch (SQLException e) {
            logger.error("Cannot get tickets available for exhibition with id " + expositionId, e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                logger.error("Cannot close connection", e);
            }
        }
        return null;
    }


}
