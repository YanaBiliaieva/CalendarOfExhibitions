package services;

import model.dao.DaoFactory;
import model.dao.implementation.ExpositionDaoImpl;
import model.dao.implementation.PaymentDaoImpl;
import model.dao.implementation.TicketsDaoImpl;
import model.dao.implementation.UserDaoImpl;
import model.entities.Exposition;
import model.entities.Payment;
import model.entities.Ticket;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Objects;

public class OrderService {

    private static OrderService orderService = new OrderService();
    private Logger logger = Logger.getLogger(OrderService.class);

    public static OrderService getOrderService() {
        return orderService;
    }

    private TicketsDaoImpl ticketsDao = DaoFactory.getTicketsDaoImpl();
    private ExpositionDaoImpl expositionDao = DaoFactory.getExpositionDaoImpl();
    private PaymentDaoImpl paymentDao = DaoFactory.getPaymentDaoImpl();
    private UserDaoImpl userDao = DaoFactory.getUserDaoImpl();

    private OrderService() {
    }

    public List<Ticket> createPayment(Integer quantity, Integer expositionId, Integer userId) {
        List<Ticket> tickets = ticketsDao.getTickets(expositionId, quantity);
        logger.debug("Tickets to be bought=" + tickets);
        if (Objects.nonNull(tickets)) {
            Integer expositionPrice = expositionDao.getPriceById(expositionId);
            logger.debug("exposition Price = " + expositionPrice);
            Integer ticketsPrice = expositionPrice * quantity;
            logger.debug("ticketsPrice Price = " + ticketsPrice);
            userDao.changeBalance(userId, ticketsPrice);
            logger.debug("Balance changed");
            Integer boughtTickets = tickets.size();
            logger.debug("Number of tickets to be bought=" + boughtTickets);
            Integer currentTickets=expositionDao.getNumberOfTickets(expositionId);
            logger.debug("Number of current tickets available=" + currentTickets);
            Integer ticketsLeft=currentTickets-boughtTickets;
            logger.debug("Number of current tickets to be left=" + ticketsLeft);
            if(ticketsLeft>0){
                boolean removed = expositionDao.removeTickets(expositionId, ticketsLeft);
                logger.debug("Number of tickets for exposition with id"+ expositionId+"decreased by  "+boughtTickets);
                if(removed){
                    paymentDao.createPayment(tickets,userId,expositionPrice);
                    logger.debug("Payment created ");
                }
            }
        }
        return tickets;
    }

    public void sendMail(List<Ticket> tickets, String userEmail, Integer expositionId) {
        Exposition exposition = expositionDao.getById(expositionId);
        logger.info(exposition.toString());
        SimpleEmail email = new SimpleEmail();
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator("mailertesting2@gmail.com", "mailer123"));
        email.setSSLOnConnect(true);
        email.setSubject("Your Tickets");
        StringBuilder body = new StringBuilder("Your secret codes are:");
        for (Ticket ticket : tickets) {
            body.append(ticket.getNumber());
        }
        String s = body.toString();
        s = s + " for Exposition with theme " + exposition.getTheme();
        s = s + " for date from " + exposition.getDateStart() + " to date " + exposition.getDateEnd();
        s = s + ". Hall " + exposition.getHallName() + "is located in " + exposition.getHallCity() + ", " + exposition.getHallAddress();
        s = s + ". Thank you.";
        logger.info("Email body=" + s);
        try {
            email.setFrom("mailertesting2@gmail.com");
            email.setMsg(s);
            email.addTo(userEmail);
            email.send();
        } catch (EmailException e) {
            logger.error("Failed to send email" + e);
        }

    }

    public List<Payment> getPayments(Integer userId) {
        return paymentDao.getUserPayments(userId);
    }
}
