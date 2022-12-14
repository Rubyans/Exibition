package app.controllerFront.models.adminModels.fourthPage;

public class ModelAddAuthor { //singleton pattern model
    //used to store data for adding an author
    private static volatile ModelAddAuthor instance; //the field must be valid for validation to work
    private static String model;

    public static ModelAddAuthor getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelAddAuthor result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelAddAuthor.class) {
            if (instance == null) {
                instance = new ModelAddAuthor();
            }
            return instance;
        }
    }

    private ModelAddAuthor() {
        model = null;
    }

    public static void delete() {
        model = null;
    } //deletes object

    public void add(boolean text) {
        model = String.valueOf(text);
    } //adds object

    public String modelCheck() { // checks object
        if (model == null)
            return null;
        return model;
    }
}
