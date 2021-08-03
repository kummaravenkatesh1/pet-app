package com.practice;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

public class DateUtils {

    public static Long convertToEpochSecond(Date date) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC"));
        return dateTime.toEpochSecond(ZoneOffset.UTC);
    }
}
