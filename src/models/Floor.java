package models;

import java.util.HashMap;
import java.util.Map;

public class Floor {

    public int floorNumber;

    public Map< Integer, Room> floorDetails;

    public Floor(int floorNumber){
        this.floorNumber = floorNumber;
        this.floorDetails = new HashMap<>();
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public Map<Integer, Room> getFloorDetails() {
        return floorDetails;
    }

    public void setFloorDetails(Map<Integer, Room> floorDetails) {
        this.floorDetails = floorDetails;
    }
}
