package app.model.adminModels.sixthPage;

public class ModelAddView { //singleton pattern model
    //used to store data for adds a view
    private static volatile ModelAddView instance; //the field must be valid for validation to work
    private static String model;

    public static ModelAddView getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelAddView result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelAddView.class) {
            if (instance == null) {
                instance = new ModelAddView();
            }
            return instance;
        }
    }

    private ModelAddView() {
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
