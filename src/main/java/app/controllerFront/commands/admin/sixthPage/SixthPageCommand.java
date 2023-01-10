package app.controllerFront.commands.admin.sixthPage;


import app.DAO.connectionDAO.HikariConnectDB;
import app.DAO.entities.adminEntities.sixthPage.ViewShow;
import app.DAO.sqlFunctions.admin.sixthPage.SixthPageDB;
import app.controllerFront.commands.interfaceCommand.Command;
import app.controllerFront.models.adminModels.sixthPage.*;
import app.service.changeLanguage.ChangeLanguage;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SixthPageCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(SixthPageCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, int request) throws ServletException, IOException {
        if (request == 1) {
/////////////////////////////////////GET-Request/////////////////////////////////////////////////////////////////
            ModelShowView modelShowView = ModelShowView.getInstance();
            ModelAddView modelAddView = ModelAddView.getInstance();
            ModelDelView modelDelView = ModelDelView.getInstance();

            if (req.getSession().getAttribute("language") != null) {
                if (req.getSession().getAttribute("language").equals("en")) {
                    req.setAttribute("languageChange", ChangeLanguage.changeEN("languageEN.properties", "adminSixth"));
                } else if (req.getSession().getAttribute("language").equals("ua")) {
                    req.setAttribute("languageChange", ChangeLanguage.changeUA("languageUA.properties", "adminSixth"));
                }
            } else {
                req.setAttribute("languageChange", ChangeLanguage.changeUA("languageUA.properties", "adminSixth"));
            }

            if (modelShowView.listShow() != null) {
                if (modelShowView.checkNull() == true) {
                    req.setAttribute("Error", true);
                } else {
                    List<ViewShow> art = modelShowView.listShow();
                    req.setAttribute("SixthPageShow", art);
                }
            } else if (modelAddView.modelCheck() != null) {
                if (modelAddView.modelCheck().equals("false"))
                    req.setAttribute("AddError", true);
                else if (modelAddView.modelCheck().equals("true"))
                    req.setAttribute("TrueAdd", true);
            } else if (modelDelView.modelCheck() != null) {
                if (modelDelView.modelCheck().equals("false"))
                    req.setAttribute("DelError", true);
                else if (modelDelView.modelCheck().equals("true"))
                    req.setAttribute("TrueDel", true);
            }
            req.getRequestDispatcher("views/adminMenu/sixthPage/AdminSixthMenu.jsp").forward(req, resp);
            req.removeAttribute("SixthPageShow");
            req.removeAttribute("Error");
            req.removeAttribute("AddError");
            req.removeAttribute("DelError");
            req.removeAttribute("TrueDel");
            req.removeAttribute("TrueAdd");
            req.removeAttribute("languageChange");
            ModelShowView.delete();
            ModelAddView.delete();
            ModelDelView.delete();
        } else {
/////////////////////////////////////Post-Request///////////////////////////////////////////////////////////////
            if (req.getParameter("updateButton") != null) {
                ModelShowView modelShowView = ModelShowView.getInstance();
                try {
                    String valueRows = req.getParameter("UpdateSort");
                    for (ViewShow view : SixthPageDB.viewShow(valueRows))
                        modelShowView.add(view);
                    LOGGER.debug("doPost in debug");
                } catch (Exception e) {
                    modelShowView.add(null);
                    LOGGER.debug("doPost " + e.getMessage());
                }
                resp.sendRedirect("exhibition?command=adminview");

            } else if (req.getParameter("addButtonView") != null) {
                String name = req.getParameter("NameView");
                ModelAddView modelAddView = ModelAddView.getInstance();
                boolean checkDel = SixthPageDB.viewAdd(name);
                if (checkDel == true)
                    modelAddView.add(true);
                else
                    modelAddView.add(false);
                resp.sendRedirect("exhibition?command=adminview");

            } else if (req.getParameter("delButtonView") != null) {
                ModelDelView modelDelView = ModelDelView.getInstance();
                String name = req.getParameter("viewDel");
                boolean checkDel = SixthPageDB.viewDel(name);
                if (checkDel == true)
                    modelDelView.add(true);
                else
                    modelDelView.add(false);
                resp.sendRedirect("exhibition?command=adminview");
            } else if (req.getParameter("roleBackButton") != null) {
                HikariConnectDB.roleBackCommit();
                resp.sendRedirect("exhibition?command=adminview");
            } else if (req.getParameter("saveButton") != null) {
                HikariConnectDB.saveCommit();
                resp.sendRedirect("exhibition?command=adminview");
            }
        }
    }

}
