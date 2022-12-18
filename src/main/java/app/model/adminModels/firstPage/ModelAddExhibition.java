package app.model.adminModels.firstPage;

public class ModelAddExhibition { //singleton pattern model
    //used to store data for adding an exhibition
    private static volatile ModelAddExhibition instance; //the field must be valid for validation to work
    private static String model;

    public static ModelAddExhibition getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelAddExhibition result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelAddExhibition.class) {
            if (instance == null) {
                instance = new ModelAddExhibition();
            }
            return instance;
        }
    }

    private ModelAddExhibition() {
        model = null;
    }

    public static void delete() { //deletes object
        model = null;
    } //deletes object

    public void add(boolean text) { //adds object
        model = String.valueOf(text);
    } //adds object

    public String modelCheck() { //checks object
        if (model == null)
            return null;
        return model;
    }
}
