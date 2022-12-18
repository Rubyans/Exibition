package app.servlets.admin.sixthPage;

import app.database.admin.sixthPage.SixthPageDB;
import app.entities.adminEntities.sixthPage.ViewShow;
import app.model.adminModels.sixthPage.ModelAddView;
import app.model.adminModels.sixthPage.ModelDelView;
import app.model.adminModels.sixthPage.ModelLanguageAdminSixth;
import app.model.adminModels.sixthPage.ModelShowView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminSixthServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(AdminSixthServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("UserRole") == null || req.getSession().getAttribute("UserRole").equals("1")) {
            resp.sendRedirect("/exhibition/auto");
        } else {
            ModelShowView modelShowView = ModelShowView.getInstance();
            ModelAddView modelAddView = ModelAddView.getInstance();
            ModelDelView modelDelView = ModelDelView.getInstance();
            ModelLanguageAdminSixth modelLanguageAdminSixth = ModelLanguageAdminSixth.getInstance();

            if (modelLanguageAdminSixth.modelCheck() != null) {
                if (modelLanguageAdminSixth.modelCheck().equals("en")) {
                    req.getSession().setAttribute("language", "en");
                }
                if (modelLanguageAdminSixth.modelCheck().equals("ua")) {
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
            if (SixthPageDB.checkConnection() == null)
                SixthPageDB.startConnnection();

            if (modelShowView.listShow() != null) {
                if (modelShowView.checkNull() == true) {
                    SixthPageDB.nullConnection();
                    req.setAttribute("Error", true);
                } else {
                    List<ViewShow> art = modelShowView.listShow();
                    req.setAttribute("SixthPageShow", art);
                }
            } else if (modelAddView.modelCheck() != null) {
                if (modelAddView.modelCheck().equals("false"))
                    req.setAttribute("AddError", true);
                else if (modelAddView.modelCheck().equals("true"))
                    req.setAttribute("TrueAdd", true);
            } else if (modelDelView.modelCheck() != null) {
                if (modelDelView.modelCheck().equals("false"))
                    req.setAttribute("DelError", true);
                else if (modelDelView.modelCheck().equals("true"))
                    req.setAttribute("TrueDel", true);
            }
            req.getRequestDispatcher("views/adminMenu/sixthPage/AdminSixthMenu.jsp").forward(req, resp);
            req.removeAttribute("SixthPageShow");
            req.removeAttribute("Error");
            req.removeAttribute("AddError");
            req.removeAttribute("DelError");
            req.removeAttribute("TrueDel");
            req.removeAttribute("TrueAdd");
            req.removeAttribute("languageEnglish");
            req.removeAttribute("languageUkraine");
            ModelShowView.delete();
            ModelAddView.delete();
            ModelDelView.delete();
            ModelLanguageAdminSixth.delete();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (SixthPageDB.checkConnection() == null)
            SixthPageDB.startConnnection();
        if (req.getParameter("updateButton") != null) {
            ModelShowView modelShowView = ModelShowView.getInstance();
            try {
                for (ViewShow view : SixthPageDB.viewShow())
                    modelShowView.add(view);
                LOGGER.debug("doPost in debug");
            } catch (Exception e) {
                modelShowView.add(null);
                LOGGER.debug("doPost " + e.getMessage());
            }
            resp.sendRedirect("/exhibition/adminview");

        } else if (req.getParameter("addButtonView") != null) {
            String name = req.getParameter("NameView");
            ModelAddView modelAddView = ModelAddView.getInstance();
            boolean checkDel = SixthPageDB.viewAdd(name);
            if (checkDel == true)
                modelAddView.add(true);
            else
                modelAddView.add(false);
            resp.sendRedirect("/exhibition/adminview");

        } else if (req.getParameter("delButtonView") != null) {
            ModelDelView modelDelView = ModelDelView.getInstance();
            String name = req.getParameter("viewDel");
            boolean checkDel = SixthPageDB.viewDel(name);
            if (checkDel == true)
                modelDelView.add(true);
            else
                modelDelView.add(false);
            resp.sendRedirect("/exhibition/adminview");
        } else if (req.getParameter("roleBackButton") != null) {
            SixthPageDB.RoleBackCommit();
            resp.sendRedirect("/exhibition/adminview");
        } else if (req.getParameter("saveButton") != null) {
            SixthPageDB.saveCommit();
            resp.sendRedirect("/exhibition/adminview");
        } else if (req.getParameter("exitButton") != null) {
            req.getSession().removeAttribute("UserRole");
            SixthPageDB.exitConnection();
            SixthPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminview");
        } else if (req.getParameter("AdminMainPagination") != null) {
            SixthPageDB.exitConnection();
            SixthPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminmain");
        } else if (req.getParameter("UserAutorizedPagination") != null) {
            SixthPageDB.exitConnection();
            SixthPageDB.nullConnection();
            resp.sendRedirect("/exhibition/userautorized");
        } else if (req.getParameter("AdminHallPagination") != null) {
            SixthPageDB.exitConnection();
            SixthPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminhall");
        } else if (req.getParameter("AdminAddressPagination") != null) {
            SixthPageDB.exitConnection();
            SixthPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminaddress");
        } else if (req.getParameter("AdminAuthorPagination") != null) {
            SixthPageDB.exitConnection();
            SixthPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminauthor");
        } else if (req.getParameter("AdminArtPagination") != null) {
            SixthPageDB.exitConnection();
            SixthPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminart");
        } else if (req.getParameter("AdminViewPagination") != null) {
            SixthPageDB.exitConnection();
            SixthPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminview");
        } else if (req.getParameter("AdminStatisticsExhibition") != null) {
            SixthPageDB.exitConnection();
            SixthPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminstatistics");
        } else if (req.getParameter("englishButton") != null) {
            ModelLanguageAdminSixth modelLanguageAdminSixth = ModelLanguageAdminSixth.getInstance();
            modelLanguageAdminSixth.add("en");
            resp.sendRedirect("/exhibition/adminview");
        } else if (req.getParameter("ukraineButton") != null) {
            ModelLanguageAdminSixth modelLanguageAdminSixth = ModelLanguageAdminSixth.getInstance();
            modelLanguageAdminSixth.add("ua");
            resp.sendRedirect("/exhibition/adminview");
        }
    }
}
