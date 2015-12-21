package io.teamkona.konatools.network.common;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;

/**
 * Created by gonzalomelov on 11/26/15.
 **/
public class LocalDateTimeAdapter implements JsonDeserializer<LocalDateTime> {

  @Override public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    // TODO Refactor Do not use string parse and unparse
    Instant instant = Instant.parse(json.getAsJsonPrimitive().getAsString());
    return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
  }
}
