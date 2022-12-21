package loan.management.utils;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtils {

    public static LocalDateTime convertDateToLocalDateTime(Date date){
        if(Objects.isNull(date))
            return null;
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static LocalDate convertDateToLocalDate(Date date) {

        if(Objects.isNull(date))
            return null;
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static Date addDate(Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return c.getTime();
    }
}