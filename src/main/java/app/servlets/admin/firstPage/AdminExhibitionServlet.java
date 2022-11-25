package app.servlets.admin.firstPage;

import app.database.admin.firstPage.FirstPageDB;
import app.entities.adminEntities.firstPage.AdminAddShow;
import app.entities.adminEntities.firstPage.AdminShow;
import app.model.adminModels.firstPage.ModelAddExhibition;
import app.model.adminModels.firstPage.ModelAddShow;
import app.model.adminModels.firstPage.ModelDel;
import app.model.adminModels.firstPage.ModelShow;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminExhibitionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        if (req.getSession().getAttribute("UserRole") == null) {
            resp.sendRedirect("/exhibition/");
        } else {
            ModelShow showModel = ModelShow.getInstance();
            ModelAddShow AddModelShow = ModelAddShow.getInstance();
            ModelAddExhibition modelAddExhibition = ModelAddExhibition.getInstance();
            ModelDel modelDel = ModelDel.getInstance();

            try {
                for (AdminAddShow addShow : FirstPageDB.ShowAddFirstPage()) {
                    AddModelShow.add(addShow);
                }
            } catch (Exception e) {
                AddModelShow.add(null);
            }

            if (AddModelShow.listShow() != null) {
                List<AdminAddShow> add = AddModelShow.listShow();
                req.setAttribute("AddShow", add);

            } else {
                req.setAttribute("Error", true);
            }


            if (showModel.listShow() != null)
            {
                if (showModel.checkNull() == true) {
                    req.setAttribute("Error", true);
                } else {
                    List<AdminShow> names = showModel.listShow();
                    req.setAttribute("FirstPage", names);
                    req.setAttribute("Error", false);
                }
            } else if (modelAddExhibition.modelCheck() != null) {
                if (modelAddExhibition.modelCheck().equals("false"))
                    req.setAttribute("AddError", true);
                else if(modelAddExhibition.modelCheck().equals("true"))
                    req.setAttribute("TrueAdd",true);
            } else if (modelDel.modelCheck() != null) {
                if (modelDel.modelCheck().equals("false"))
                    req.setAttribute("DelError", true);
                else if(modelDel.modelCheck().equals("true"))
                    req.setAttribute("TrueDel",true);
            }

            req.getRequestDispatcher("views/adminMenu/firstPage/AdminFirstMenu.jsp").forward(req, resp);
            req.removeAttribute("GuestList");
            req.removeAttribute("Error");
            req.removeAttribute("AddError");
            req.removeAttribute("DelError");
            req.removeAttribute("TrueAdd");
            req.removeAttribute("TrueDel");
            ModelShow.delete();
            ModelAddShow.delete();
            ModelAddExhibition.delete();
            ModelDel.delete();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ModelAddShow.delete();
        ModelShow.delete();
        ModelAddExhibition.delete();
        ModelDel.delete();

        System.out.println("Nice");

        if (req.getParameter("updateButton") != null) {
            ModelShow showModel = ModelShow.getInstance();
            try {
                for (AdminShow show : FirstPageDB.exibitionShow()) {
                    showModel.add(show);
                }

            } catch (Exception e) {
                showModel.add(null);
            }

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

        } else if (req.getParameter("delButtonServlet") != null) {

            String nameExhibitionDel = req.getParameter("nameExhibitionDel");
            ModelDel modelDel = ModelDel.getInstance();

            boolean checkDel = FirstPageDB.DelFirstPage(nameExhibitionDel);
            if (checkDel == true)
                modelDel.add(true);
            else
                modelDel.add(false);
        }
        else if(req.getParameter("roleBackButton") != null)
            FirstPageDB.RoleBackCommit();
        else if (req.getParameter("saveButton") != null)
            FirstPageDB.SaveCommit();

        resp.sendRedirect("/exhibition/adminmain");
    }
}
