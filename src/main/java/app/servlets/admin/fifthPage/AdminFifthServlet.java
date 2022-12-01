package app.servlets.admin.fifthPage;



import app.database.admin.fifthPage.FifthPageDB;
import app.entities.adminEntities.fifthPage.ArtShow;
import app.model.adminModels.fifthPage.ModelAddArt;
import app.model.adminModels.fifthPage.ModelDelArt;
import app.model.adminModels.fifthPage.ModelShowArt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminFifthServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("UserRole") == null)
        {
            resp.sendRedirect("/exhibition/");
        }
        else
        {
            ModelShowArt modelShowArt=ModelShowArt.getInstance();
            ModelAddArt modelAddArt = ModelAddArt.getInstance();
            ModelDelArt modelDelArt = ModelDelArt.getInstance();

            if(FifthPageDB.checkConnection()==null)
                FifthPageDB.startConnnection();

            if (modelShowArt.listShow() != null) {
                if (modelShowArt.checkNull() == true) {
                    FifthPageDB.nullConnection();
                    req.setAttribute("Error", true);
                } else {
                    List<ArtShow> art = modelShowArt.listShow();
                    req.setAttribute("FifthPageShow", art);
                }
            }
            else if (modelAddArt.modelCheck() != null) {
                if (modelAddArt.modelCheck().equals("false"))
                    req.setAttribute("AddError", true);
                else if (modelAddArt.modelCheck().equals("true"))
                    req.setAttribute("TrueAdd", true);

            }
            else if (modelDelArt.modelCheck() != null) {
                if (modelDelArt.modelCheck().equals("false"))
                    req.setAttribute("DelError", true);
                else if(modelDelArt.modelCheck().equals("true"))
                    req.setAttribute("TrueDel",true);
            }

            req.getRequestDispatcher("views/adminMenu/fifthPage/AdminFifthMenu.jsp").forward(req, resp);
            req.removeAttribute("FifthPageShow");
            req.removeAttribute("Error");
            req.removeAttribute("AddError");
            req.removeAttribute("DelError");
            req.removeAttribute("TrueDel");
            req.removeAttribute("TrueAdd");
            ModelShowArt.delete();
            ModelAddArt.delete();
            ModelDelArt.delete();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ModelShowArt.delete();
        ModelAddArt.delete();
        ModelDelArt.delete();

        if(FifthPageDB.checkConnection()==null)
            FifthPageDB.startConnnection();

        if (req.getParameter("updateButton") != null)
        {
            ModelShowArt modelShowArt = ModelShowArt.getInstance();

            try {
                for (ArtShow art : FifthPageDB.artShow())
                    modelShowArt.add(art);
            } catch (Exception e) {
                modelShowArt.add(null);
            }
        }
        else if (req.getParameter("addButtonArt")!=null)
        {
            String name= req.getParameter("NameArt");
            int yearCreation = Integer.parseInt(req.getParameter("CreationArt"));
            Double price = Double.parseDouble(req.getParameter("PriceArt"));

            ModelAddArt modelAddArt = ModelAddArt.getInstance();
            boolean checkDel = FifthPageDB.artAdd(name,yearCreation,price);
            if (checkDel == true)
                modelAddArt.add(true);
            else
                modelAddArt.add(false);

        }
        else if (req.getParameter("delButtonArt") != null) {

            ModelDelArt modelDelArt = ModelDelArt.getInstance();

            String name = req.getParameter("artDel");


            boolean checkDel = FifthPageDB.artDel(name);
            if (checkDel == true)
                modelDelArt.add(true);
            else
                modelDelArt.add(false);
        }
        else if(req.getParameter("roleBackButton") != null)
            FifthPageDB.RoleBackCommit();
        else if (req.getParameter("saveButton") != null)
            FifthPageDB.saveCommit();
        else if(req.getParameter("exitButton")!=null)
            req.getSession().removeAttribute("UserRole");
        resp.sendRedirect("/exhibition/adminart");
    }
}
