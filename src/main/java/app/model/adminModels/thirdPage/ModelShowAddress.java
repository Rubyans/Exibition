package app.model.adminModels.thirdPage;

import app.entities.adminEntities.secondPage.HallShow;
import app.entities.adminEntities.thirdPage.AddressShow;
import app.model.adminModels.secondPage.ModelShowHall;

import java.util.ArrayList;
import java.util.List;

public class ModelShowAddress
{
    private static ModelShowAddress instance = new ModelShowAddress();
    private static List<AddressShow> model;

    public static ModelShowAddress getInstance() {

        return instance;
    }
    private ModelShowAddress() {
        model = new ArrayList<>();
    }

    public static void delete()
    {
        model.clear();
    }

    public void add(AddressShow address) {
        model.add(address);
    }
    public List<AddressShow> listShow()
    {
        if(model.size()==0)
            return null;
        return model;
    }
    public Boolean checkNull() {
        for (AddressShow address : model) {
            if (address != null)
                return false;
        }
        return true;
    }
}
