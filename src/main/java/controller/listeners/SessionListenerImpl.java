package controller.listeners;

import org.apache.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * обработка события добавления и изменения атрибута сессии
 */
@WebListener
public class SessionListenerImpl implements HttpSessionAttributeListener {
    private static Logger log = Logger.getLogger(SessionListenerImpl.class);

    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        log.info("add:" + httpSessionBindingEvent.getClass().getSimpleName() + " : " + httpSessionBindingEvent.getValue());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
        log.info("remove:" + httpSessionBindingEvent.getClass().getSimpleName() + " : " + httpSessionBindingEvent.getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
        log.info("replace:" + httpSessionBindingEvent.getClass().getSimpleName() + " : " + httpSessionBindingEvent.getValue());
    }
}
