package io.teamkona.konatools.date;

import java.util.Date;
import java.util.Locale;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.format.DateTimeFormatter;

/**
 * Created by gonzalomelo on 12/28/15.
 */
public class DateHelper {

  // TODO Review
  // http://stackoverflow.com/questions/19431234/converting-between-java-time-localdatetime-and-java-util-date

  public final static String DATE_TIME_WITH_SECONDS = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

  public static LocalDateTime date2LocalDateTime(Date value) {
    Instant instant = Instant.ofEpochMilli(value.getTime());
    return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
  }

  public static LocalDate date2LocalDate(Date value) {
    Instant instant = Instant.ofEpochMilli(value.getTime());
    return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
  }

  public static LocalTime date2LocalTime(Date value) {
    Instant instant = Instant.ofEpochMilli(value.getTime());
    return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalTime();
  }

  public static Instant date2Instant(Date value) {
    return Instant.ofEpochMilli(value.getTime());
  }

  public static Date localDateTime2Date(LocalDateTime value) {
    Instant instant = value.atZone(ZoneId.systemDefault()).toInstant();
    return new Date(instant.toEpochMilli());
  }

  public static String birthDateToStringFomattedUsingSlashesddMMYYYY(Date date) {
    return birthDateToStringFomattedUsingPattern(date, "dd/MM/YYYY");
  }

  public static String birthDateToStringFomattedUsingDashesYYYYMMdd(Date date) {
    return birthDateToStringFomattedUsingPattern(date, "YYYY-MM-dd");
  }

  public static String birthDateToStringFomattedUsingPattern(Date date, String pattern) {
    Instant instant = Instant.ofEpochMilli(date.getTime());
    DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern(pattern).withLocale(Locale.getDefault()).withZone(ZoneId.of("UTC").normalized());
    return formatter.format(instant);
  }

  //public static Date localDate2Date(LocalDate value) {
  //  LocalDate ld = value;
  //  Instant instant = ld.atStartOvaluey().atZone(ZoneId.systemDefault()).toInstant();
  //  Date res = Date.from(instant);
  //  return res;
  //}
  //
  //public static Date localTime2Date(LocalTime value) {
  //  LocalTime lt = value;
  //  Instant instant = lt.atDate(LocalDate.of(A_YEAR, A_MONTH, A_DAY)).atZone(ZoneId.systemDefault()).toInstant();
  //  Date res = Date.from(instant);
  //  return res;
  //}

}