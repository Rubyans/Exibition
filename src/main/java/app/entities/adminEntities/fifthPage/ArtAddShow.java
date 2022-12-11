package app.entities.adminEntities.fifthPage;

import java.util.ArrayList;
import java.util.List;

public class ArtAddShow { //the class receives data from the database and passes it to the model, which passes it to the select
    private List<String> firstName;
    private List<String> lastName;
    private List<String> email;
    private List<String> view;
    public ArtAddShow(List<String> firstName, List<String> lastName, List<String> email, List<String> view) { //the constructor gets lists of art-related data
        this.firstName = new ArrayList<>(firstName);
        this.lastName = new ArrayList<>(lastName);
        this.email = new ArrayList<>(email);
        this.view = new ArrayList<>(view);
    }
///////////////////getFunctions//////////////////////////

    public List<String> getFullName() { //function returns fullName
        List<String> fullName = new ArrayList<>();
        for (int i = 0; i < firstName.size(); i++) {
            fullName.add(firstName.get(i) + " " + lastName.get(i) + " " + email.get(i));
        }
        return fullName;
    }
    public List<String> getFirstName() { return firstName; }
    public List<String> getLastName() { return lastName; }
    public List<String> getEmail() { return email; }
    public List<String> getView() {
        return view;
    }

///////////////////setFunctions//////////////////////////

    public void setFirstName(List<String> firstName) { this.firstName=new ArrayList<>(firstName); }
    public void setLastName(List<String> lastName) { this.lastName=new ArrayList<>(lastName); }
    public void setEmail(List<String> email) { this.email=new ArrayList<>(email); }
    public void setView(List<String> view) { this.view=new ArrayList<>(view); }





}