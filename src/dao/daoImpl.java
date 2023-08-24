package dao;

import db.InMemoryDataBase;
import models.BookedSlot;
import models.Building;
import models.Floor;
import models.Room;
import utils.StringToDate;

import java.text.ParseException;
import java.util.*;

public class daoImpl implements Dao{

    public InMemoryDataBase db = InMemoryDataBase.getInstance();

    public void addBuilding(Building building){
        db.getDb().put(building.getBuildingNumber(), building);
    }

    @Override
    public void addFloor(int buildingName, Floor floor) {
        db.getDb().get(buildingName).buildingDetails.put(floor.getFloorNumber(), floor);
    }

    @Override
    public void addRoom(int buildingName, int floorNumber, Room room) {
        db.getDb().get(buildingName).buildingDetails.get(floorNumber).floorDetails.put(room.getRoomNumber(), room);
    }
    @Override
    public List<BookedSlot> getFreeSlotOfRoom(int buildingNumber, int floorNumber, BookedSlot bookedSlot) throws ParseException {

        List<BookedSlot> allbookedSlots = db.getDb().get(buildingNumber).buildingDetails.get(floorNumber).floorDetails.get(bookedSlot.getRoomNumber()).roomBookedSlotsList;
        //allbookedSlots.sort(null);
        List<BookedSlot> freeSlots = new ArrayList<>();

        Date startTime = new StringToDate().convertStringToDate("2022-01-01T00:00:00");
        Date endTime = new StringToDate().convertStringToDate("2022-01-01T23:59:59");

        for(BookedSlot slot: allbookedSlots){

            Date slotStartTime= slot.getStartTime();
            Date slotEndTime= slot.getEndTime();

            if(startTime.compareTo(slotStartTime) < 0){
                freeSlots.add(new BookedSlot(slot.getRoomNumber(), startTime, slotStartTime));
            }
            startTime = slotEndTime;
        }

        if(startTime.compareTo(endTime) < 0){
            freeSlots.add(new BookedSlot(bookedSlot.getRoomNumber(), startTime, endTime));
        }

        return freeSlots;
    }

    @Override
    public List<Integer> getAvailableRoomsInAFrame(Date startTime, Date endTime) {

        List<Integer> allFreeRooms = new ArrayList<>();
        Set<Integer> BuildingSet = db.getDb().keySet();

        for(Integer buildingNumber: BuildingSet){

            Set<Integer> floorSet = db.getDb().get(buildingNumber).buildingDetails.keySet();

            for(Integer floorNumber: floorSet) {

                Set<Integer> roomSet = db.getDb().get(buildingNumber).buildingDetails.get(floorNumber).floorDetails.keySet();

                for (Integer roomNumber : roomSet) {

                    if(isRoomFree(buildingNumber, floorNumber, roomNumber, startTime, endTime));
                        allFreeRooms.add(roomNumber);
                }
            }
        }
        return allFreeRooms;
    }

    @Override
    public void bookSlot(int buildingNumber, int floorNumber, BookedSlot SlotToBeBooked) {
;        db.getDb().get(buildingNumber).buildingDetails.get(floorNumber).floorDetails.get(SlotToBeBooked.getRoomNumber()).roomBookedSlotsList.add(SlotToBeBooked);
    }

    @Override
    public void cancelBooking(int buildingNumber, int floorNumber, BookedSlot SlotToBeCancelled) {
        List<BookedSlot> slots=db.getDb().get(buildingNumber).buildingDetails.get(floorNumber).floorDetails.get(SlotToBeCancelled.getRoomNumber()).roomBookedSlotsList;
        for(BookedSlot bookedSlots:slots){
            if(SlotToBeCancelled.getStartTime().compareTo(bookedSlots.startTime)==0 && SlotToBeCancelled.getEndTime().compareTo(bookedSlots.endTime)==0){
                slots.remove(SlotToBeCancelled);
            }
        }
    }

    @Override
    public List<BookedSlot> getAllBookedSlots() {
        List<BookedSlot> allBookedSlots = new ArrayList<>();
        Set<Integer> BuildingSet = db.getDb().keySet();

        for(Integer buildingNumber: BuildingSet){

            Set<Integer> floorSet = db.getDb().get(buildingNumber).buildingDetails.keySet();

            for(Integer floorNumber: floorSet) {


                Set<Integer> roomSet = db.getDb().get(buildingNumber).buildingDetails.get(floorNumber).floorDetails.keySet();

                for (Integer roomNumber : roomSet) {

                    List<BookedSlot> bookedSlot = db.getDb().get(buildingNumber).buildingDetails.get(floorNumber).floorDetails.get(roomNumber).roomBookedSlotsList;

                    allBookedSlots.addAll(bookedSlot);
                }
            }
        }
        return allBookedSlots;
    }

    @Override
    public Boolean doesBuildingExists(int buildingNumber) {
        return db.getDb().containsKey(buildingNumber);
    }

    @Override
    public Boolean doesFloorExists(int buildingNumber, int floorNumber) {
        if(doesBuildingExists(buildingNumber))
            return db.getDb().get(buildingNumber).buildingDetails.containsKey(floorNumber);
        return false;
    }

    @Override
    public Boolean doesRoomExists(int buildingNumber, int floorNumber, int roomNumber) {
        if(doesFloorExists(buildingNumber, floorNumber))
            return db.getDb().get(buildingNumber).buildingDetails.get(floorNumber).floorDetails.containsKey(roomNumber);
        return false;
    }

    @Override
    public Boolean isRoomFree(int buildingNumber, int floorNumber, int roomNumber, Date startTime, Date endTime) {
        List<BookedSlot> bookedSlots = db.getDb().get(buildingNumber).buildingDetails.get(floorNumber).floorDetails.get(roomNumber).roomBookedSlotsList;


            for (BookedSlot bookedSlot : bookedSlots) {

                int roomNum = bookedSlot.getRoomNumber();
                Date start = bookedSlot.getStartTime();
                Date end = bookedSlot.getEndTime();
                //TODO: correct condition
                if( (startTime.compareTo(start) <=0 && endTime.compareTo(start)>=0) || (startTime.compareTo(end) <=0 && endTime.compareTo(end)>=0)) {
                    return false;
                }
        }
        return true;
    }
}
