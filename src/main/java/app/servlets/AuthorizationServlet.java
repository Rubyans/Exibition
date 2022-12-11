package app.servlets;

import app.database.UserAutorizationDB;
import app.model.ModelAuthorization;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ModelAuthorization model = ModelAuthorization.getInstance();

        if (UserAutorizationDB.checkConnection() == null)
            UserAutorizationDB.startConnnection();

        if (model.listUser() != null) {
            if (model.checkNull() == true) {
                UserAutorizationDB.nullConnection();
                req.setAttribute("Error", true);
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            } else {
                if (model.roleCheck().equals("1")) {
                    UserAutorizationDB.exitConnection();
                    UserAutorizationDB.nullConnection();
                    req.getSession().setAttribute("UserRole", model.roleCheck());
                    req.getSession().setAttribute("UserId", model.getId());
                    resp.sendRedirect("/exhibition/user");

                } else if (model.roleCheck().equals("2")) {
                    UserAutorizationDB.exitConnection();
                    UserAutorizationDB.nullConnection();
                    req.getSession().setAttribute("UserRole", model.roleCheck());
                    resp.sendRedirect("/exhibition/adminmain");
                } else {
                    UserAutorizationDB.exitConnection();
                    UserAutorizationDB.nullConnection();
                    req.setAttribute("UserRoleCheck", model.roleCheck());
                    req.getRequestDispatcher("index.jsp").forward(req, resp);

                }
            }
        } else
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        req.removeAttribute("UserRoleCheck");
        req.removeAttribute("Error");
        ModelAuthorization.delete();

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (UserAutorizationDB.checkConnection() == null)
            UserAutorizationDB.startConnnection();

        if (req.getParameter("loginButton") != null) {
            String login = req.getParameter("loginUser");
            String password = req.getParameter("passwordUser");

            ModelAuthorization model = ModelAuthorization.getInstance();
            model.add(UserAutorizationDB.authorizationuser(login, password));

            resp.sendRedirect("/exhibition/auto");
        }

    }
}
