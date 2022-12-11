package app.servlets;

import app.database.UserGuestDB;
import app.entities.UserGuest;
import app.model.ModelGuest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GuestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        if (UserGuestDB.checkConnection() == null)
            UserGuestDB.startConnnection();

        ModelGuest model = ModelGuest.getInstance();

        if (model.listUserGuest() != null) {
            if (model.checkNull() == true) {
                UserGuestDB.nullConnection();
                req.setAttribute("Error", true);
            } else {
                List<UserGuest> names = model.listUserGuest();
                req.setAttribute("GuestList", names);
                req.setAttribute("Error", false);
            }

        }
        req.getRequestDispatcher("views/GuestForm.jsp").forward(req, resp);
        req.removeAttribute("GuestList");
        req.removeAttribute("Error");
        ModelGuest.delete();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (UserGuestDB.checkConnection() == null)
            UserGuestDB.startConnnection();


        if (req.getParameter("autoButton") != null) {
            UserGuestDB.exitConnection();
            UserGuestDB.nullConnection();
            resp.sendRedirect("/exhibition/");
        } else if (req.getParameter("updateButton") != null) {
            ModelGuest model = ModelGuest.getInstance();
            try {
                for (UserGuest guest : UserGuestDB.authorizationUser()) {
                    model.add(guest);
                }
            } catch (Exception e) {
                model.add(null);
            }

            resp.sendRedirect("/exhibition/guest");
        }
    }

}
