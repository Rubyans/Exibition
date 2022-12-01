package app.model.adminModels.seventhPage;


import app.entities.adminEntities.seventhPage.UserAutorizedShow;

import java.util.ArrayList;
import java.util.List;

public class ModelShowUserAutorized {
    private static volatile ModelShowUserAutorized instance;
    private static List<UserAutorizedShow> model;

    public static ModelShowUserAutorized getInstance() {

        ModelShowUserAutorized result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelShowUserAutorized.class) {
            if (instance == null) {
                instance = new ModelShowUserAutorized();
            }
            return instance;
        }
    }

    private ModelShowUserAutorized() {
        model = new ArrayList<>();
    }

    public static void delete() {
        model.clear();
    }

    public void add(UserAutorizedShow userAuto) {
        model.add(userAuto);
    }

    public List<UserAutorizedShow> listShow() {
        if (model.size() == 0)
            return null;
        return model;
    }

    public Boolean checkNull() {
        for (UserAutorizedShow userAuto : model) {
            if (userAuto != null)
                return false;
        }
        return true;
    }
}
