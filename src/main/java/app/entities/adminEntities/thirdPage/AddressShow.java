package app.entities.adminEntities.thirdPage;

public class AddressShow { //the class is used to display the data associated with the address table in the database
    private int Unumber;
    private String country;
    private String city;
    private String street;
    private int numberHouse;

    public AddressShow(int Unumber, String country, String city, String street, int numberHouse) { //the constructor gets data from database address table
        this.Unumber = Unumber;
        this.country = country;
        this.city = city;
        this.street = street;
        this.numberHouse = numberHouse;
    }

    ///////////////////getFunctions//////////////////////////
    public int getUnumber() { return Unumber; }
    public String getCountry() {
        return country;
    }
    public String getCity() {
        return city;
    }
    public String getStreet() {
        return street;
    }
    public int getNumberHouse() {
        return numberHouse;
    }

    ///////////////////setFunctions//////////////////////////

    public void setUnumber(int Unumber) { this.Unumber = Unumber; }
    public void setCountry(String country) { this.country = country; }
    public void setCity(String city) { this.city = city; }
    public void setStreet(String street) { this.street = street; }
    public void setNumberHouse(int numberHouse) {
        this.numberHouse = numberHouse;
    }

}
