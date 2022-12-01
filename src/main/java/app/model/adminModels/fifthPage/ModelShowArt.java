package app.model.adminModels.fifthPage;

import app.entities.adminEntities.fifthPage.ArtShow;

import java.util.ArrayList;
import java.util.List;

public class ModelShowArt
{
    private static volatile ModelShowArt instance;
    private static List<ArtShow> model;

    public static ModelShowArt getInstance() {

        ModelShowArt result = instance;
        if (result != null) {
            return result;
        }
        synchronized(ModelShowArt.class) {
            if (instance == null) {
                instance = new ModelShowArt();
            }
            return instance;
        }
    }
    private ModelShowArt() {
        model = new ArrayList<>();
    }

    public static void delete()
    {
        model.clear();
    }

    public void add(ArtShow art) {
        model.add(art);
    }
    public List<ArtShow> listShow()
    {
        if(model.size()==0)
            return null;
        return model;
    }
    public Boolean checkNull() {
        for (ArtShow art : model) {
            if (art != null)
                return false;
        }
        return true;
    }
}
