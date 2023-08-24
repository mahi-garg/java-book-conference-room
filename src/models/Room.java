package models;

import java.util.ArrayList;
import java.util.List;

public class Room {
    public int roomNumber;
    public List<BookedSlot> roomBookedSlotsList;

    public Room(int roomNumber){
        this.roomNumber = roomNumber;
        this.roomBookedSlotsList = new ArrayList<>();
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public List<BookedSlot> getRoomBookedSlots() {
        return roomBookedSlotsList;
    }

    public void setRoomBookedSlots(List<BookedSlot> roomBookedSlotsList) {
        this.roomBookedSlotsList = roomBookedSlotsList;
    }
}
