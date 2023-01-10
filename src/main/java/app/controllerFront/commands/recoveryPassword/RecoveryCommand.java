package app.controllerFront.commands.recoveryPassword;

import app.DAO.connectionDAO.HikariConnectDB;
import app.DAO.sqlFunctions.recoveryPassword.RecoveryUserDB;
import app.controllerFront.commands.interfaceCommand.Command;
import app.controllerFront.models.recoveryModels.ModelRecovery;
import app.service.changeLanguage.ChangeLanguage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RecoveryCommand implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, int request) throws ServletException, IOException {
/////////////////////////////////////GET-Request/////////////////////////////////////////////////////////////////
        if (request == 1) {

            ModelRecovery modelRecovery = ModelRecovery.getInstance();

            if (req.getSession().getAttribute("language") != null) {
                if (req.getSession().getAttribute("language").equals("en")) {
                    req.setAttribute("languageChange", ChangeLanguage.changeEN("languageEN.properties", "recovery"));
                } else if (req.getSession().getAttribute("language").equals("ua")) {
                    req.setAttribute("languageChange", ChangeLanguage.changeUA("languageUA.properties", "recovery"));
                }
            } else {
                req.setAttribute("languageChange", ChangeLanguage.changeUA("languageUA.properties", "recovery"));
            }


            if (modelRecovery.modelCheck() != null) {
                if (modelRecovery.modelCheck().equals("true")) {
                    req.setAttribute("RecoveryChange", true);
                    req.getSession().setAttribute("recovery", true);
                } else
                    req.setAttribute("Error", true);
            }
            req.getRequestDispatcher("views/recoveryPassword/RecoveryForm.jsp").forward(req, resp);
            req.removeAttribute("RecoveryChange");
            req.removeAttribute("Error");
            req.removeAttribute("languageChange");
            ModelRecovery.delete();
        } else {
/////////////////////////////////////Post-Request/////////////////////////////////////////////////////////////////
            if (req.getParameter("recoveryButton") != null) {
                String email = req.getParameter("recoveryEmail");

                if (HikariConnectDB.checkConnection() == false) {
                    HikariConnectDB object = new HikariConnectDB();
                }

                ModelRecovery modelRecovery = ModelRecovery.getInstance();
                boolean checkRes = RecoveryUserDB.recoveryUser(email);
                if (checkRes == true)
                    modelRecovery.add(true);
                else
                    modelRecovery.add(false);
                resp.sendRedirect("exhibition?command=recovery");
            } else if (req.getParameter("autoButton") != null) {
                resp.sendRedirect("exhibition?command=auto");
            }
        }
    }

}
