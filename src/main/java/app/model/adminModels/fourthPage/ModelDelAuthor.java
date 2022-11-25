package app.model.adminModels.fourthPage;


public class ModelDelAuthor
{
    private static ModelDelAuthor instance = new ModelDelAuthor();
    private static String model;

    public static ModelDelAuthor getInstance() {

        return instance;
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
