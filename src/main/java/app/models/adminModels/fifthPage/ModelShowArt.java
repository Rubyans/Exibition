package app.models.adminModels.fifthPage;

import app.DAO.entities.adminEntities.fifthPage.ArtShow;

import java.util.ArrayList;
import java.util.List;

public class ModelShowArt { //singleton pattern model
    //used to store class objects with the results of a show request
    private static volatile ModelShowArt instance; //the field must be valid for validation to work
    private static List<ArtShow> model; //data storage list

    public static ModelShowArt getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelShowArt result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelShowArt.class) {
            if (instance == null) {
                instance = new ModelShowArt();
            }
            return instance;
        }
    }
    private ModelShowArt() {
        model = new ArrayList<>();
    }
    public static void delete() { //deletes object
        model.clear();
    } //deletes object
    public void add(ArtShow art) { //adds object
        model.add(art);
    } //adds object
    public List<ArtShow> listShow() { //returns list
        if (model.size() == 0)
            return null;
        return model;
    }
    public Boolean checkNull() { //checks list for null values
        for (ArtShow art : model) {
            if (art != null)
                return false;
        }
        return true;
    }
}
