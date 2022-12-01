package app.entities.adminEntities.seventhPage;

public class UserAutorizedShow
{
    private String fistName;
    private String lastName;
    private String login;
    private String password;
    private String email;
    private String role;
    private Double amount;

    public UserAutorizedShow(String fistName,String lastName,String login,String password,String email,Double amount,String role)
    {
        this.fistName=fistName;
        this.lastName=lastName;
        this.login=login;
        this.password=password;
        this.email=email;
        this.amount=amount;
        this.role=role;

    }
    public String getFirstName() {
        return fistName;
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
    public String getRole() {

        if(role.equals("2"))
            return "Адміністратор";
        return "Користувач";
    }


}
