package app.servlets;

import app.database.UserAutorizationDB;
import app.database.UserRegistrationDB;
import app.entities.UserRegistration;
import app.model.ModelRegistration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (UserRegistrationDB.checkConnection() == null)
            UserRegistrationDB.startConnnection();
        ModelRegistration model = ModelRegistration.getInstance();

        if (model.checkString() != null) {
            if (model.returnString().equals("true")) {
                UserRegistrationDB.exitConnection();
                UserRegistrationDB.nullConnection();
                req.setAttribute("UserAddTrue", true);
            } else if (model.returnString().equals("false"))
                req.setAttribute("UserAddError", true);
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/RegistrationForm.jsp");
        requestDispatcher.forward(req, resp);
        req.removeAttribute("UserAddTrue");
        req.removeAttribute("UserAddError");
        model.delete();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (UserRegistrationDB.checkConnection() == null)
            UserRegistrationDB.startConnnection();

        if (req.getParameter("registrationButton") != null) {
            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            String email = req.getParameter("email");
            String login = req.getParameter("login");
            String password = req.getParameter("password");

            ModelRegistration model = ModelRegistration.getInstance();
            model.add(UserRegistrationDB.registrationDB(firstName, lastName, email, login, password).getCheckRegistration());

            resp.sendRedirect("/exhibition/reg");
        } else if (req.getParameter("autorizedButton") != null) {
            UserRegistrationDB.exitConnection();
            UserAutorizationDB.nullConnection();
        }

    }
}
