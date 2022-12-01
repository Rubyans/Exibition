package app.servlets.admin.sixthPage;

import app.database.admin.sixthPage.SixthPageDB;
import app.entities.adminEntities.sixthPage.ViewShow;
import app.model.adminModels.sixthPage.ModelAddView;
import app.model.adminModels.sixthPage.ModelDelView;
import app.model.adminModels.sixthPage.ModelShowView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminSixthServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("UserRole") == null)
        {
            resp.sendRedirect("/exhibition/");
        }
        else
        {
            ModelShowView modelShowView=ModelShowView.getInstance();
            ModelAddView modelAddView = ModelAddView.getInstance();
            ModelDelView modelDelView = ModelDelView.getInstance();

            if(SixthPageDB.checkConnection()==null)
                SixthPageDB.startConnnection();

            if (modelShowView.listShow() != null) {
                if (modelShowView.checkNull() == true) {
                    SixthPageDB.nullConnection();
                    req.setAttribute("Error", true);
                } else {
                    List<ViewShow> art = modelShowView.listShow();
                    req.setAttribute("SixthPageShow", art);
                }
            }
            else if (modelAddView.modelCheck() != null) {
                if (modelAddView.modelCheck().equals("false"))
                    req.setAttribute("AddError", true);
                else if (modelAddView.modelCheck().equals("true"))
                    req.setAttribute("TrueAdd", true);

            }
            else if (modelDelView.modelCheck() != null) {
                if (modelDelView.modelCheck().equals("false"))
                    req.setAttribute("DelError", true);
                else if(modelDelView.modelCheck().equals("true"))
                    req.setAttribute("TrueDel",true);
            }

            req.getRequestDispatcher("views/adminMenu/sixthPage/AdminSixthMenu.jsp").forward(req, resp);
            req.removeAttribute("SixthPageShow");
            req.removeAttribute("Error");
            req.removeAttribute("AddError");
            req.removeAttribute("DelError");
            req.removeAttribute("TrueDel");
            req.removeAttribute("TrueAdd");

            ModelShowView.delete();
            ModelAddView.delete();
            ModelDelView.delete();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ModelShowView.delete();
        ModelAddView.delete();
        ModelDelView.delete();

        if(SixthPageDB.checkConnection()==null)
            SixthPageDB.startConnnection();

        if (req.getParameter("updateButton") != null)
        {
            ModelShowView modelShowView = ModelShowView.getInstance();

            try {
                for (ViewShow view : SixthPageDB.viewShow())
                    modelShowView.add(view);
            } catch (Exception e) {
                modelShowView.add(null);
            }
        }
        else if (req.getParameter("addButtonView")!=null)
        {
            String name= req.getParameter("NameView");

            ModelAddView modelAddView = ModelAddView.getInstance();
            boolean checkDel = SixthPageDB.viewAdd(name);
            if (checkDel == true)
                modelAddView.add(true);
            else
                modelAddView.add(false);

        }
        else if (req.getParameter("delButtonView") != null) {

            ModelDelView modelDelView = ModelDelView.getInstance();

            String name = req.getParameter("viewDel");

            System.out.println("nnnnnnaaa"+name);
            boolean checkDel = SixthPageDB.viewDel(name);
            if (checkDel == true)
                modelDelView.add(true);
            else
                modelDelView.add(false);
        }
        else if(req.getParameter("roleBackButton") != null)
            SixthPageDB.RoleBackCommit();
        else if (req.getParameter("saveButton") != null)
            SixthPageDB.saveCommit();
        else if(req.getParameter("exitButton")!=null)
            req.getSession().removeAttribute("UserRole");
        resp.sendRedirect("/exhibition/adminview");
    }
}
