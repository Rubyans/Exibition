package app.controllerFront.commands;


import app.DAO.connectionDAO.HikariConnectDB;
import app.DAO.entities.UserGuest;
import app.DAO.sqlFunctions.UserGuestDB;
import app.controllerFront.commands.interfaceCommand.Command;
import app.controllerFront.models.guestModels.ModelGuest;
import app.service.changeLanguage.ChangeLanguage;
import app.service.sorting.SortGuest;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class GuestCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(GuestCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, int request) throws ServletException, IOException {

        if (request == 1) {
/////////////////////////////////////GET-Request/////////////////////////////////////////////////////////////////
            ModelGuest modelGuest = ModelGuest.getInstance();

            if (req.getSession().getAttribute("language") != null) {
                if (req.getSession().getAttribute("language").equals("en")) {
                    req.setAttribute("languageChange", ChangeLanguage.changeEN("languageEN.properties", "guest"));
                } else if (req.getSession().getAttribute("language").equals("ua")) {
                    req.setAttribute("languageChange", ChangeLanguage.changeUA("languageUA.properties", "guest"));
                }
            } else {
                req.setAttribute("languageChange", ChangeLanguage.changeUA("languageUA.properties", "guest"));
            }

            if (modelGuest.listUserGuest() != null) {
                if (modelGuest.checkNull() == true) {
                    req.setAttribute("Error", true);
                } else {
                    List<UserGuest> names = modelGuest.listUserGuest();
                    req.setAttribute("GuestList", names);
                    req.setAttribute("Error", false);
                }

            }
            req.getRequestDispatcher("views/GuestForm.jsp").forward(req, resp);
            req.removeAttribute("GuestList");
            req.removeAttribute("Error");
            req.removeAttribute("languageChange");
            ModelGuest.delete();
        } else {
/////////////////////////////////////Post-Request/////////////////////////////////////////////////////////////////
            if (HikariConnectDB.checkConnection() == false) {
                HikariConnectDB object = new HikariConnectDB();
            }

            if (req.getParameter("autoButton") != null) {
                resp.sendRedirect("exhibition?command=auto");
            } else if (req.getParameter("updateButton") != null) {
                ModelGuest modelGuest = ModelGuest.getInstance();
                try {
                    String valueRows=req.getParameter("UpdateSort");
                    SortGuest.setValueRows(valueRows);
                    for (UserGuest guest : UserGuestDB.authorizationUser(valueRows)) {
                        modelGuest.add(guest);
                    }
                    LOGGER.debug("doPost in debug");
                } catch (Exception e) {
                    modelGuest.add(null);
                    LOGGER.debug("doPost " + e.getMessage());
                }

                resp.sendRedirect("exhibition?command=guest");
            } else if (req.getParameter("sortButton") != null) {
                ModelGuest modelGuest = ModelGuest.getInstance();
                try {
                    if (req.getParameter("SelectSort").equals("1")) {
                        for (UserGuest guest : SortGuest.sortAscending()) {
                            modelGuest.add(guest);
                        }
                    } else {
                        for (UserGuest guest : SortGuest.sortDescending()) {
                            modelGuest.add(guest);
                        }
                    }
                    LOGGER.debug("doPostSort in debug");
                } catch (Exception e) {
                    modelGuest.add(null);
                    LOGGER.debug("doPost " + e.getMessage());
                }
                resp.sendRedirect("exhibition?command=guest");
            }
        }
    }
}
