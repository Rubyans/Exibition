package app.controllerFront.commands.admin.eighthPage;


import app.DAO.connectionDAO.HikariConnectDB;
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
            if (req.getSession().getAttribute("UserRole") == null || req.getSession().getAttribute("UserRole").equals("1")) {
                resp.sendRedirect("exhibition?command=auto");
            } else {
                ModelShowStatistics modelShowStatistics = ModelShowStatistics.getInstance();
                ModelLanguageAdminEighth modelLanguageAdminEighth = ModelLanguageAdminEighth.getInstance();

                if (modelLanguageAdminEighth.modelCheck() != null) {
                    if (modelLanguageAdminEighth.modelCheck().equals("en")) {
                        req.getSession().setAttribute("language", "en");
                    }
                    if (modelLanguageAdminEighth.modelCheck().equals("ua")) {
                        req.getSession().setAttribute("language", "ua");
                    }
                }
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
                ModelLanguageAdminEighth.delete();
            }
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
            } else if (req.getParameter("exitButton") != null) {
                req.getSession().removeAttribute("UserRole");
                HikariConnectDB.exitConnection();
                resp.sendRedirect("exhibition?command=userautorized");
            } else if (req.getParameter("AdminMainPagination") != null) {
                resp.sendRedirect("exhibition?command=adminmain");
            } else if (req.getParameter("UserAutorizedPagination") != null) {
                resp.sendRedirect("exhibition?command=userautorized");
            } else if (req.getParameter("AdminHallPagination") != null) {
                resp.sendRedirect("exhibition?command=adminhall");
            } else if (req.getParameter("AdminAddressPagination") != null) {
                resp.sendRedirect("exhibition?command=adminaddress");
            } else if (req.getParameter("AdminAuthorPagination") != null) {
                resp.sendRedirect("exhibition?command=adminauthor");
            } else if (req.getParameter("AdminArtPagination") != null) {
                resp.sendRedirect("exhibition?command=adminart");
            } else if (req.getParameter("AdminViewPagination") != null) {
                resp.sendRedirect("exhibition?command=adminview");
            } else if (req.getParameter("AdminStatisticsExhibition") != null) {
                resp.sendRedirect("exhibition?command=adminstatistics");
            } else if (req.getParameter("englishButton") != null) {
                ModelLanguageAdminEighth modelLanguageAdminEighth = ModelLanguageAdminEighth.getInstance();
                modelLanguageAdminEighth.add("en");
                resp.sendRedirect("exhibition?command=adminstatistics");
            } else if (req.getParameter("ukraineButton") != null) {
                ModelLanguageAdminEighth modelLanguageAdminEighth = ModelLanguageAdminEighth.getInstance();
                modelLanguageAdminEighth.add("ua");
                resp.sendRedirect("exhibition?command=adminstatistics");
            }
        }
    }

}
