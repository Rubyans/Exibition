package app.model.adminModels.thirdPage;


public class ModelDelAddress { //singleton pattern model
    //used to store data for deleting an address
    private static volatile ModelDelAddress instance; //the field must be valid for validation to work
    private static String model;

    public static ModelDelAddress getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelDelAddress result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelDelAddress.class) {
            if (instance == null) {
                instance = new ModelDelAddress();
            }
            return instance;
        }
    }

    private ModelDelAddress() {
        model = null;
    }

    public static void delete() {
        model = null;
    } //deletes object

    public void add(boolean text) {
        model = String.valueOf(text);
    } //adds object

    public String modelCheck() { //checks object
        if (model == null)
            return null;
        return model;
    }
}
