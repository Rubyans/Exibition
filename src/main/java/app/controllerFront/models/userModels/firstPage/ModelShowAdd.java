package app.controllerFront.models.userModels.firstPage;

import app.DAO.entities.userEntities.firstPage.UserShowAdd;

import java.util.ArrayList;
import java.util.List;

public class ModelShowAdd { //singleton pattern model
    //used to store data for shows a ticket
    private static volatile ModelShowAdd instance; //the field must be valid for validation to work
    private static List<UserShowAdd> model;

    private ModelShowAdd() {
        model = new ArrayList<>();
    }

    public static ModelShowAdd getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelShowAdd result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelShowAdd.class) {
            if (instance == null) {
                instance = new ModelShowAdd();
            }
            return instance;
        }
    }

    public static void delete() {
        model.clear();
    } //deletes object

    public void add(UserShowAdd user) {
        model.add(user);
    } //adds object

    public List<UserShowAdd> listShow() { //checks size of object
        if (model.size() == 0)
            return null;
        return model;
    }

    public Boolean checkNull() { //checks object of list
        for (UserShowAdd user : model) {
            if (user != null)
                return false;
        }
        return true;
    }
}
