package app.servlets;

import app.database.UserDB;
import app.entities.User;
import app.model.ModelAuthorization;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthorizationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String PathJsp = null;

        System.out.println("role "+req.getAttribute("UserRoleCheck"));
        if(req.getAttribute("UserRoleCheck")==null)
        {
            if(req.getSession().getAttribute("UserRole").equals("1")) {
                PathJsp = "views/AdminMenu.html";

            } else if (req.getSession().getAttribute("UserRole").equals("2")) {
                PathJsp = "views/UserAutoMenu.jsp";
            }
        }
        else
        {
            PathJsp="index.jsp";
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(PathJsp);
        requestDispatcher.forward(req, resp);
        req.removeAttribute("UserRoleCheck");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("loginUser");
        String password = req.getParameter("passwordUser");

        User user=UserDB.authorizationuser(login,password);

        System.out.println("userrole "+user.getRole());
        if(user.getRole().equals("false"))
        {
            req.setAttribute("UserRoleCheck",user.getRole());
        }
        else
        {
            System.out.println("session");
            req.getSession().setAttribute("UserRole",user.getRole());

            if(user.getRole().equals("false")!=true) {
                ModelAuthorization model = ModelAuthorization.getInstance();
                model.add(user);
            }
        }
        doGet(req, resp);
    }
}
