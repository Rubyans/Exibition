package app.servlets;

import app.database.UserDB;
import app.model.ModelAuthorization;

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
                req.setAttribute("Error", true);
                req.getRequestDispatcher(PathJsp).forward(req, resp);
            } else {
                if (model.roleCheck().equals("1")) {
                    req.getSession().setAttribute("UserRole", model.roleCheck());
                    resp.sendRedirect("/exhibition/user");

                } else if (model.roleCheck().equals("2")) {
                    req.getSession().setAttribute("UserRole", model.roleCheck());
                    resp.sendRedirect("/exhibition/adminmain");
                } else {
                    req.setAttribute("UserRoleCheck", model.roleCheck());
                    req.getRequestDispatcher(PathJsp).forward(req, resp);

                }
            }
        }
        else
            req.getRequestDispatcher(PathJsp).forward(req, resp);
        req.removeAttribute("UserRoleCheck");
        req.removeAttribute("Error");
        ModelAuthorization.delete();

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String login = req.getParameter("loginUser");
        String password = req.getParameter("passwordUser");

        ModelAuthorization model = ModelAuthorization.getInstance();
        model.add(UserDB.authorizationuser(login, password));

        resp.sendRedirect("/exhibition/auto");

    }
}
