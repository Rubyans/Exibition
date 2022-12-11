package app.model.userModels.firstPage;


public class ModelSaveCommit {
    private static volatile ModelSaveCommit instance;
    private static String model;

    public static ModelSaveCommit getInstance() {

        ModelSaveCommit result = instance;
        if (result != null) {
            return result;
        }
        synchronized(ModelSaveCommit.class) {
            if (instance == null) {
                instance = new ModelSaveCommit();
            }
            return instance;
        }
    }
    private ModelSaveCommit() {
        model = null;
    }

    public static void delete()
    {
        model=null;
    }

    public void add(boolean text) {
        model= String.valueOf(text);
    }
    public String modelCheck()
    {
        if(model==null)
            return null;
        return model;
    }
}
