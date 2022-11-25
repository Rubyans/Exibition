package app.model.adminModels.secondPage;

import app.entities.adminEntities.secondPage.HallShow;

import java.util.ArrayList;
import java.util.List;

public class ModelShowHall
{
    private static ModelShowHall instance = new ModelShowHall();
    private static List<HallShow> model;

    public static ModelShowHall getInstance() {

        return instance;
    }
    private ModelShowHall() {
        model = new ArrayList<>();
    }

    public static void delete()
    {
        model.clear();
    }

    public void add(HallShow hall) {
        model.add(hall);
    }
    public List<HallShow> listShow()
    {
        if(model.size()==0)
            return null;
        return model;
    }
    public Boolean checkNull() {
        for (HallShow hall : model) {
            if (hall != null)
                return false;
        }
        return true;
    }
}
