package app.controllerFront.commands;

import app.DAO.connectionDAO.HikariConnectDB;
import app.DAO.entities.UserRegistration;
import app.DAO.sqlFunctions.UserRegistrationDB;
import app.controllerFront.commands.interfaceCommand.Command;
import app.controllerFront.models.registrationModels.ModelLanguageRegistration;
import app.controllerFront.models.registrationModels.ModelRegistration;
import app.service.changeLanguage.ChangeLanguage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommand implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, int request) throws ServletException, IOException {
        if (request == 1) {
/////////////////////////////////////GET-Request/////////////////////////////////////////////////////////////////
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
                    req.setAttribute("languageChange", ChangeLanguage.changeEN("language.properties", "registration"));
                } else if (req.getSession().getAttribute("language").equals("ua")) {
                    req.setAttribute("languageChange", ChangeLanguage.changeUA("language.properties", "registration"));
                }
            } else {
                req.setAttribute("languageChange", ChangeLanguage.changeUA("language.properties", "registration"));
            }

            if (modelRegistration.checkString() != null) {
                if (modelRegistration.returnString().equals("true")) {
                    req.setAttribute("UserAddTrue", true);
                } else if (modelRegistration.returnString().equals("false"))
                    req.setAttribute("UserAddError", true);
            }
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/RegistrationForm.jsp");
            requestDispatcher.forward(req, resp);
            req.removeAttribute("UserAddTrue");
            req.removeAttribute("UserAddError");
            req.removeAttribute("languageChange");
            ModelRegistration.delete();
            ModelLanguageRegistration.delete();
        } else {
/////////////////////////////////////Post-Request///////////////////////////////////////////////////////////////
            if (req.getParameter("registrationButton") != null) {
                String firstName = req.getParameter("firstName");
                String lastName = req.getParameter("lastName");
                String email = req.getParameter("email");
                String login = req.getParameter("login");
                String password = req.getParameter("password");

                if (HikariConnectDB.checkConnection() == false) {
                    HikariConnectDB object = new HikariConnectDB();
                }
                ModelRegistration model = ModelRegistration.getInstance();
                UserRegistration reg = UserRegistrationDB.registrationDB(firstName, lastName, email, login, password);
                model.add(reg.getCheckRegistration());

                resp.sendRedirect("exhibition?command=reg");
            } else if (req.getParameter("autorizedButton") != null) {
                resp.sendRedirect("exhibition?command=auto");
            } else if (req.getParameter("englishButton") != null) {
                ModelLanguageRegistration modelLanguageRegistration = ModelLanguageRegistration.getInstance();
                modelLanguageRegistration.add("en");
                resp.sendRedirect("exhibition?command=reg");
            } else if (req.getParameter("ukraineButton") != null) {
                ModelLanguageRegistration modelLanguageRegistration = ModelLanguageRegistration.getInstance();
                modelLanguageRegistration.add("ua");
                resp.sendRedirect("exhibition?command=reg");
            }
        }
    }
}
