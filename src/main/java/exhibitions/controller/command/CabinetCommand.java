package exhibitions.controller.command;

import exhibitions.controller.ConfigurationManager;
import exhibitions.model.entities.Payment;
import exhibitions.model.entities.User;
import exhibitions.services.OrderService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class CabinetCommand implements Command{
    private Logger logger = Logger.getLogger(CabinetCommand.class);
    private OrderService orderService= OrderService.getOrderService();
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer userId=((User) request.getSession().getAttribute("user")).getUserId();
        if(Objects.nonNull(userId)){
            try {
                List<Payment> payments=orderService.getPayments(userId);
                request.setAttribute("payments",payments);
                logger.info("payments="+payments);
                request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.myorders"))
                        .forward(request, response);
            } catch (ServletException | IOException e) {
                logger.error("Cannot go to my orders page " + e);
            }
        }else {
            request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.index"))
                    .forward(request, response);
        }

        return null;
    }
}
