package app.DAO.entities.adminEntities.firstPage;

import java.util.ArrayList;
import java.util.List;

public class AdminAddShow { //The class is needed to work with data that is passed to the select
    private List<String> hall;
    private List<String> address;
    private List<String> art;

    public AdminAddShow(List<String> hall, List<String> address, List<String> art) { //the constructor receives lists of data to create the future hall
        this.hall = new ArrayList<>(hall);
        this.address = new ArrayList<>(address);
        this.art = new ArrayList<>(art);
    }

    ///////////////////getFunctions//////////////////////////

    public List<String> getHall() {
        return hall;
    }
    public List<String> getAddress() {
        return address;
    }
    public List<String> getArt() { return art; }

    ///////////////////setFunctions//////////////////////////

    public void setHall(List<String> hall) { this.hall = hall; }
    public void setAddress(List<String> address) { this.address = address; }
    public void setArt(List<String> art) { this.art = art; }
}