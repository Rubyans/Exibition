package app.model.adminModels.seventhPage;

public class ModelAddUserAutorized {
    private static volatile ModelAddUserAutorized instance;
    private static String model;

    public static ModelAddUserAutorized getInstance() {

        ModelAddUserAutorized result = instance;
        if (result != null) {
            return result;
        }
        synchronized(ModelAddUserAutorized.class) {
            if (instance == null) {
                instance = new ModelAddUserAutorized();
            }
            return instance;
        }
    }
    private ModelAddUserAutorized() {
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
