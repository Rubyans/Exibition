package app.listners;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class SessionListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();

        String url = ctx.getInitParameter("DBURL");
        String u = ctx.getInitParameter("DBUSER");
        String p = ctx.getInitParameter("DBPWD");

        System.out.println("dsfdsf");
//        //create database connection from init parameters and set it to context
//        DBConnectionManager dbManager = new DBConnectionManager(url, u, p);
//        ctx.setAttribute("DBManager", dbManager);
//        System.out.println("Database connection initialized for Application.");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();
        //DBConnectionManager dbManager = (DBConnectionManager) ctx.getAttribute("DBManager");
        //dbManager.closeConnection();
        System.out.println("Database connection closed for Application.");

    }

}
