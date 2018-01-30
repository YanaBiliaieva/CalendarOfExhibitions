package exhibitions.model.dao;

import exhibitions.model.entities.Payment;
import exhibitions.model.entities.Ticket;

import java.util.List;

public interface PaymentDao {
    void createPayment(List<Ticket> tickets, Integer user, Integer price);

    Payment getById(int id);

    List<Payment> getUserPayments(Integer userId);

    List<Payment> getAllPayments();

    void update(Payment payment);

    void delete(int id);
}
