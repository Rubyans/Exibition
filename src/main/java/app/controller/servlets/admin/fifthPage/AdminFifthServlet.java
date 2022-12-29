package app.controller.servlets.admin.fifthPage;

import app.DAO.connectionDAO.HikariConnectDB;
import app.DAO.sqlFunctions.admin.fifthPage.FifthPageDB;
import app.DAO.entities.adminEntities.fifthPage.ArtAddShow;
import app.DAO.entities.adminEntities.fifthPage.ArtShow;
import app.models.adminModels.fifthPage.*;

import app.service.changeLanguage.ChangeLanguage;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminFifthServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(AdminFifthServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("UserRole") == null || req.getSession().getAttribute("UserRole").equals("1")) {
            resp.sendRedirect("/exhibition/auto");
        } else {
            ModelShowArt modelShowArt = ModelShowArt.getInstance();
            ModelAddArt modelAddArt = ModelAddArt.getInstance();
            ModelDelArt modelDelArt = ModelDelArt.getInstance();
            ModelAddShow modelAddShow = ModelAddShow.getInstance();
            ModelLanguageAdminFifth modelLanguageAdminFifth = ModelLanguageAdminFifth.getInstance();

            if (modelLanguageAdminFifth.modelCheck() != null) {
                if (modelLanguageAdminFifth.modelCheck().equals("en")) {
                    req.getSession().setAttribute("language", "en");
                }
                if (modelLanguageAdminFifth.modelCheck().equals("ua")) {
                    req.getSession().setAttribute("language", "ua");
                }
            }
            if (req.getSession().getAttribute("language") != null) {
                if (req.getSession().getAttribute("language").equals("en")) {
                    req.setAttribute("languageChange", ChangeLanguage.changeEN("language.properties", "adminFifth"));
                } else if (req.getSession().getAttribute("language").equals("ua")) {
                    req.setAttribute("languageChange", ChangeLanguage.changeUA("language.properties", "adminFifth"));
                }
            } else {
                req.setAttribute("languageChange", ChangeLanguage.changeUA("language.properties", "adminFifth"));
            }
            try {
                for (ArtAddShow addShow : FifthPageDB.addArtShow())
                    modelAddShow.add(addShow);
                LOGGER.debug("doGet in debug");
            } catch (Exception e) {
                LOGGER.error("doGet " + e.getMessage());
                modelAddShow.add(null);
            }

            if (modelAddShow.listShow() != null) {
                List<ArtAddShow> add = modelAddShow.listShow();
                req.setAttribute("AddShow", add);

            } else {
                req.setAttribute("Error", true);
            }
            if (modelShowArt.listShow() != null) {
                if (modelShowArt.checkNull() == true) {
                    req.setAttribute("Error", true);
                } else {
                    List<ArtShow> art = modelShowArt.listShow();
                    req.setAttribute("FifthPageShow", art);
                }
            } else if (modelAddArt.modelCheck() != null) {
                if (modelAddArt.modelCheck().equals("false"))
                    req.setAttribute("AddError", true);
                else if (modelAddArt.modelCheck().equals("true"))
                    req.setAttribute("TrueAdd", true);

            } else if (modelDelArt.modelCheck() != null) {
                if (modelDelArt.modelCheck().equals("false"))
                    req.setAttribute("DelError", true);
                else if (modelDelArt.modelCheck().equals("true"))
                    req.setAttribute("TrueDel", true);
            }

            req.getRequestDispatcher("views/adminMenu/fifthPage/AdminFifthMenu.jsp").forward(req, resp);
            req.removeAttribute("FifthPageShow");
            req.removeAttribute("Error");
            req.removeAttribute("AddError");
            req.removeAttribute("DelError");
            req.removeAttribute("TrueDel");
            req.removeAttribute("TrueAdd");
            req.removeAttribute("languageChange");
            ModelShowArt.delete();
            ModelAddArt.delete();
            ModelDelArt.delete();
            ModelAddShow.delete();
            ModelLanguageAdminFifth.delete();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("updateButton") != null) {
            ModelShowArt modelShowArt = ModelShowArt.getInstance();
            try {
                for (ArtShow art : FifthPageDB.artShow())
                    modelShowArt.add(art);
                LOGGER.debug("doPost in debug");
            } catch (Exception e) {
                LOGGER.error("doPost " + e.getMessage());
                modelShowArt.add(null);
            }
            resp.sendRedirect("/exhibition/adminart");
        } else if (req.getParameter("addButtonArt") != null) {
            String name = req.getParameter("NameArt");
            Integer yearCreation = Integer.parseInt(req.getParameter("CreationArt"));
            Double price = Double.parseDouble(req.getParameter("PriceArt"));
            List<String> author = List.of(req.getParameterValues("author"));
            List<String> view = List.of(req.getParameterValues("view"));
            ModelAddArt modelAddArt = ModelAddArt.getInstance();
            boolean checkDel = FifthPageDB.artAdd(name, yearCreation, price, author, view);
            if (checkDel == true)
                modelAddArt.add(true);
            else
                modelAddArt.add(false);
            resp.sendRedirect("/exhibition/adminart");
        } else if (req.getParameter("delButtonArt") != null) {

            ModelDelArt modelDelArt = ModelDelArt.getInstance();
            String name = req.getParameter("artDel");
            boolean checkDel = FifthPageDB.artDel(name);
            if (checkDel == true)
                modelDelArt.add(true);
            else
                modelDelArt.add(false);
            resp.sendRedirect("/exhibition/adminart");
        } else if (req.getParameter("roleBackButton") != null) {
            HikariConnectDB.roleBackCommit();
            resp.sendRedirect("/exhibition/adminart");
        } else if (req.getParameter("saveButton") != null) {
            HikariConnectDB.saveCommit();
            resp.sendRedirect("/exhibition/adminart");
        } else if (req.getParameter("exitButton") != null) {
            req.getSession().removeAttribute("UserRole");
            HikariConnectDB.exitConnection();
            resp.sendRedirect("/exhibition/adminart");
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
            ModelLanguageAdminFifth modelLanguageAdminFifth = ModelLanguageAdminFifth.getInstance();
            modelLanguageAdminFifth.add("en");
            resp.sendRedirect("/exhibition/adminart");
        } else if (req.getParameter("ukraineButton") != null) {
            ModelLanguageAdminFifth modelLanguageAdminFifth = ModelLanguageAdminFifth.getInstance();
            modelLanguageAdminFifth.add("ua");
            resp.sendRedirect("/exhibition/adminart");
        }

    }
}
