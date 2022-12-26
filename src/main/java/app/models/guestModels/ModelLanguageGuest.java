package app.models.guestModels;


public class ModelLanguageGuest { //singleton pattern model
    //used to store the results of changes language
    private static volatile ModelLanguageGuest instance; //the field must be valid for validation to work
    private static String model;
    public static ModelLanguageGuest getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelLanguageGuest result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelLanguageGuest.class) {
            if (instance == null) {
                instance = new ModelLanguageGuest();
            }
            return instance;
        }
    }

    private ModelLanguageGuest() {
        model = null;
    }
    public static void delete() {
        model = null;
    } //deletes data
    public void add(String text) { model = text; } //adds data
    public String modelCheck() { //checks object
        if (model == null)
            return null;
        return model;
    }
}
