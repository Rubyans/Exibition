package app.model.adminModels.sixthPage;


public class ModelDelView
{
    private static volatile ModelDelView instance;
    private static String model;

    public static ModelDelView getInstance() {

        ModelDelView result = instance;
        if (result != null) {
            return result;
        }
        synchronized(ModelDelView.class) {
            if (instance == null) {
                instance = new ModelDelView();
            }
            return instance;
        }
    }
    private ModelDelView() {
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
