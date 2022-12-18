package app.model.userModels.firstPage;


public class ModelRoleBackCommit { //singleton pattern model
    //used to store data for roleback
    private static volatile ModelRoleBackCommit instance; //the field must be valid for validation to work
    private static String model;

    public static ModelRoleBackCommit getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelRoleBackCommit result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelRoleBackCommit.class) {
            if (instance == null) {
                instance = new ModelRoleBackCommit();
            }
            return instance;
        }
    }

    private ModelRoleBackCommit() {
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
