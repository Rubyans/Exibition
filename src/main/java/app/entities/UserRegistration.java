package app.entities;

public class UserRegistration { //the class is used to store the result of registration
    private boolean checkRegistration;
    public UserRegistration(boolean checkRegistration) { //the constructor receives a boolean value, as a result of which it becomes clear whether the user has been registered
        this.checkRegistration = checkRegistration;
    }

    ///////////////////getFunctions//////////////////////////

    public String getCheckRegistration() { return String.valueOf(checkRegistration); }

    ///////////////////setFunctions//////////////////////////

    public void setCheckRegistration(boolean checkRegistration) {
        this.checkRegistration = checkRegistration;
    }
}
