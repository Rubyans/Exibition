package app.controllerFront.models.userModels.secondPage;


public class ModelLanguageUserSecond { //singleton pattern model
    //used to store the results of changes language
    private static volatile ModelLanguageUserSecond instance; //the field must be valid for validation to work
    private static String model;
    public static ModelLanguageUserSecond getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelLanguageUserSecond result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelLanguageUserSecond.class) {
            if (instance == null) {
                instance = new ModelLanguageUserSecond();
            }
            return instance;
        }
    }

    private ModelLanguageUserSecond() {
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
