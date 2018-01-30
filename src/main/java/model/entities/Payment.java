package model.entities;

import java.io.Serializable;
import java.util.Date;

public class Payment implements Serializable {
    private Integer paymentId;
    private Integer userId;
    private Date date;
    private Ticket ticket;
    private Integer amount;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }



    public Payment() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }


    public Payment(Integer paymentId, Integer userId, Date date, Ticket ticket, Integer amount) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.date = date;
        this.ticket = ticket;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;

        Payment payment = (Payment) o;

        if (getPaymentId() != payment.getPaymentId()) return false;
        if (getUserId() != payment.getUserId()) return false;
        if (ticket.hashCode() != payment.ticket.hashCode()) return false;
        return getDate().equals(payment.getDate());
    }

    @Override
    public int hashCode() {
        Integer result = getPaymentId();
        result = 31 * result + getUserId();
        result = 31 * result + ticket.hashCode();
        result = 31 * result + getDate().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", userId=" + userId +
                ", date=" + date +
                ", ticket=" + ticket +
                ", amount=" + amount +
                '}';
    }
}
