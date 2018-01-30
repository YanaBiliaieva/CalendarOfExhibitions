package exhibitions.model.dao.implementation;

import exhibitions.model.dao.TicketsDao;
import exhibitions.model.entities.Exposition;
import exhibitions.model.entities.Ticket;
import org.apache.log4j.Logger;
import exhibitions.transactions.ConnectionWrapper;
import exhibitions.transactions.TransactionManager;

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
    private static final String GET_SELECTED_TICKETS = "SELECT t.id_ti,t.number, e.theme, e.date_start, e.date_end " +
            "FROM tickets t LEFT JOIN expositions e On t.fk_id_ex = e.id_ex LEFT JOIN payments p ON t.id_ti = p.fk_id_ti " +
            "WHERE p.id_pa IS NULL && fk_id_ex=? LIMIT ?";
    private static final String CREATE_TICKETS = "INSERT INTO tickets (number, fk_id_ex) VALUES (?,LAST_INSERT_ID())";

    @Override
    public boolean createTickets(Exposition exposition) {
        log.info("exposition.hashCode()="+exposition.hashCode());
        ConnectionWrapper con = null;
        boolean created = false;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(CREATE_TICKETS);
            for (int i = 0; i < exposition.getTicketsAvailable(); i++) {
                String number = generateNumber(exposition,i);
                log.info("Ticket number created " + number);
                statement.setString(1, number);

                statement.executeUpdate();
                log.debug("Ticket for exposition with id " + exposition.getId() + " created");
                created = true;
            }

        } catch (SQLException e) {
            log.error("Cannot create tickets for exposition with id " + exposition.getId(), e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }
        return created;
    }

    static String generateNumber(Exposition exposition, int i) {
        log.info(exposition.hashCode());
        String  number= String.valueOf(Math.abs(exposition.hashCode()+i));
        log.info(number);
        return number;
    }

    @Override
    public List<Ticket> getTickets(Integer expositionId, Integer numberOfTickets) {
        ConnectionWrapper con = null;
        List<Ticket> tickets = new ArrayList<>();
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(GET_SELECTED_TICKETS);
            statement.setInt(1, expositionId);
            statement.setInt(2, numberOfTickets);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                ticket.setTicketId(resultSet.getInt("t.id_ti"));
                ticket.setNumber(resultSet.getInt("t.number"));
                Exposition exposition = new Exposition();
                exposition.setId(expositionId);
                exposition.setTheme(resultSet.getString("e.theme"));
                exposition.setDateStart(resultSet.getDate("e.date_start"));
                exposition.setDateEnd(resultSet.getDate("e.date_end"));
                ticket.setExposition(exposition);
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            log.error("Cannot get Ticket for exposition " + expositionId, e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }

        return tickets;
    }



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
                Exposition exposition = new Exposition();
                exposition.setId(resultSet.getInt("fk_id_ex"));
                ticket.setExposition(exposition);
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
                Exposition exposition = new Exposition();
                exposition.setId(resultSet.getInt("fk_id_ex"));
                ticket.setExposition(exposition);
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
            statement.setInt(2, ticket.getExposition().getId());
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
