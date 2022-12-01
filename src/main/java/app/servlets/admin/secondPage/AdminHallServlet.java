package app.servlets.admin.secondPage;



import app.database.admin.secondPage.SecondPageDB;
import app.entities.adminEntities.secondPage.HallShow;
import app.model.adminModels.secondPage.ModelAddHall;
import app.model.adminModels.secondPage.ModelDelHall;
import app.model.adminModels.secondPage.ModelShowHall;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminHallServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(SecondPageDB.checkConnection()==null)
            SecondPageDB.startConnnection();

        if (req.getSession().getAttribute("UserRole") == null)
        {
            resp.sendRedirect("/exhibition/");
        }
        else
        {
            ModelShowHall showHall = ModelShowHall.getInstance();
            ModelAddHall modelAddHall= ModelAddHall.getInstance();
            ModelDelHall modelDelHall = ModelDelHall.getInstance();
            if (showHall.listShow() != null) {
                if (showHall.checkNull() == true) {
                    req.setAttribute("Error", true);
                    SecondPageDB.nullConnection();
                } else {
                    List<HallShow> names = showHall.listShow();
                    req.setAttribute("SecondPageShow", names);
                }
            } else if (modelAddHall.modelCheck() != null) {
                if (modelAddHall.modelCheck().equals("false"))
                    req.setAttribute("AddError", true);
                else if (modelAddHall.modelCheck().equals("true"))
                    req.setAttribute("TrueAdd", true);

            }else if (modelDelHall.modelCheck() != null) {
                if (modelDelHall.modelCheck().equals("false"))
                    req.setAttribute("DelError", true);
                else if(modelDelHall.modelCheck().equals("true"))
                    req.setAttribute("TrueDel",true);
            }

            System.out.println("dsgffg");
            req.getRequestDispatcher("views/adminMenu/secondPage/AdminSecondMenu.jsp").forward(req, resp);
            req.removeAttribute("SecondPageShow");
            req.removeAttribute("Error");
            req.removeAttribute("AddError");
            req.removeAttribute("DelError");
            req.removeAttribute("TrueDel");
            req.removeAttribute("TrueAdd");
            ModelShowHall.delete();
            ModelAddHall.delete();
            ModelDelHall.delete();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ModelShowHall.delete();
        ModelAddHall.delete();
        ModelDelHall.delete();


        if(SecondPageDB.checkConnection()==null)
            SecondPageDB.startConnnection();


        if (req.getParameter("updateButton") != null)
        {
            ModelShowHall showHall = ModelShowHall.getInstance();

            try {
                for (HallShow hall : SecondPageDB.hallShow())
                    showHall.add(hall);
            } catch (Exception e) {
                showHall.add(null);
            }
        } else if (req.getParameter("addButtonHall")!=null)
        {
            String nameHall = req.getParameter("nameHall");
            String square = req.getParameter("square");
            ModelAddHall modelAddHall = ModelAddHall.getInstance();

            boolean checkDel = SecondPageDB.hallAdd(nameHall,Double.valueOf(square));
            if (checkDel == true)
                modelAddHall.add(true);
            else
                modelAddHall.add(false);

        }
        else if (req.getParameter("delButtonHall") != null) {

            String nameHall = req.getParameter("nameDel");
            ModelDelHall modelDelHall = ModelDelHall.getInstance();
            System.out.println("NameHall "+nameHall);
            boolean checkDel = SecondPageDB.hallDel(nameHall);
            if (checkDel == true) {
                System.out.println("TRUUUUE");
                modelDelHall.add(true);
            }
            else
                modelDelHall.add(false);
        }
        else if(req.getParameter("roleBackButton") != null)
            SecondPageDB.RoleBackCommit();
        else if (req.getParameter("saveButton") != null)
            SecondPageDB.saveCommit();
        else if(req.getParameter("exitButton")!=null)
            req.getSession().removeAttribute("UserRole");
        resp.sendRedirect("/exhibition/adminhall");
    }

}
