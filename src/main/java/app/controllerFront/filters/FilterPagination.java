package app.controllerFront.filters;


import app.DAO.connectionDAO.HikariConnectDB;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FilterPagination implements Filter {
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
            if (command.equals("auto") || command.equals("reg") || command.equals("guest") || command.equals("recovery") || command.equals("recoverypassword")) {
                chain.doFilter(req, resp);
            } else {
                if (command.equals("user") || command.equals("userexhibition")) { //check pagination for user
                    if (req.getParameter("UserPagination") != null) {
                        resp.sendRedirect("exhibition?command=user");
                    } else if (req.getParameter("UserExhibitionPagination") != null) {
                        resp.sendRedirect("exhibition?command=userexhibition");
                    } else if (req.getParameter("exitButton") != null) {
                        HikariConnectDB.exitConnection();
                        req.getSession().removeAttribute("UserRole");
                        resp.sendRedirect("exhibition?command=auto");
                    } else {
                        chain.doFilter(req, resp);
                    }
                } else { //check pagination for admin
                    if (req.getParameter("exitButton") != null) {
                        req.getSession().removeAttribute("UserRole");
                        HikariConnectDB.exitConnection();
                        resp.sendRedirect("exhibition?command=auto");
                    } else if (req.getParameter("AdminMainPagination") != null) {
                        resp.sendRedirect("exhibition?command=adminmain");
                    } else if (req.getParameter("UserAutorizedPagination") != null) {
                        resp.sendRedirect("exhibition?command=userautorized");
                    } else if (req.getParameter("AdminHallPagination") != null) {
                        resp.sendRedirect("exhibition?command=adminhall");
                    } else if (req.getParameter("AdminAddressPagination") != null) {
                        resp.sendRedirect("exhibition?command=adminaddress");
                    } else if (req.getParameter("AdminAuthorPagination") != null) {
                        resp.sendRedirect("exhibition?command=adminauthor");
                    } else if (req.getParameter("AdminArtPagination") != null) {
                        resp.sendRedirect("exhibition?command=adminart");
                    } else if (req.getParameter("AdminViewPagination") != null) {
                        resp.sendRedirect("exhibition?command=adminview");
                    } else if (req.getParameter("AdminStatisticsPagination") != null) {
                        resp.sendRedirect("exhibition?command=adminstatistics");
                    } else {
                        chain.doFilter(req, resp);
                    }
                }
            }
        }
    }

    public void destroy() {
        config = null;
    }
}
