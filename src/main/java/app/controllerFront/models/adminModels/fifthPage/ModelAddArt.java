package app.controllerFront.models.adminModels.fifthPage;


public class ModelAddArt { //singleton pattern model
    //used to store the results of a successful or unsuccessful addition
    private static volatile ModelAddArt instance; //the field must be valid for validation to work
    private static String model;
    public static ModelAddArt getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelAddArt result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelAddArt.class) {
            if (instance == null) {
                instance = new ModelAddArt();
            }
            return instance;
        }
    }

    private ModelAddArt() {
        model = null;
    }
    public static void delete() {
        model = null;
    } //deletes data
    public void add(boolean text) {
        model = String.valueOf(text);
    } //adds data
    public String modelCheck() { //checks object
        if (model == null)
            return null;
        return model;
    }
}
