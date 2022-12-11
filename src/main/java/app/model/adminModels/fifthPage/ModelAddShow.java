package app.model.adminModels.fifthPage;

import app.entities.adminEntities.fifthPage.ArtAddShow;

import java.util.ArrayList;
import java.util.List;

public class ModelAddShow {
    private static volatile ModelAddShow instance;
    private static List<ArtAddShow> model;

    private ModelAddShow() {
        model = new ArrayList<>();
    }

    public static ModelAddShow getInstance() {

        ModelAddShow result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelAddShow.class) {
            if (instance == null) {
                instance = new ModelAddShow();
            }
            return instance;
        }
    }

    public static void delete() {
        model.clear();
    }

    public void add(ArtAddShow art) {
        model.add(art);
    }

    public List<ArtAddShow> listShow() {
        if (model.size() == 0)
            return null;
        return model;
    }

    public Boolean checkNull() {
        for (ArtAddShow art : model) {
            if (art != null)
                return false;
        }
        return true;
    }
}