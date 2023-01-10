package app.controllerFront.commands.admin.fifthPage;

import app.DAO.connectionDAO.HikariConnectDB;
import app.DAO.entities.adminEntities.fifthPage.ArtAddShow;
import app.DAO.entities.adminEntities.fifthPage.ArtShow;
import app.DAO.sqlFunctions.admin.fifthPage.FifthPageDB;
import app.controllerFront.commands.interfaceCommand.Command;
import app.controllerFront.models.adminModels.fifthPage.*;
import app.service.changeLanguage.ChangeLanguage;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FifthPageCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(FifthPageCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, int request) throws ServletException, IOException {
        if (request == 1) {
/////////////////////////////////////GET-Request/////////////////////////////////////////////////////////////////
            ModelShowArt modelShowArt = ModelShowArt.getInstance();
            ModelAddArt modelAddArt = ModelAddArt.getInstance();
            ModelDelArt modelDelArt = ModelDelArt.getInstance();
            ModelAddShow modelAddShow = ModelAddShow.getInstance();

            if (req.getSession().getAttribute("language") != null) {
                if (req.getSession().getAttribute("language").equals("en")) {
                    req.setAttribute("languageChange", ChangeLanguage.changeEN("languageEN.properties", "adminFifth"));
                } else if (req.getSession().getAttribute("language").equals("ua")) {
                    req.setAttribute("languageChange", ChangeLanguage.changeUA("languageUA.properties", "adminFifth"));
                }
            } else {
                req.setAttribute("languageChange", ChangeLanguage.changeUA("languageUA.properties", "adminFifth"));
            }
            try {
                for (ArtAddShow addShow : FifthPageDB.addArtShow())
                    modelAddShow.add(addShow);
                LOGGER.debug("doGet in debug");
            } catch (Exception e) {
                LOGGER.error("doGet " + e.getMessage());
                modelAddShow.add(null);
            }

            if (modelAddShow.listShow() != null) {
                List<ArtAddShow> add = modelAddShow.listShow();
                req.setAttribute("AddShow", add);

            } else {
                req.setAttribute("Error", true);
            }
            if (modelShowArt.listShow() != null) {
                if (modelShowArt.checkNull() == true) {
                    req.setAttribute("Error", true);
                } else {
                    List<ArtShow> art = modelShowArt.listShow();
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
            req.removeAttribute("languageChange");
            ModelShowArt.delete();
            ModelAddArt.delete();
            ModelDelArt.delete();
            ModelAddShow.delete();
        } else {
/////////////////////////////////////Post-Request///////////////////////////////////////////////////////////////
            if (req.getParameter("updateButton") != null) {
                ModelShowArt modelShowArt = ModelShowArt.getInstance();
                try {
                    String valueRows=req.getParameter("UpdateSort");
                    for (ArtShow art : FifthPageDB.artShow(valueRows))
                        modelShowArt.add(art);
                    LOGGER.debug("doPost in debug");
                } catch (Exception e) {
                    LOGGER.error("doPost " + e.getMessage());
                    modelShowArt.add(null);
                }
                resp.sendRedirect("exhibition?command=adminart");
            } else if (req.getParameter("addButtonArt") != null) {
                String name = req.getParameter("NameArt");
                Integer yearCreation = Integer.parseInt(req.getParameter("CreationArt"));
                Double price = Double.parseDouble(req.getParameter("PriceArt"));
                List<String> author = List.of(req.getParameterValues("author"));
                List<String> view = List.of(req.getParameterValues("view"));
                ModelAddArt modelAddArt = ModelAddArt.getInstance();
                boolean checkDel = FifthPageDB.artAdd(name, yearCreation, price, author, view);
                if (checkDel == true)
                    modelAddArt.add(true);
                else
                    modelAddArt.add(false);
                resp.sendRedirect("exhibition?command=adminart");
            } else if (req.getParameter("delButtonArt") != null) {

                ModelDelArt modelDelArt = ModelDelArt.getInstance();
                String name = req.getParameter("artDel");
                boolean checkDel = FifthPageDB.artDel(name);
                if (checkDel == true)
                    modelDelArt.add(true);
                else
                    modelDelArt.add(false);
                resp.sendRedirect("exhibition?command=adminart");
            } else if (req.getParameter("roleBackButton") != null) {
                HikariConnectDB.roleBackCommit();
                resp.sendRedirect("exhibition?command=adminart");
            } else if (req.getParameter("saveButton") != null) {
                HikariConnectDB.saveCommit();
                resp.sendRedirect("exhibition?command=adminart");
            }
        }
    }

}
