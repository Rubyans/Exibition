package app.controllerFront.commands.admin.seventhPage;


import app.DAO.connectionDAO.HikariConnectDB;
import app.DAO.entities.adminEntities.seventhPage.UserAutorizedShow;
import app.DAO.sqlFunctions.admin.seventhPage.SeventhPageDB;
import app.controllerFront.commands.interfaceCommand.Command;
import app.controllerFront.models.adminModels.seventhPage.*;
import app.service.changeLanguage.ChangeLanguage;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SeventhPageCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(SeventhPageCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, int request) throws ServletException, IOException {
        if (request == 1) {
/////////////////////////////////////GET-Request/////////////////////////////////////////////////////////////////
            ModelShowUserAutorized modelShowUserAutorized = ModelShowUserAutorized.getInstance();
            ModelAddUserAutorized modelAddUserAutorized = ModelAddUserAutorized.getInstance();
            ModelAddAmountAutorized modelAddAmountAutorized = ModelAddAmountAutorized.getInstance();
            ModelDelUserAutorized modelDelUserAutorized = ModelDelUserAutorized.getInstance();
            ModelChangeAccess modelChangeAccess = ModelChangeAccess.getInstance();
            if (req.getSession().getAttribute("language") != null) {
                if (req.getSession().getAttribute("language").equals("en")) {
                    req.setAttribute("languageChange", ChangeLanguage.changeEN("languageEN.properties", "adminSeventh"));
                } else if (req.getSession().getAttribute("language").equals("ua")) {
                    req.setAttribute("languageChange", ChangeLanguage.changeUA("languageUA.properties", "adminSeventh"));
                }
            } else {
                req.setAttribute("languageChange", ChangeLanguage.changeUA("languageUA.properties", "adminSeventh"));
            }

            if (modelShowUserAutorized.listShow() != null) {
                if (modelShowUserAutorized.checkNull() == true) {
                    req.setAttribute("Error", true);
                } else {
                    List<UserAutorizedShow> user = modelShowUserAutorized.listShow();
                    req.setAttribute("SeventhPageShow", user);
                }
            } else if (modelAddUserAutorized.modelCheck() != null) {
                if (modelAddUserAutorized.modelCheck().equals("false"))
                    req.setAttribute("AddError", true);
                else if (modelAddUserAutorized.modelCheck().equals("true"))
                    req.setAttribute("TrueAdd", true);
            } else if (modelChangeAccess.modelCheck() != null) {
                if (modelChangeAccess.modelCheck().equals("false"))
                    req.setAttribute("ChangeError", true);
                else if (modelChangeAccess.modelCheck().equals("true"))
                    req.setAttribute("TrueChange", true);
            } else if (modelAddAmountAutorized.modelCheck() != null) {
                if (modelAddAmountAutorized.modelCheck().equals("true"))
                    req.setAttribute("AddAmount", true);
                else if (modelAddAmountAutorized.modelCheck().equals("false"))
                    req.setAttribute("AddAmountError", true);
            } else if (modelDelUserAutorized.modelCheck() != null) {
                if (modelDelUserAutorized.modelCheck().equals("false"))
                    req.setAttribute("DelError", true);
                else if (modelDelUserAutorized.modelCheck().equals("true"))
                    req.setAttribute("TrueDel", true);
            }
            req.getRequestDispatcher("views/adminMenu/seventhPage/AdminSeventhMenu.jsp").forward(req, resp);
            req.removeAttribute("SeventhPageShow");
            req.removeAttribute("Error");
            req.removeAttribute("AddError");
            req.removeAttribute("AddAmount");
            req.removeAttribute("AddAmountError");
            req.removeAttribute("DelError");
            req.removeAttribute("TrueDel");
            req.removeAttribute("TrueAdd");
            req.removeAttribute("languageChange");
            req.removeAttribute("ChangeError");
            req.removeAttribute("TrueChange");
            ModelShowUserAutorized.delete();
            ModelAddUserAutorized.delete();
            ModelDelUserAutorized.delete();
            ModelAddAmountAutorized.delete();
            ModelChangeAccess.delete();
        } else {
/////////////////////////////////////Post-Request///////////////////////////////////////////////////////////////
            if (req.getParameter("updateButton") != null) {
                ModelShowUserAutorized modelShowUserAutorized = ModelShowUserAutorized.getInstance();
                try {
                    String valueRows=req.getParameter("UpdateSort");
                    for (UserAutorizedShow userAuto : SeventhPageDB.userAuto(valueRows))
                        modelShowUserAutorized.add(userAuto);
                    LOGGER.debug("doPost in debug");
                } catch (Exception e) {
                    modelShowUserAutorized.add(null);
                    LOGGER.error("doPost " + e.getMessage());
                }
                resp.sendRedirect("exhibition?command=userautorized");
            } else if (req.getParameter("addButtonAuto") != null) {

                Double amount = 0.0;
                String fistName = req.getParameter("firstName");
                String lastName = req.getParameter("lastName");
                String login = req.getParameter("login");
                String password = req.getParameter("password");
                String email = req.getParameter("email");
                if (req.getParameter("amount") != "")
                    amount = Double.valueOf(req.getParameter("amount"));
                String role = req.getParameter("role");
                ModelAddUserAutorized modelAddUserAutorized = ModelAddUserAutorized.getInstance();
                boolean checkDel = SeventhPageDB.userAutoAdd(fistName, lastName, login, password, email, amount, role);
                if (checkDel == true)
                    modelAddUserAutorized.add(true);
                else
                    modelAddUserAutorized.add(false);
                resp.sendRedirect("exhibition?command=userautorized");
            } else if (req.getParameter("delButtonAuto") != null) {
                ModelDelUserAutorized modelDelUserAutorized = ModelDelUserAutorized.getInstance();
                String email = req.getParameter("autoDel");
                boolean checkDel = SeventhPageDB.userAutoDel(email);
                if (checkDel == true)
                    modelDelUserAutorized.add(true);
                else
                    modelDelUserAutorized.add(false);
                resp.sendRedirect("exhibition?command=userautorized");
            } else if (req.getParameter("ButtonMoney") != null) {
                String email = req.getParameter("emailMoney");
                Double money = Double.valueOf(req.getParameter("money"));
                ModelAddAmountAutorized modelAddAmountAutorized = ModelAddAmountAutorized.getInstance();
                boolean checkAmount = SeventhPageDB.userAmountAdd(email, money);
                if (checkAmount == true)
                    modelAddAmountAutorized.add(true);
                else
                    modelAddAmountAutorized.add(false);
                resp.sendRedirect("exhibition?command=userautorized");
            } else if (req.getParameter("accessButtonServlet") != null) {
                String access = req.getParameter("access");
                String emailUser = req.getParameter("emailExhibitionAccess");
                ModelChangeAccess modelChangeAccess = ModelChangeAccess.getInstance();
                boolean checkAccess = SeventhPageDB.accessFirstPage(emailUser, access);
                if (checkAccess == true)
                    modelChangeAccess.add(true);
                else
                    modelChangeAccess.add(false);
                resp.sendRedirect("exhibition?command=userautorized");
            } else if (req.getParameter("roleBackButton") != null) {
                HikariConnectDB.roleBackCommit();
                resp.sendRedirect("exhibition?command=userautorized");
            } else if (req.getParameter("saveButton") != null) {
                HikariConnectDB.saveCommit();
                resp.sendRedirect("exhibition?command=userautorized");
            }
        }
    }

}
