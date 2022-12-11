package app.servlets.admin.thirdPage;

import app.database.admin.thirdPage.ThirdPageDB;
import app.entities.adminEntities.thirdPage.AddressShow;
import app.model.adminModels.thirdPage.ModelAddAddress;
import app.model.adminModels.thirdPage.ModelDelAddress;
import app.model.adminModels.thirdPage.ModelShowAddress;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminAddressServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("UserRole") == null || req.getSession().getAttribute("UserRole").equals("1")) {
            resp.sendRedirect("/exhibition/");
        } else {
            ModelShowAddress modelShowAddress = ModelShowAddress.getInstance();
            ModelAddAddress modelAddAddress = ModelAddAddress.getInstance();
            ModelDelAddress modelDelAddress = ModelDelAddress.getInstance();

            if (ThirdPageDB.checkConnection() == null)
                ThirdPageDB.startConnnection();

            if (modelShowAddress.listShow() != null) {
                if (modelShowAddress.checkNull() == true) {
                    ThirdPageDB.nullConnection();
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
            ModelShowAddress.delete();
            ModelAddAddress.delete();
            ModelDelAddress.delete();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (ThirdPageDB.checkConnection() == null)
            ThirdPageDB.startConnnection();

        if (req.getParameter("updateButton") != null) {
            ModelShowAddress modelShowAddress = ModelShowAddress.getInstance();
            try {
                for (AddressShow address : ThirdPageDB.addressShow())
                    modelShowAddress.add(address);
            } catch (Exception e) {
                modelShowAddress.add(null);
            }
            resp.sendRedirect("/exhibition/adminaddress");
        } else if (req.getParameter("addButtonAddress") != null) {
            String country = req.getParameter("countryAddress");
            String city = req.getParameter("cityAddress");
            String street = req.getParameter("streetAddress");
            int numberHouse = Integer.parseInt(req.getParameter("houseAddress"));
            ModelAddAddress modelAddAddress = ModelAddAddress.getInstance();
            boolean checkDel = ThirdPageDB.addressAdd(country, city, street, numberHouse);
            if (checkDel == true)
                modelAddAddress.add(true);
            else
                modelAddAddress.add(false);
            resp.sendRedirect("/exhibition/adminaddress");
        } else if (req.getParameter("delButtonAddress") != null) {
            int Unumber = Integer.parseInt(req.getParameter("addressDel"));
            ModelDelAddress modelDelAddress = ModelDelAddress.getInstance();

            boolean checkDel = ThirdPageDB.addressDel(Unumber);
            if (checkDel == true) {
                modelDelAddress.add(true);
            } else
                modelDelAddress.add(false);
            resp.sendRedirect("/exhibition/adminaddress");
        } else if (req.getParameter("roleBackButton") != null) {
            ThirdPageDB.RoleBackCommit();
            resp.sendRedirect("/exhibition/adminaddress");
        } else if (req.getParameter("saveButton") != null) {
            ThirdPageDB.saveCommit();
            resp.sendRedirect("/exhibition/adminaddress");
        } else if (req.getParameter("exitButton") != null) {
            req.getSession().removeAttribute("UserRole");
            ThirdPageDB.exitConnection();
            ThirdPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminmain");
        } else if (req.getParameter("AdminMainPagination") != null) {
            ThirdPageDB.exitConnection();
            ThirdPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminmain");
        } else if (req.getParameter("UserAutorizedPagination") != null) {
            ThirdPageDB.exitConnection();
            ThirdPageDB.nullConnection();
            resp.sendRedirect("/exhibition/userautorized");
        } else if (req.getParameter("AdminHallPagination") != null) {
            ThirdPageDB.exitConnection();
            ThirdPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminhall");
        } else if (req.getParameter("AdminAddressPagination") != null) {
            ThirdPageDB.exitConnection();
            ThirdPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminaddress");
        } else if (req.getParameter("AdminAuthorPagination") != null) {
            ThirdPageDB.exitConnection();
            ThirdPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminauthor");
        } else if (req.getParameter("AdminArtPagination") != null) {
            ThirdPageDB.exitConnection();
            ThirdPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminart");
        } else if (req.getParameter("AdminViewPagination") != null) {
            ThirdPageDB.exitConnection();
            ThirdPageDB.nullConnection();
            resp.sendRedirect("/exhibition/adminview");
        }


    }
}
