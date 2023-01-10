package app.controllerFront.models.recoveryModels;

public class ModelRecovery { //singleton pattern model
    //used to store data for pin-code
    private static volatile ModelRecovery instance; //the field must be valid for validation to work
    private static String model;

    public static ModelRecovery getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelRecovery result = instance;
        if (result != null) {
            return result;
        }
        synchronized(ModelRecovery.class) {
            if (instance == null) {
                instance = new ModelRecovery();
            }
            return instance;
        }
    }
    private ModelRecovery() {
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
