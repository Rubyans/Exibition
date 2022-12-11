package app.model;

import app.entities.UserAutorization;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModelAuthorization {
    private static volatile ModelAuthorization instance;

    private static List<UserAutorization> model;

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

    public String getId() {return String.valueOf(model.get(0).getUserId()); }

    public static void delete() {
        model.clear();
    }

    public void add(UserAutorization userAutorization) {
        model.add(userAutorization);
    }

    public List<UserAutorization> listUser() {
        if (model.size() == 0)
            return null;
        return model;
    }

    public Boolean checkNull() {
        for (UserAutorization userAutorization : model) {
            if (userAutorization != null)
                return false;
        }
        return true;
    }

    public List<String> list() {
        return model.stream()
                .map(UserAutorization::getFirstName)
                .collect(Collectors.toList());
    }
}
