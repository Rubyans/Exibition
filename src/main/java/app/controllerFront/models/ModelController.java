package app.controllerFront.models;

import javax.servlet.http.HttpServletRequest;

public class ModelController { //singleton pattern model
    //used to store class objects with the results of a show request
    private static volatile ModelController instance; //the field must be valid for validation to work
    private static String modelReq; //data storage list

    public static ModelController getInstance() {
        // double-checked locking is used to
        // prevent multiple lone objects from being created
        // if the method is called from multiple threads at the same time.
        ModelController result = instance;
        if (result != null) {
            return result;
        }
        synchronized (ModelController.class) {
            if (instance == null) {
                instance = new ModelController();
            }
            return instance;
        }
    }
    private ModelController() {
        modelReq = null;
    }


    public void add(HttpServletRequest req) { //adds object
        modelReq=req.getParameter("command");
    }



    public String showParametr() { //returns list
        if (modelReq == null)
            return null;
        return modelReq;
    }
//    public Boolean checkNull() { //checks list for null values
//        for (ArtShow art : model) {
//            if (art != null)
//                return false;
//        }
//        return true;
//    }
}
