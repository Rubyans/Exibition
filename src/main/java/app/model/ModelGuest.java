package app.model;

import app.entities.UserGuest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ModelGuest {
    private static  ModelGuest instance = new ModelGuest();
    private static List<UserGuest> model;

    public static ModelGuest getInstance() {

        return instance;
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
