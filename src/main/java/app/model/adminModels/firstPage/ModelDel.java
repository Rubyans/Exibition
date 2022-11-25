package app.model.adminModels.firstPage;

public class ModelDel {

    private static ModelDel instance = new ModelDel();
    private static String model;

    public static ModelDel getInstance() {

        return instance;
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
