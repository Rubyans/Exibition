package app.model.userModels.firstPage;


public class ModelRoleBackCommit {
    private static volatile ModelRoleBackCommit instance;
    private static String model;

    public static ModelRoleBackCommit getInstance() {

        ModelRoleBackCommit result = instance;
        if (result != null) {
            return result;
        }
        synchronized(ModelRoleBackCommit.class) {
            if (instance == null) {
                instance = new ModelRoleBackCommit();
            }
            return instance;
        }
    }
    private ModelRoleBackCommit() {
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
