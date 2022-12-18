package app.model.adminModels.firstPage;

public class ModelDel { //singleton pattern model
    //used to store data for deleting an exhibition
    private static volatile ModelDel instance; //the field must be valid for validation to work
    private static String model;

    public static ModelDel getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelDel result = instance;
        if (result != null) {
            return result;
        }
        synchronized(ModelDel.class) {
            if (instance == null) {
                instance = new ModelDel();
            }
            return instance;
        }
    }
    private ModelDel() {
        model = null;
    }

    public static void delete()
    {
        model=null;
    } //deletes object

    public void add(boolean text) {
        model= String.valueOf(text);
    } //adds object
    public String modelCheck() //checks object
    {
        if(model==null)
            return null;
        return model;
    }
}
