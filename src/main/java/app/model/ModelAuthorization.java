package app.model;

import app.entities.User;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModelAuthorization {
    private static volatile ModelAuthorization instance;

    private static List<User> model;

    public static ModelAuthorization getInstance() {
        ModelAuthorization result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelAuthorization.class) {
            if (instance == null) {
                instance = new ModelAuthorization();
            }
            return instance;
        }
    }

    private ModelAuthorization() {
        model = new ArrayList<>();
    }


    public String roleCheck() {
        return model.get(0).getRole();
    }

    public static void delete() {
        model.clear();
    }

    public void add(User user) {
        model.add(user);
    }

    public List<User> listUser() {
        if (model.size() == 0)
            return null;
        return model;
    }

    public Boolean checkNull() {
        for (User user : model) {
            if (user != null)
                return false;
        }
        return true;
    }

    public List<String> list() {
        return model.stream()
                .map(User::getFirstName)
                .collect(Collectors.toList());
    }
}
