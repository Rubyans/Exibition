package app.controllerFront.models.adminModels.secondPage;


public class ModelAddHall {  //singleton pattern model
    //used to store data for adding a hall
    private static volatile ModelAddHall instance; //the field must be valid for validation to work
    private static String model;

    public static ModelAddHall getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelAddHall result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelAddHall.class) {
            if (instance == null) {
                instance = new ModelAddHall();
            }
            return instance;
        }
    }

    private ModelAddHall() {
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
