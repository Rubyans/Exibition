package app.models.userModels.firstPage;


public class ModelAddTicket { //singleton pattern model
    //used to store data for adds a ticket
    private static volatile ModelAddTicket instance; //the field must be valid for validation to work
    private static String model;

    public static ModelAddTicket getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
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
    } //deletes object

    public void add(boolean text) {
        model= String.valueOf(text);
    } //adds object
    public String modelCheck() //checks object
    {
        if(model==null)
            return null;
        return model;
    }
}
