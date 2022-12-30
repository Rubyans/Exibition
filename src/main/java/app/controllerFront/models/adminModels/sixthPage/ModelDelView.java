package app.controllerFront.models.adminModels.sixthPage;


public class ModelDelView { //singleton pattern model
    //used to store data for deletes a view
    private static volatile ModelDelView instance; //the field must be valid for validation to work
    private static String model;

    public static ModelDelView getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelDelView result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelDelView.class) {
            if (instance == null) {
                instance = new ModelDelView();
            }
            return instance;
        }
    }

    private ModelDelView() {
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
