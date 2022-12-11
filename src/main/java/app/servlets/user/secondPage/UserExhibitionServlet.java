package app.servlets.user.secondPage;

import app.database.user.secondPage.SecondPageDB;
import app.entities.userEntities.secondPage.UserShowExhibition;
import app.model.userModels.secondPage.ModelShowExhibition;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserExhibitionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("UserRole") == null) {
            resp.sendRedirect("/exhibition/");
        } else {
            ModelShowExhibition modelShowExhibition = ModelShowExhibition.getInstance();

            if (SecondPageDB.checkConnection() == null)
                SecondPageDB.startConnnection();

            if (modelShowExhibition.listShow() != null) {
                if (modelShowExhibition.checkNull() == true) {
                    SecondPageDB.nullConnection();
                    req.setAttribute("Error", true);
                } else {
                    List<UserShowExhibition> names = modelShowExhibition.listShow();
                    req.setAttribute("SecondPage", names);
                    req.setAttribute("Error", false);
                }
            }
            req.getRequestDispatcher("views/userMenu/secondPage/UserSecondMenu.jsp").forward(req, resp);
            req.removeAttribute("SecondPage");
            req.removeAttribute("Error");
            ModelShowExhibition.delete();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (SecondPageDB.checkConnection() == null)
            SecondPageDB.startConnnection();

        if (req.getParameter("updateButton") != null) {
            ModelShowExhibition modelShowExhibition = ModelShowExhibition.getInstance();
            try {
                String userId = (String) req.getSession().getAttribute("UserId");
                for (UserShowExhibition userShow : SecondPageDB.ShowExhibition(userId)) {
                    modelShowExhibition.add(userShow);
                }
            } catch (Exception e) {
                modelShowExhibition.add(null);
            }
            resp.sendRedirect("/exhibition/userexhibition");
        } else if (req.getParameter("exitButton") != null) {
            SecondPageDB.exitConnection();
            SecondPageDB.nullConnection();
            req.getSession().removeAttribute("UserRole");
            resp.sendRedirect("/exhibition/userexhibition");
        }
        else if (req.getParameter("UserPagination") != null) {
            SecondPageDB.exitConnection();
            SecondPageDB.nullConnection();
            resp.sendRedirect("/exhibition/user");
        }
        else if (req.getParameter("UserExhibitionPagination") != null) {
            SecondPageDB.exitConnection();
            SecondPageDB.nullConnection();
            resp.sendRedirect("/exhibition/userexhibition");
        }
    }
}
