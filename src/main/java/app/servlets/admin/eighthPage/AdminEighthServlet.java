package app.servlets.admin.eighthPage;

import app.database.admin.eighthPage.EighthPageDB;
import app.entities.adminEntities.eighthPage.ExhibitionStatisticsShow;
import app.model.adminModels.eighthPage.ModelLanguageAdminEighth;
import app.model.adminModels.eighthPage.ModelShowStatistics;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminEighthServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(AdminEighthServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("UserRole") == null || req.getSession().getAttribute("UserRole").equals("1")) {
            resp.sendRedirect("/exhibition/auto");
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
                    req.setAttribute("languageEnglish", true);
                } else if (req.getSession().getAttribute("language").equals("ua")) {
                    req.setAttribute("languageUkraine", true);
                }
            }

            if (EighthPageDB.checkConnection() == null)
                EighthPageDB.startConnnection();

            if (modelShowStatistics.listShow() != null) {
                if (modelShowStatistics.checkNull() == true) {
                    EighthPageDB.nullConnection();
                    req.setAttribute("Error", true);
                } else {
                    List<ExhibitionStatisticsShow> exhibition = modelShowStatistics.listShow();
                    req.setAttribute("EighthPageShow", exhibition);
                }
            }
            req.getRequestDispatcher("views/adminMenu/eighthPage/AdminEighthMenu.jsp").forward(req, resp);
            req.removeAttribute("EighthPageShow");
            req.removeAttribute("Error");
            req.removeAttribute("languageEnglish");
            req.removeAttribute("languageUkraine");
            ModelShowStatistics.delete();
            ModelLanguageAdminEighth.delete();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (EighthPageDB.checkConnection() == null)
            EighthPageDB.startConnnection();

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
            resp.sendRedirect("/exhibition/adminstatistics");
        } else if (req.getParameter("exitButton") != null) {
            req.getSession().removeAttribute("UserRole");
            EighthPageDB.exitConnection();
            EighthPageDB.nullConnection();
            resp.sendRedirect("/exhibition/userautorized");
        } else if (req.getParameter("AdminMainPagination") != null) {
            EighthPageDB.exitConnection();
            EighthPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminmain");
        } else if (req.getParameter("UserAutorizedPagination") != null) {
            EighthPageDB.exitConnection();
            EighthPageDB.nullConnection();
            resp.sendRedirect("/exhibition/userautorized");
        } else if (req.getParameter("AdminHallPagination") != null) {
            EighthPageDB.exitConnection();
            EighthPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminhall");
        } else if (req.getParameter("AdminAddressPagination") != null) {
            EighthPageDB.exitConnection();
            EighthPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminaddress");
        } else if (req.getParameter("AdminAuthorPagination") != null) {
            EighthPageDB.exitConnection();
            EighthPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminauthor");
        } else if (req.getParameter("AdminArtPagination") != null) {
            EighthPageDB.exitConnection();
            EighthPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminart");
        } else if (req.getParameter("AdminViewPagination") != null) {
            EighthPageDB.exitConnection();
            EighthPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminview");
        } else if (req.getParameter("AdminStatisticsExhibition") != null) {
            EighthPageDB.exitConnection();
            EighthPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminstatistics");
        } else if (req.getParameter("englishButton") != null) {
            ModelLanguageAdminEighth modelLanguageAdminEighth = ModelLanguageAdminEighth.getInstance();
            modelLanguageAdminEighth.add("en");
            resp.sendRedirect("/exhibition/adminstatistics");
        } else if (req.getParameter("ukraineButton") != null) {
            ModelLanguageAdminEighth modelLanguageAdminEighth = ModelLanguageAdminEighth.getInstance();
            modelLanguageAdminEighth.add("ua");
            resp.sendRedirect("/exhibition/adminstatistics");
        }
    }
}
