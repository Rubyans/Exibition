package app.model.adminModels.thirdPage;



public class ModelAddAddress
{
    private static volatile ModelAddAddress instance;
    private static String model;

    public static ModelAddAddress getInstance() {

        ModelAddAddress result = instance;
        if (result != null) {
            return result;
        }
        synchronized(ModelAddAddress.class) {
            if (instance == null) {
                instance = new ModelAddAddress();
            }
            return instance;
        }
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
