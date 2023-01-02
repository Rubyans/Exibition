package app.controllerFront.redirect;

import app.controllerFront.commands.AuthorizedCommand;
import app.controllerFront.commands.GuestCommand;
import app.controllerFront.commands.RegistrationCommand;
import app.controllerFront.commands.admin.eighthPage.EighthPageCommand;
import app.controllerFront.commands.admin.fifthPage.FifthPageCommand;
import app.controllerFront.commands.admin.firstPage.FirstPageCommand;
import app.controllerFront.commands.admin.fourthPage.FourthPageCommand;
import app.controllerFront.commands.admin.secondPage.SecondPageCommand;
import app.controllerFront.commands.admin.seventhPage.SeventhPageCommand;
import app.controllerFront.commands.admin.sixthPage.SixthPageCommand;
import app.controllerFront.commands.admin.thirdPage.ThirdPageCommand;
import app.controllerFront.commands.user.firstPage.UserFirstCommand;
import app.controllerFront.commands.user.secondPage.UserSecondCommand;
import app.controllerFront.models.ModelController;

import javax.servlet.http.HttpServletRequest;

public class RedirectInternals {

    private ModelController modelController = ModelController.getInstance();

    public RedirectInternals(HttpServletRequest req) {

        modelController.add(req);
    }

    public Object redirect() {

        switch (modelController.showParametr()) {

            case "auto": {
                return new AuthorizedCommand();
            }
            case "reg": {
                return new RegistrationCommand();
            }
            case "guest": {
                return new GuestCommand();
            }
            case "user": {
                return new UserFirstCommand();
            }
            case "userexhibition": {
                return new UserSecondCommand();
            }
            case "adminmain": {
                return new FirstPageCommand();
            }
            case "adminhall": {
                return new SecondPageCommand();
            }
            case "adminaddress": {
                return new ThirdPageCommand();
            }
            case "adminauthor": {
                return new FourthPageCommand();
            }
            case "adminart": {
                return new FifthPageCommand();
            }
            case "adminview": {
                return new SixthPageCommand();
            }
            case "userautorized": {
                return new SeventhPageCommand();
            }
            case "adminstatistics": {
                return new EighthPageCommand();
            }
            default: {
                return new AuthorizedCommand();
            }
        }
    }

}
