package app.controllerFront.commands.user.secondPage;

import app.DAO.connectionDAO.HikariConnectDB;
import app.DAO.entities.userEntities.secondPage.UserShowExhibition;
import app.DAO.sqlFunctions.user.secondPage.SecondPageDB;
import app.controllerFront.commands.interfaceCommand.Command;
import app.controllerFront.models.userModels.secondPage.ModelLanguageUserSecond;
import app.controllerFront.models.userModels.secondPage.ModelShowExhibition;
import app.service.changeLanguage.ChangeLanguage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserSecondCommand implements Command {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, int request) throws ServletException, IOException {
        if (request == 1) {
/////////////////////////////////////GET-Request/////////////////////////////////////////////////////////////////
            if (req.getSession().getAttribute("UserRole") == null) {
                resp.sendRedirect("exhibition?command=auto");
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
                        req.setAttribute("languageChange", ChangeLanguage.changeEN("language.properties", "userSecond"));
                    } else if (req.getSession().getAttribute("language").equals("ua")) {
                        req.setAttribute("languageChange", ChangeLanguage.changeUA("language.properties", "userSecond"));
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
        } else {
/////////////////////////////////////Post-Request///////////////////////////////////////////////////////////////
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
                resp.sendRedirect("exhibition?command=userexhibition");
            } else if (req.getParameter("exitButton") != null) {
                HikariConnectDB.exitConnection();
                req.getSession().removeAttribute("UserRole");
                resp.sendRedirect("exhibition?command=auto");
            } else if (req.getParameter("UserPagination") != null) {
                resp.sendRedirect("exhibition?command=user");
            } else if (req.getParameter("UserExhibitionPagination") != null) {
                resp.sendRedirect("exhibition?command=userexhibition");
            } else if (req.getParameter("englishButton") != null) {
                ModelLanguageUserSecond modelLanguageUserSecond = ModelLanguageUserSecond.getInstance();
                modelLanguageUserSecond.add("en");
                resp.sendRedirect("/exhibition?command=userexhibition");
            } else if (req.getParameter("ukraineButton") != null) {
                ModelLanguageUserSecond modelLanguageUserSecond = ModelLanguageUserSecond.getInstance();
                modelLanguageUserSecond.add("ua");
                resp.sendRedirect("exhibition?command=userexhibition");
            }
        }
    }

}
