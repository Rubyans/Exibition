package app.controllerFront.commands.user.secondPage;

import app.DAO.entities.userEntities.secondPage.UserShowExhibition;
import app.DAO.sqlFunctions.user.secondPage.SecondPageDB;
import app.controllerFront.commands.interfaceCommand.Command;
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
            ModelShowExhibition modelShowExhibition = ModelShowExhibition.getInstance();

            if (req.getSession().getAttribute("language") != null) {
                if (req.getSession().getAttribute("language").equals("en")) {
                    req.setAttribute("languageChange", ChangeLanguage.changeEN("languageEN.properties", "userSecond"));
                } else if (req.getSession().getAttribute("language").equals("ua")) {
                    req.setAttribute("languageChange", ChangeLanguage.changeUA("languageUA.properties", "userSecond"));
                }
            } else {
                req.setAttribute("languageChange", ChangeLanguage.changeUA("languageUA.properties", "userSecond"));
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
        } else {
/////////////////////////////////////Post-Request///////////////////////////////////////////////////////////////
            if (req.getParameter("updateButton") != null) {
                ModelShowExhibition modelShowExhibition = ModelShowExhibition.getInstance();
                try {
                    String userId = (String) req.getSession().getAttribute("UserId");
                    String valueRows=req.getParameter("UpdateSort");
                    for (UserShowExhibition userShow : SecondPageDB.showExhibition(valueRows,userId)) {
                        modelShowExhibition.add(userShow);
                    }
                } catch (Exception e) {
                    modelShowExhibition.add(null);
                }
                resp.sendRedirect("exhibition?command=userexhibition");
            }
        }
    }

}
