package app.model;


public class ModelRegistration {
    private static volatile ModelRegistration instance;
    private static String model;
    public static ModelRegistration getInstance() {

        ModelRegistration result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelRegistration.class) {
            if (instance == null) {
                instance = new ModelRegistration();
            }
            return instance;
        }
    }
    private ModelRegistration() {
        model = null;
    }
    public static void delete() {
        model = null;
    }
    public void add(String regString) {
        model = regString;
    }
    public String checkString() {
        if (model == null)
            return null;
        return model;
    }
    public String returnString() {
        return model;
    }
}
