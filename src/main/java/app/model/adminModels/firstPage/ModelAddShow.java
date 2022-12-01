package app.model.adminModels.firstPage;

import app.entities.adminEntities.firstPage.AdminAddShow;

import java.util.ArrayList;
import java.util.List;

public class ModelAddShow {
    private static volatile ModelAddShow instance;
    private static List<AdminAddShow> model;
    private ModelAddShow() { model = new ArrayList<>(); }

    public static ModelAddShow getInstance() {

        ModelAddShow result = instance;
        if (result != null) {
            return result;
        }
        synchronized(ModelAddShow.class) {
            if (instance == null) {
                instance = new ModelAddShow();
            }
            return instance;
        }
    }

    public static void delete()
    {
        model.clear();
    }

    public void add(AdminAddShow admin) {
        model.add(admin);
    }
    public List<AdminAddShow> listShow()
    {
        if(model.size()==0)
            return null;
        return model;
    }
    public Boolean checkNull() {
        for (AdminAddShow admin : model) {
            if (admin != null)
                return false;
        }
        return true;
    }
}
