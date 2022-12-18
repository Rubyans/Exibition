package app.servlets.admin.firstPage;

import app.database.admin.firstPage.FirstPageDB;
import app.entities.adminEntities.firstPage.AdminAddShow;
import app.entities.adminEntities.firstPage.AdminShow;
import app.model.adminModels.firstPage.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminExhibitionServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(AdminExhibitionServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession().getAttribute("UserRole") == null || req.getSession().getAttribute("UserRole").equals("1")) {
            resp.sendRedirect("/exhibition/auto");
        } else {
            ModelShow showModel = ModelShow.getInstance();
            ModelAddShow AddModelShow = ModelAddShow.getInstance();
            ModelAddExhibition modelAddExhibition = ModelAddExhibition.getInstance();
            ModelDel modelDel = ModelDel.getInstance();
            ModelLanguageAdminFirst modelLanguageAdminFirst = ModelLanguageAdminFirst.getInstance();
            ModelChangeAccess modelChangeAccess = ModelChangeAccess.getInstance();
            if (modelLanguageAdminFirst.modelCheck() != null) {
                if (modelLanguageAdminFirst.modelCheck().equals("en")) {
                    req.getSession().setAttribute("language", "en");
                }
                if (modelLanguageAdminFirst.modelCheck().equals("ua")) {
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

            if (FirstPageDB.checkConnection() == null)
                FirstPageDB.startConnnection();

            try {
                for (AdminAddShow addShow : FirstPageDB.ShowAddFirstPage())
                    AddModelShow.add(addShow);
                LOGGER.debug("doGet in debug");
            } catch (Exception e) {
                AddModelShow.add(null);
                LOGGER.error("doGet " + e.getMessage());
            }

            if (AddModelShow.listShow() != null) {
                List<AdminAddShow> add = AddModelShow.listShow();
                req.setAttribute("AddShow", add);

            } else {
                FirstPageDB.nullConnection();
                req.setAttribute("Error", true);
            }

            if (showModel.listShow() != null) {
                if (showModel.checkNull() == true) {
                    FirstPageDB.nullConnection();
                    req.setAttribute("Error", true);
                } else {
                    List<AdminShow> names = showModel.listShow();
                    req.setAttribute("FirstPage", names);
                    req.setAttribute("Error", false);
                }
            } else if (modelChangeAccess.modelCheck() != null) {
                if (modelChangeAccess.modelCheck().equals("false"))
                    req.setAttribute("ChangeError", true);
                else if (modelChangeAccess.modelCheck().equals("true"))
                    req.setAttribute("TrueChange", true);
            } else if (modelAddExhibition.modelCheck() != null) {
                if (modelAddExhibition.modelCheck().equals("false"))
                    req.setAttribute("AddError", true);
                else if (modelAddExhibition.modelCheck().equals("true"))
                    req.setAttribute("TrueAdd", true);
            } else if (modelDel.modelCheck() != null) {
                if (modelDel.modelCheck().equals("false"))
                    req.setAttribute("DelError", true);
                else if (modelDel.modelCheck().equals("true"))
                    req.setAttribute("TrueDel", true);
            }

            req.getRequestDispatcher("views/adminMenu/firstPage/AdminFirstMenu.jsp").forward(req, resp);
            req.removeAttribute("GuestList");
            req.removeAttribute("Error");
            req.removeAttribute("AddError");
            req.removeAttribute("DelError");
            req.removeAttribute("TrueAdd");
            req.removeAttribute("TrueDel");
            req.removeAttribute("ChangeError");
            req.removeAttribute("TrueChange");
            req.removeAttribute("languageEnglish");
            req.removeAttribute("languageUkraine");
            ModelShow.delete();
            ModelAddShow.delete();
            ModelAddExhibition.delete();
            ModelDel.delete();
            ModelLanguageAdminFirst.delete();
            ModelChangeAccess.delete();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (FirstPageDB.checkConnection() == null)
            FirstPageDB.startConnnection();

        if (req.getParameter("updateButton") != null) {
            ModelShow showModel = ModelShow.getInstance();
            try {
                for (AdminShow show : FirstPageDB.exibitionShow())
                    showModel.add(show);
                LOGGER.debug("doPost in debug");
            } catch (Exception e) {
                LOGGER.error("doPost " + e.getMessage());
                showModel.add(null);
            }
            resp.sendRedirect("/exhibition/adminmain");
        } else if (req.getParameter("addButtonServlet") != null) {
            String nameExibition = req.getParameter("nameExibition");
            String description = req.getParameter("description");
            String price = req.getParameter("price");
            String start = req.getParameter("start");
            String end = req.getParameter("end");
            List<String> hall = List.of(req.getParameterValues("hall"));
            List<String> address = List.of(req.getParameterValues("address"));
            List<String> workArt = List.of(req.getParameterValues("workArt"));

            ModelAddExhibition modelAddExhibition = ModelAddExhibition.getInstance();

            boolean checkAdd = FirstPageDB.AddFirstPage(nameExibition, description, Double.valueOf(price), start, end, hall, address, workArt);
            if (checkAdd == true)
                modelAddExhibition.add(true);
            else
                modelAddExhibition.add(false);
            resp.sendRedirect("/exhibition/adminmain");
        } else if (req.getParameter("delButtonServlet") != null) {
            String nameExhibitionDel = req.getParameter("nameExhibitionDel");
            ModelDel modelDel = ModelDel.getInstance();
            boolean checkDel = FirstPageDB.DelFirstPage(nameExhibitionDel);
            if (checkDel == true)
                modelDel.add(true);
            else
                modelDel.add(false);
            resp.sendRedirect("/exhibition/adminmain");
        } else if (req.getParameter("accessButtonServlet") != null) {
            String access = req.getParameter("access");
            String nameExhibition = req.getParameter("nameExhibitionAccess");
            ModelChangeAccess modelChangeAccess = ModelChangeAccess.getInstance();
            boolean checkAccess = FirstPageDB.AccessFirstPage(nameExhibition, access);
            if (checkAccess == true)
                modelChangeAccess.add(true);
            else
                modelChangeAccess.add(false);
            resp.sendRedirect("/exhibition/adminmain");
        } else if (req.getParameter("roleBackButton") != null) {
            FirstPageDB.RoleBackCommit();
            resp.sendRedirect("/exhibition/adminmain");
        } else if (req.getParameter("saveButton") != null) {
            FirstPageDB.SaveCommit();
            resp.sendRedirect("/exhibition/adminmain");
        } else if (req.getParameter("exitButton") != null) {
            req.getSession().removeAttribute("UserRole");
            FirstPageDB.exitConnection();
            FirstPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminmain");
        } else if (req.getParameter("AdminMainPagination") != null) {
            FirstPageDB.exitConnection();
            FirstPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminmain");
        } else if (req.getParameter("UserAutorizedPagination") != null) {
            FirstPageDB.exitConnection();
            FirstPageDB.nullConnection();
            resp.sendRedirect("/exhibition/userautorized");
        } else if (req.getParameter("AdminHallPagination") != null) {
            FirstPageDB.exitConnection();
            FirstPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminhall");
        } else if (req.getParameter("AdminAddressPagination") != null) {
            FirstPageDB.exitConnection();
            FirstPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminaddress");
        } else if (req.getParameter("AdminAuthorPagination") != null) {
            FirstPageDB.exitConnection();
            FirstPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminauthor");
        } else if (req.getParameter("AdminArtPagination") != null) {
            FirstPageDB.exitConnection();
            FirstPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminart");
        } else if (req.getParameter("AdminViewPagination") != null) {
            FirstPageDB.exitConnection();
            FirstPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminview");
        } else if (req.getParameter("AdminStatisticsExhibition") != null) {
            FirstPageDB.exitConnection();
            FirstPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminstatistics");
        } else if (req.getParameter("englishButton") != null) {
            ModelLanguageAdminFirst modelLanguageAdminFirst = ModelLanguageAdminFirst.getInstance();
            modelLanguageAdminFirst.add("en");
            resp.sendRedirect("/exhibition/adminmain");
        } else if (req.getParameter("ukraineButton") != null) {
            ModelLanguageAdminFirst modelLanguageAdminFirst = ModelLanguageAdminFirst.getInstance();
            modelLanguageAdminFirst.add("ua");
            resp.sendRedirect("/exhibition/adminmain");
        }
    }
}
