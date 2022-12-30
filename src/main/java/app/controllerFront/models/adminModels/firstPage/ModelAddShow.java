package app.controllerFront.models.adminModels.firstPage;

import app.DAO.entities.adminEntities.firstPage.AdminAddShow;

import java.util.ArrayList;
import java.util.List;

public class ModelAddShow { //singleton pattern model
    //used to store data for showing add an exhibition
    private static volatile ModelAddShow instance; //the field must be valid for validation to work
    private static List<AdminAddShow> model;

    private ModelAddShow() {
        model = new ArrayList<>();
    }

    public static ModelAddShow getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelAddShow result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelAddShow.class) {
            if (instance == null) {
                instance = new ModelAddShow();
            }
            return instance;
        }
    }

    public static void delete() { //deletes object
        model.clear();
    } //deletes object

    public void add(AdminAddShow admin) { //adds object
        model.add(admin);
    } //adds object

    public List<AdminAddShow> listShow() { //checks lenth of list
        if (model.size() == 0)
            return null;
        return model;
    }

    public Boolean checkNull() { //checks null of list
        for (AdminAddShow admin : model) {
            if (admin != null)
                return false;
        }
        return true;
    }
}
