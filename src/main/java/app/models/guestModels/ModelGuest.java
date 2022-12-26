package app.models.guestModels;

import app.DAO.entities.UserGuest;

import java.util.ArrayList;
import java.util.List;

public class ModelGuest { //singleton pattern model
    //used to store data for shows a guest
    private static volatile ModelGuest instance; //the field must be valid for validation to work
    private static List<UserGuest> model;

    public static ModelGuest getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelGuest result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelGuest.class) {
            if (instance == null) {
                instance = new ModelGuest();
            }
            return instance;
        }
    }

    private ModelGuest() {
        model = new ArrayList<>();
    }

    public static void delete() {
        model.clear();
    } //deletes object

    public void add(UserGuest guest) {
        model.add(guest);
    } //adds object

    public List<UserGuest> listUserGuest() { //checks size of object
        if (model.size() == 0)
            return null;
        return model;
    }

    public Boolean checkNull() { //checks objects of size
        for (UserGuest guest : model) {
            if (guest != null)
                return false;
        }
        return true;
    }
}
