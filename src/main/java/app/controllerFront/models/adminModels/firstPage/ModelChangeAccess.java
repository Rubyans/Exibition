package app.controllerFront.models.adminModels.firstPage;

public class ModelChangeAccess { //singleton pattern model
    //used to store data for chenging an access
    private static volatile ModelChangeAccess instance; //the field must be valid for validation to work
    private static String model;

    public static ModelChangeAccess getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelChangeAccess result = instance;
        if (result != null) {
            return result;
        }
        synchronized(ModelChangeAccess.class) {
            if (instance == null) {
                instance = new ModelChangeAccess();
            }
            return instance;
        }
    }
    private ModelChangeAccess() {
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
