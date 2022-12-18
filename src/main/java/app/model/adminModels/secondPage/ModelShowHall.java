package app.model.adminModels.secondPage;

import app.entities.adminEntities.secondPage.HallShow;

import java.util.ArrayList;
import java.util.List;

public class ModelShowHall { //singleton pattern model
    //used to store data for showing a hall
    private static volatile ModelShowHall instance; //the field must be valid for validation to work
    private static List<HallShow> model;

    public static ModelShowHall getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelShowHall result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelShowHall.class) {
            if (instance == null) {
                instance = new ModelShowHall();
            }
            return instance;
        }
    }

    private ModelShowHall() {
        model = new ArrayList<>();
    }

    public static void delete() {
        model.clear();
    } //deletes object

    public void add(HallShow hall) {
        model.add(hall);
    } //adds object

    public List<HallShow> listShow() { //checks size of list
        if (model.size() == 0)
            return null;
        return model;
    }

    public Boolean checkNull() { //checks null of list
        for (HallShow hall : model) {
            if (hall != null)
                return false;
        }
        return true;
    }
}
