package app.controller.listners;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class SessionListener implements HttpSessionBindingListener { //class listener
    private static final Logger LOGGER = Logger.getLogger(SessionListener.class); //logger

    @Override
    public void valueBound(HttpSessionBindingEvent event) { //Called when an object produced in session
        LOGGER.debug(event.getSession().getAttribute("language") + " in valueBound()");
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) { //Called when an object removed from the session
        LOGGER.debug(event.getSession().getAttribute("language") + " in valueUnbound()");
    }
}
