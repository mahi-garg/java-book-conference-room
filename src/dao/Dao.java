package dao;

import models.BookedSlot;
import models.Building;
import models.Floor;
import models.Room;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface Dao {

    public void addBuilding(Building building);

    public void addFloor(int buildingName, Floor floorNumber);
    public void addRoom(int buildingName, int floorNumber, Room room );

    //TODO: more functionality can be added
   public List<BookedSlot> getFreeSlotOfRoom(int buildingNumber, int floorNumber, BookedSlot bookedSlot) throws ParseException;
    public List<Integer> getAvailableRoomsInAFrame(Date startTime, Date endTime);

   public void bookSlot(int buildingNumber, int floorNumber, BookedSlot bookedSlot);

   public void cancelBooking( int buildingNumber, int floorNumber, BookedSlot bookedSlot);
    public List<BookedSlot> getAllBookedSlots();
    public Boolean doesBuildingExists(int buildingNumber);
    public Boolean doesFloorExists(int buildingNumber, int floorNumber);
    public Boolean doesRoomExists(int buildingNumber, int floorNumber, int roomNumber);
   public Boolean isRoomFree(int buildingNumber, int floorNumber, int roomNumber, Date startTime, Date endTime);
}
