package com.baddy.utils;



import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

    /*
    * java.util.Date
    * java.sql.Timestamp
    * java.time.LocalDate
    * java.time.LocalDateTime
    * java.time.OffsetDateTime
    * java.time.ZonedDateTime
    * java.time.LocalTime
    * java.time.Instant
     * */


    // _____________________________________________Conversioni______________________________

    /**
     * Converte un timestamp (millisecondi da Epoch) in {@link Date}.
     *
     * @param currentTime Millisecondi da Epoch.
     * @return Oggetto {@link Date}.
     */
    public static Date toDate(Long currentTime) {
        return Date.from(Instant.ofEpochMilli(currentTime));
    }

    /**
     * Converte un {@link LocalDate} in {@link Date}, usando un fuso orario specificato.
     *
     * @param localDate La data da convertire.
     * @param zoneId Il fuso orario da applicare.
     * @return Oggetto {@link Date}.
     */
    public static Date toDate(LocalDate localDate, ZoneId zoneId) {
        return Date.from(localDate.atStartOfDay(zoneId).toInstant());
    }

    /**
     * Converte un {@link LocalDateTime} in {@link Date}, usando un fuso orario specificato.
     *
     * @param localDateTime La data con orario da convertire.
     * @param zoneId Il fuso orario da applicare.
     * @return Oggetto {@link Date}.
     */
    public static Date toDate(LocalDateTime localDateTime, ZoneId zoneId) {
        return Date.from(localDateTime.atZone(zoneId).toInstant());
    }

    //trasforma in localdate

    public static LocalDate toLocalDate(String date, DateTimeFormatter formatter) {
        return LocalDate.parse(date, formatter);
    }

    public static LocalDate toLocalDate(Date date, ZoneId zoneId) {
        return date.toInstant().atZone(zoneId).toLocalDate();
    }

    public static LocalDate toLocalDate(LocalDateTime localDateTime) {
        return localDateTime.toLocalDate();
    }

    //trasforma in localdatetime

    public static LocalDateTime toLocalDateTime(String date, DateTimeFormatter formatter) {
        return LocalDateTime.parse(date, formatter);
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDateTime toLocalDateTime(LocalDate localDate, ZoneId zoneId) {
        return localDate.atStartOfDay().atZone(zoneId).toLocalDateTime();
    }

    public static LocalDateTime toLocalDateTime(ZonedDateTime zonedDateTime) {
        return zonedDateTime.toLocalDateTime();
    }

    public static LocalDateTime toLocalDateTime(OffsetDateTime offsetDateTime, ZoneId zoneId) {
        return offsetDateTime.atZoneSameInstant(zoneId).toLocalDateTime();
    }



    //trasforma in offsetDateTime

    public static OffsetDateTime toOffsetDateTime(String date, DateTimeFormatter formatter) {
        return OffsetDateTime.parse(date, formatter);
    }

    public static OffsetDateTime toOffsetDateTime(Date date, ZoneId zoneId) {
        return date.toInstant().atZone(zoneId).toOffsetDateTime();
    }

    public static OffsetDateTime toOffsetDateTime(LocalDate localDate, ZoneId zoneId) {
        return localDate.atStartOfDay().atZone(zoneId).toOffsetDateTime();
    }

    public static OffsetDateTime toOffsetDateTime(LocalDateTime localDateTime, ZoneId zoneId) {
        return localDateTime.atZone(zoneId).toOffsetDateTime();
    }

    public static OffsetDateTime toOffsetDateTime(ZonedDateTime zonedDateTime, ZoneId zoneId) {
        return zonedDateTime.toOffsetDateTime();
    }

    //trasforma in zoned date time

    public static ZonedDateTime toZonedDateTime(String date, DateTimeFormatter formatter) {
        return ZonedDateTime.parse(date, formatter);
    }

    public static ZonedDateTime toZonedDateTime(Date date, ZoneId zoneId) {
        return date.toInstant().atZone(zoneId);
    }

    public static ZonedDateTime toZonedDateTime(LocalDate date, ZoneId zoneId) {
        return date.atStartOfDay().atZone(zoneId);
    }

    public static ZonedDateTime toZonedDateTime(LocalDateTime localDateTime, ZoneId zoneId) {
        return localDateTime.atZone(zoneId);
    }

    public static ZonedDateTime toZonedDateTime(OffsetDateTime offsetDateTime, ZoneId zoneId) {
        return offsetDateTime.toInstant().atZone(zoneId);
    }

    //trasformazione in localTime
    public static LocalTime toLocalTime(String time, DateTimeFormatter formatter) {
        return LocalTime.parse(time, formatter);
    }

    public static LocalTime toLocalTime(Date date, ZoneId zoneId) {
        return date.toInstant().atZone(zoneId).toLocalTime();
    }

    public static LocalTime toLocalTime(LocalDateTime localDateTime, ZoneId zoneId) {
        return localDateTime.toLocalTime();
    }

    public static LocalTime toLocalTime(OffsetDateTime offsetDateTime, ZoneId zoneId) {
        return offsetDateTime.toLocalTime();
    }

    public static LocalTime toLocalTime(ZonedDateTime zonedDateTime, ZoneId zoneId) {
        return zonedDateTime.toInstant().atZone(zoneId).toLocalTime();
    }

    //trasformazione in istant

    public static Instant toInstant(String date) {
        return Instant.parse(date);
    }

    public static Instant toInstant(Date date) {
        return Instant.ofEpochMilli(date.getTime());
    }

    public static Instant toInstant(Timestamp timestamp) {
        return timestamp.toInstant();
    }

    public static Instant toInstant(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant();
    }

    public static Instant toInstant(LocalDateTime localDateTime, ZoneId zoneId) {
        return localDateTime.atZone(zoneId).toInstant();
    }

    public static Instant toInstant(OffsetDateTime offsetDateTime) {
        return offsetDateTime.toInstant();
    }

    public static Instant toInstant(ZonedDateTime zonedDateTime) {
        return zonedDateTime.toInstant();
    }

    // Formattazione

    // Formattazione con formato ISO di default
    public static String format(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    // Formattazione con ISO per ZonedDateTime
    public static String format(ZonedDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }

    // Formattazione per OffsetDateTime
    public static String format(OffsetDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    // Formattazione per LocalDate (solo data, senza orario)
    public static String format(LocalDate date) {
        return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }

    // Formattazione per LocalTime (solo orario)
    public static String format(LocalTime time) {
        return time.format(DateTimeFormatter.ISO_LOCAL_TIME);
    }

    public static String format(Instant instant) {
        return DateTimeFormatter.ISO_INSTANT.format(instant);
    }

    public static String format(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static String format(Timestamp timestamp, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(timestamp);
    }

    public static String format(LocalDate localDate, DateTimeFormatter formatter) {
        return localDate.format(formatter);
    }

    public static String format(LocalDateTime localDateTime, DateTimeFormatter formatter) {
        return localDateTime.format(formatter);
    }

    public static String format(OffsetDateTime offsetDateTime, DateTimeFormatter formatter) {
        return offsetDateTime.format(formatter);
    }

    public static String format(ZonedDateTime zonedDateTime, DateTimeFormatter formatter) {
        return zonedDateTime.format(formatter);
    }

    public static String format(LocalTime localTime, DateTimeFormatter formatter) {
        return localTime.format(formatter);
    }

    public static String format(Instant instant, DateTimeFormatter formatter) {
        return formatter.format(instant);
    }
}
