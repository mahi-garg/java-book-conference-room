import service.BookingService;

import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws ParseException {
        System.out.println("Hello world!");
        BookingService bookingService = new BookingService();
        System.out.println("1------------------------------------------------------");
        bookingService.addRoom(3,1, 1);
        System.out.println("1------------------------------------------------------");
        bookingService.addRoom(3,1, 2);
        System.out.println("2------------------------------------------------------");
        bookingService.addRoom(3,1, 2);
        System.out.println("------------------------------------------------------");
        //"YYYY-MM-DDTHH:MM:SS"
        bookingService.bookSlot(3, 1, 2, "2022-01-01T12:10:10", "2022-01-01T12:50:10");
        System.out.println("------------------------------------------------------");
        bookingService.bookSlot(3, 1, 2, "2022-01-01T12:10:10", "2022-01-01T12:50:10");
        System.out.println("------------------------------------------------------");
        bookingService.getAllBookedSlots();
        System.out.println("------------------------------------------------------");
        bookingService.bookSlot(3, 1, 2, "2022-01-01T13:05:10", "2022-01-01T14:50:10");
        System.out.println("------------------------------------------------------");
        bookingService.getAllBookedSlots();
        System.out.println("------------------------------------------------------");
        bookingService.cancelBooking(3, 1, 2, "2022-01-01T12:10:10", "2022-01-01T12:50:10");
        System.out.println("------------------------------------------------------");
        bookingService.getAllBookedSlots();
        System.out.println("------------------------------------------------------");
        bookingService.bookSlot(3, 1, 2, "2022-01-01T12:10:10", "2022-01-01T12:50:10");
        System.out.println("------------------------------------------------------");
        bookingService.getAllBookedSlots();
        System.out.println("------------------------------------------------------");
        bookingService.getAllAvailableRooms("2022-01-01T12:50:10", "2022-01-01T15:00:00");
    }
}