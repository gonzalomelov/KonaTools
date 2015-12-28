package io.teamkona.konatools.date;

import java.util.Date;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;
import org.threeten.bp.ZoneId;

/**
 * Created by gonzalomelo on 12/28/15.
 */
public class DateHelper {

  public static LocalDateTime date2LocalDateTime(Date value) {
    Date ts = value;
    Instant instant = Instant.ofEpochMilli(ts.getTime());
    LocalDateTime res = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    return res;
  }

  public static LocalDate date2LocalDate(Date value) {
    Date date = value;
    Instant instant = Instant.ofEpochMilli(date.getTime());
    LocalDate res = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
    return res;
  }

  public static LocalTime date2LocalTime(Date value) {
    Date time = value;
    Instant instant = Instant.ofEpochMilli(time.getTime());
    LocalTime res = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalTime();
    return res;
  }

  //public static Date localDateTime2Date(LocalDateTime value) {
  //  LocalDateTime ldt = value;
  //  Instant instant = ldt.atZone(ZoneId.systemDefault()).toInstant();
  //  Date res = Date.from(instant);
  //  return res;
  //}
  //
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
