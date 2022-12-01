package app.model.adminModels.fourthPage;

import app.entities.adminEntities.fourthPage.AuthorShow;

import java.util.ArrayList;
import java.util.List;

public class ModelShowAuthor
{
    private static volatile ModelShowAuthor instance;
    private static List<AuthorShow> model;

    public static ModelShowAuthor getInstance() {

        ModelShowAuthor result = instance;
        if (result != null) {
            return result;
        }
        synchronized(ModelShowAuthor.class) {
            if (instance == null) {
                instance = new ModelShowAuthor();
            }
            return instance;
        }
    }
    private ModelShowAuthor() {
        model = new ArrayList<>();
    }

    public static void delete()
    {
        model.clear();
    }

    public void add(AuthorShow author) {
        model.add(author);
    }
    public List<AuthorShow> listShow()
    {
        if(model.size()==0)
            return null;
        return model;
    }
    public Boolean checkNull() {
        for (AuthorShow author : model) {
            if (author != null)
                return false;
        }
        return true;
    }
}
