package app.controllerFront.commands.admin.thirdPage;


import app.DAO.connectionDAO.HikariConnectDB;
import app.DAO.entities.adminEntities.thirdPage.AddressShow;
import app.DAO.sqlFunctions.admin.thirdPage.ThirdPageDB;
import app.controllerFront.commands.interfaceCommand.Command;
import app.controllerFront.models.adminModels.thirdPage.*;
import app.service.changeLanguage.ChangeLanguage;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ThirdPageCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ThirdPageCommand.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp, int request) throws ServletException, IOException {
        if (request == 1) {
/////////////////////////////////////GET-Request/////////////////////////////////////////////////////////////////
            ModelShowAddress modelShowAddress = ModelShowAddress.getInstance();
            ModelAddAddress modelAddAddress = ModelAddAddress.getInstance();
            ModelDelAddress modelDelAddress = ModelDelAddress.getInstance();

            if (req.getSession().getAttribute("language") != null) {
                if (req.getSession().getAttribute("language").equals("en")) {
                    req.setAttribute("languageChange", ChangeLanguage.changeEN("languageEN.properties", "adminThird"));
                } else if (req.getSession().getAttribute("language").equals("ua")) {
                    req.setAttribute("languageChange", ChangeLanguage.changeUA("languageUA.properties", "adminThird"));
                }
            } else {
                req.setAttribute("languageChange", ChangeLanguage.changeUA("languageUA.properties", "adminThird"));
            }

            if (modelShowAddress.listShow() != null) {
                if (modelShowAddress.checkNull() == true) {
                    req.setAttribute("Error", true);
                } else {
                    List<AddressShow> address = modelShowAddress.listShow();
                    req.setAttribute("ThirdPageShow", address);
                }
            } else if (modelAddAddress.modelCheck() != null) {
                if (modelAddAddress.modelCheck().equals("false"))
                    req.setAttribute("AddError", true);
                else if (modelAddAddress.modelCheck().equals("true"))
                    req.setAttribute("TrueAdd", true);

            } else if (modelDelAddress.modelCheck() != null) {
                if (modelDelAddress.modelCheck().equals("false"))
                    req.setAttribute("DelError", true);
                else if (modelDelAddress.modelCheck().equals("true"))
                    req.setAttribute("TrueDel", true);
            }
            req.getRequestDispatcher("views/adminMenu/thirdPage/AdminThirdMenu.jsp").forward(req, resp);
            req.removeAttribute("ThirdPageShow");
            req.removeAttribute("Error");
            req.removeAttribute("AddError");
            req.removeAttribute("DelError");
            req.removeAttribute("TrueDel");
            req.removeAttribute("TrueAdd");
            req.removeAttribute("languageChange");
            ModelShowAddress.delete();
            ModelAddAddress.delete();
            ModelDelAddress.delete();
        } else {
/////////////////////////////////////Post-Request///////////////////////////////////////////////////////////////
            if (req.getParameter("updateButton") != null) {
                ModelShowAddress modelShowAddress = ModelShowAddress.getInstance();
                try {
                    String valueRows=req.getParameter("UpdateSort");
                    for (AddressShow address : ThirdPageDB.addressShow(valueRows))
                        modelShowAddress.add(address);
                    LOGGER.debug("doPost in debug");
                } catch (Exception e) {
                    modelShowAddress.add(null);
                    LOGGER.error("doPost " + e.getMessage());
                }
                resp.sendRedirect("exhibition?command=adminaddress");
            } else if (req.getParameter("addButtonAddress") != null) {
                String country = req.getParameter("countryAddress");
                String city = req.getParameter("cityAddress");
                String street = req.getParameter("streetAddress");
                Integer numberHouse = Integer.parseInt(req.getParameter("houseAddress"));
                ModelAddAddress modelAddAddress = ModelAddAddress.getInstance();
                boolean checkDel = ThirdPageDB.addressAdd(country, city, street, numberHouse);
                if (checkDel == true)
                    modelAddAddress.add(true);
                else
                    modelAddAddress.add(false);
                resp.sendRedirect("exhibition?command=adminaddress");
            } else if (req.getParameter("delButtonAddress") != null) {
                Integer Unumber = Integer.parseInt(req.getParameter("addressDel"));
                ModelDelAddress modelDelAddress = ModelDelAddress.getInstance();
                boolean checkDel = ThirdPageDB.addressDel(Unumber);
                if (checkDel == true) {
                    modelDelAddress.add(true);
                } else
                    modelDelAddress.add(false);
                resp.sendRedirect("exhibition?command=adminaddress");
            } else if (req.getParameter("roleBackButton") != null) {
                HikariConnectDB.roleBackCommit();
                resp.sendRedirect("exhibition?command=adminaddress");
            } else if (req.getParameter("saveButton") != null) {
                HikariConnectDB.saveCommit();
                resp.sendRedirect("exhibition?command=adminaddress");
            }
        }
    }

}
