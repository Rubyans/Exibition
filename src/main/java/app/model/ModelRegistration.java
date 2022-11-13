package app.model;

import app.entities.UserGuest;

import java.util.ArrayList;
import java.util.List;

public class ModelRegistration
{
    private static  ModelRegistration instance = new ModelRegistration();
    private static String model;

    public static ModelRegistration getInstance() {

        return instance;
    }
    private ModelRegistration() {
        model=null;
    }

    public static void delete()
    {
        model=null;
    }
    public void add(String regString) {
        model=regString;
    }
    public String checkString()
    {
        if(model==null)
            return null;
        return model;
    }
    public String returnString()
    {
        return model;
    }
}
