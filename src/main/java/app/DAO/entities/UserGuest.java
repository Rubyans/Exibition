package app.DAO.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserGuest { //the class is used to store data from database exhibition table to show to unauthorized user
    private String nameExibition;
    private String descriptionExibition;
    private List<String> expositionName;
    private Double price;
    private Date dateStart;
    private Date dateEnd;

    public UserGuest(String nameExibition,String descriptionExibition, List<String> expositionName,Double price,Date dateStart,Date dateEnd) {
        //the constructor gets exhibition data for non-authorized user
        this.nameExibition=nameExibition;
        this.descriptionExibition=descriptionExibition;
        this.expositionName=new ArrayList<>(expositionName);
        this.price=price;
        this.dateStart=dateStart;
        this.dateEnd=dateEnd;
    }

    ///////////////////getFunctions//////////////////////////

    public String getNameExhibition() { return nameExibition; }
    public String getDescriptionExibition() {
        return descriptionExibition;
    }
    public String[] getExpositionName()
    {
        return expositionName.toArray(new String[0]);
    }
    public Double getPrice() {
        return price;
    }
    public Date getDateStart() {
        return dateStart;
    }
    public Date getDateEnd() {
        return dateEnd;
    }

    ///////////////////setFunctions//////////////////////////

    public void setNameExibition(String nameExibition) {
        this.nameExibition = nameExibition;
    }
    public void setDescriptionExibition(String descriptionExibition) { this.descriptionExibition = descriptionExibition; }
    public void setExpositionName(List<String> expositionName) {
        this.expositionName = expositionName;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }
    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }
}
