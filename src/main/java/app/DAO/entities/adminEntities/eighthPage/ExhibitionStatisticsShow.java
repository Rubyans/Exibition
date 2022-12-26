package app.DAO.entities.adminEntities.eighthPage;

public class ExhibitionStatisticsShow { //the class is used to display the data associated with the table of statistics exhibition in the database
    private String nameExhibition;
    private String ticketCount;

    public ExhibitionStatisticsShow(String nameExhibition, String ticketCount) {
        //the constructor gets data from the table of statistics
        this.nameExhibition = nameExhibition;
        this.ticketCount = ticketCount;
    }

    ///////////////////getFunctions//////////////////////////

    public String getNameExhibition() {
        return nameExhibition;
    }
    public String getTicketCount() {
        return ticketCount;
    }

    ///////////////////setFunctions//////////////////////////

    public void setNameExhibition(String nameExhibition) {
        this.nameExhibition = nameExhibition;
    }

    public void setTicketCount(String ticketCount) {
        this.ticketCount = ticketCount;
    }

}
