package app.models.adminModels.firstPage;

import app.DAO.entities.adminEntities.firstPage.AdminShow;

import java.util.ArrayList;
import java.util.List;

public class ModelShow { //singleton pattern model
    //used to store data for showing an exhibition
    private static volatile ModelShow instance; //the field must be valid for validation to work
    private static List<AdminShow> model;

    public static ModelShow getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelShow result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelShow.class) {
            if (instance == null) {
                instance = new ModelShow();
            }
            return instance;
        }
    }

    private ModelShow() {
        model = new ArrayList<>();
    }

    public static void delete() {
        model.clear();
    } //deletes object

    public void add(AdminShow admin) {
        model.add(admin);
    } //adds object

    public List<AdminShow> listShow() { //returns list
        if (model.size() == 0)
            return null;
        return model;
    }

    public Boolean checkNull() { //checks list of null values
        for (AdminShow admin : model) {
            if (admin != null)
                return false;
        }
        return true;
    }
}
