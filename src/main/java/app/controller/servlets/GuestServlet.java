package app.controller.servlets;

import app.DAO.connectionDAO.HikariConnectDB;
import app.DAO.sqlFunctions.UserGuestDB;
import app.DAO.entities.UserGuest;
import app.models.guestModels.ModelGuest;
import app.models.guestModels.ModelLanguageGuest;
import app.service.changeLanguage.ChangeLanguage;
import app.service.sorting.SortGuest;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GuestServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(GuestServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ModelGuest modelGuest = ModelGuest.getInstance();
        ModelLanguageGuest modelLanguageGuest = ModelLanguageGuest.getInstance();

        if (modelLanguageGuest.modelCheck() != null) {
            if (modelLanguageGuest.modelCheck().equals("en")) {
                req.getSession().setAttribute("language", "en");
            }
            if (modelLanguageGuest.modelCheck().equals("ua")) {
                req.getSession().setAttribute("language", "ua");
            }
        }

        if (req.getSession().getAttribute("language") != null) {
            if (req.getSession().getAttribute("language").equals("en")) {
                req.setAttribute("languageChange", ChangeLanguage.changeEN("language.properties","guest"));
            } else if (req.getSession().getAttribute("language").equals("ua")) {
                req.setAttribute("languageChange", ChangeLanguage.changeUA("language.properties","guest"));
            }
        } else {
            req.setAttribute("languageChange", ChangeLanguage.changeUA("language.properties","guest"));
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
        ModelLanguageGuest.delete();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (HikariConnectDB.checkConnection() == false) {
            HikariConnectDB object = new HikariConnectDB();
        }

        if (req.getParameter("autoButton") != null) {
            resp.sendRedirect("/exhibition/auto");
        } else if (req.getParameter("updateButton") != null) {
            ModelGuest modelGuest = ModelGuest.getInstance();
            try {
                for (UserGuest guest : UserGuestDB.authorizationUser()) {
                    modelGuest.add(guest);
                }
                LOGGER.debug("doPost in debug");
            } catch (Exception e) {
                modelGuest.add(null);
                LOGGER.debug("doPost " + e.getMessage());
            }
            resp.sendRedirect("/exhibition/guest");
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
            resp.sendRedirect("/exhibition/guest");
        } else if (req.getParameter("englishButton") != null) {
            ModelLanguageGuest modelLanguageGuest = ModelLanguageGuest.getInstance();
            modelLanguageGuest.add("en");
            resp.sendRedirect("/exhibition/guest");
        } else if (req.getParameter("ukraineButton") != null) {
            ModelLanguageGuest modelLanguageGuest = ModelLanguageGuest.getInstance();
            modelLanguageGuest.add("ua");
            resp.sendRedirect("/exhibition/guest");
        }
    }

}
