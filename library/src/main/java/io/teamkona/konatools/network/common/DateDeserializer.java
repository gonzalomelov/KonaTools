package io.teamkona.konatools.network.common;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import io.teamkona.konatools.date.DateHelper;
import io.teamkona.konatools.date.Time24HoursValidator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by gonzalomelo on 12/30/15.
 */
public class DateDeserializer implements JsonDeserializer<Date> {

  private static final String TAG = DateDeserializer.class.getSimpleName();

  @Override public Date deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {

    if (json == null) return null;

    Time24HoursValidator timeValidator = new Time24HoursValidator();
    SimpleDateFormat sdf;
    TimeZone tz = TimeZone.getTimeZone("UTC");

    if (timeValidator.validate(json.getAsString())) {
      sdf = new SimpleDateFormat("HH:mm", Locale.US);
    } else {
      sdf = new SimpleDateFormat(DateHelper.DATE_TIME_WITH_SECONDS, Locale.US);
    }

    sdf.setTimeZone(tz);

    Date date;
    try {
      date = sdf.parse(json.getAsString());
    } catch (ParseException e) {
      date = null;
    }

    return date;
  }
}