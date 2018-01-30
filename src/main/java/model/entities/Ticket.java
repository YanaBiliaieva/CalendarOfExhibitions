package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Ticket implements Serializable {
    private int ticketId;
    private int number;
    private Exposition exposition;
    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Exposition getExposition() {
        return exposition;
    }

    public void setExposition(Exposition exposition) {
        this.exposition = exposition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;

        Ticket ticket = (Ticket) o;

        if (getTicketId() != ticket.getTicketId()) return false;
        if (getNumber() != ticket.getNumber()) return false;
        return Objects.equals(exposition.getId(), ticket.getExposition().getId());
    }

    @Override
    public int hashCode() {
        int result = getTicketId();
        result = 31 * result + getNumber();
        result = 31 * result + exposition.getId();
        return result;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", number=" + number +
                ", expositionId=" + exposition.getId() +
                '}';
    }
}
