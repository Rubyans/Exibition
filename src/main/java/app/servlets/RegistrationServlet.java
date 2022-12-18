package app.servlets;

import app.database.UserAutorizationDB;
import app.database.UserRegistrationDB;
import app.entities.UserRegistration;
import app.model.registrationModels.ModelLanguageRegistration;
import app.model.registrationModels.ModelRegistration;

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
        ModelRegistration modelRegistration = ModelRegistration.getInstance();
        ModelLanguageRegistration modelLanguageRegistration = ModelLanguageRegistration.getInstance();
        if (modelLanguageRegistration.modelCheck() != null) {
            if (modelLanguageRegistration.modelCheck().equals("en")) {
                req.getSession().setAttribute("language", "en");
            }
            if (modelLanguageRegistration.modelCheck().equals("ua")) {
                req.getSession().setAttribute("language", "ua");
            }
        }

        if (req.getSession().getAttribute("language") != null) {
            if (req.getSession().getAttribute("language").equals("en")) {
                req.setAttribute("languageEnglish", true);
            } else if (req.getSession().getAttribute("language").equals("ua")) {
                req.setAttribute("languageUkraine", true);
            }
        }

        if (modelRegistration.checkString() != null) {
            if (modelRegistration.returnString().equals("true")) {
                UserRegistrationDB.exitConnection();
                UserRegistrationDB.nullConnection();
                req.setAttribute("UserAddTrue", true);
            } else if (modelRegistration.returnString().equals("false"))
                req.setAttribute("UserAddError", true);
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/RegistrationForm.jsp");
        requestDispatcher.forward(req, resp);
        req.removeAttribute("UserAddTrue");
        req.removeAttribute("UserAddError");
        req.removeAttribute("languageEnglish");
        req.removeAttribute("languageUkraine");
        ModelRegistration.delete();
        ModelLanguageRegistration.delete();
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
            UserRegistration reg = UserRegistrationDB.registrationDB(firstName, lastName, email, login, password);
            model.add(reg.getCheckRegistration());

            resp.sendRedirect("/exhibition/reg");
        } else if (req.getParameter("autorizedButton") != null) {
            UserRegistrationDB.exitConnection();
            UserAutorizationDB.nullConnection();
            resp.sendRedirect("/exhibition/auto");
        } else if (req.getParameter("englishButton") != null) {
            ModelLanguageRegistration modelLanguageRegistration = ModelLanguageRegistration.getInstance();
            modelLanguageRegistration.add("en");
            resp.sendRedirect("/exhibition/reg");
        } else if (req.getParameter("ukraineButton") != null) {
            ModelLanguageRegistration modelLanguageRegistration = ModelLanguageRegistration.getInstance();
            modelLanguageRegistration.add("ua");
            resp.sendRedirect("/exhibition/reg");
        }
    }
}
