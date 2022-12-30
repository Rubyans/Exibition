package app.controllerFront.models.adminModels.secondPage;


public class ModelDelHall { //singleton pattern model
    //used to store data for deleting a hall
    private static volatile ModelDelHall instance; //the field must be valid for validation to work
    private static String model;

    public static ModelDelHall getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelDelHall result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelDelHall.class) {
            if (instance == null) {
                instance = new ModelDelHall();
            }
            return instance;
        }
    }

    private ModelDelHall() {
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
