package app.controllerFront.controller;

import app.controllerFront.listners.SessionListener;
import app.controllerFront.redirect.RedirectInternals;
import app.controllerFront.commands.interfaceCommand.Command;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FrontController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(FrontController.class); //logger

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp, 1);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp, 2);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp, int request) {
        try {
            RedirectInternals redirectInternals = new RedirectInternals(req);
            Command command = (Command) redirectInternals.redirect();
            command.execute(req, resp, request);
        } catch (Exception e) {
            LOGGER.error("Error "+e.getMessage());
        }
    }
}
