package any.mind.pointsystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateHelper {

    public static final String DATE_ISO_INSTANT_UTC = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    public static LocalDateTime toLocalDate(String date, String pattern) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(pattern));
    }

    public static String toFormattedDate(LocalDateTime date, String pattern){
        return DateTimeFormatter.ofPattern(pattern).format(date);
    }
}
