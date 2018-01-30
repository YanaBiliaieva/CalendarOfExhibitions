package exhibitions.controller.listeners;

import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListenerImpl implements ServletContextListener {
    private static Logger log = Logger.getLogger(ContextListenerImpl.class);

    @Override
    public void contextInitialized(ServletContextEvent ev) {
        ServletContext context=ev.getServletContext();
        String mailFeedback=context.getInitParameter("feedback");
        context.log("Context initialized with parameter:"+mailFeedback);
        log.info("Context initialized with parameter:"+mailFeedback);
    }

    @Override
    public void contextDestroyed(ServletContextEvent ev) {

    }
}
