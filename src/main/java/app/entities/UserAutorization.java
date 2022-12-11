package app.entities;

public class UserAutorization { //the class is used to store data related to the entrance to the personal account
    public Integer userId;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String email;
    public String role;

    public UserAutorization(Integer userId, String firstName, String lastName, String login, String password, String email, String role) {
        //the constructor receives authorized data for further validation
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    ///////////////////getFunctions//////////////////////////

    public Integer getUserId() {
        return userId;
    }
    public String getRole() {
        return role;
    }
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

    ///////////////////setFunctions//////////////////////////

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

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




}
