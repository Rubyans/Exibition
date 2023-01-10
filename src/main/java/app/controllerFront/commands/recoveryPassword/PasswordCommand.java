package app.controllerFront.commands.recoveryPassword;

import app.DAO.sqlFunctions.recoveryPassword.RecoveryUserDB;
import app.controllerFront.commands.interfaceCommand.Command;
import app.controllerFront.models.recoveryModels.ModelRecoveryPassword;
import app.service.changeLanguage.ChangeLanguage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PasswordCommand implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, int request) throws ServletException, IOException {
/////////////////////////////////////GET-Request/////////////////////////////////////////////////////////////////
        if (request == 1) {

            ModelRecoveryPassword modelRecoveryPassword = ModelRecoveryPassword.getInstance();

            if (req.getSession().getAttribute("language") != null) {
                if (req.getSession().getAttribute("language").equals("en")) {
                    req.setAttribute("languageChange", ChangeLanguage.changeEN("languageEN.properties", "recoveryPassword"));
                } else if (req.getSession().getAttribute("language").equals("ua")) {
                    req.setAttribute("languageChange", ChangeLanguage.changeUA("languageUA.properties", "recoveryPassword"));
                }
            } else {
                req.setAttribute("languageChange", ChangeLanguage.changeUA("languageUA.properties", "recoveryPassword"));
            }

            if (modelRecoveryPassword.modelCheck() != null) {
                if (modelRecoveryPassword.modelCheck().equals("true")) {
                    req.setAttribute("RecoveryChange", true);
                } else
                    req.setAttribute("Error", true);
            }
            req.getRequestDispatcher("views/recoveryPassword/PasswordForm.jsp").forward(req, resp);
            req.removeAttribute("RecoveryChange");
            req.removeAttribute("Error");
            req.removeAttribute("languageChange");
            ModelRecoveryPassword.delete();
        } else {
/////////////////////////////////////Post-Request/////////////////////////////////////////////////////////////////
            if (req.getParameter("confirmButton") != null) {
                String pin = req.getParameter("recoveryCode");
                String password = req.getParameter("confirmPassword");

                ModelRecoveryPassword modelRecoveryPassword = ModelRecoveryPassword.getInstance();
                boolean checkRes = RecoveryUserDB.recoveryPassword(pin, password);
                if (checkRes == true)
                    modelRecoveryPassword.add(true);
                else
                    modelRecoveryPassword.add(false);
                resp.sendRedirect("exhibition?command=recoverypassword");
            } else if (req.getParameter("autoButton") != null) {
                req.getSession().removeAttribute("recovery");
                resp.sendRedirect("exhibition?command=auto");
            }
        }
    }

}
