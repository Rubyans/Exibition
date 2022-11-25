package app.model.adminModels.thirdPage;


public class ModelDelAddress
{
    private static ModelDelAddress instance = new ModelDelAddress();
    private static String model;

    public static ModelDelAddress getInstance() {

        return instance;
    }
    private ModelDelAddress() {
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
