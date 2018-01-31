package exhibitions.model.dao.implementation;

import exhibitions.model.dao.PaymentDao;
import exhibitions.model.entities.Exposition;
import exhibitions.model.entities.Payment;
import exhibitions.model.entities.Ticket;
import exhibitions.model.transactions.ConnectionWrapper;
import exhibitions.model.transactions.TransactionManager;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentDaoImpl implements PaymentDao {
    private static Logger log = Logger.getLogger(HallDaoImpl.class);
    private static final String GET_BY_ID = "SELECT * FROM payments WHERE id_pa = ?";
    private static final String CREATE_PAYMENT = "INSERT INTO payments(fk_id_ti, fk_id_us, amount) VALUES (?,?,?)";
    private static final String DELETE_PAYMENT = "DELETE FROM payments WHERE id_pa=?";
    private static final String UPDATE_PAYMENT = "UPDATE payments SET fk_id_ti=?,fk_id_us=? WHERE id_pa=?";
    private static final String GET_ALL_TICKETS_BY_USER ="SELECT id_pa, date, amount, fk_id_us, t.id_ti,t.number, " +
            "e.id_ex,e.theme, e.price,e.date_start,e.date_end FROM payments p RIGHT JOIN tickets t " +
            "ON (p.fk_id_ti=t.id_ti)LEFT JOIN expositions e ON (t.fk_id_ex= e.id_ex) WHERE p.fk_id_us=?";
    private static final String GET_ALL_PAYMENTS = "SELECT * FROM payments";

    public PaymentDaoImpl() {
    }
    @Override
    public List<Payment> getUserPayments(Integer userId) {
        ConnectionWrapper con = null;
        ResultSet resultSet = null;
        List<Payment> payments = new ArrayList<>();
        try {
            con = TransactionManager.getConnection();
            PreparedStatement statement = con.preparedStatement(GET_ALL_TICKETS_BY_USER);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            if(resultSet!=null){
                while (resultSet.next()) {
                    Payment payment=new Payment();
                    payment.setPaymentId(resultSet.getInt("id_pa"));
                    payment.setDate(resultSet.getDate("date"));
                    payment.setUserId(resultSet.getInt("fk_id_us"));
                    payment.setAmount(resultSet.getInt("amount"));
                    Ticket ticket=new Ticket();
                    ticket.setTicketId(resultSet.getInt("t.id_ti"));
                    ticket.setNumber(resultSet.getInt("t.number"));
                    Exposition exposition=new Exposition();
                    exposition.setId(resultSet.getInt("e.id_ex"));
                    exposition.setTheme(resultSet.getString("e.theme"));
                    exposition.setPrice(resultSet.getInt("e.price"));
                    exposition.setDateStart(resultSet.getDate("e.date_start"));
                    exposition.setDateEnd(resultSet.getDate("e.date_end"));
                    ticket.setExposition(exposition);
                    payment.setTicket(ticket);
                    payments.add(payment);
                }
            }

        } catch (SQLException e) {
            log.error("Can't find Payments for User Id " + userId, e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                log.error("Can't close connection", e);
            }
        }
        return payments;
    }
    @Override
    public void createPayment(List<Ticket> tickets, Integer userId, Integer price) {
        ConnectionWrapper con = null;
        try {
            con = TransactionManager.getConnection();
            log.info("Tickets "+tickets.toString());
            for (Ticket ticket : tickets) {
                PreparedStatement statement = con.preparedStatement(CREATE_PAYMENT);
                statement.setInt(1, ticket.getTicketId());
                statement.setInt(2, userId);
                statement.setInt(3,price);
                statement.executeUpdate();
            }

        } catch (SQLException e) {
            log.error("Cannot create Payment for user "+userId+e);
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
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
                //payment.setTicketId(resultSet.getInt("fk_id_ti"));
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
            //statement.setInt(1, payment.getTicketId());
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
