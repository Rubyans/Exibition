package app.servlets;

import app.database.UserGuestDB;
import app.entities.UserGuest;
import app.model.guestModels.ModelGuest;
import app.model.guestModels.ModelLanguageGuest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GuestServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(GuestServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (UserGuestDB.checkConnection() == null)
            UserGuestDB.startConnnection();

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
                req.setAttribute("languageEnglish", true);
            } else if (req.getSession().getAttribute("language").equals("ua")) {
                req.setAttribute("languageUkraine", true);
            }
        }

        if (modelGuest.listUserGuest() != null) {
            if (modelGuest.checkNull() == true) {
                UserGuestDB.nullConnection();
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
        req.removeAttribute("languageEnglish");
        req.removeAttribute("languageUkraine");
        ModelGuest.delete();
        ModelLanguageGuest.delete();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (UserGuestDB.checkConnection() == null)
            UserGuestDB.startConnnection();


        if (req.getParameter("autoButton") != null) {
            UserGuestDB.exitConnection();
            UserGuestDB.nullConnection();
            resp.sendRedirect("/exhibition/auto");
        } else if (req.getParameter("updateButton") != null) {
            ModelGuest model = ModelGuest.getInstance();
            try {
                for (UserGuest guest : UserGuestDB.authorizationUser()) {
                    model.add(guest);
                }
                LOGGER.debug("doPost in debug");
            } catch (Exception e) {
                model.add(null);
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
