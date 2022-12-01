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
        if (req.getSession().getAttribute("UserRole") == null)
        {
            resp.sendRedirect("/exhibition/");
        }
        else
        {
            ModelShowAuthor modelShowAuthor=ModelShowAuthor.getInstance();
            ModelAddAuthor modelAddAuthor = ModelAddAuthor.getInstance();
            ModelDelAuthor modelDelAuthor = ModelDelAuthor.getInstance();

            if(FourthPageDB.checkConnection()==null)
                FourthPageDB.startConnnection();

            if (modelShowAuthor.listShow() != null) {
                if (modelShowAuthor.checkNull() == true) {
                    FourthPageDB.nullConnection();
                    req.setAttribute("Error", true);
                } else {
                    List<AuthorShow> address = modelShowAuthor.listShow();
                    req.setAttribute("FouthPageShow", address);
                }
            }
            else if (modelAddAuthor.modelCheck() != null) {
                if (modelAddAuthor.modelCheck().equals("false"))
                    req.setAttribute("AddError", true);
                else if (modelAddAuthor.modelCheck().equals("true"))
                    req.setAttribute("TrueAdd", true);

            }
            else if (modelDelAuthor.modelCheck() != null) {
                if (modelDelAuthor.modelCheck().equals("false"))
                    req.setAttribute("DelError", true);
                else if(modelDelAuthor.modelCheck().equals("true"))
                    req.setAttribute("TrueDel",true);
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
        ModelShowAuthor.delete();
        ModelAddAuthor.delete();
        ModelDelAuthor.delete();

        if(FourthPageDB.checkConnection()==null)
            FourthPageDB.startConnnection();

        if (req.getParameter("updateButton") != null)
        {
            ModelShowAuthor modelShowAuthor = ModelShowAuthor.getInstance();

            try {
                for (AuthorShow author : FourthPageDB.authorShow())
                    modelShowAuthor.add(author);
            } catch (Exception e) {
                modelShowAuthor.add(null);
            }
        }
        else if (req.getParameter("addButtonAuthor")!=null)
        {
            String firstName= req.getParameter("firstNameAuthor");
            String lastName = req.getParameter("lastNameAuthor");
            String email = req.getParameter("emailAuthor");

            ModelAddAuthor modelAddAuthor = ModelAddAuthor.getInstance();
            boolean checkDel = FourthPageDB.authorAdd(firstName,lastName,email);
            if (checkDel == true)
                modelAddAuthor.add(true);
            else
                modelAddAuthor.add(false);

        }
        else if (req.getParameter("delButtonAuthor") != null) {

            ModelDelAuthor modelDelAuthor = ModelDelAuthor.getInstance();

            String email = req.getParameter("authorDel");


            boolean checkDel = FourthPageDB.authorDel(email);
            if (checkDel == true)
                modelDelAuthor.add(true);
            else
                modelDelAuthor.add(false);
        }
        else if(req.getParameter("roleBackButton") != null)
            FourthPageDB.RoleBackCommit();
        else if (req.getParameter("saveButton") != null)
            FourthPageDB.saveCommit();
        else if(req.getParameter("exitButton")!=null)
            req.getSession().removeAttribute("UserRole");
        resp.sendRedirect("/exhibition/adminauthor");
    }
}
