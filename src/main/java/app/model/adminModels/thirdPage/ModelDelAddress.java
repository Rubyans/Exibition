package app.model.adminModels.thirdPage;


public class ModelDelAddress
{
    private static volatile ModelDelAddress instance;
    private static String model;

    public static ModelDelAddress getInstance() {

        ModelDelAddress result = instance;
        if (result != null) {
            return result;
        }
        synchronized(ModelDelAddress.class) {
            if (instance == null) {
                instance = new ModelDelAddress();
            }
            return instance;
        }
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
