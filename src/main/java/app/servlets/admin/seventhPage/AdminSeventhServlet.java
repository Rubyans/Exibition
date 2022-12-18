package app.servlets.admin.seventhPage;

import app.database.admin.seventhPage.SeventhPageDB;
import app.entities.adminEntities.seventhPage.UserAutorizedShow;
import app.model.adminModels.seventhPage.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminSeventhServlet extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger(AdminSeventhServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("UserRole") == null || req.getSession().getAttribute("UserRole").equals("1")) {
            resp.sendRedirect("/exhibition/auto");
        } else {
            ModelShowUserAutorized modelShowUserAutorized = ModelShowUserAutorized.getInstance();
            ModelAddUserAutorized modelAddUserAutorized = ModelAddUserAutorized.getInstance();
            ModelAddAmountAutorized modelAddAmountAutorized = ModelAddAmountAutorized.getInstance();
            ModelDelUserAutorized modelDelUserAutorized = ModelDelUserAutorized.getInstance();
            ModelLanguageAdminSeventh modelLanguageAdminSeventh = ModelLanguageAdminSeventh.getInstance();
            ModelChangeAccess modelChangeAccess = ModelChangeAccess.getInstance();
            if (modelLanguageAdminSeventh.modelCheck() != null) {
                if (modelLanguageAdminSeventh.modelCheck().equals("en")) {
                    req.getSession().setAttribute("language", "en");
                }
                if (modelLanguageAdminSeventh.modelCheck().equals("ua")) {
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
            if (SeventhPageDB.checkConnection() == null)
                SeventhPageDB.startConnnection();

            if (modelShowUserAutorized.listShow() != null) {
                if (modelShowUserAutorized.checkNull() == true) {
                    SeventhPageDB.nullConnection();
                    req.setAttribute("Error", true);
                } else {
                    List<UserAutorizedShow> user = modelShowUserAutorized.listShow();
                    req.setAttribute("SeventhPageShow", user);
                }
            } else if (modelAddUserAutorized.modelCheck() != null) {
                if (modelAddUserAutorized.modelCheck().equals("false"))
                    req.setAttribute("AddError", true);
                else if (modelAddUserAutorized.modelCheck().equals("true"))
                    req.setAttribute("TrueAdd", true);
            } else if (modelChangeAccess.modelCheck() != null) {
                if (modelChangeAccess.modelCheck().equals("false"))
                    req.setAttribute("ChangeError", true);
                else if (modelChangeAccess.modelCheck().equals("true"))
                    req.setAttribute("TrueChange", true);
            } else if (modelAddAmountAutorized.modelCheck() != null) {
                if (modelAddAmountAutorized.modelCheck().equals("true"))
                    req.setAttribute("AddAmount", true);
                else if (modelAddAmountAutorized.modelCheck().equals("false"))
                    req.setAttribute("AddAmountError", true);
            } else if (modelDelUserAutorized.modelCheck() != null) {
                if (modelDelUserAutorized.modelCheck().equals("false"))
                    req.setAttribute("DelError", true);
                else if (modelDelUserAutorized.modelCheck().equals("true"))
                    req.setAttribute("TrueDel", true);
            }
            req.getRequestDispatcher("views/adminMenu/seventhPage/AdminSeventhMenu.jsp").forward(req, resp);
            req.removeAttribute("SeventhPageShow");
            req.removeAttribute("Error");
            req.removeAttribute("AddError");
            req.removeAttribute("AddAmount");
            req.removeAttribute("AddAmountError");
            req.removeAttribute("DelError");
            req.removeAttribute("TrueDel");
            req.removeAttribute("TrueAdd");
            req.removeAttribute("languageEnglish");
            req.removeAttribute("languageUkraine");
            req.removeAttribute("ChangeError");
            req.removeAttribute("TrueChange");
            ModelShowUserAutorized.delete();
            ModelAddUserAutorized.delete();
            ModelDelUserAutorized.delete();
            ModelAddAmountAutorized.delete();
            ModelLanguageAdminSeventh.delete();
            ModelChangeAccess.delete();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (SeventhPageDB.checkConnection() == null)
            SeventhPageDB.startConnnection();

        if (req.getParameter("updateButton") != null) {
            ModelShowUserAutorized modelShowUserAutorized = ModelShowUserAutorized.getInstance();
            try {
                for (UserAutorizedShow userAuto : SeventhPageDB.userAuto())
                    modelShowUserAutorized.add(userAuto);
                LOGGER.debug("doPost in debug");
            } catch (Exception e) {
                modelShowUserAutorized.add(null);
                LOGGER.error("doPost " + e.getMessage());
            }
            resp.sendRedirect("/exhibition/userautorized");
        } else if (req.getParameter("addButtonAuto") != null) {

            Double amount = 0.0;
            String fistName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            String email = req.getParameter("email");
            if (req.getParameter("amount") != "")
                amount = Double.valueOf(req.getParameter("amount"));
            String role = req.getParameter("role");
            ModelAddUserAutorized modelAddUserAutorized = ModelAddUserAutorized.getInstance();
            boolean checkDel = SeventhPageDB.userAutoAdd(fistName, lastName, login, password, email, amount, role);
            if (checkDel == true)
                modelAddUserAutorized.add(true);
            else
                modelAddUserAutorized.add(false);
            resp.sendRedirect("/exhibition/userautorized");
        } else if (req.getParameter("delButtonAuto") != null) {
            ModelDelUserAutorized modelDelUserAutorized = ModelDelUserAutorized.getInstance();
            String email = req.getParameter("autoDel");
            boolean checkDel = SeventhPageDB.userAutoDel(email);
            if (checkDel == true)
                modelDelUserAutorized.add(true);
            else
                modelDelUserAutorized.add(false);
            resp.sendRedirect("/exhibition/userautorized");
        } else if (req.getParameter("ButtonMoney") != null) {
            String email = req.getParameter("emailMoney");
            Double money = Double.valueOf(req.getParameter("money"));
            ModelAddAmountAutorized modelAddAmountAutorized = ModelAddAmountAutorized.getInstance();
            boolean checkAmount = SeventhPageDB.userAmountAdd(email, money);
            if (checkAmount == true)
                modelAddAmountAutorized.add(true);
            else
                modelAddAmountAutorized.add(false);
            resp.sendRedirect("/exhibition/userautorized");
        } else if (req.getParameter("accessButtonServlet") != null) {
            String access = req.getParameter("access");
            String emailUser = req.getParameter("emailExhibitionAccess");
            ModelChangeAccess modelChangeAccess = ModelChangeAccess.getInstance();
            boolean checkAccess = SeventhPageDB.AccessFirstPage(emailUser, access);
            if (checkAccess == true)
                modelChangeAccess.add(true);
            else
                modelChangeAccess.add(false);
            resp.sendRedirect("/exhibition/userautorized");
        } else if (req.getParameter("roleBackButton") != null) {
            SeventhPageDB.RoleBackCommit();
            resp.sendRedirect("/exhibition/userautorized");
        } else if (req.getParameter("saveButton") != null) {
            SeventhPageDB.saveCommit();
            resp.sendRedirect("/exhibition/userautorized");
        } else if (req.getParameter("exitButton") != null) {
            req.getSession().removeAttribute("UserRole");
            SeventhPageDB.exitConnection();
            SeventhPageDB.nullConnection();
            resp.sendRedirect("/exhibition/userautorized");
        } else if (req.getParameter("AdminMainPagination") != null) {
            SeventhPageDB.exitConnection();
            SeventhPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminmain");
        } else if (req.getParameter("UserAutorizedPagination") != null) {
            SeventhPageDB.exitConnection();
            SeventhPageDB.nullConnection();
            resp.sendRedirect("/exhibition/userautorized");
        } else if (req.getParameter("AdminHallPagination") != null) {
            SeventhPageDB.exitConnection();
            SeventhPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminhall");
        } else if (req.getParameter("AdminAddressPagination") != null) {
            SeventhPageDB.exitConnection();
            SeventhPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminaddress");
        } else if (req.getParameter("AdminAuthorPagination") != null) {
            SeventhPageDB.exitConnection();
            SeventhPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminauthor");
        } else if (req.getParameter("AdminArtPagination") != null) {
            SeventhPageDB.exitConnection();
            SeventhPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminart");
        } else if (req.getParameter("AdminViewPagination") != null) {
            SeventhPageDB.exitConnection();
            SeventhPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminview");
        } else if (req.getParameter("AdminStatisticsExhibition") != null) {
            SeventhPageDB.exitConnection();
            SeventhPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminstatistics");
        } else if (req.getParameter("englishButton") != null) {
            ModelLanguageAdminSeventh modelLanguageAdminSeventh = ModelLanguageAdminSeventh.getInstance();
            modelLanguageAdminSeventh.add("en");
            resp.sendRedirect("/exhibition/userautorized");
        } else if (req.getParameter("ukraineButton") != null) {
            ModelLanguageAdminSeventh modelLanguageAdminSeventh = ModelLanguageAdminSeventh.getInstance();
            modelLanguageAdminSeventh.add("ua");
            resp.sendRedirect("/exhibition/userautorized");
        }
    }
}
