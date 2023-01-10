package app.controllerFront.filters;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilterSession implements Filter {
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

        if (command == null) {
            resp.sendRedirect("exhibition?command=auto");
        } else {
            if (command.equals("auto") || command.equals("reg") || command.equals("guest") || command.equals("recovery")) {
                chain.doFilter(req, resp);
            } else {
                if (command.equals("user") || command.equals("userexhibition")) { //check session for user
                    if (req.getSession().getAttribute("UserRole") != null) {
                        chain.doFilter(req, resp);
                    } else {
                        resp.sendRedirect("exhibition?command=auto");
                    }
                } else if (command.equals("recoverypassword")) { //check session for recovery password
                    if (req.getSession().getAttribute("recovery") != null) {
                        chain.doFilter(req, resp);
                    } else {
                        resp.sendRedirect("exhibition?command=auto");
                    }
                } else { //check session for admin

                    if (req.getSession().getAttribute("UserRole") != null) {
                        if (req.getSession().getAttribute("UserRole").equals("2"))
                            chain.doFilter(req, resp);
                        else
                            resp.sendRedirect("exhibition?command=auto");
                    } else
                        resp.sendRedirect("exhibition?command=auto");
                }
            }
        }
    }

    public void destroy() {
        config = null;
    }
}
