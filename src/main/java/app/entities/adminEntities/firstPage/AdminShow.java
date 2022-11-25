package app.entities.adminEntities.firstPage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AdminShow {
    private String nameExibition;
    private String descriptionExibition;
    private List<String> expositionName;
    private BigDecimal price;
    private Date dateStart;
    private Date dateEnd;

    private List<String> nameHall;

    private List<String> nameAuthor;
    private List<String> nameview;
    private List<String> addressExibition;

    public AdminShow(String nameExibition, String descriptionExibition, List<String> expositionName, BigDecimal price,
                     Date dateStart, Date dateEnd, List<String> nameHall, List<String> nameAuthor, List<String> nameview, List<String> addressExibition) {
        this.nameExibition = nameExibition;
        this.descriptionExibition = descriptionExibition;
        this.expositionName = new ArrayList<>(expositionName);
        this.price = price;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.nameHall = new ArrayList<>(nameHall);
        this.nameAuthor = new ArrayList<>(nameAuthor);
        this.nameview =  new ArrayList<>(nameview);
        this.addressExibition = new ArrayList<>(addressExibition);
    }

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
        List<String> temp;
        if (tempCount == expositionName.size()) {
            return new ArrayList<>(Collections.singleton(expositionName.get(0)));
        } else {
            return expositionName.stream().distinct().collect(Collectors.toList());
        }
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

    public List<String> getNameHall() {
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


    public List<String> getNameAuthor() {
        int tempCount = 0;
        String tempName = nameAuthor.get(0);
        for (String name : nameAuthor) {
            if (tempName.equals(name) == true) {
                tempName = name;
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

    public List<String> getNameview() {
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

    public List<String> getAddressExibition() {
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

}
