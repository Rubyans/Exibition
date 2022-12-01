package app.model.adminModels.sixthPage;

public class ModelAddView {
    private static volatile ModelAddView instance;
    private static String model;

    public static ModelAddView getInstance() {

        ModelAddView result = instance;
        if (result != null) {
            return result;
        }
        synchronized(ModelAddView.class) {
            if (instance == null) {
                instance = new ModelAddView();
            }
            return instance;
        }
    }
    private ModelAddView() {
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
