package app.entities.adminEntities.thirdPage;

public class AddressShow
{
    private int Unumber;
    private String country;
    private String city;
    private String street;
    private int numberHouse;

    public AddressShow(int Unumber,String country, String city,String street,int numberHouse)
    {
        this.Unumber=Unumber;
        this.country=country;
        this.city=city;
        this.street=street;
        this.numberHouse=numberHouse;
    }

    public int getUnumber()
    {
        return Unumber;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }
    public String getStreet()
    {
        return street;
    }
    public int getNumberHouse()
    {
        return numberHouse;
    }
}
