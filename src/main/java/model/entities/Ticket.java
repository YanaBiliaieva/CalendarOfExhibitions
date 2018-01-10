package model.entities;

import java.io.Serializable;

public class Ticket implements Serializable {
    private int ticketId;
    private int number;
    private int expositionId;

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

    public int getExpositionId() {
        return expositionId;
    }

    public void setExpositionId(int expositionId) {
        this.expositionId = expositionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;

        Ticket ticket = (Ticket) o;

        if (getTicketId() != ticket.getTicketId()) return false;
        if (getNumber() != ticket.getNumber()) return false;
        return getExpositionId() == ticket.getExpositionId();
    }

    @Override
    public int hashCode() {
        int result = getTicketId();
        result = 31 * result + getNumber();
        result = 31 * result + getExpositionId();
        return result;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", number=" + number +
                ", expositionId=" + expositionId +
                '}';
    }
}
