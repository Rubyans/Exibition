package app.model.userModels.firstPage;


public class ModelAddTicket {
    private static volatile ModelAddTicket instance;
    private static String model;

    public static ModelAddTicket getInstance() {

        ModelAddTicket result = instance;
        if (result != null) {
            return result;
        }
        synchronized(ModelAddTicket.class) {
            if (instance == null) {
                instance = new ModelAddTicket();
            }
            return instance;
        }
    }
    private ModelAddTicket() {
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
