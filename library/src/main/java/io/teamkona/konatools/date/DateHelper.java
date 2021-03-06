package io.teamkona.konatools.date;

import android.text.TextUtils;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZoneOffset;
import org.threeten.bp.format.DateTimeFormatter;

/**
 * Created by gonzalomelo on 12/28/15.
 */
public class DateHelper {

  // TODO Review
  // http://stackoverflow.com/questions/19431234/converting-between-java-time-localdatetime-and-java-util-date

  public final static String DATE_TIME_WITH_SECONDS = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

  public static LocalDateTime date2LocalDateTime(Date value) {
    if (value == null) return null;
    Instant instant = Instant.ofEpochMilli(value.getTime());
    return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
  }

  public static LocalDate date2LocalDate(Date value) {
    if (value == null) return null;
    Instant instant = Instant.ofEpochMilli(value.getTime());
    return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
  }

  public static LocalTime date2LocalTime(Date value) {
    if (value == null) return null;
    Instant instant = Instant.ofEpochMilli(value.getTime());
    return LocalDateTime.ofInstant(instant, ZoneId.of("Z")).toLocalTime();
  }

  public static Instant date2Instant(Date value) {
    if (value == null) return null;
    return Instant.ofEpochMilli(value.getTime());
  }

  public static Date localDateTime2Date(LocalDateTime value) {
    if (value == null) return null;
    Instant instant = value.atZone(ZoneId.systemDefault()).toInstant();
    return new Date(instant.toEpochMilli());
  }

  public static String birthDateToStringFomattedUsingSlashesddMMYYYY(Date value) {
    if (value == null) return null;
    return birthDateToStringFomattedUsingPattern(value, "dd/MM/YYYY");
  }

  public static String birthDateToStringFomattedUsingDashesYYYYMMdd(Date value) {
    if (value == null) return null;
    return birthDateToStringFomattedUsingPattern(value, "YYYY-MM-dd");
  }

  public static String birthDateToStringFomattedUsingPattern(Date value, String pattern) {
    if (value == null) return null;
    Instant instant = Instant.ofEpochMilli(value.getTime());
    DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern(pattern).withLocale(Locale.getDefault()).withZone(ZoneId.of("UTC").normalized());
    return formatter.format(instant);
  }

  public static Date stringFomattedUsingSlashesMMddYYYYToBirthDate(String date) {
    if (TextUtils.isEmpty(date)) return null;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    LocalDate localDate = LocalDate.parse(date, formatter);
    Instant instant = localDate.atStartOfDay().toInstant(ZoneOffset.UTC);
    return new Date(instant.toEpochMilli());
  }

  public static Date stringFomattedUsingDashesYYYYMMddToBirthDate(String date) {
    if (TextUtils.isEmpty(date)) return null;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate localDate = LocalDate.parse(date, formatter);
    Instant instant = localDate.atStartOfDay().toInstant(ZoneOffset.UTC);
    return new Date(instant.toEpochMilli());
  }

  public static String stringTimeToStringDate(String time) {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
    LocalDateTime localDateTime =
        LocalDateTime.of(LocalDate.ofEpochDay(0), LocalTime.parse(time, dtf));
    Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
    return instant.toString();
  }

  public static Date convertFromDefaultToUTC(Date date) {
    return DateHelper.convertTimeZone(date, TimeZone.getDefault(), TimeZone.getTimeZone("UTC"));
  }

  public static Date convertFromUTCToDefault(Date date) {
    return DateHelper.convertTimeZone(date, TimeZone.getTimeZone("UTC"), TimeZone.getDefault());
  }

  /**
   * Converts the given <code>date</code> from the <code>fromTimeZone</code> to the
   * <code>toTimeZone</code>.  Since java.util.Date has does not really store time zome
   * information, this actually converts the date to the date that it would be in the
   * other time zone.
   * @param date
   * @param fromTimeZone
   * @param toTimeZone
   * @return
   */
  public static Date convertTimeZone(Date date, TimeZone fromTimeZone, TimeZone toTimeZone)
  {
    long fromTimeZoneOffset = getTimeZoneUTCAndDSTOffset(date, fromTimeZone);
    long toTimeZoneOffset = getTimeZoneUTCAndDSTOffset(date, toTimeZone);

    return new Date(date.getTime() + (toTimeZoneOffset - fromTimeZoneOffset));
  }

  /**
   * Calculates the offset of the <code>timeZone</code> from UTC, factoring in any
   * additional offset due to the time zone being in daylight savings time as of
   * the given <code>date</code>.
   * @param date
   * @param timeZone
   * @return
   */
  private static long getTimeZoneUTCAndDSTOffset(Date date, TimeZone timeZone)
  {
    long timeZoneDSTOffset = 0;
    if(timeZone.inDaylightTime(date))
    {
      timeZoneDSTOffset = timeZone.getDSTSavings();
    }

    return timeZone.getRawOffset() + timeZoneDSTOffset;
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
