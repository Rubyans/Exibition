package app.entities.adminEntities.seventhPage;
public class UserAutorizedShow { //the class is used to display the data associated with the table of authorized users in the database
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String email;
    private String role;
    private Double amount;
    private String access;

    public UserAutorizedShow(String firstName, String lastName, String login, String password, String email, Double amount, String role,String access) {
        //the constructor gets data from the table of authorized database users
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.amount = amount;
        this.role = role;
        this.access=access;
    }

    ///////////////////getFunctions//////////////////////////

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public Double getAmount() {
        return amount;
    }
    public String getRole() {  //the function returns the text for better display in the table
        if (role.equals("2"))
            return "Адміністратор";
        return "Користувач";
    }
    public String getAccess() {
        if(access.equals("1"))
            return "Дозволено";
        return "Заборонено";
    }
    ///////////////////setFunctions//////////////////////////

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public void setFistName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public void setAccess(String access) { this.access = access; }
}
