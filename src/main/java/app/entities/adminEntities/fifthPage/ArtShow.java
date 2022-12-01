package app.entities.adminEntities.fifthPage;

public class ArtShow
{
    private String name;
    private int yearCreation;
    private Double price;

    public ArtShow(String name, int yearCreation,Double price)
    {
        this.name=name;
        this.yearCreation=yearCreation;
        this.price=price;

    }

    public String getName() {
        return name;
    }

    public int getCreation() {
        return yearCreation;
    }
    public Double getPrice()
    {
        return price;
    }
}
