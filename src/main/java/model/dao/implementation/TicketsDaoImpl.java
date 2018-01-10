package model.dao.implementation;

import model.dao.TicketsDao;
import model.entities.Ticket;
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

public class TicketsDaoImpl implements TicketsDao {
    private static Logger log = Logger.getLogger(TicketsDaoImpl.class);
    private static final String GET_BY_ID = "SELECT * FROM tickets WHERE id_ti = ?";
    private static final String CREATE_TICKET = "INSERT INTO tickets(number, fk_id_ex) VALUES (?,?)";
    private static final String DELETE_TICKET = "DELETE FROM tickets WHERE id_ti=?";
    private static final String UPDATE_TICKET = "UPDATE tickets SET number=?,fk_id_ex=? WHERE id_ti=?";
    private static final String GET_ALL_TICKETS = "SELECT * FROM tickets";
    @Override
    public void createTicket(int number, int exhibition) {
        ConnectionWrapper con = null;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(CREATE_TICKET);
            statement.setInt(1, number);
            statement.setInt(2, exhibition);
            statement.executeQuery();
        } catch (SQLException e) {
            log.error("Cannot create Ticket with number " + number, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }
    }

    @Override
    public Ticket getById(int id) {
        ConnectionWrapper con = null;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(GET_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setNumber(resultSet.getInt("number"));
                ticket.setExpositionId(resultSet.getInt("fk_id_ex"));
                ticket.setTicketId(id);
                return ticket;
            }
        } catch (SQLException e) {
            log.error("Cannot get ticket by id " + id, e);
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
    public List<Ticket> getAllTickets() {
        ConnectionWrapper con = null;
        ResultSet resultSet = null;
        Map<Integer, Ticket> ticketMap = null;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(GET_ALL_TICKETS);
            ticketMap = new HashMap<>();
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_ex");
                Ticket ticket = new Ticket();
                ticket.setTicketId(id);
                ticket.setNumber(resultSet.getInt("number"));
                ticket.setExpositionId(resultSet.getInt("fk_id_ex"));
                ticketMap.put(id, ticket);
            }
        } catch (SQLException e) {
            log.error("Can't find tickets", e);
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
        return new ArrayList<>(ticketMap.values());
    }

    @Override
    public void update(Ticket ticket) {
        ConnectionWrapper con = null;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(UPDATE_TICKET);
            statement.setInt(1, ticket.getNumber());
            statement.setInt(2, ticket.getExpositionId());
            statement.setInt(3, ticket.getTicketId());
            statement.executeQuery();

        } catch (SQLException e) {
            log.error("Cannot update ticket with id=" + ticket.getTicketId(), e);
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
            PreparedStatement statement = con.preparedStatement(DELETE_TICKET);
            statement.setInt(1, id);
            statement.executeQuery();
        } catch (SQLException e) {
            log.error("Cannot delete ticket with id" + id, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }
    }
}
