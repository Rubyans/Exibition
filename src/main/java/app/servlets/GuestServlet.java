package app.servlets;

import app.database.GuestDB;
import app.database.UserDB;
import app.entities.UserGuest;
import app.model.ModelGuest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GuestServlet extends HttpServlet {
    private static int count;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        ModelGuest model = ModelGuest.getInstance();

        if (model.listUserGuest() != null)
        {
            if(model.checkNull()==true)
            {
                System.out.println("nuulll");
                req.setAttribute("Error",true);

            }
            else {

                List<UserGuest> names = model.listUserGuest();
                req.setAttribute("GuestList", names);
                req.setAttribute("Error",false);
                System.out.println("not null");
            }

        }
        req.getRequestDispatcher("views/GuestForm.jsp").forward(req, resp);
        req.removeAttribute("GuestList");
        req.removeAttribute("Error");
        count++;
        ModelGuest.delete();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String updateButton = req.getParameter("updateButton");
        ModelGuest.delete();
        if (req.getParameter("autoButton") != null) {
            resp.sendRedirect("/exibition/");
        } else if (updateButton != null)
        {
            ModelGuest model = ModelGuest.getInstance();
            try {
                for (UserGuest guest : GuestDB.authorizationuser()) {
                    model.add(guest);
                }
            }
            catch (Exception e)
            {
                model.add(null);
            }

            resp.sendRedirect("/exibition/guest");
        }
    }

}
