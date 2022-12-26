package app.models.userModels.secondPage;


import app.DAO.entities.userEntities.secondPage.UserShowExhibition;

import java.util.ArrayList;
import java.util.List;

public class ModelShowExhibition { //singleton pattern model
    //used to store data for shows an exhibition
    private static volatile ModelShowExhibition instance; //the field must be valid for validation to work
    private static List<UserShowExhibition> model;

    public static ModelShowExhibition getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelShowExhibition result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelShowExhibition.class) {
            if (instance == null) {
                instance = new ModelShowExhibition();
            }
            return instance;
        }
    }

    private ModelShowExhibition() {
        model = new ArrayList<>();
    }

    public static void delete() {
        model.clear();
    } //deletes object

    public void add(UserShowExhibition user) {
        model.add(user);
    } //adds object

    public List<UserShowExhibition> listShow() { //checks size of list
        if (model.size() == 0)
            return null;
        return model;
    }

    public Boolean checkNull() { //checks objects of list
        for (UserShowExhibition user : model) {
            if (user != null)
                return false;
        }
        return true;
    }
}
