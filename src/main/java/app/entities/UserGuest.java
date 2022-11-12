package app.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserGuest {

    private String nameExibition;
    private String descriptionExibition;
    private List<String> expositionName;
    private BigDecimal price;
    private Date dateStart;
    private Date dateEnd;

    public UserGuest(String nameExibition,String descriptionExibition, List<String> expositionName,BigDecimal price,Date dateStart,Date dateEnd)
    {
        this.nameExibition=nameExibition;
        this.descriptionExibition=descriptionExibition;
        this.expositionName=new ArrayList<>(expositionName);;
        this.price=price;
        this.dateStart=dateStart;
        this.dateEnd=dateEnd;
    }
    public String getNameExhibition() {

        return nameExibition;
    }
    public String getDescriptionExibition() {
        return descriptionExibition;
    }
    public String[] getExpositionName()
    {
        return expositionName.toArray(new String[0]);
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

}
