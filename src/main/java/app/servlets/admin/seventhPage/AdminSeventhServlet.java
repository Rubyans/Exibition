package app.servlets.admin.seventhPage;


import app.database.admin.seventhPage.SeventhPageDB;
import app.entities.adminEntities.seventhPage.UserAutorizedShow;
import app.model.adminModels.seventhPage.ModelAddAmountAutorized;
import app.model.adminModels.seventhPage.ModelAddUserAutorized;
import app.model.adminModels.seventhPage.ModelDelUserAutorized;
import app.model.adminModels.seventhPage.ModelShowUserAutorized;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminSeventhServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("UserRole") == null) {
            resp.sendRedirect("/exhibition/");
        } else {
            ModelShowUserAutorized modelShowUserAutorized = ModelShowUserAutorized.getInstance();
            ModelAddUserAutorized modelAddUserAutorized = ModelAddUserAutorized.getInstance();
            ModelAddAmountAutorized modelAddAmountAutorized = ModelAddAmountAutorized.getInstance();
            ModelDelUserAutorized modelDelUserAutorized = ModelDelUserAutorized.getInstance();
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
            } else if (modelAddAmountAutorized.modelCheck()!=null)
            {
                System.out.println("mooo "+modelAddAmountAutorized.modelCheck());

                if(modelAddAmountAutorized.modelCheck().equals("true"))
                    req.setAttribute("AddAmount",true);
                else if (modelAddAmountAutorized.modelCheck().equals("false"))
                    req.setAttribute("AddAmountError", true);
            }
            else if (modelDelUserAutorized.modelCheck() != null) {
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
            ModelShowUserAutorized.delete();
            ModelAddUserAutorized.delete();
            ModelDelUserAutorized.delete();
            ModelAddAmountAutorized.delete();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (SeventhPageDB.checkConnection() == null)
            SeventhPageDB.startConnnection();

        if (req.getParameter("updateButton") != null) {
            ModelShowUserAutorized modelShowUserAutorized = ModelShowUserAutorized.getInstance();

            try {
                for (UserAutorizedShow userAuto : SeventhPageDB.UserAuto())
                    modelShowUserAutorized.add(userAuto);
            } catch (Exception e) {
                modelShowUserAutorized.add(null);
            }
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
            boolean checkDel = SeventhPageDB.UserAutoAdd(fistName, lastName, login, password, email, amount, role);
            if (checkDel == true)
                modelAddUserAutorized.add(true);
            else
                modelAddUserAutorized.add(false);

        } else if (req.getParameter("delButtonAuto") != null) {
            ModelDelUserAutorized modelDelUserAutorized = ModelDelUserAutorized.getInstance();
            String email = req.getParameter("autoDel");
            boolean checkDel = SeventhPageDB.UserAutoDel(email);
            if (checkDel == true)
                modelDelUserAutorized.add(true);
            else
                modelDelUserAutorized.add(false);
        } else if (req.getParameter("ButtonMoney") != null) {
            String email = req.getParameter("emailMoney");

            Double money = Double.valueOf(req.getParameter("money"));
            ModelAddAmountAutorized modelAddAmountAutorized = ModelAddAmountAutorized.getInstance();
            boolean checkAmount = SeventhPageDB.UserAmountAdd(email, money);

            if (checkAmount == true)
                modelAddAmountAutorized.add(true);
            else
                modelAddAmountAutorized.add(false);
        } else if (req.getParameter("roleBackButton") != null)
            SeventhPageDB.RoleBackCommit();
        else if (req.getParameter("saveButton") != null)
            SeventhPageDB.saveCommit();
        else if (req.getParameter("exitButton") != null)
            req.getSession().removeAttribute("UserRole");

        resp.sendRedirect("/exhibition/userautorized");
    }
}
