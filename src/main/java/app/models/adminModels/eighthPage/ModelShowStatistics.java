package app.models.adminModels.eighthPage;


import app.DAO.entities.adminEntities.eighthPage.ExhibitionStatisticsShow;

import java.util.ArrayList;
import java.util.List;

public class ModelShowStatistics { //singleton pattern model
    //used to store class objects with the results of a show statistics
    private static volatile ModelShowStatistics instance; //the field must be valid for validation to work
    private static List<ExhibitionStatisticsShow> model; //data storage list

    public static ModelShowStatistics getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelShowStatistics result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelShowStatistics.class) {
            if (instance == null) {
                instance = new ModelShowStatistics();
            }
            return instance;
        }
    }
    private ModelShowStatistics() {
        model = new ArrayList<>();
    }
    public static void delete() { //deletes object
        model.clear();
    } //deletes object
    public void add(ExhibitionStatisticsShow exhibition) { //adds object
        model.add(exhibition);
    } //adds object
    public List<ExhibitionStatisticsShow> listShow() { //returns list
        if (model.size() == 0)
            return null;
        return model;
    }
    public Boolean checkNull() { //checks list for null values
        for (ExhibitionStatisticsShow exhibition : model) {
            if (exhibition != null)
                return false;
        }
        return true;
    }
}
