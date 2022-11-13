package app.servlets;

import app.database.UserDB;
import app.entities.User;
import app.model.ModelAuthorization;
import app.model.ModelGuest;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String PathJsp = "index.jsp";

        ModelAuthorization model = ModelAuthorization.getInstance();

        if (model.listUser() != null) {
            if (model.checkNull() == true) {
                PathJsp = "index.jsp";
                req.setAttribute("Error", true);
            } else {
                if (model.roleCheck().equals("1")) {
                    req.getSession().setAttribute("UserRole", model.roleCheck());
                    PathJsp = "views/UserAutoMenu.jsp";

                } else if (model.roleCheck().equals("2")) {
                    req.getSession().setAttribute("UserRole", model.roleCheck());
                    PathJsp = "views/AdminMenu.html";
                } else {
                    req.setAttribute("UserRoleCheck", model.roleCheck());
                }
            }
        }
        req.getRequestDispatcher(PathJsp).forward(req, resp);
        req.removeAttribute("UserRoleCheck");
        req.removeAttribute("Error");
        ModelAuthorization.delete();

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ModelGuest.delete();

        String login = req.getParameter("loginUser");
        String password = req.getParameter("passwordUser");

        ModelAuthorization model = ModelAuthorization.getInstance();
        model.add(UserDB.authorizationuser(login, password));

        resp.sendRedirect("/exibition/auto");

    }
}
