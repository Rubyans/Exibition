package app.model.adminModels.secondPage;


public class ModelDelHall
{
    private static volatile ModelDelHall instance;
    private static String model;

    public static ModelDelHall getInstance() {

        ModelDelHall result = instance;
        if (result != null) {
            return result;
        }
        synchronized(ModelDelHall.class) {
            if (instance == null) {
                instance = new ModelDelHall();
            }
            return instance;
        }
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
