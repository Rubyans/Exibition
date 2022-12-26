package app.DAO.entities.adminEntities.thirdPage;

public class AddressShow { //the class is used to display the data associated with the address table in the database
    private Integer Unumber;
    private String country;
    private String city;
    private String street;
    private Integer numberHouse;

    public AddressShow(Integer Unumber, String country, String city, String street, Integer numberHouse) { //the constructor gets data from database address table
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

    public void setUnumber(Integer Unumber) { this.Unumber = Unumber; }
    public void setCountry(String country) { this.country = country; }
    public void setCity(String city) { this.city = city; }
    public void setStreet(String street) { this.street = street; }
    public void setNumberHouse(Integer numberHouse) {
        this.numberHouse = numberHouse;
    }

}
