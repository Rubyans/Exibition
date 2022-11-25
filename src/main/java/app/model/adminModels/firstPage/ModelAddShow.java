package app.model.adminModels.firstPage;

import app.entities.adminEntities.firstPage.AdminAddShow;

import java.util.ArrayList;
import java.util.List;

public class ModelAddShow {
    private static ModelAddShow instance = new ModelAddShow();
    private static List<AdminAddShow> model;

    public static ModelAddShow getInstance() {

        return instance;
    }
    private ModelAddShow() {
        model = new ArrayList<>();
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
