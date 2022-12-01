package app.model.adminModels.sixthPage;


import app.entities.adminEntities.sixthPage.ViewShow;

import java.util.ArrayList;
import java.util.List;

public class ModelShowView {
    private static volatile ModelShowView instance;
    private static List<ViewShow> model;

    public static ModelShowView getInstance() {

        ModelShowView result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelShowView.class) {
            if (instance == null) {
                instance = new ModelShowView();
            }
            return instance;
        }
    }

    private ModelShowView() {
        model = new ArrayList<>();
    }

    public static void delete() {
        model.clear();
    }

    public void add(ViewShow view) {
        model.add(view);
    }

    public List<ViewShow> listShow() {
        if (model.size() == 0)
            return null;
        return model;
    }

    public Boolean checkNull() {
        for (ViewShow view : model) {
            if (view != null)
                return false;
        }
        return true;
    }
}
