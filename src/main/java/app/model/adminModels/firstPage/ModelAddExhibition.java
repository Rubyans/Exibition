package app.model.adminModels.firstPage;

public class ModelAddExhibition
{
    private static volatile ModelAddExhibition instance;
    private static String model;

    public static ModelAddExhibition getInstance() {

        ModelAddExhibition result = instance;
        if (result != null) {
            return result;
        }
        synchronized(ModelAddExhibition.class) {
            if (instance == null) {
                instance = new ModelAddExhibition();
            }
            return instance;
        }
    }
    private ModelAddExhibition() {
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
