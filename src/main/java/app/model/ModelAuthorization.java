package app.model;

import app.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModelAuthorization {
    private static ModelAuthorization instance = new ModelAuthorization();

    private List<User> model;

    public static ModelAuthorization getInstance() {
        return instance;
    }

    private ModelAuthorization() {
        model = new ArrayList<>();
    }

    public void add(User user) {
        model.add(user);
    }

    public List<String> list() {
        return model.stream()
                .map(User::getFirstName)
                .collect(Collectors.toList());
    }
}
