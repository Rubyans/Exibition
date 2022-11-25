package app.entities.adminEntities.secondPage;

import java.math.BigDecimal;

public class HallShow
{
    private String name;
    private BigDecimal square;

    public HallShow(String name, BigDecimal square) {
        this.name=name;
        this.square=square;
    }
    public String getName() {
        return name;
    }

    public BigDecimal getSquare() {
        return square;
    }

}
