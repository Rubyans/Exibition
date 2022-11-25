package app.model.adminModels.secondPage;

public class ModelDelHall
{
    private static ModelDelHall instance = new ModelDelHall();
    private static String model;

    public static ModelDelHall getInstance() {

        return instance;
    }
    private ModelDelHall() {
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
