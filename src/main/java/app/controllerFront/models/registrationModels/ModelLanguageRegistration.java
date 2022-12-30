package app.controllerFront.models.registrationModels;


public class ModelLanguageRegistration { //singleton pattern model
    //used to store the results of changes language
    private static volatile ModelLanguageRegistration instance; //the field must be valid for validation to work
    private static String model;
    public static ModelLanguageRegistration getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelLanguageRegistration result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelLanguageRegistration.class) {
            if (instance == null) {
                instance = new ModelLanguageRegistration();
            }
            return instance;
        }
    }

    private ModelLanguageRegistration() {
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
