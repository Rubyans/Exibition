package app.controller.servlets.user.secondPage;

import app.DAO.connectionDAO.HikariConnectDB;
import app.DAO.sqlFunctions.user.secondPage.SecondPageDB;
import app.DAO.entities.userEntities.secondPage.UserShowExhibition;
import app.models.userModels.secondPage.ModelLanguageUserSecond;
import app.models.userModels.secondPage.ModelShowExhibition;
import app.service.changeLanguage.ChangeLanguage;

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
            resp.sendRedirect("/exhibition/auto");
        } else {
            ModelShowExhibition modelShowExhibition = ModelShowExhibition.getInstance();
            ModelLanguageUserSecond modelLanguageUserSecond = ModelLanguageUserSecond.getInstance();

            if (modelLanguageUserSecond.modelCheck() != null) {
                if (modelLanguageUserSecond.modelCheck().equals("en")) {
                    req.getSession().setAttribute("language", "en");
                }
                if (modelLanguageUserSecond.modelCheck().equals("ua")) {
                    req.getSession().setAttribute("language", "ua");
                }
            }

            if (req.getSession().getAttribute("language") != null) {
                if (req.getSession().getAttribute("language").equals("en")) {
                    req.setAttribute("languageChange", ChangeLanguage.changeEN("language.properties","userSecond"));
                } else if (req.getSession().getAttribute("language").equals("ua")) {
                    req.setAttribute("languageChange", ChangeLanguage.changeUA("language.properties","userSecond"));
                }
            } else {
                req.setAttribute("languageChange", ChangeLanguage.changeUA("language.properties", "userSecond"));
            }

            if (modelShowExhibition.listShow() != null) {
                if (modelShowExhibition.checkNull() == true) {
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
            req.removeAttribute("languageChange");
            ModelShowExhibition.delete();
            ModelLanguageUserSecond.delete();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("updateButton") != null) {
            ModelShowExhibition modelShowExhibition = ModelShowExhibition.getInstance();
            try {
                String userId = (String) req.getSession().getAttribute("UserId");
                for (UserShowExhibition userShow : SecondPageDB.showExhibition(userId)) {
                    modelShowExhibition.add(userShow);
                }
            } catch (Exception e) {
                modelShowExhibition.add(null);
            }
            resp.sendRedirect("/exhibition/userexhibition");
        } else if (req.getParameter("exitButton") != null) {
            HikariConnectDB.exitConnection();
            req.getSession().removeAttribute("UserRole");
            resp.sendRedirect("/exhibition/auto");
        } else if (req.getParameter("UserPagination") != null) {
            resp.sendRedirect("/exhibition/user");
        } else if (req.getParameter("UserExhibitionPagination") != null) {
            resp.sendRedirect("/exhibition/userexhibition");
        } else if (req.getParameter("englishButton") != null) {
            ModelLanguageUserSecond modelLanguageUserSecond = ModelLanguageUserSecond.getInstance();
            modelLanguageUserSecond.add("en");
            resp.sendRedirect("/exhibition/userexhibition");
        } else if (req.getParameter("ukraineButton") != null) {
            ModelLanguageUserSecond modelLanguageUserSecond = ModelLanguageUserSecond.getInstance();
            modelLanguageUserSecond.add("ua");
            resp.sendRedirect("/exhibition/userexhibition");
        }
    }
}
