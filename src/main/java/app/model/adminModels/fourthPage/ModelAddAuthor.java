package app.model.adminModels.fourthPage;



public class ModelAddAuthor {
    private static volatile ModelAddAuthor instance;
    private static String model;

    public static ModelAddAuthor getInstance() {

        ModelAddAuthor result = instance;
        if (result != null) {
            return result;
        }
        synchronized(ModelAddAuthor.class) {
            if (instance == null) {
                instance = new ModelAddAuthor();
            }
            return instance;
        }
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
