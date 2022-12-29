package app.controller.servlets.admin.secondPage;


import app.DAO.connectionDAO.HikariConnectDB;
import app.DAO.sqlFunctions.admin.secondPage.SecondPageDB;
import app.DAO.entities.adminEntities.secondPage.HallShow;
import app.models.adminModels.secondPage.ModelAddHall;
import app.models.adminModels.secondPage.ModelDelHall;
import app.models.adminModels.secondPage.ModelLanguageAdminSecond;
import app.models.adminModels.secondPage.ModelShowHall;
import app.service.changeLanguage.ChangeLanguage;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminSecondServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(AdminSecondServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        if (req.getSession().getAttribute("UserRole") == null || req.getSession().getAttribute("UserRole").equals("1")) {
            resp.sendRedirect("/exhibition/auto");
        } else {
            ModelShowHall showHall = ModelShowHall.getInstance();
            ModelAddHall modelAddHall = ModelAddHall.getInstance();
            ModelDelHall modelDelHall = ModelDelHall.getInstance();
            ModelLanguageAdminSecond modelLanguageAdminSecond = ModelLanguageAdminSecond.getInstance();

            if (modelLanguageAdminSecond.modelCheck() != null) {
                if (modelLanguageAdminSecond.modelCheck().equals("en")) {
                    req.getSession().setAttribute("language", "en");
                }
                if (modelLanguageAdminSecond.modelCheck().equals("ua")) {
                    req.getSession().setAttribute("language", "ua");
                }
            }
            if (req.getSession().getAttribute("language") != null) {
                if (req.getSession().getAttribute("language").equals("en")) {
                    req.setAttribute("languageChange", ChangeLanguage.changeEN("language.properties", "adminSecond"));
                } else if (req.getSession().getAttribute("language").equals("ua")) {
                    req.setAttribute("languageChange", ChangeLanguage.changeUA("language.properties", "adminSecond"));
                }
            } else {
                req.setAttribute("languageChange", ChangeLanguage.changeUA("language.properties", "adminSecond"));
            }
            if (showHall.listShow() != null) {
                if (showHall.checkNull() == true) {
                    req.setAttribute("Error", true);
                } else {
                    List<HallShow> names = showHall.listShow();
                    req.setAttribute("SecondPageShow", names);
                }
            } else if (modelAddHall.modelCheck() != null) {
                if (modelAddHall.modelCheck().equals("false"))
                    req.setAttribute("AddError", true);
                else if (modelAddHall.modelCheck().equals("true"))
                    req.setAttribute("TrueAdd", true);

            } else if (modelDelHall.modelCheck() != null) {
                if (modelDelHall.modelCheck().equals("false"))
                    req.setAttribute("DelError", true);
                else if (modelDelHall.modelCheck().equals("true"))
                    req.setAttribute("TrueDel", true);
            }

            req.getRequestDispatcher("views/adminMenu/secondPage/AdminSecondMenu.jsp").forward(req, resp);
            req.removeAttribute("SecondPageShow");
            req.removeAttribute("Error");
            req.removeAttribute("AddError");
            req.removeAttribute("DelError");
            req.removeAttribute("TrueDel");
            req.removeAttribute("TrueAdd");
            req.removeAttribute("languageChange");
            ModelShowHall.delete();
            ModelAddHall.delete();
            ModelDelHall.delete();
            ModelLanguageAdminSecond.delete();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("updateButton") != null) {
            ModelShowHall showHall = ModelShowHall.getInstance();
            try {
                for (HallShow hall : SecondPageDB.hallShow())
                    showHall.add(hall);
                LOGGER.debug("dePost in debug");
            } catch (Exception e) {
                showHall.add(null);
                LOGGER.debug("dePost " + e.getMessage());
            }
            resp.sendRedirect("/exhibition/adminhall");
        } else if (req.getParameter("addButtonHall") != null) {
            String nameHall = req.getParameter("nameHall");
            String square = req.getParameter("square");
            ModelAddHall modelAddHall = ModelAddHall.getInstance();
            boolean checkDel = SecondPageDB.hallAdd(nameHall, Double.valueOf(square));
            if (checkDel == true)
                modelAddHall.add(true);
            else
                modelAddHall.add(false);
            resp.sendRedirect("/exhibition/adminhall");
        } else if (req.getParameter("delButtonHall") != null) {

            String nameHall = req.getParameter("nameDel");
            ModelDelHall modelDelHall = ModelDelHall.getInstance();
            boolean checkDel = SecondPageDB.hallDel(nameHall);
            if (checkDel == true) {
                modelDelHall.add(true);
            } else
                modelDelHall.add(false);
            resp.sendRedirect("/exhibition/adminhall");
        } else if (req.getParameter("roleBackButton") != null) {
            HikariConnectDB.roleBackCommit();
            resp.sendRedirect("/exhibition/adminhall");
        } else if (req.getParameter("saveButton") != null) {
            HikariConnectDB.saveCommit();
            resp.sendRedirect("/exhibition/adminhall");
        } else if (req.getParameter("exitButton") != null) {
            req.getSession().removeAttribute("UserRole");
            HikariConnectDB.exitConnection();
            resp.sendRedirect("/exhibition/adminhall");
        } else if (req.getParameter("AdminMainPagination") != null) {
            resp.sendRedirect("/exhibition/adminmain");
        } else if (req.getParameter("UserAutorizedPagination") != null) {
            resp.sendRedirect("/exhibition/userautorized");
        } else if (req.getParameter("AdminHallPagination") != null) {
            resp.sendRedirect("/exhibition/adminhall");
        } else if (req.getParameter("AdminAddressPagination") != null) {
            resp.sendRedirect("/exhibition/adminaddress");
        } else if (req.getParameter("AdminAuthorPagination") != null) {
            resp.sendRedirect("/exhibition/adminauthor");
        } else if (req.getParameter("AdminArtPagination") != null) {
            resp.sendRedirect("/exhibition/adminart");
        } else if (req.getParameter("AdminViewPagination") != null) {
            resp.sendRedirect("/exhibition/adminview");
        } else if (req.getParameter("AdminStatisticsExhibition") != null) {
            resp.sendRedirect("/exhibition/adminstatistics");
        } else if (req.getParameter("englishButton") != null) {
            ModelLanguageAdminSecond modelLanguageAdminSecond = ModelLanguageAdminSecond.getInstance();
            modelLanguageAdminSecond.add("en");
            resp.sendRedirect("/exhibition/adminhall");
        } else if (req.getParameter("ukraineButton") != null) {
            ModelLanguageAdminSecond modelLanguageAdminSecond = ModelLanguageAdminSecond.getInstance();
            modelLanguageAdminSecond.add("ua");
            resp.sendRedirect("/exhibition/adminhall");
        }
    }

}
