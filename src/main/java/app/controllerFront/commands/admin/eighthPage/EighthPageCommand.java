package app.controllerFront.commands.admin.eighthPage;


import app.DAO.entities.adminEntities.eighthPage.ExhibitionStatisticsShow;
import app.DAO.sqlFunctions.admin.eighthPage.EighthPageDB;
import app.controllerFront.commands.interfaceCommand.Command;
import app.controllerFront.models.adminModels.eighthPage.*;
import app.service.changeLanguage.ChangeLanguage;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EighthPageCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(EighthPageCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, int request) throws ServletException, IOException {
        if (request == 1) {
/////////////////////////////////////GET-Request/////////////////////////////////////////////////////////////////
            ModelShowStatistics modelShowStatistics = ModelShowStatistics.getInstance();
            if (req.getSession().getAttribute("language") != null) {
                if (req.getSession().getAttribute("language").equals("en")) {
                    req.setAttribute("languageChange", ChangeLanguage.changeEN("language.properties", "adminEighth"));
                } else if (req.getSession().getAttribute("language").equals("ua")) {
                    req.setAttribute("languageChange", ChangeLanguage.changeUA("language.properties", "adminEighth"));
                }
            } else {
                req.setAttribute("languageChange", ChangeLanguage.changeUA("language.properties", "adminEighth"));
            }

            if (modelShowStatistics.listShow() != null) {
                if (modelShowStatistics.checkNull() == true) {
                    req.setAttribute("Error", true);
                } else {
                    List<ExhibitionStatisticsShow> exhibition = modelShowStatistics.listShow();
                    req.setAttribute("EighthPageShow", exhibition);
                }
            }
            req.getRequestDispatcher("views/adminMenu/eighthPage/AdminEighthMenu.jsp").forward(req, resp);
            req.removeAttribute("EighthPageShow");
            req.removeAttribute("Error");
            req.removeAttribute("languageChange");
            ModelShowStatistics.delete();
        } else {
/////////////////////////////////////Post-Request///////////////////////////////////////////////////////////////
            if (req.getParameter("updateButton") != null) {
                ModelShowStatistics modelShowStatistics = ModelShowStatistics.getInstance();
                try {
                    for (ExhibitionStatisticsShow exhibition : EighthPageDB.statisticsShow())
                        modelShowStatistics.add(exhibition);
                    LOGGER.debug("doPost in debug");
                } catch (Exception e) {
                    modelShowStatistics.add(null);
                    LOGGER.error("doPost " + e.getMessage());
                }
                resp.sendRedirect("exhibition?command=adminstatistics");
            }
        }
    }

}
