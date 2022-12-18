package app.model.adminModels.seventhPage;

public class ModelAddUserAutorized { //singleton pattern model
    //used to store data for adding a user
    private static volatile ModelAddUserAutorized instance; //the field must be valid for validation to work
    private static String model;

    public static ModelAddUserAutorized getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelAddUserAutorized result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelAddUserAutorized.class) {
            if (instance == null) {
                instance = new ModelAddUserAutorized();
            }
            return instance;
        }
    }

    private ModelAddUserAutorized() {
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
