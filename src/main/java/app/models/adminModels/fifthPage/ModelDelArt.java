package app.models.adminModels.fifthPage;


public class ModelDelArt { //singleton pattern model
    //used to store class objects with the results of a delete request
    private static volatile ModelDelArt instance; //the field must be valid for validation to work
    private static String model;

    public static ModelDelArt getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelDelArt result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelDelArt.class) {
            if (instance == null) {
                instance = new ModelDelArt();
            }
            return instance;
        }
    }

    private ModelDelArt() {
        model = null;
    }

    public static void delete() { //deletes object
        model = null;
    } //deletes object
    public void add(boolean text) { //adds object
        model = String.valueOf(text);
    } //adds object
    public String modelCheck() { //checks object for null values
        if (model == null)
            return null;
        return model;
    }
}
