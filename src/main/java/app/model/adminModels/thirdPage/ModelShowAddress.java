package app.model.adminModels.thirdPage;

import app.entities.adminEntities.thirdPage.AddressShow;

import java.util.ArrayList;
import java.util.List;

public class ModelShowAddress { //singleton pattern model
    //used to store data for shows an address
    private static volatile ModelShowAddress instance; //the field must be valid for validation to work
    private static List<AddressShow> model;

    public static ModelShowAddress getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelShowAddress result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelShowAddress.class) {
            if (instance == null) {
                instance = new ModelShowAddress();
            }
            return instance;
        }
    }

    private ModelShowAddress() {
        model = new ArrayList<>();
    }

    public static void delete() {
        model.clear();
    } //deletes object

    public void add(AddressShow address) {
        model.add(address);
    } //adds object

    public List<AddressShow> listShow() { //checks size of list
        if (model.size() == 0)
            return null;
        return model;
    }

    public Boolean checkNull() { //checks objects of list
        for (AddressShow address : model) {
            if (address != null)
                return false;
        }
        return true;
    }
}
