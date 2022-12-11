package app.model.userModels.firstPage;


import app.entities.userEntities.firstPage.UserShowMoney;

import java.util.ArrayList;
import java.util.List;

public class ModelShowMoney {
    private static volatile ModelShowMoney instance;
    private static List<UserShowMoney> model;
    private ModelShowMoney() { model = new ArrayList<>(); }

    public static ModelShowMoney getInstance() {

        ModelShowMoney result = instance;
        if (result != null) {
            return result;
        }
        synchronized(ModelShowMoney.class) {
            if (instance == null) {
                instance = new ModelShowMoney();
            }
            return instance;
        }
    }

    public static void delete()
    {
        model.clear();
    }

    public void add(UserShowMoney money) {
        model.add(money);
    }
    public List<UserShowMoney> listShow()
    {
        if(model.size()==0)
            return null;
        return model;
    }
    public Boolean checkNull() {
        for (UserShowMoney user : model) {
            if (user != null)
                return false;
        }
        return true;
    }
}
