package app.entities.userEntities.firstPage;

public class UserShowMoney { //the class is used to display the money of the authorized user in the DB
    public Double money;
    public UserShowMoney(Double money) { //the constructor gets data from table authorized user to display money
        this.money = money;
    }

    ///////////////////getFunctions//////////////////////////

    public Double getAmount() {
        return money;
    }

    ///////////////////setFunctions//////////////////////////

    public void setMoney(Double money) {
        this.money = money;
    }
}
