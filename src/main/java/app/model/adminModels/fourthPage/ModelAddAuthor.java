package app.model.adminModels.fourthPage;


public class ModelAddAuthor {
    private static ModelAddAuthor instance = new ModelAddAuthor();
    private static String model;

    public static ModelAddAuthor getInstance() {

        return instance;
    }
    private ModelAddAuthor() {
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
