package model.dao;

import model.entities.Ticket;
import java.util.List;


public interface TicketsDao {
    void createTicket(int number, int exhibition);

    Ticket getById(int id);

    List<Ticket> getAllTickets();

    void update(Ticket ticket);

    void delete(int id);
}
