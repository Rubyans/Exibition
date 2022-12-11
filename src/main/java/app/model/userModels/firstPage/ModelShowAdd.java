package app.model.userModels.firstPage;

import app.entities.userEntities.firstPage.UserShowAdd;

import java.util.ArrayList;
import java.util.List;

public class ModelShowAdd {
    private static volatile ModelShowAdd instance;
    private static List<UserShowAdd> model;
    private ModelShowAdd() { model = new ArrayList<>(); }

    public static ModelShowAdd getInstance() {

        ModelShowAdd result = instance;
        if (result != null) {
            return result;
        }
        synchronized(ModelShowAdd.class) {
            if (instance == null) {
                instance = new ModelShowAdd();
            }
            return instance;
        }
    }

    public static void delete()
    {
        model.clear();
    }

    public void add(UserShowAdd user) {
        model.add(user);
    }
    public List<UserShowAdd> listShow()
    {
        if(model.size()==0)
            return null;
        return model;
    }
    public Boolean checkNull() {
        for (UserShowAdd user : model) {
            if (user != null)
                return false;
        }
        return true;
    }
}
