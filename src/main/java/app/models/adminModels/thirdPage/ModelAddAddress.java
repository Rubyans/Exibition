package app.models.adminModels.thirdPage;


public class ModelAddAddress { //singleton pattern model
    //used to store data for adds an address
    private static volatile ModelAddAddress instance; //the field must be valid for validation to work
    private static String model;

    public static ModelAddAddress getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelAddAddress result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelAddAddress.class) {
            if (instance == null) {
                instance = new ModelAddAddress();
            }
            return instance;
        }
    }

    private ModelAddAddress() {
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
