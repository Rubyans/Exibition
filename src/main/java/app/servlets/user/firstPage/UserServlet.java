package app.servlets.user.firstPage;

import app.database.user.firstPage.FirstPageDB;
import app.entities.userEntities.firstPage.UserShowAdd;
import app.entities.userEntities.firstPage.UserShowExhibition;
import app.entities.userEntities.firstPage.UserShowMoney;
import app.model.userModels.firstPage.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession().getAttribute("UserRole") == null) {
            resp.sendRedirect("/exhibition/");
        } else {
            ModelShowExhibition modelShowExhibition = ModelShowExhibition.getInstance();
            ModelShowAdd modelShowAdd = ModelShowAdd.getInstance();
            ModelShowMoney modelShowMoney = ModelShowMoney.getInstance();
            ModelAddTicket modelAddTicket = ModelAddTicket.getInstance();
            ModelRoleBackCommit modelRoleBackCommit = ModelRoleBackCommit.getInstance();
            ModelSaveCommit modelSaveCommit = ModelSaveCommit.getInstance();
            if (FirstPageDB.checkConnection() == null)
                FirstPageDB.startConnnection();

            try {
                for (UserShowAdd showAdd : FirstPageDB.ShowAddExhibition()) {
                    modelShowAdd.add(showAdd);
                }
                String userId = (String) req.getSession().getAttribute("UserId");
                for (UserShowMoney showMoney : FirstPageDB.ShowMoney(userId))
                    modelShowMoney.add(showMoney);

            } catch (Exception e) {
                modelShowAdd.add(null);
                modelShowMoney.add(null);
            }

            if (modelShowAdd.listShow() != null) {
                List<UserShowAdd> listAdd = modelShowAdd.listShow();
                req.setAttribute("AddShow", listAdd);
                List<UserShowMoney> listMoney = modelShowMoney.listShow();
                req.setAttribute("Money", listMoney);
            } else {
                FirstPageDB.nullConnection();
                req.setAttribute("Error", true);
            }
            if (modelShowExhibition.listShow() != null) {
                if (modelShowExhibition.checkNull() == true) {
                    FirstPageDB.nullConnection();
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
            ModelShowExhibition.delete();
            ModelShowMoney.delete();
            ModelShowAdd.delete();
            ModelAddTicket.delete();
            ModelRoleBackCommit.delete();
            ModelSaveCommit.delete();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (FirstPageDB.checkConnection() == null)
            FirstPageDB.startConnnection();

        if (req.getParameter("updateButton") != null) {
            ModelShowExhibition modelShowExhibition = ModelShowExhibition.getInstance();
            String userId = (String) req.getSession().getAttribute("UserId");
            try {
                for (UserShowExhibition user : FirstPageDB.userShowEx(userId)) {
                    modelShowExhibition.add(user);
                }
            } catch (Exception e) {
                modelShowExhibition.add(null);
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
            boolean checkAdd = FirstPageDB.RoleBackCommit();
            if (checkAdd == true)
                modelRoleBackCommit.add(true);
            else
                modelRoleBackCommit.add(false);
            resp.sendRedirect("/exhibition/user");
        } else if (req.getParameter("saveButton") != null) {
            ModelSaveCommit modelSaveCommit = ModelSaveCommit.getInstance();
            boolean checkAdd = FirstPageDB.SaveCommit();
            if (checkAdd == true)
                modelSaveCommit.add(true);
            else
                modelSaveCommit.add(false);
            resp.sendRedirect("/exhibition/user");
        } else if (req.getParameter("exitButton") != null) {
            req.getSession().removeAttribute("UserRole");
            FirstPageDB.exitConnection();
            FirstPageDB.nullConnection();
            resp.sendRedirect("/exhibition/user");
        } else if (req.getParameter("UserPagination") != null) {
            FirstPageDB.exitConnection();
            FirstPageDB.nullConnection();
            resp.sendRedirect("/exhibition/user");
        } else if (req.getParameter("UserExhibitionPagination") != null) {
            FirstPageDB.exitConnection();
            FirstPageDB.nullConnection();
            resp.sendRedirect("/exhibition/userexhibition");
        }
    }
}