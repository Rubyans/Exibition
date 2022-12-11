package app.servlets.admin.fourthPage;

import app.database.admin.fourthPage.FourthPageDB;
import app.entities.adminEntities.fourthPage.AuthorShow;
import app.model.adminModels.fourthPage.ModelAddAuthor;
import app.model.adminModels.fourthPage.ModelDelAuthor;
import app.model.adminModels.fourthPage.ModelShowAuthor;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminFourthServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("UserRole") == null || req.getSession().getAttribute("UserRole").equals("1")) {
            resp.sendRedirect("/exhibition/");
        } else {
            ModelShowAuthor modelShowAuthor = ModelShowAuthor.getInstance();
            ModelAddAuthor modelAddAuthor = ModelAddAuthor.getInstance();
            ModelDelAuthor modelDelAuthor = ModelDelAuthor.getInstance();

            if (FourthPageDB.checkConnection() == null)
                FourthPageDB.startConnnection();

            if (modelShowAuthor.listShow() != null) {
                if (modelShowAuthor.checkNull() == true) {
                    FourthPageDB.nullConnection();
                    req.setAttribute("Error", true);
                } else {
                    List<AuthorShow> address = modelShowAuthor.listShow();
                    req.setAttribute("FouthPageShow", address);
                }
            } else if (modelAddAuthor.modelCheck() != null) {
                if (modelAddAuthor.modelCheck().equals("false"))
                    req.setAttribute("AddError", true);
                else if (modelAddAuthor.modelCheck().equals("true"))
                    req.setAttribute("TrueAdd", true);
            } else if (modelDelAuthor.modelCheck() != null) {
                if (modelDelAuthor.modelCheck().equals("false"))
                    req.setAttribute("DelError", true);
                else if (modelDelAuthor.modelCheck().equals("true"))
                    req.setAttribute("TrueDel", true);
            }
            req.getRequestDispatcher("views/adminMenu/fourthPage/AdminFourthMenu.jsp").forward(req, resp);
            req.removeAttribute("FouthPageShow");
            req.removeAttribute("Error");
            req.removeAttribute("AddError");
            req.removeAttribute("DelError");
            req.removeAttribute("TrueDel");
            req.removeAttribute("TrueAdd");
            ModelShowAuthor.delete();
            ModelAddAuthor.delete();
            ModelDelAuthor.delete();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (FourthPageDB.checkConnection() == null)
            FourthPageDB.startConnnection();

        if (req.getParameter("updateButton") != null) {
            ModelShowAuthor modelShowAuthor = ModelShowAuthor.getInstance();
            try {
                for (AuthorShow author : FourthPageDB.authorShow())
                    modelShowAuthor.add(author);
            } catch (Exception e) {
                modelShowAuthor.add(null);
            }
            resp.sendRedirect("/exhibition/adminauthor");
        } else if (req.getParameter("addButtonAuthor") != null) {
            String firstName = req.getParameter("firstNameAuthor");
            String lastName = req.getParameter("lastNameAuthor");
            String email = req.getParameter("emailAuthor");

            ModelAddAuthor modelAddAuthor = ModelAddAuthor.getInstance();
            boolean checkDel = FourthPageDB.authorAdd(firstName, lastName, email);
            if (checkDel == true)
                modelAddAuthor.add(true);
            else
                modelAddAuthor.add(false);
            resp.sendRedirect("/exhibition/adminauthor");
        } else if (req.getParameter("delButtonAuthor") != null) {

            ModelDelAuthor modelDelAuthor = ModelDelAuthor.getInstance();
            String email = req.getParameter("authorDel");
            boolean checkDel = FourthPageDB.authorDel(email);
            if (checkDel == true)
                modelDelAuthor.add(true);
            else
                modelDelAuthor.add(false);
            resp.sendRedirect("/exhibition/adminauthor");
        } else if (req.getParameter("roleBackButton") != null) {
            FourthPageDB.RoleBackCommit();
            resp.sendRedirect("/exhibition/adminauthor");
        } else if (req.getParameter("saveButton") != null) {
            FourthPageDB.saveCommit();
            resp.sendRedirect("/exhibition/adminauthor");
        } else if (req.getParameter("exitButton") != null) {
            req.getSession().removeAttribute("UserRole");
            FourthPageDB.exitConnection();
            FourthPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminauthor");
        } else if (req.getParameter("AdminMainPagination") != null) {
            FourthPageDB.exitConnection();
            FourthPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminmain");
        } else if (req.getParameter("UserAutorizedPagination") != null) {
            FourthPageDB.exitConnection();
            FourthPageDB.nullConnection();
            resp.sendRedirect("/exhibition/userautorized");
        } else if (req.getParameter("AdminHallPagination") != null) {
            FourthPageDB.exitConnection();
            FourthPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminhall");
        } else if (req.getParameter("AdminAddressPagination") != null) {
            FourthPageDB.exitConnection();
            FourthPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminaddress");
        } else if (req.getParameter("AdminAuthorPagination") != null) {
            FourthPageDB.exitConnection();
            FourthPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminauthor");
        } else if (req.getParameter("AdminArtPagination") != null) {
            FourthPageDB.exitConnection();
            FourthPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminart");
        } else if (req.getParameter("AdminViewPagination") != null) {
            FourthPageDB.exitConnection();
            FourthPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminview");
        }
    }
}
