package app.controllerFront.models.recoveryModels;

public class ModelRecoveryPassword { //singleton pattern model
    //used to store data for recovery password
    private static volatile ModelRecoveryPassword instance; //the field must be valid for validation to work
    private static String model;

    public static ModelRecoveryPassword getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelRecoveryPassword result = instance;
        if (result != null) {
            return result;
        }
        synchronized(ModelRecoveryPassword.class) {
            if (instance == null) {
                instance = new ModelRecoveryPassword();
            }
            return instance;
        }
    }
    private ModelRecoveryPassword() {
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
