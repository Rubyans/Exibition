package app.models.adminModels.seventhPage;

public class ModelAddAmountAutorized { //singleton pattern model
    //used to store data for adding an amount
    private static volatile ModelAddAmountAutorized instance; //the field must be valid for validation to work
    private static String model;

    public static ModelAddAmountAutorized getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelAddAmountAutorized result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelAddAmountAutorized.class) {
            if (instance == null) {
                instance = new ModelAddAmountAutorized();
            }
            return instance;
        }
    }

    private ModelAddAmountAutorized() {
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
