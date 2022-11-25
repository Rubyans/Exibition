package app.model.adminModels.secondPage;

import app.model.adminModels.firstPage.ModelAddExhibition;

public class ModelAddHall
{
    private static ModelAddHall instance = new ModelAddHall();
    private static String model;

    public static ModelAddHall getInstance() {

        return instance;
    }
    private ModelAddHall() {
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
