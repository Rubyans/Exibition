package app.servlets.admin.secondPage;


import app.database.admin.secondPage.SecondPageDB;
import app.entities.adminEntities.secondPage.HallShow;
import app.model.adminModels.secondPage.ModelAddHall;
import app.model.adminModels.secondPage.ModelDelHall;
import app.model.adminModels.secondPage.ModelLanguageAdminSecond;
import app.model.adminModels.secondPage.ModelShowHall;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminHallServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(AdminHallServlet.class);

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
                    req.setAttribute("languageEnglish", true);
                } else if (req.getSession().getAttribute("language").equals("ua")) {
                    req.setAttribute("languageUkraine", true);
                }
            }

            if (SecondPageDB.checkConnection() == null)
                SecondPageDB.startConnnection();

            if (showHall.listShow() != null) {
                if (showHall.checkNull() == true) {
                    req.setAttribute("Error", true);
                    SecondPageDB.nullConnection();
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
            req.removeAttribute("languageEnglish");
            req.removeAttribute("languageUkraine");
            ModelShowHall.delete();
            ModelAddHall.delete();
            ModelDelHall.delete();
            ModelLanguageAdminSecond.delete();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (SecondPageDB.checkConnection() == null)
            SecondPageDB.startConnnection();
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
            SecondPageDB.RoleBackCommit();
            resp.sendRedirect("/exhibition/adminhall");
        } else if (req.getParameter("saveButton") != null) {
            SecondPageDB.saveCommit();
            resp.sendRedirect("/exhibition/adminhall");
        } else if (req.getParameter("exitButton") != null) {
            req.getSession().removeAttribute("UserRole");
            SecondPageDB.exitConnection();
            SecondPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminhall");
        } else if (req.getParameter("AdminMainPagination") != null) {
            SecondPageDB.exitConnection();
            SecondPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminmain");
        } else if (req.getParameter("UserAutorizedPagination") != null) {
            SecondPageDB.exitConnection();
            SecondPageDB.nullConnection();
            resp.sendRedirect("/exhibition/userautorized");
        } else if (req.getParameter("AdminHallPagination") != null) {
            SecondPageDB.exitConnection();
            SecondPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminhall");
        } else if (req.getParameter("AdminAddressPagination") != null) {
            SecondPageDB.exitConnection();
            SecondPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminaddress");
        } else if (req.getParameter("AdminAuthorPagination") != null) {
            SecondPageDB.exitConnection();
            SecondPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminauthor");
        } else if (req.getParameter("AdminArtPagination") != null) {
            SecondPageDB.exitConnection();
            SecondPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminart");
        } else if (req.getParameter("AdminViewPagination") != null) {
            SecondPageDB.exitConnection();
            SecondPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminview");
        } else if (req.getParameter("AdminStatisticsExhibition") != null) {
            SecondPageDB.exitConnection();
            SecondPageDB.nullConnection();
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
