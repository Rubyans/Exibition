package app.DAO.entities.userEntities.secondPage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class UserShowExhibition { //the class is used to display exhibitions for which an authorized user has bought tickets
    private String nameExibition;
    private String descriptionExibition;
    private List<String> expositionName;
    private BigDecimal price;
    private Date dateStart;
    private Date dateEnd;
    private String hours;
    private List<String> nameHall;
    private List<String> nameAuthor;
    private List<String> nameview;
    private List<String> addressExibition;
    public UserShowExhibition(String nameExibition, String descriptionExibition, List<String> expositionName, BigDecimal price, Date dateStart, Date dateEnd,String hours, List<String> nameHall, List<String> nameAuthor, List<String> nameview, List<String> addressExibition) {
        //the constructor receives data from several tables (authorized user, ticket, exhibition) of the database and makes it possible to work with them further
        this.nameExibition = nameExibition;
        this.descriptionExibition = descriptionExibition;
        this.expositionName = new ArrayList<>(expositionName);
        this.price = price;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.hours=hours;
        this.nameHall = new ArrayList<>(nameHall);
        this.nameAuthor = new ArrayList<>(nameAuthor);
        this.nameview = new ArrayList<>(nameview);
        this.addressExibition = new ArrayList<>(addressExibition);
    }

    ///////////////////getFunctions//////////////////////////

    public String getNameExhibition() { return nameExibition; }
    public String getDescriptionExibition() {
        return descriptionExibition;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public Date getDateStart() {
        return dateStart;
    }
    public Date getDateEnd() {
        return dateEnd;
    }
    public String getHours() {
        return hours;
    }


    public List<String> getExpositionName() { //the function removes the same data

        int tempCount = 0;
        String tempName = expositionName.get(0);
        for (String name : expositionName) {
            if (tempName.equals(name) == true) {
                tempName = name;
                tempCount++;
            }
        }
        List<String> temp;
        if (tempCount == expositionName.size()) {
            return new ArrayList<>(Collections.singleton(expositionName.get(0)));
        } else {
            return expositionName.stream().distinct().collect(Collectors.toList());
        }
    }

    public List<String> getNameHall() { //the function removes the same data
        int tempCount = 0;
        String tempName = nameHall.get(0);
        for (String name : nameHall) {
            if (tempName.equals(name) == true) {
                tempName = name;
                tempCount++;
            }
        }
        if (tempCount == nameHall.size())
            return new ArrayList<>(Collections.singleton(nameHall.get(0)));
        else {
            return nameHall.stream().distinct().collect(Collectors.toList());
        }
    }
    public List<String> getNameAuthor() { //the function removes the same data
        int tempCount = 0;
        String tempName = nameAuthor.get(0);
        for (String author : nameAuthor) {
            if (tempName.equals(author) == true) {
                tempName = author;
                tempCount++;
            }
        }
        List<String> temp;
        if (tempCount == nameAuthor.size()) {
            return new ArrayList<>(Collections.singleton(nameAuthor.get(0)));
        } else {
            return nameAuthor.stream().distinct().collect(Collectors.toList());
        }
    }

    public List<String> getNameview() { //the function removes the same data
        int tempCount = 0;
        String tempName = nameview.get(0);
        for (String name : nameview) {
            if (tempName.equals(name) == true) {
                tempName = name;
                tempCount++;
            }
        }
        List<String> temp;
        if (tempCount == nameview.size()) {
            return new ArrayList<>(Collections.singleton(nameview.get(0)));
        } else {
            return nameview.stream().distinct().collect(Collectors.toList());
        }
    }

    public List<String> getAddressExibition() { //the function removes the same data
        int tempCount = 0;
        String tempName = addressExibition.get(0);
        for (String address : addressExibition) {
            if (tempName.equals(address) == true) {
                tempName = address;
                tempCount++;
            }
        }
        if (tempCount == addressExibition.size())
            return new ArrayList<>(Collections.singleton(addressExibition.get(0)));
        else
            return addressExibition.stream().distinct().collect(Collectors.toList());
    }

    ///////////////////setFunctions//////////////////////////

    public void setNameExibition(String nameExibition) {
        this.nameExibition = nameExibition;
    }
    public void setDescriptionExibition(String descriptionExibition) { this.descriptionExibition = descriptionExibition; }
    public void setExpositionName(List<String> expositionName) { this.expositionName = new ArrayList<>(expositionName); }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }
    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }
    public void setHours(String hours) {
        this.hours = hours;
    }
    public void setNameview(List<String> nameview) {
        this.nameview = new ArrayList<>(nameview);
    }
    public void setNameHall(List<String> nameHall) {
        this.nameHall = new ArrayList<>(nameHall);
    }
    public void setNameAuthor(List<String> nameAuthor) {
        this.nameAuthor = new ArrayList<>(nameAuthor);
    }
    public void setAddressExibition(List<String> addressExibition) { this.addressExibition = new ArrayList<>(addressExibition);}
}
