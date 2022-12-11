package app.entities.userEntities.firstPage;

import java.util.Date;

public class UserShowExhibition { //the class is used to display the data associated with the exhibition table for an authorized user in the database
    private String nameExhibition;
    private Double price;
    private Date dateStart;
    private Date dateEnd;
    private Integer ticket;

    public UserShowExhibition(String nameExhibition, Double price, Date dateStart, Date dateEnd, Integer ticket) {
        //the constructor receives data from the exhibition table to display to the authorized user
        this.nameExhibition = nameExhibition;
        this.price = price;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.ticket = ticket;
    }

    ///////////////////getFunctions//////////////////////////

    public String getNameExhibition() { return nameExhibition; }
    public Double getPrice() {
        return price;
    }
    public Date getDateStart() {
        return dateStart;
    }
    public Date getDateEnd() {
        return dateEnd;
    }
    public String getTicket() { //the function outputs a string for better formatting in tables
        if (ticket == null)
            return "Відсутні";
        return String.valueOf(ticket);
    }

    ///////////////////setFunctions//////////////////////////

    public void setNameExhibition(String nameExhibition) {
        this.nameExhibition = nameExhibition;
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
    public void setTicket(Integer ticket) {
        this.ticket = ticket;
    }
}
