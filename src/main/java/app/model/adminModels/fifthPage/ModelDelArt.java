package app.model.adminModels.fifthPage;


public class ModelDelArt
{
    private static volatile ModelDelArt instance;
    private static String model;

    public static ModelDelArt getInstance() {

        ModelDelArt result = instance;
        if (result != null) {
            return result;
        }
        synchronized(ModelDelArt.class) {
            if (instance == null) {
                instance = new ModelDelArt();
            }
            return instance;
        }
    }
    private ModelDelArt() {
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
