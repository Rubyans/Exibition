package app.models.userModels.firstPage;


import app.DAO.entities.userEntities.firstPage.UserShowMoney;

import java.util.ArrayList;
import java.util.List;

public class ModelShowMoney { //singleton pattern model
    //used to store data for shows a money
    private static volatile ModelShowMoney instance; //the field must be valid for validation to work
    private static List<UserShowMoney> model;

    private ModelShowMoney() {
        model = new ArrayList<>();
    }

    public static ModelShowMoney getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelShowMoney result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelShowMoney.class) {
            if (instance == null) {
                instance = new ModelShowMoney();
            }
            return instance;
        }
    }

    public static void delete() {
        model.clear();
    } //deletes object

    public void add(UserShowMoney money) {
        model.add(money);
    } //adds object

    public List<UserShowMoney> listShow() { //checks size of list
        if (model.size() == 0)
            return null;
        return model;
    }

    public Boolean checkNull() { //checks objects of list
        for (UserShowMoney user : model) {
            if (user != null)
                return false;
        }
        return true;
    }
}
