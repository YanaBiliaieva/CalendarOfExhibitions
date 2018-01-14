package controller.servlets;

import org.apache.log4j.Logger;
import controller.command.Command;
import controller.command.FactoryCommand;

import javax.servlet.RequestDispatcher;
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
        String page=null;
        FactoryCommand factory = FactoryCommand.getInstance();
        logger.info((String) req.getAttribute("controller/command"));
        Command command = factory.getCommand((String) req.getAttribute("controller/command"));

        try {
            page=command.execute(req,resp);
            if(page!=null){
                RequestDispatcher requestDispatcher=getServletContext().getRequestDispatcher(page);
                requestDispatcher.forward(req,resp);
            }else {
                //TODO
            }
        } catch (ServletException e) {
            e.printStackTrace();
            logger.error("Cannot execute command "+e);
        } catch (IOException e) {
            logger.error("Cannot execute command "+e);
        }

    }
}
