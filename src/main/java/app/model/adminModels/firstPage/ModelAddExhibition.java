package app.model.adminModels.firstPage;

public class ModelAddExhibition
{
    private static ModelAddExhibition instance = new ModelAddExhibition();
    private static String model;

    public static ModelAddExhibition getInstance() {

        return instance;
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
