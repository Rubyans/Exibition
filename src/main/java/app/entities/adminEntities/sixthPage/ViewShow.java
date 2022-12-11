package app.entities.adminEntities.sixthPage;

public class ViewShow { //the class is used to display the data associated with the table of genres in the database
    private String name;
    private int viewId;
    public ViewShow(int viewId, String name) { //the constructor receives data from the database genre table
        this.viewId = viewId;
        this.name = name;
    }

    ///////////////////getFunctions//////////////////////////

    public String getName() {
        return name;
    }
    public int getId() {
        return viewId;
    }

    ///////////////////setFunctions//////////////////////////

    public void setName(String name) { this.name = name; }
    public void setViewId() { this.viewId = viewId; }
}
