package app.model.adminModels.firstPage;

import app.entities.adminEntities.firstPage.AdminShow;

import java.util.ArrayList;
import java.util.List;

public class ModelShow {
    private static ModelShow instance = new ModelShow();
    private static List<AdminShow> model;

    public static ModelShow getInstance() {

        return instance;
    }
    private ModelShow() {
        model = new ArrayList<>();
    }

    public static void delete()
    {
        model.clear();
    }

    public void add(AdminShow admin) {
        model.add(admin);
    }
    public List<AdminShow> listShow()
    {
        if(model.size()==0)
            return null;
        return model;
    }
    public Boolean checkNull() {
        for (AdminShow admin : model) {
            if (admin != null)
                return false;
        }
        return true;
    }
}
