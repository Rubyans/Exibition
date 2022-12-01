package app.model;

import app.entities.UserGuest;

import java.util.ArrayList;
import java.util.List;

public class ModelGuest {
    private static volatile ModelGuest instance;
    private static List<UserGuest> model;

    public static ModelGuest getInstance() {

        ModelGuest result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelGuest.class) {
            if (instance == null) {
                instance = new ModelGuest();
            }
            return instance;
        }
    }
    private ModelGuest() {
        model = new ArrayList<>();
    }

    public static void delete()
    {
        model.clear();
    }
    public void add(UserGuest guest) {
        model.add(guest);
    }
    public List<UserGuest> listUserGuest()
    {
        if(model.size()==0)
            return null;
        return model;
    }
    public Boolean checkNull()
    {
        for(UserGuest guest:model)
        {
            if(guest!=null)
                return false;
        }
        return true;
    }
}
