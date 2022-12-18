package app.model.adminModels.fifthPage;
import app.entities.adminEntities.fifthPage.ArtAddShow;
import java.util.ArrayList;
import java.util.List;

public class ModelAddShow { //singleton pattern model
    //used to store class objects with the results of an output request
    private static volatile ModelAddShow instance; //the field must be valid for validation to work
    private static List<ArtAddShow> model; //data storage list
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
    public static void delete() {
        model.clear();
    } //deletes object
    public void add(ArtAddShow art) {
        model.add(art);
    } //adds object
    public List<ArtAddShow> listShow() { //returns list
        if (model.size() == 0)
            return null;
        return model;
    }
    public Boolean checkNull() { //checks list for null values
        for (ArtAddShow art : model) {
            if (art != null)
                return false;
        }
        return true;
    }
}
