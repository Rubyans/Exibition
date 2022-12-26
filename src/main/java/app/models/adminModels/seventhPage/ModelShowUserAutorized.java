package app.models.adminModels.seventhPage;

import app.DAO.entities.adminEntities.seventhPage.UserAutorizedShow;

import java.util.ArrayList;
import java.util.List;

public class ModelShowUserAutorized { //singleton pattern model
    //used to store data for showing a user
    private static volatile ModelShowUserAutorized instance; //the field must be valid for validation to work
    private static List<UserAutorizedShow> model;

    public static ModelShowUserAutorized getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelShowUserAutorized result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelShowUserAutorized.class) {
            if (instance == null) {
                instance = new ModelShowUserAutorized();
            }
            return instance;
        }
    }

    private ModelShowUserAutorized() {
        model = new ArrayList<>();
    }

    public static void delete() {
        model.clear();
    } //deletes object

    public void add(UserAutorizedShow userAuto) { //adds object
        model.add(userAuto);
    } //adds object

    public List<UserAutorizedShow> listShow() { //checks size of list
        if (model.size() == 0)
            return null;
        return model;
    }

    public Boolean checkNull() { //checks null of object
        for (UserAutorizedShow userAuto : model) {
            if (userAuto != null)
                return false;
        }
        return true;
    }
}
