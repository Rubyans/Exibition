package app.controllerFront.filters;


import app.controllerFront.listners.SessionListener;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilterLanguage implements Filter {
    private FilterConfig config = null;
    private boolean active = false;

    public void init(FilterConfig config) throws ServletException { //initialization filter
        this.config = config;
        String act = config.getInitParameter("active");
        if (act != null)
            active = (act.toUpperCase().equals("TRUE"));
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String command = req.getParameter("command"); // /exhibition?command=...
        SessionListener listener = new SessionListener();
        if (command == null) {
            resp.sendRedirect("exhibition?command=auto");
        } else { ///check language
            if (req.getParameter("englishButton") != null) {
                req.getSession().setAttribute("checkLanguage", listener);
                req.getSession().setAttribute("language", "en");
                resp.sendRedirect("exhibition?command=" + command);
            } else if (req.getParameter("ukraineButton") != null) {
                req.getSession().setAttribute("checkLanguage", listener);
                req.getSession().setAttribute("language", "ua");
                resp.sendRedirect("exhibition?command=" + command);
            } else {
                chain.doFilter(req, resp);
            }
        }
    }

    public void destroy() {
        config = null;
    }
}
