package app.controllerFront.models.authorizationModels;

import app.DAO.entities.UserAutorization;


import java.util.ArrayList;
import java.util.List;

public class ModelAuthorization { //singleton pattern model
    //used to store data for authorization
    private static volatile ModelAuthorization instance; //the field must be valid for validation to work
    private static List<UserAutorization> model;

    public static ModelAuthorization getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelAuthorization result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelAuthorization.class) {
            if (instance == null) {
                instance = new ModelAuthorization();
            }
            return instance;
        }
    }

    private ModelAuthorization() {
        model = new ArrayList<>();
    }

    public static void delete() {
        model.clear();
    } //deletes object

    public void add(UserAutorization userAutorization) {
        model.add(userAutorization);
    } //adds object

    public List<UserAutorization> listUser() { //checks size of list
        if (model.size() == 0)
            return null;
        return model;
    }

    public Boolean checkNull() { //checks objects of list
        for (UserAutorization userAutorization : model) {
            if (userAutorization != null)
                return false;
        }
        return true;
    }
}
