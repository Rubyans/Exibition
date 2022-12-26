package app.models.registrationModels;


public class ModelRegistration { //singleton pattern model
    //used to store data for registration
    private static volatile ModelRegistration instance; //the field must be valid for validation to work
    private static String model;
    public static ModelRegistration getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelRegistration result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelRegistration.class) {
            if (instance == null) {
                instance = new ModelRegistration();
            }
            return instance;
        }
    }
    private ModelRegistration() {
        model = null;
    }
    public static void delete() {
        model = null;
    } //deletes object
    public void add(String regString) {
        model = regString;
    } //adds object
    public String checkString() { //checks object
        if (model == null)
            return null;
        return model;
    }
    public String returnString() {
        return model;
    } //returns object
}
