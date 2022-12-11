package app.servlets.admin.fifthPage;


import app.database.admin.fifthPage.FifthPageDB;
import app.database.admin.firstPage.FirstPageDB;
import app.entities.adminEntities.fifthPage.ArtAddShow;
import app.entities.adminEntities.fifthPage.ArtShow;
import app.entities.adminEntities.firstPage.AdminAddShow;
import app.model.adminModels.fifthPage.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminFifthServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("UserRole") == null || req.getSession().getAttribute("UserRole").equals("1")) {
            resp.sendRedirect("/exhibition/");
        } else {
            ModelShowArt modelShowArt = ModelShowArt.getInstance();
            ModelAddArt modelAddArt = ModelAddArt.getInstance();
            ModelDelArt modelDelArt = ModelDelArt.getInstance();
            ModelAddShow modelAddShow = ModelAddShow.getInstance();

            if (FifthPageDB.checkConnection() == null)
                FifthPageDB.startConnnection();

            try {
                for (ArtAddShow addShow : FifthPageDB.addArtShow())
                    modelAddShow.add(addShow);
            } catch (Exception e) {
                modelAddShow.add(null);
            }

            if (modelAddShow.listShow() != null) {
                List<ArtAddShow> add = modelAddShow.listShow();
                req.setAttribute("AddShow", add);

            } else {
                FifthPageDB.nullConnection();
                req.setAttribute("Error", true);
            }

            if (modelShowArt.listShow() != null) {
                if (modelShowArt.checkNull() == true) {
                    FifthPageDB.nullConnection();
                    req.setAttribute("Error", true);
                } else {
                    List<ArtShow> art = modelShowArt.listShow();
                    for(ArtShow arts: art)
                    {
                        System.out.println("ss"+ arts.getFullName());
                    }
                    req.setAttribute("FifthPageShow", art);
                }
            } else if (modelAddArt.modelCheck() != null) {
                if (modelAddArt.modelCheck().equals("false"))
                    req.setAttribute("AddError", true);
                else if (modelAddArt.modelCheck().equals("true"))
                    req.setAttribute("TrueAdd", true);

            } else if (modelDelArt.modelCheck() != null) {
                if (modelDelArt.modelCheck().equals("false"))
                    req.setAttribute("DelError", true);
                else if (modelDelArt.modelCheck().equals("true"))
                    req.setAttribute("TrueDel", true);
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
            ModelAddShow.delete();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (FifthPageDB.checkConnection() == null)
            FifthPageDB.startConnnection();

        if (req.getParameter("updateButton") != null) {
            ModelShowArt modelShowArt = ModelShowArt.getInstance();
            try {
                for (ArtShow art : FifthPageDB.artShow())
                    modelShowArt.add(art);
            } catch (Exception e) {
                modelShowArt.add(null);
            }
            resp.sendRedirect("/exhibition/adminart");
        } else if (req.getParameter("addButtonArt") != null) {
            String name = req.getParameter("NameArt");
            int yearCreation = Integer.parseInt(req.getParameter("CreationArt"));
            Double price = Double.parseDouble(req.getParameter("PriceArt"));
            List<String> author=List.of(req.getParameterValues("author"));
            List<String> view=List.of(req.getParameterValues("view"));
            ModelAddArt modelAddArt = ModelAddArt.getInstance();
            boolean checkDel = FifthPageDB.artAdd(name, yearCreation, price,author,view);
            if (checkDel == true)
                modelAddArt.add(true);
            else
                modelAddArt.add(false);
            resp.sendRedirect("/exhibition/adminart");
        } else if (req.getParameter("delButtonArt") != null) {

            ModelDelArt modelDelArt = ModelDelArt.getInstance();
            String name = req.getParameter("artDel");
            boolean checkDel = FifthPageDB.artDel(name);
            if (checkDel == true)
                modelDelArt.add(true);
            else
                modelDelArt.add(false);
            resp.sendRedirect("/exhibition/adminart");
        } else if (req.getParameter("roleBackButton") != null) {
            FifthPageDB.RoleBackCommit();
            resp.sendRedirect("/exhibition/adminart");
        } else if (req.getParameter("saveButton") != null) {
            FifthPageDB.saveCommit();
            resp.sendRedirect("/exhibition/adminart");
        } else if (req.getParameter("exitButton") != null) {
            req.getSession().removeAttribute("UserRole");
            FifthPageDB.exitConnection();
            FifthPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminart");
        } else if (req.getParameter("AdminMainPagination") != null) {
            FifthPageDB.exitConnection();
            FifthPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminmain");
        } else if (req.getParameter("UserAutorizedPagination") != null) {
            FifthPageDB.exitConnection();
            FifthPageDB.nullConnection();
            resp.sendRedirect("/exhibition/userautorized");
        } else if (req.getParameter("AdminHallPagination") != null) {
            FifthPageDB.exitConnection();
            FifthPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminhall");
        } else if (req.getParameter("AdminAddressPagination") != null) {
            FifthPageDB.exitConnection();
            FifthPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminaddress");
        } else if (req.getParameter("AdminAuthorPagination") != null) {
            FifthPageDB.exitConnection();
            FifthPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminauthor");
        } else if (req.getParameter("AdminArtPagination") != null) {
            FifthPageDB.exitConnection();
            FifthPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminart");
        } else if (req.getParameter("AdminViewPagination") != null) {
            FifthPageDB.exitConnection();
            FifthPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminview");
        }
    }
}
