package app.model.userModels.firstPage;

import app.entities.userEntities.firstPage.UserShowExhibition;

import java.util.ArrayList;
import java.util.List;

public class ModelShowExhibition {
    private static volatile ModelShowExhibition instance;
    private static List<UserShowExhibition> model;

    public static ModelShowExhibition getInstance() {
        ModelShowExhibition result = instance;
        if (result != null) {
            return result;
        }
        synchronized(ModelShowExhibition.class) {
            if (instance == null) {
                instance = new ModelShowExhibition();
            }
            return instance;
        }
    }
    private ModelShowExhibition() {
        model = new ArrayList<>();
    }

    public static void delete()
    {
        model.clear();
    }

    public void add(UserShowExhibition user) {
        model.add(user);
    }
    public List<UserShowExhibition> listShow()
    {
        if(model.size()==0)
            return null;
        return model;
    }
    public Boolean checkNull() {
        for (UserShowExhibition user : model) {
            if (user != null)
                return false;
        }
        return true;
    }
}
