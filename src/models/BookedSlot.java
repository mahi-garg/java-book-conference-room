package models;

import java.util.Date;

public class BookedSlot {
    public int roomNumber;
    public Date startTime;
    public Date endTime;

    public BookedSlot(int roomNumber, Date startTime, Date endTime){
        this.endTime = endTime;
        this.startTime = startTime;
        this.roomNumber = roomNumber;
    }
    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
