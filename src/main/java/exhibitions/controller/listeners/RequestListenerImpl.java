package exhibitions.controller.listeners;

import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
public class RequestListenerImpl implements ServletRequestListener {
    private static Logger log = Logger.getLogger(RequestListenerImpl.class);

    /**
     * используем для получения информации о запросе
     */
    @Override
    public void requestInitialized(ServletRequestEvent ev) {
        HttpServletRequest request= (HttpServletRequest) ev.getServletRequest();
        String uri="Request initialized for "+ request.getRequestURL();
        String id="Request initialized with id="+request.getRequestedSessionId();
        log.info(uri+"\n"+id);
        ServletContext context=ev.getServletContext();
        Integer requestCounter= (Integer) request.getSession().getAttribute("counter");
        if(requestCounter==null){
            requestCounter=0;
        }
        context.log(uri+"\n"+id+ ", Request counter = "+requestCounter);


    }

    @Override
    public void requestDestroyed(ServletRequestEvent ev) {
        log.info("Request destroyed: "+ev.getServletRequest().getAttribute("lifecycle"));
    }
}
