package app.model.adminModels.seventhPage;

public class ModelAddAmountAutorized {
    private static volatile ModelAddAmountAutorized instance;
    private static String model;

    public static ModelAddAmountAutorized getInstance() {

        ModelAddAmountAutorized result = instance;
        if (result != null) {
            return result;
        }
        synchronized(ModelAddAmountAutorized.class) {
            if (instance == null) {
                instance = new ModelAddAmountAutorized();
            }
            return instance;
        }
    }
    private ModelAddAmountAutorized() {
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
