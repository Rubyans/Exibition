package app.controllerFront.models.adminModels.fourthPage;

import app.DAO.entities.adminEntities.fourthPage.AuthorShow;

import java.util.ArrayList;
import java.util.List;

public class ModelShowAuthor { //singleton pattern model
    //used to store data for showing an author
    private static volatile ModelShowAuthor instance; //the field must be valid for validation to work
    private static List<AuthorShow> model;

    public static ModelShowAuthor getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelShowAuthor result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelShowAuthor.class) {
            if (instance == null) {
                instance = new ModelShowAuthor();
            }
            return instance;
        }
    }

    private ModelShowAuthor() {
        model = new ArrayList<>();
    }

    public static void delete() {
        model.clear();
    } //deletes object

    public void add(AuthorShow author) {
        model.add(author);
    } //adds object

    public List<AuthorShow> listShow() { //returns list
        if (model.size() == 0)
            return null;
        return model;
    }

    public Boolean checkNull() { //checks list of null values
        for (AuthorShow author : model) {
            if (author != null)
                return false;
        }
        return true;
    }
}
