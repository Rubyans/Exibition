package app.controllerFront.commands.admin.fourthPage;


import app.DAO.connectionDAO.HikariConnectDB;
import app.DAO.entities.adminEntities.fourthPage.AuthorShow;
import app.DAO.sqlFunctions.admin.fourthPage.FourthPageDB;
import app.controllerFront.commands.interfaceCommand.Command;
import app.controllerFront.models.adminModels.fourthPage.*;
import app.service.changeLanguage.ChangeLanguage;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FourthPageCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(FourthPageCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, int request) throws ServletException, IOException {
        if (request == 1) {
/////////////////////////////////////GET-Request/////////////////////////////////////////////////////////////////
            ModelShowAuthor modelShowAuthor = ModelShowAuthor.getInstance();
            ModelAddAuthor modelAddAuthor = ModelAddAuthor.getInstance();
            ModelDelAuthor modelDelAuthor = ModelDelAuthor.getInstance();
            if (req.getSession().getAttribute("language") != null) {
                if (req.getSession().getAttribute("language").equals("en")) {
                    req.setAttribute("languageChange", ChangeLanguage.changeEN("languageEN.properties", "adminFourth"));
                } else if (req.getSession().getAttribute("language").equals("ua")) {
                    req.setAttribute("languageChange", ChangeLanguage.changeUA("languageUA.properties", "adminFourth"));
                }
            } else {
                req.setAttribute("languageChange", ChangeLanguage.changeUA("languageUA.properties", "adminFourth"));
            }

            if (modelShowAuthor.listShow() != null) {
                if (modelShowAuthor.checkNull() == true) {
                    req.setAttribute("Error", true);
                } else {
                    List<AuthorShow> address = modelShowAuthor.listShow();
                    req.setAttribute("FouthPageShow", address);
                }
            } else if (modelAddAuthor.modelCheck() != null) {
                if (modelAddAuthor.modelCheck().equals("false"))
                    req.setAttribute("AddError", true);
                else if (modelAddAuthor.modelCheck().equals("true"))
                    req.setAttribute("TrueAdd", true);
            } else if (modelDelAuthor.modelCheck() != null) {
                if (modelDelAuthor.modelCheck().equals("false"))
                    req.setAttribute("DelError", true);
                else if (modelDelAuthor.modelCheck().equals("true"))
                    req.setAttribute("TrueDel", true);
            }
            req.getRequestDispatcher("views/adminMenu/fourthPage/AdminFourthMenu.jsp").forward(req, resp);
            req.removeAttribute("FouthPageShow");
            req.removeAttribute("Error");
            req.removeAttribute("AddError");
            req.removeAttribute("DelError");
            req.removeAttribute("TrueDel");
            req.removeAttribute("TrueAdd");
            req.removeAttribute("languageChange");
            ModelShowAuthor.delete();
            ModelAddAuthor.delete();
            ModelDelAuthor.delete();
        } else {
/////////////////////////////////////Post-Request///////////////////////////////////////////////////////////////
            if (req.getParameter("updateButton") != null) {
                ModelShowAuthor modelShowAuthor = ModelShowAuthor.getInstance();
                try {
                    String valueRows=req.getParameter("UpdateSort");
                    for (AuthorShow author : FourthPageDB.authorShow(valueRows))
                        modelShowAuthor.add(author);
                    LOGGER.debug("doPost in debug");
                } catch (Exception e) {
                    LOGGER.error("doPost " + e.getMessage());
                    modelShowAuthor.add(null);
                }
                resp.sendRedirect("exhibition?command=adminauthor");
            } else if (req.getParameter("addButtonAuthor") != null) {
                String firstName = req.getParameter("firstNameAuthor");
                String lastName = req.getParameter("lastNameAuthor");
                String email = req.getParameter("emailAuthor");

                ModelAddAuthor modelAddAuthor = ModelAddAuthor.getInstance();
                boolean checkDel = FourthPageDB.authorAdd(firstName, lastName, email);
                if (checkDel == true)
                    modelAddAuthor.add(true);
                else
                    modelAddAuthor.add(false);
                resp.sendRedirect("exhibition?command=adminauthor");
            } else if (req.getParameter("delButtonAuthor") != null) {

                ModelDelAuthor modelDelAuthor = ModelDelAuthor.getInstance();
                String email = req.getParameter("authorDel");
                boolean checkDel = FourthPageDB.authorDel(email);
                if (checkDel == true)
                    modelDelAuthor.add(true);
                else
                    modelDelAuthor.add(false);
                resp.sendRedirect("exhibition?command=adminauthor");
            } else if (req.getParameter("roleBackButton") != null) {
                HikariConnectDB.roleBackCommit();
                resp.sendRedirect("exhibition?command=adminauthor");
            } else if (req.getParameter("saveButton") != null) {
                HikariConnectDB.saveCommit();
                resp.sendRedirect("exhibition?command=adminauthor");
            }
        }
    }

}
