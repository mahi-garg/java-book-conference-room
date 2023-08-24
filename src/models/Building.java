package models;


import java.util.HashMap;
import java.util.Map;

public class Building {
    public int buildingNumber;
    public Map< Integer, Floor> buildingDetails;

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public Map<Integer, Floor> getBuildingDetails() {
        return buildingDetails;
    }

    public void setBuildingDetails(Map<Integer, Floor> buildingDetails) {
        this.buildingDetails = buildingDetails;
    }

    public Building(int buildingNumber){
        this.buildingNumber = buildingNumber;
        this.buildingDetails = new HashMap<>();
    }

}
