package app.entities.adminEntities.fourthPage;

public class AuthorShow { //the class is used to display the data associated with the table of authors in the database
    private String firstName;
    private String lastName;
    private String email;

    public AuthorShow(String firstName, String lastName, String email) { //the constructor receives data about the authors from the database
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    ///////////////////getFunctions//////////////////////////

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }

    ///////////////////setFunctions//////////////////////////

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmail(String email) { this.email = email; }
}
