package app.model.adminModels.thirdPage;

import app.entities.adminEntities.thirdPage.AddressShow;

import java.util.ArrayList;
import java.util.List;

public class ModelShowAddress
{
    private static volatile ModelShowAddress instance;
    private static List<AddressShow> model;

    public static ModelShowAddress getInstance() {

        ModelShowAddress result = instance;
        if (result != null) {
            return result;
        }
        synchronized(ModelShowAddress.class) {
            if (instance == null) {
                instance = new ModelShowAddress();
            }
            return instance;
        }
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
