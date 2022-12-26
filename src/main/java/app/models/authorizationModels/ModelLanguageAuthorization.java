package app.models.authorizationModels;


public class ModelLanguageAuthorization { //singleton pattern model
    //used to store the results of changes language
    private static volatile ModelLanguageAuthorization instance; //the field must be valid for validation to work
    private static String model;
    public static ModelLanguageAuthorization getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelLanguageAuthorization result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelLanguageAuthorization.class) {
            if (instance == null) {
                instance = new ModelLanguageAuthorization();
            }
            return instance;
        }
    }

    private ModelLanguageAuthorization() {
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
