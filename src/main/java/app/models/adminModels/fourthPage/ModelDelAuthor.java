package app.models.adminModels.fourthPage;


public class ModelDelAuthor { //singleton pattern model
    //used to store data for deleting an author
    private static volatile ModelDelAuthor instance; //the field must be valid for validation to work
    private static String model;

    public static ModelDelAuthor getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelDelAuthor result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelDelAuthor.class) {
            if (instance == null) {
                instance = new ModelDelAuthor();
            }
            return instance;
        }
    }

    private ModelDelAuthor() {
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
