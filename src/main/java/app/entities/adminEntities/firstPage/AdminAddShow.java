package app.entities.adminEntities.firstPage;

import java.util.ArrayList;
import java.util.List;

public class AdminAddShow {

    private List<String> hall;
    private List<String> address;
    private List<String> art;

    public AdminAddShow(List<String> hall,List<String> address,List<String> art){
        this.hall=new ArrayList<>(hall);
        this.address=new ArrayList<>(address);
        this.art=new ArrayList<>(art);

    }
    public List<String> getHall() {
        return hall;
    }

    public List<String> getAddress() {
        return address;
    }
    public List<String> getArt() {return art;}

}