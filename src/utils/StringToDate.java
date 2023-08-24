package utils;

import java.text.*;
import java.util.Date;

public class StringToDate {

    public Date convertStringToDate( String dateString) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD'T'HH:MM:SS");

        return dateFormat.parse(dateString);
    }
}
