package app.model.adminModels.fifthPage;


public class ModelAddArt {
    private static volatile ModelAddArt instance;
    private static String model;

    public static ModelAddArt getInstance() {

        ModelAddArt result = instance;
        if (result != null) {
            return result;
        }
        synchronized(ModelAddArt.class) {
            if (instance == null) {
                instance = new ModelAddArt();
            }
            return instance;
        }
    }
    private ModelAddArt() {
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
