package app.model.adminModels.seventhPage;


public class ModelDelUserAutorized
{
    private static volatile ModelDelUserAutorized instance;
    private static String model;

    public static ModelDelUserAutorized getInstance() {

        ModelDelUserAutorized result = instance;
        if (result != null) {
            return result;
        }
        synchronized(ModelDelUserAutorized.class) {
            if (instance == null) {
                instance = new ModelDelUserAutorized();
            }
            return instance;
        }
    }
    private ModelDelUserAutorized() {
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
