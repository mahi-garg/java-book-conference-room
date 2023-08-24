package service;

import dao.Dao;
import dao.daoImpl;
import models.BookedSlot;
import models.Building;
import models.Floor;
import models.Room;
import utils.StringToDate;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class BookingService {

    Dao dao;

    public BookingService(){
        dao = new daoImpl();
    }

    public void addBuilding(int buildingNumber){

        //if building already present-> no need to add
        //TODO: print a message
        if(dao.doesBuildingExists(buildingNumber)){
            System.out.println("Building " + buildingNumber + " already exist");
            return;
        }
        Building building = new Building(buildingNumber);
        dao.addBuilding(building);
        System.out.println("Building " + buildingNumber + " added");
    }

    public void addFloor(int buildingNumber, int floorNumber){

        addBuilding(buildingNumber);

        if(!dao.doesFloorExists(buildingNumber, floorNumber)){

           Floor floor = new Floor(floorNumber);
            dao.addFloor(buildingNumber, floor);
            System.out.println("floor " + floorNumber +" added");
        }
        else {
            System.out.println("floor " + floorNumber +" already exist");
        }
    }

    public void addRoom(int buildingNumber, int floorNumber, int roomNumber){

        addFloor(buildingNumber, floorNumber);
        if(!dao.doesRoomExists(buildingNumber, floorNumber, roomNumber)){

            Room room = new Room(roomNumber);
            dao.addRoom(buildingNumber, floorNumber, room);
            System.out.println("room " + roomNumber +" added");
        }else{
            System.out.println("room "+ roomNumber + " already exist");
        }
        return;
    }

    public void bookSlot(int buildingNumber, int floorNumber, int roomNumber, String startTime, String endTime ) throws ParseException {

        StringToDate stringToDate = new StringToDate();
        Date start = stringToDate.convertStringToDate(startTime);
        Date end =  stringToDate.convertStringToDate(endTime);

        //TODO: check case
        if( start.getTime()- end.getTime() >= 12*60*60*1000){
            System.out.println("slot can not be booked for more than 12 hours");
            return;
        }

        if(!dao.doesRoomExists(buildingNumber, floorNumber, roomNumber)){
            System.out.println("room does not exist on the building Floor");
            return;
        }
        BookedSlot bookedSlot = new BookedSlot(roomNumber, start, end);
        Boolean res = dao.isRoomFree(buildingNumber, floorNumber, roomNumber, start, end);
        if(res) {
            //TODO: store bookingId


            dao.bookSlot(buildingNumber, floorNumber, bookedSlot);
            System.out.println("slot booked");
        }
        else {
            System.out.println("room with " + roomNumber + " is occupied. Plesae Book any slot from :");
            List<BookedSlot> freeSlots = dao.getFreeSlotOfRoom(buildingNumber, floorNumber, bookedSlot);

            for(BookedSlot slot: freeSlots){
                System.out.println( "startTime " + slot.getStartTime() + " endTime " + slot.getEndTime());
            }
        }
    }

    public void cancelBooking(int buildingNumber, int floorNumber, int roomNumber, String startTime, String endTime ) throws ParseException {

        StringToDate stringToDate = new StringToDate();
        Date start = stringToDate.convertStringToDate(startTime);
        Date end =  stringToDate.convertStringToDate(startTime);
        BookedSlot cancelSlot = new BookedSlot(roomNumber, start, end);
        dao.cancelBooking(buildingNumber, floorNumber, cancelSlot);
        System.out.println("booking cancelled");
    }

    public void getAllBookedSlots(){

        List<BookedSlot> allBookedSlots = dao.getAllBookedSlots();

        for(BookedSlot bookedSlot: allBookedSlots){

            int roomNumber = bookedSlot.getRoomNumber();
            Date start = bookedSlot.getStartTime();
            Date end  = bookedSlot.getEndTime();

            System.out.println( roomNumber + " "+ start + " "+ end );
        }
    }

    public void getAllAvailableRooms(String startTime, String endTime) throws ParseException {

        Date start = new StringToDate().convertStringToDate(startTime);
        Date end =  new StringToDate().convertStringToDate(startTime);

        List<Integer> availableRooms = dao.getAvailableRoomsInAFrame(start, end);

        for(Integer roomNumber: availableRooms){
            System.out.println(roomNumber);
        }
    }
}
