package app.entities.adminEntities.firstPage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AdminShow { //the class is used to store exhibition-related data.
    private String nameExibition;
    private String descriptionExibition;
    private String accessExhibition;
    private List<String> expositionName;
    private Double price;
    private Date dateStart;
    private Date dateEnd;
    private List<String> nameHall;
    private List<String> nameAuthor;
    private List<String> nameview;
    private List<String> addressExibition;

    public AdminShow(String nameExibition, String descriptionExibition, List<String> expositionName, Double price,
                     Date dateStart, Date dateEnd, String accessExhibition, List<String> nameHall, List<String> nameAuthor, List<String> nameview, List<String> addressExibition) {
        //the constructor stores lists and data fields that will be displayed in the table in the future
        this.nameExibition = nameExibition;
        this.descriptionExibition = descriptionExibition;
        this.expositionName = new ArrayList<>(expositionName);
        this.price = price;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.accessExhibition = accessExhibition;
        this.nameHall = new ArrayList<>(nameHall);
        this.nameAuthor = new ArrayList<>(nameAuthor);
        this.nameview = new ArrayList<>(nameview);
        this.addressExibition = new ArrayList<>(addressExibition);
    }

    ///////////////////getFunctions//////////////////////////

    public String getNameExhibition() {
        return nameExibition;
    }

    public String getDescriptionExibition() {
        return descriptionExibition;
    }

    public List<String> getExpositionName() {

        int tempCount = 0;
        String tempName = expositionName.get(0);
        for (String name : expositionName) {
            if (tempName.equals(name) == true) {
                tempName = name;
                tempCount++;
            }
        }
        if (tempCount == expositionName.size()) {
            return new ArrayList<>(Collections.singleton(expositionName.get(0)));
        } else {
            return expositionName.stream().distinct().collect(Collectors.toList());
        }
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

    public String getAccessExhibition() {
        if (accessExhibition.equals("1"))
            return "Дозволено";
        return "Заборонено";
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
        for (String name : nameAuthor) {
            if (tempName.equals(name) == true) {
                tempName = name;
                tempCount++;
            }
        }
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

    public void setDescriptionExibition(String descriptionExibition) {
        this.descriptionExibition = descriptionExibition;
    }

    public void setExpositionName(List<String> expositionName) {
        this.expositionName = new ArrayList<>(expositionName);
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
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

    public void setAddressExibition(List<String> addressExibition) {
        this.addressExibition = new ArrayList<>(addressExibition);
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setAccessExhibition(String accessExhibition) {
        this.accessExhibition = accessExhibition;
    }
}
