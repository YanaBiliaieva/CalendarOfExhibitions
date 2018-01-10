package model.entities;

import java.io.Serializable;
import java.util.Date;

public class Payment implements Serializable {
    private int paymentId;
    private int userId;
    private int ticketId;
    private Date date;

    public Payment() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public Payment(int paymentId, int userId, int ticketId, Date date) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.ticketId = ticketId;
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;

        Payment payment = (Payment) o;

        if (getPaymentId() != payment.getPaymentId()) return false;
        if (getUserId() != payment.getUserId()) return false;
        if (getTicketId() != payment.getTicketId()) return false;
        return getDate().equals(payment.getDate());
    }

    @Override
    public int hashCode() {
        int result = getPaymentId();
        result = 31 * result + getUserId();
        result = 31 * result + getTicketId();
        result = 31 * result + getDate().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", userId=" + userId +
                ", ticketId=" + ticketId +
                ", date=" + date +
                '}';
    }
}
