package model.dao.implementation;

import model.dao.PaymentDao;
import model.entities.Payment;
import model.entities.Ticket;
import org.apache.log4j.Logger;
import transactions.ConnectionWrapper;
import transactions.TransactionManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.*;

public class PaymentDaoImpl implements PaymentDao {
    private static Logger log = Logger.getLogger(HallDaoImpl.class);
    private static final String GET_BY_ID = "SELECT * FROM payments WHERE id_pa = ?";
    private static final String CREATE_PAYMENT = "INSERT INTO payments(fk_id_ti, fk_id_us) VALUES (?,?)";
    private static final String DELETE_PAYMENT = "DELETE FROM payments WHERE id_pa=?";
    private static final String UPDATE_PAYMENT = "UPDATE payments SET fk_id_ti=?,fk_id_us=? WHERE id_pa=?";
    private static final String GET_ALL_TICKETS_BY_USER =
            "SELECT * FROM tickets t LEFT JOIN payments p ON (t.id_ti=p.fk_id_ti) WHERE p.fk_id_us=?";
    private static final String GET_ALL_PAYMENTS = "SELECT * FROM payments";

    public PaymentDaoImpl() {
    }

    @Override
    public void createPayment(int ticket, int user) {
        ConnectionWrapper con = null;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(CREATE_PAYMENT);
            statement.setInt(1, ticket);
            statement.setInt(2, user);
            statement.executeQuery();
        } catch (SQLException e) {
            log.error("Cannot create Payment with " + ticket+" and user "+user, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }
    }

    @Override
    public Payment getById(int id) {
        ConnectionWrapper con = null;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(GET_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Payment payment = new Payment();
                payment.setUserId(resultSet.getInt("fk_id_us"));
                payment.setTicketId(resultSet.getInt("fk_id_ti"));
                payment.setDate(resultSet.getDate("date"));
                payment.setPaymentId(id);
                return payment;
            }
        } catch (SQLException e) {
            log.error("Cannot get Payment by id " + id, e);
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
    public Map<Ticket, Date> getTicketsByUserId(int userId) {
        ConnectionWrapper con = null;
        ResultSet resultSet = null;
        Map<Ticket, Date> ticketDateMapById = null;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(GET_ALL_TICKETS_BY_USER);
            statement.setInt(1, userId);
            ticketDateMapById = new HashMap<>();
            Date date = null;
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id_ti");
                Ticket ticket = new Ticket();
                ticket.setTicketId(id);
                ticket.setExpositionId(resultSet.getInt("fk_id_ex"));
                date = (resultSet.getDate("date"));
                ticketDateMapById.put(ticket, date);
            }
        } catch (SQLException e) {
            log.error("Can't find Tickets for User Id " + userId, e);
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
        return ticketDateMapById;
    }

    @Override
    public List<Payment> getAllPayments() {
        ConnectionWrapper con = null;
        ResultSet resultSet = null;
        Map<Integer, Payment> allPayments = null;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(GET_ALL_PAYMENTS);
            allPayments = new HashMap<>();
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Payment payment = new Payment();
                payment.setPaymentId(id);
                payment.setUserId(resultSet.getInt("fk_id_us"));
                payment.setTicketId(resultSet.getInt("fk_id_ti"));
                allPayments.put(id, payment);
            }
        } catch (SQLException e) {
            log.error("Can't find Payments", e);
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
        return new ArrayList<>(allPayments.values());

    }

    @Override
    public void update(Payment payment) {
        ConnectionWrapper con = null;
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(UPDATE_PAYMENT);
            statement.setInt(1, payment.getTicketId());
            statement.setInt(2, payment.getUserId());
            statement.setInt(3, payment.getPaymentId());
            statement.executeQuery();

        } catch (SQLException e) {
            log.error("Cannot update payment with id=" + payment.getPaymentId(), e);
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
            PreparedStatement statement = con.preparedStatement(DELETE_PAYMENT);
            statement.setInt(1, id);
            statement.executeQuery();
        } catch (SQLException e) {
            log.error("Cannot delete payment with id" + id, e);
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                log.error("Cannot close connection", e);
            }
        }
    }
}
