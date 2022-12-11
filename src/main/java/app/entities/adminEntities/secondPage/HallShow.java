package app.entities.adminEntities.secondPage;

import java.math.BigDecimal;

public class HallShow { //the class is used to display the data associated with the table of halls in the database
    private String name;
    private BigDecimal square;
    public HallShow(String name, BigDecimal square) { //the constructor receives data about the halls from the database
        this.name = name;
        this.square = square;
    }

    ///////////////////getFunctions//////////////////////////

    public String getName() {
        return name;
    }
    public BigDecimal getSquare() {
        return square;
    }

    ///////////////////setFunctions//////////////////////////

    public void setName(String name) { this.name = name; }
    public void setSquare(BigDecimal square) { this.square = square; }
}
