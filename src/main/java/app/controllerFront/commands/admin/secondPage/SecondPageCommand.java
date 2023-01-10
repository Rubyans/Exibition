package app.controllerFront.commands.admin.secondPage;


import app.DAO.connectionDAO.HikariConnectDB;
import app.DAO.entities.adminEntities.secondPage.HallShow;
import app.DAO.sqlFunctions.admin.secondPage.SecondPageDB;
import app.controllerFront.commands.interfaceCommand.Command;
import app.controllerFront.models.adminModels.secondPage.*;
import app.service.changeLanguage.ChangeLanguage;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SecondPageCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(SecondPageCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, int request) throws ServletException, IOException {
        if (request == 1) {
/////////////////////////////////////GET-Request/////////////////////////////////////////////////////////////////
            ModelShowHall showHall = ModelShowHall.getInstance();
            ModelAddHall modelAddHall = ModelAddHall.getInstance();
            ModelDelHall modelDelHall = ModelDelHall.getInstance();
            if (req.getSession().getAttribute("language") != null) {
                if (req.getSession().getAttribute("language").equals("en")) {
                    req.setAttribute("languageChange", ChangeLanguage.changeEN("languageEN.properties", "adminSecond"));
                } else if (req.getSession().getAttribute("language").equals("ua")) {
                    req.setAttribute("languageChange", ChangeLanguage.changeUA("languageUA.properties", "adminSecond"));
                }
            } else {
                req.setAttribute("languageChange", ChangeLanguage.changeUA("languageUA.properties", "adminSecond"));
            }
            if (showHall.listShow() != null) {
                if (showHall.checkNull() == true) {
                    req.setAttribute("Error", true);
                } else {
                    List<HallShow> names = showHall.listShow();
                    req.setAttribute("SecondPageShow", names);
                }
            } else if (modelAddHall.modelCheck() != null) {
                if (modelAddHall.modelCheck().equals("false"))
                    req.setAttribute("AddError", true);
                else if (modelAddHall.modelCheck().equals("true"))
                    req.setAttribute("TrueAdd", true);

            } else if (modelDelHall.modelCheck() != null) {
                if (modelDelHall.modelCheck().equals("false"))
                    req.setAttribute("DelError", true);
                else if (modelDelHall.modelCheck().equals("true"))
                    req.setAttribute("TrueDel", true);
            }

            req.getRequestDispatcher("views/adminMenu/secondPage/AdminSecondMenu.jsp").forward(req, resp);
            req.removeAttribute("SecondPageShow");
            req.removeAttribute("Error");
            req.removeAttribute("AddError");
            req.removeAttribute("DelError");
            req.removeAttribute("TrueDel");
            req.removeAttribute("TrueAdd");
            req.removeAttribute("languageChange");
            ModelShowHall.delete();
            ModelAddHall.delete();
            ModelDelHall.delete();
        } else {
/////////////////////////////////////Post-Request///////////////////////////////////////////////////////////////
            if (req.getParameter("updateButton") != null) {
                ModelShowHall showHall = ModelShowHall.getInstance();
                try {
                    String valueRows=req.getParameter("UpdateSort");
                    for (HallShow hall : SecondPageDB.hallShow(valueRows))
                        showHall.add(hall);
                    LOGGER.debug("dePost in debug");
                } catch (Exception e) {
                    showHall.add(null);
                    LOGGER.debug("dePost " + e.getMessage());
                }
                resp.sendRedirect("exhibition?command=adminhall");
            } else if (req.getParameter("addButtonHall") != null) {
                String nameHall = req.getParameter("nameHall");
                String square = req.getParameter("square");
                ModelAddHall modelAddHall = ModelAddHall.getInstance();
                boolean checkDel = SecondPageDB.hallAdd(nameHall, Double.valueOf(square));
                if (checkDel == true)
                    modelAddHall.add(true);
                else
                    modelAddHall.add(false);
                resp.sendRedirect("exhibition?command=adminhall");
            } else if (req.getParameter("delButtonHall") != null) {

                String nameHall = req.getParameter("nameDel");
                ModelDelHall modelDelHall = ModelDelHall.getInstance();
                boolean checkDel = SecondPageDB.hallDel(nameHall);
                if (checkDel == true) {
                    modelDelHall.add(true);
                } else
                    modelDelHall.add(false);
                resp.sendRedirect("exhibition?command=adminhall");
            } else if (req.getParameter("roleBackButton") != null) {
                HikariConnectDB.roleBackCommit();
                resp.sendRedirect("exhibition?command=adminhall");
            } else if (req.getParameter("saveButton") != null) {
                HikariConnectDB.saveCommit();
                resp.sendRedirect("exhibition?command=adminhall");
            }
        }
    }

}
