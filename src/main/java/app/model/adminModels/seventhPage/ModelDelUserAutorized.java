package app.model.adminModels.seventhPage;


public class ModelDelUserAutorized { //singleton pattern model
    //used to store data for deleting a user
    private static volatile ModelDelUserAutorized instance; //the field must be valid for validation to work
    private static String model;

    public static ModelDelUserAutorized getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelDelUserAutorized result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelDelUserAutorized.class) {
            if (instance == null) {
                instance = new ModelDelUserAutorized();
            }
            return instance;
        }
    }

    private ModelDelUserAutorized() {
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
