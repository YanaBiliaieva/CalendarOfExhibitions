package controller.command;

import org.apache.log4j.Logger;
import services.ExhibitionsService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ExhibitionsCommand implements Command {
    private Logger logger = Logger.getLogger(ExhibitionsCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("in ExhibitionsCommand execute!!!!");
        List<List<String>> exhibitions=ExhibitionsService.getExhibitions();
        request.getSession().setAttribute("lst",exhibitions);
        request.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(request,response);
        return request.getParameter("page");
    }
}
