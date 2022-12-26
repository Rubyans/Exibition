package app.DAO.entities.userEntities.firstPage;

public class UserShowAdd { //the class is used to get the title of the exhibitions
    private String nameExhibition;
    public UserShowAdd(String nameExhibition) { //the constructor gets the name of the exhibition from the database
        this.nameExhibition = nameExhibition;
    }

    ///////////////////getFunctions//////////////////////////

    public String getNameExhibition() {
        return nameExhibition;
    }

    ///////////////////setFunctions//////////////////////////

    public void setNameExhibition(String nameExhibition) {
        this.nameExhibition = nameExhibition;
    }
}
