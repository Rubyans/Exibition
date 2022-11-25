package app.model.adminModels.thirdPage;

import app.model.adminModels.secondPage.ModelAddHall;

public class ModelAddAddress
{
    private static ModelAddAddress instance = new ModelAddAddress();
    private static String model;

    public static ModelAddAddress getInstance() {

        return instance;
    }
    private ModelAddAddress() {
        model = null;
    }

    public static void delete()
    {
        model=null;
    }

    public void add(boolean text) {
        model= String.valueOf(text);
    }
    public String modelCheck()
    {
        if(model==null)
            return null;
        return model;
    }
}
