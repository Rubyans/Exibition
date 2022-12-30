package app.controllerFront.controller;

import app.controllerFront.redirect.RedirectInternals;
import app.controllerFront.commands.interfaceCommand.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        SessionListener listener = new SessionListener();
//
//        ModelAuthorization model = ModelAuthorization.getInstance();
//        ModelLanguageAuthorization modelLanguageAuthorization = ModelLanguageAuthorization.getInstance();
//
//        if (modelLanguageAuthorization.modelCheck() != null) {
//            if (modelLanguageAuthorization.modelCheck().equals("en")) {
//                req.getSession().setAttribute("checkLanguage", listener);
//                req.getSession().setAttribute("language", "en");
//            }
//            if (modelLanguageAuthorization.modelCheck().equals("ua")) {
//                req.getSession().setAttribute("language", "ua");
//            }
//        }
//
//        if (req.getSession().getAttribute("language") != null) {
//            if (req.getSession().getAttribute("language").equals("en")) {
//                req.setAttribute("languageChange", ChangeLanguage.changeEN("language.properties", "authorization"));
//            } else if (req.getSession().getAttribute("language").equals("ua")) {
//                req.setAttribute("languageChange", ChangeLanguage.changeUA("language.properties", "authorization"));
//            }
//        } else {
//            req.setAttribute("languageChange", ChangeLanguage.changeUA("language.properties", "authorization"));
//        }
//        if (model.listUser() != null) {
//            if (model.checkNull() == true) {
//                req.setAttribute("Error", true);
//                req.getRequestDispatcher("index.jsp").forward(req, resp);
//            } else {
//                List<UserAutorization> user = model.listUser();
//                if (user.get(0).getRole().equals("1")) {
//                    if (user.get(0).getAccess().equals("1")) {
//                        req.getSession().setAttribute("UserRole", user.get(0).getRole());
//                        req.getSession().setAttribute("UserId", user.get(0).getUserId());
//                        resp.sendRedirect("/exhibition/user");
//                    } else {
//                        req.setAttribute("UserBlock", true);
//                        req.getRequestDispatcher("index.jsp").forward(req, resp);
//                    }
//
//                } else if (user.get(0).getRole().equals("2")) {
//                    if (user.get(0).getAccess().equals("1")) {
//                        req.getSession().setAttribute("UserRole", user.get(0).getRole());
//                        resp.sendRedirect("/exhibition/adminmain");
//                    } else {
//                        req.setAttribute("UserBlock", true);
//                        req.getRequestDispatcher("index.jsp").forward(req, resp);
//                    }
//                } else {
//                    req.setAttribute("UserRoleCheck", user.get(0).getRole());
//                    req.getRequestDispatcher("index.jsp").forward(req, resp);
//                }
//            }
//        } else {
//            req.getRequestDispatcher("index.jsp").forward(req, resp);
//        }
//        req.removeAttribute("UserRoleCheck");
//        req.removeAttribute("Error");
//        req.removeAttribute("languageChange");
//        req.removeAttribute("UserBlock");
//        ModelAuthorization.delete();
//        ModelLanguageAuthorization.delete();

        //System.out.println("Sget1 "+req.getRequestURL());

//        RequestDispatcher requestDispatcher=RequestDispatcher.getRequestDispatcher("/index.jsp");
//        requestDispatcher.forward(req,resp);
        //req.getRequestDispatcher("login.jsp").forward(req, resp);

        //System.out.println("Sget2 "+req.getRequestURL());
        //req.getRequestDispatcher("index.jsp").forward(req, resp);
        System.out.println("doGet");
        process(req,resp,1);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        if (req.getParameter("loginButton") != null) {
//
//            if (HikariConnectDB.checkConnection() == false) {
//                HikariConnectDB object = new HikariConnectDB();
//            }
//            String login = req.getParameter("loginUser");
//            String password = req.getParameter("passwordUser");
//
//            ModelAuthorization model = ModelAuthorization.getInstance();
//            model.add(UserAutorizationDB.authorizationUser(login, password));
//
//            resp.sendRedirect("/exhibition/auto");
//        } else if (req.getParameter("englishButton") != null) {
//            ModelLanguageAuthorization modelLanguageAuthorization = ModelLanguageAuthorization.getInstance();
//            modelLanguageAuthorization.add("en");
//            resp.sendRedirect("/exhibition/auto");
//        } else if (req.getParameter("ukraineButton") != null) {
//            ModelLanguageAuthorization modelLanguageAuthorization = ModelLanguageAuthorization.getInstance();
//            modelLanguageAuthorization.add("ua");
//            resp.sendRedirect("/exhibition/auto");
//        }
        //System.out.println("Spost "+req.getRequestURL());
        //req.getRequestDispatcher("/index.jsp").forward(req, resp);
        System.out.println("doPost");
        process(req,resp,2);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp,int request) {
        try {

            //System.out.println("Popal "+request.getRequestURL());

            RedirectInternals redirectInternals=new RedirectInternals(req);
            Command command= (Command) redirectInternals.redirect();
            command.execute(req,resp,request);
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }
}
