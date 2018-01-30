package exhibitions.model.dao;

import exhibitions.model.entities.Exposition;
import exhibitions.model.entities.Ticket;

import java.util.List;


public interface TicketsDao {
    boolean createTickets(Exposition exposition);

    void createTicket(int number, int exhibition);

    Ticket getById(int id);

    List<Ticket> getAllTickets();

    void update(Ticket ticket);

    void delete(int id);

    List<Ticket> getTickets(Integer expositionId, Integer numberOfTickets);


}
