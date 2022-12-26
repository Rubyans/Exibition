package app.models.adminModels.sixthPage;


import app.DAO.entities.adminEntities.sixthPage.ViewShow;

import java.util.ArrayList;
import java.util.List;

public class ModelShowView { //singleton pattern model
    //used to store data for showing a view
    private static volatile ModelShowView instance; //the field must be valid for validation to work
    private static List<ViewShow> model;

    public static ModelShowView getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelShowView result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelShowView.class) {
            if (instance == null) {
                instance = new ModelShowView();
            }
            return instance;
        }
    }

    private ModelShowView() {
        model = new ArrayList<>();
    }

    public static void delete() {
        model.clear();
    } //deletes object

    public void add(ViewShow view) {
        model.add(view);
    } //adds object

    public List<ViewShow> listShow() { //checks size of list
        if (model.size() == 0)
            return null;
        return model;
    }

    public Boolean checkNull() { //checks object
        for (ViewShow view : model) {
            if (view != null)
                return false;
        }
        return true;
    }
}
