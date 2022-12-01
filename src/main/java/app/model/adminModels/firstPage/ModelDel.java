package app.model.adminModels.firstPage;

public class ModelDel {

    private static volatile ModelDel instance;
    private static String model;

    public static ModelDel getInstance() {

        ModelDel result = instance;
        if (result != null) {
            return result;
        }
        synchronized(ModelDel.class) {
            if (instance == null) {
                instance = new ModelDel();
            }
            return instance;
        }
    }
    private ModelDel() {
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
