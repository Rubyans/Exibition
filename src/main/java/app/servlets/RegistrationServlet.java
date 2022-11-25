package app.servlets;

import app.database.UserDB;
import app.entities.User;
import app.model.ModelGuest;
import app.model.ModelRegistration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("doGet");


        ModelRegistration model = ModelRegistration.getInstance();

        if(model.checkString()!=null)
        {
            req.setAttribute("UserAdd",model.returnString());
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/RegistrationForm.jsp");
        requestDispatcher.forward(req, resp);
        req.removeAttribute("UserAdd");
        model.delete();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("doPost");
        String firstName=req.getParameter("firstName");
        String lastName=req.getParameter("lastName");
        String email=req.getParameter("email");
        String login=req.getParameter("login");
        String password = req.getParameter("password");

        ModelRegistration model=ModelRegistration.getInstance();
        model.add(UserDB.registrationDB(firstName,lastName,email,login,password));

        resp.sendRedirect("/exhibition/reg");

    }
}
