package app.model.adminModels.secondPage;


public class ModelAddHall
{
    private static volatile ModelAddHall instance;
    private static String model;

    public static ModelAddHall getInstance() {

        ModelAddHall result = instance;
        if (result != null) {
            return result;
        }
        synchronized(ModelAddHall.class) {
            if (instance == null) {
                instance = new ModelAddHall();
            }
            return instance;
        }
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
