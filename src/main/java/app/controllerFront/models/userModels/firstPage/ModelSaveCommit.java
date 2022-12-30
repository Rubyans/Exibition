package app.controllerFront.models.userModels.firstPage;


public class ModelSaveCommit { //singleton pattern model
    //used to store data for savecommit
    private static volatile ModelSaveCommit instance; //the field must be valid for validation to work
    private static String model;

    public static ModelSaveCommit getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelSaveCommit result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelSaveCommit.class) {
            if (instance == null) {
                instance = new ModelSaveCommit();
            }
            return instance;
        }
    }

    private ModelSaveCommit() {
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
