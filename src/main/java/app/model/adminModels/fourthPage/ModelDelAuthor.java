package app.model.adminModels.fourthPage;


public class ModelDelAuthor
{
    private static volatile ModelDelAuthor instance;
    private static String model;

    public static ModelDelAuthor getInstance() {

        ModelDelAuthor result = instance;
        if (result != null) {
            return result;
        }
        synchronized(ModelDelAuthor.class) {
            if (instance == null) {
                instance = new ModelDelAuthor();
            }
            return instance;
        }
    }
    private ModelDelAuthor() {
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
