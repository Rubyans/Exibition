package app.entities.adminEntities.fifthPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ArtShow { //the class is used to get art data
    private String name;
    private Integer yearCreation;
    private Double price;
    private List<String> nameView;
    private List<String> fullName;

    public ArtShow(String name, Integer yearCreation, Double price, List<String> nameView, List<String> fullName) {
        //the constructor receives data from the database related to art
        this.name = name;
        this.yearCreation = yearCreation;
        this.price = price;
        this.nameView = new ArrayList<>(nameView);
        this.fullName = new ArrayList<>(fullName);
    }

    ///////////////////getFunctions//////////////////////////

    public String getName() {
        return name;
    }
    public int getCreation() {
        return yearCreation;
    }
    public Double getPrice() {
        return price;
    }
    public List<String> getNameView() { //the function removes the same data
        int tempCount = 0;
        String tempName = nameView.get(0);
        for (String name : nameView) {
            if (tempName.equals(name) == true) {
                tempName = name;
                tempCount++;
            }
        }
        if (tempCount == nameView.size()) {
            return new ArrayList<>(Collections.singleton(nameView.get(0)));
        } else {
            return nameView.stream().distinct().collect(Collectors.toList());
        }
    }
    public List<String> getFullName() { //the function removes the same data
        int tempCount = 0;
        String tempName = fullName.get(0);
        for (String name : fullName) {
            if (tempName.equals(name) == true) {
                tempName = name;
                tempCount++;
            }
        }
        if (tempCount == fullName.size()) {
            return new ArrayList<>(Collections.singleton(fullName.get(0)));
        } else {
            return fullName.stream().distinct().collect(Collectors.toList());
        }
    }

    ///////////////////setFunctions//////////////////////////

    public void setName(String name) { this.name = name; }
    public void setCreation(Integer yearCreation) { this.yearCreation = yearCreation; }
    public void setPrice(Double price) { this.price = price; }
    public void setNameView(List<String> nameView) { this.nameView = nameView; }
    public void setFullName(List<String> fullName) { this.fullName = fullName; }
}
