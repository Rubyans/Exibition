package app.controller.servlets.user.firstPage;

import app.DAO.connectionDAO.HikariConnectDB;
import app.DAO.sqlFunctions.user.firstPage.FirstPageDB;
import app.DAO.entities.userEntities.firstPage.UserShowAdd;
import app.DAO.entities.userEntities.firstPage.UserShowExhibition;
import app.DAO.entities.userEntities.firstPage.UserShowMoney;
import app.models.userModels.firstPage.*;
import app.service.changeLanguage.ChangeLanguage;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(UserServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession().getAttribute("UserRole") == null) {
            resp.sendRedirect("/exhibition/auto");
        } else {
            ModelShowExhibition modelShowExhibition = ModelShowExhibition.getInstance();
            ModelShowAdd modelShowAdd = ModelShowAdd.getInstance();
            ModelShowMoney modelShowMoney = ModelShowMoney.getInstance();
            ModelAddTicket modelAddTicket = ModelAddTicket.getInstance();
            ModelRoleBackCommit modelRoleBackCommit = ModelRoleBackCommit.getInstance();
            ModelSaveCommit modelSaveCommit = ModelSaveCommit.getInstance();
            ModelLanguageUserFirst modelLanguageUserFirst = ModelLanguageUserFirst.getInstance();

            if (modelLanguageUserFirst.modelCheck() != null) {
                if (modelLanguageUserFirst.modelCheck().equals("en")) {
                    req.getSession().setAttribute("language", "en");
                }
                if (modelLanguageUserFirst.modelCheck().equals("ua")) {
                    req.getSession().setAttribute("language", "ua");
                }
            }

            if (req.getSession().getAttribute("language") != null) {
                if (req.getSession().getAttribute("language").equals("en")) {
                    req.setAttribute("languageChange", ChangeLanguage.changeEN("language.properties","userFirst"));
                } else if (req.getSession().getAttribute("language").equals("ua")) {
                    req.setAttribute("languageChange", ChangeLanguage.changeUA("language.properties","userFirst"));
                }
            } else {
                req.setAttribute("languageChange", ChangeLanguage.changeUA("language.properties","userFirst"));
            }

            try {
                for (UserShowAdd showAdd : FirstPageDB.showAddExhibition()) {
                    modelShowAdd.add(showAdd);
                }
                String userId = (String) req.getSession().getAttribute("UserId");
                for (UserShowMoney showMoney : FirstPageDB.showMoney(userId))
                    modelShowMoney.add(showMoney);
                LOGGER.debug("doGet in debug");
            } catch (Exception e) {
                modelShowAdd.add(null);
                modelShowMoney.add(null);
                LOGGER.error("doGet " + e.getMessage());
            }

            if (modelShowAdd.listShow() != null) {
                List<UserShowAdd> listAdd = modelShowAdd.listShow();
                req.setAttribute("AddShow", listAdd);
                List<UserShowMoney> listMoney = modelShowMoney.listShow();
                req.setAttribute("Money", listMoney);
            } else {
                req.setAttribute("Error", true);
            }
            if (modelShowExhibition.listShow() != null) {
                if (modelShowExhibition.checkNull() == true) {
                    req.setAttribute("Error", true);
                } else {
                    List<UserShowExhibition> address = modelShowExhibition.listShow();
                    req.setAttribute("FirstPageUser", address);
                }
            } else if (modelAddTicket.modelCheck() != null) {
                if (modelAddTicket.modelCheck().equals("false"))
                    req.setAttribute("AddError", true);
                else if (modelAddTicket.modelCheck().equals("true"))
                    req.setAttribute("TrueAdd", true);
            } else if (modelSaveCommit.modelCheck() != null) {
                if (modelSaveCommit.modelCheck().equals("false"))
                    req.setAttribute("SaveCommitError", true);
                else if (modelSaveCommit.modelCheck().equals("true"))
                    req.setAttribute("SaveCommitTrue", true);
            } else if (modelRoleBackCommit.modelCheck() != null) {
                if (modelRoleBackCommit.modelCheck().equals("false"))
                    req.setAttribute("RoleBackCommitError", true);
                else if (modelRoleBackCommit.modelCheck().equals("true"))
                    req.setAttribute("RoleBackCommitTrue", true);
            }
            req.getRequestDispatcher("views/userMenu/firstPage/UserFirstMenu.jsp").forward(req, resp);
            req.removeAttribute("FirstPageUser");
            req.removeAttribute("Error");
            req.removeAttribute("AddShow");
            req.removeAttribute("Money");
            req.removeAttribute("AddError");
            req.removeAttribute("TrueAdd");
            req.removeAttribute("SaveCommitError");
            req.removeAttribute("SaveCommitTrue");
            req.removeAttribute("RoleBackCommitError");
            req.removeAttribute("RoleBackCommitTrue");
            req.removeAttribute("languageChange");
            ModelShowExhibition.delete();
            ModelShowMoney.delete();
            ModelShowAdd.delete();
            ModelAddTicket.delete();
            ModelRoleBackCommit.delete();
            ModelSaveCommit.delete();
            ModelLanguageUserFirst.delete();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        if (req.getParameter("updateButton") != null) {
            ModelShowExhibition modelShowExhibition = ModelShowExhibition.getInstance();
            String userId = (String) req.getSession().getAttribute("UserId");
            try {
                for (UserShowExhibition user : FirstPageDB.userShowEx(userId)) {
                    modelShowExhibition.add(user);
                }
                LOGGER.debug("doGet in debug");
            } catch (Exception e) {
                modelShowExhibition.add(null);
                LOGGER.error("doPost " + e.getMessage());
            }
            resp.sendRedirect("/exhibition/user");
        } else if (req.getParameter("addButtonTicket") != null) {
            List<String> nameExhibition = List.of(req.getParameterValues("nameExhibition"));
            String userId = (String) req.getSession().getAttribute("UserId");
            ModelAddTicket modelAddTicket = ModelAddTicket.getInstance();

            boolean checkAdd = FirstPageDB.ticketAdd(nameExhibition, userId);
            if (checkAdd == true)
                modelAddTicket.add(true);
            else
                modelAddTicket.add(false);
            resp.sendRedirect("/exhibition/user");
        } else if (req.getParameter("roleBackButton") != null) {
            ModelRoleBackCommit modelRoleBackCommit = ModelRoleBackCommit.getInstance();
            boolean checkAdd = HikariConnectDB.roleBackCommit();
            if (checkAdd == true)
                modelRoleBackCommit.add(true);
            else
                modelRoleBackCommit.add(false);
            resp.sendRedirect("/exhibition/user");
        } else if (req.getParameter("saveButton") != null) {
            ModelSaveCommit modelSaveCommit = ModelSaveCommit.getInstance();
            boolean checkAdd = HikariConnectDB.saveCommit();
            if (checkAdd == true)
                modelSaveCommit.add(true);
            else
                modelSaveCommit.add(false);
            resp.sendRedirect("/exhibition/user");
        } else if (req.getParameter("exitButton") != null) {
            req.getSession().removeAttribute("UserRole");
            HikariConnectDB.exitConnection();
            resp.sendRedirect("/exhibition/user");
        } else if (req.getParameter("UserPagination") != null) {
            resp.sendRedirect("/exhibition/user");
        } else if (req.getParameter("UserExhibitionPagination") != null) {
            resp.sendRedirect("/exhibition/userexhibition");
        } else if (req.getParameter("englishButton") != null) {
            ModelLanguageUserFirst modelLanguageUserFirst = ModelLanguageUserFirst.getInstance();
            modelLanguageUserFirst.add("en");
            resp.sendRedirect("/exhibition/user");
        } else if (req.getParameter("ukraineButton") != null) {
            ModelLanguageUserFirst modelLanguageUserFirst = ModelLanguageUserFirst.getInstance();
            modelLanguageUserFirst.add("ua");
            resp.sendRedirect("/exhibition/user");
        }
    }
}
