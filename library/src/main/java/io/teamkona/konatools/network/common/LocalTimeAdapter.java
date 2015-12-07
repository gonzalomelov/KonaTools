package io.teamkona.konatools.network.common;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import org.threeten.bp.LocalTime;
import org.threeten.bp.format.DateTimeFormatter;

/**
 * Created by gonzalomelov on 12/7/15.
 **/
public class LocalTimeAdapter implements JsonDeserializer<LocalTime> {
  @Override public LocalTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    // TODO Refactor Do not use string parse and unparse
    return LocalTime.parse(json.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ISO_LOCAL_TIME);
  }
}
