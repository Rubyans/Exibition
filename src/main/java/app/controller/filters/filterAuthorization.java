package app.controller.filters;

import app.DAO.connectionDAO.HikariConnectDB;
import app.DAO.sqlFunctions.UserAutorizationDB;
import app.models.authorizationModels.ModelAuthorization;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class filterAuthorization implements Filter {
    private FilterConfig config = null;
    private boolean active = false;

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
        String act = config.getInitParameter("active");
        if (act != null)
            active = (act.toUpperCase().equals("TRUE"));
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) request;
        HttpServletResponse resp= (HttpServletResponse) response;

        if (active) {



            if (req.getParameter("loginButton") != null) {

                if (HikariConnectDB.checkConnection() == false) {
                    HikariConnectDB object = new HikariConnectDB();
                }
                String login = req.getParameter("loginUser");
                String password = req.getParameter("passwordUser");

                ModelAuthorization model = ModelAuthorization.getInstance();
                model.add(UserAutorizationDB.authorizationUser(login, password));

                resp.sendRedirect("/exhibition/reg");
            }




        }
        chain.doFilter(req, resp);
    }

    public void destroy() {
        config = null;
    }
}
