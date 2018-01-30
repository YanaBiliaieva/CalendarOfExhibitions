package controller.servlets;

import controller.command.Command;
import controller.command.FactoryCommand;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletDispatcher extends HttpServlet{

    private Logger logger = Logger.getLogger(ServletDispatcher.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("In doGet ServletDispatcher method");
        processRequest(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("In doPost ServletDispatcher method");
        processRequest(req,resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) {
        logger.info("In processRequest ServletDispatcher method");

        FactoryCommand factory = FactoryCommand.getInstance();
        String action=((String) req.getAttribute("controller/command")).toLowerCase();

        Command command = factory.getCommand(((String) req.getAttribute("controller/command")).toLowerCase());

        try {

            command.execute(req,resp);
            logger.info("--------------------EXECUTED");
            logger.info("action==="+action);

        } catch (ServletException e) {
            e.printStackTrace();
            logger.error("Cannot execute command "+e);
        } catch (IOException e) {
            logger.error("Cannot execute command "+e);
        }

    }
}
