package controller.command;

import controller.ConfigurationManager;
import model.entities.Exposition;
import model.entities.Payment;
import model.entities.Ticket;
import model.entities.User;
import org.apache.log4j.Logger;
import services.ExhibitionsService;
import services.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrdersCommand implements Command {
    private ExhibitionsService exhibitionsService = ExhibitionsService.getExhibitionsService();
    private Logger logger = Logger.getLogger(OrdersCommand.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        if (request.getMethod().equals("GET")) {
            Integer expositionId = Integer.valueOf(request.getParameter("expositionId"));

            Exposition exposition = exhibitionsService.getExposition(expositionId);
            logger.debug("request.setAttribute exposition==" + exposition.toString());
            request.setAttribute("exposition", exposition);

            try {
                request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.order"))
                        .forward(request, response);
            } catch (ServletException | IOException e) {
                logger.error("Cannot go to order page " + e);
            }

        } else if (request.getMethod().equals("POST")) {
            Integer quantity = Integer.parseInt(request.getParameter("quantity"));
            logger.info("Selected tickets quantity is " + quantity);
            Integer expositionId= Integer.valueOf(request.getParameter("expositionId"));
            logger.info("Selected exposition id is " + expositionId);
            Integer userId=((User) request.getSession().getAttribute("user")).getUserId();
            String userEmail=((User) request.getSession().getAttribute("user")).getEmail();
            logger.info("Current user id is " + userId);
            OrderService orderService= OrderService.getOrderService();
            if(quantity>0){
                List<Ticket> tickets= orderService.createPayment(quantity, expositionId, userId);
                logger.info("tickets="+tickets);
            }
            try {
                List<Payment> payments=orderService.getPayments(userId);
                request.setAttribute("payments",payments);
                logger.info("payments="+payments);
                request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.myorders"))
                        .forward(request, response);
            } catch (ServletException | IOException e) {
                logger.error("Cannot go to my orders page " + e);
            }
        }

    }
}
