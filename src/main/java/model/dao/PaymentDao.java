package model.dao;

import model.entities.Payment;
import model.entities.Ticket;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PaymentDao {
    void createPayment(int ticket, int user);

    Payment getById(int id);

    Map<Ticket, Date> getTicketsByUserId(int userId);

    List<Payment> getAllPayments();

    void update(Payment payment);

    void delete(int id);
}
