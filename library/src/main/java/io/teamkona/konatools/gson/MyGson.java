package io.teamkona.konatools.gson;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.teamkona.konatools.network.common.LocalDateTimeAdapter;
import io.teamkona.konatools.network.common.NullableURLAdapter;
import java.net.URL;
import org.threeten.bp.LocalDateTime;

/**
 * Created by gonzalomelov on 11/25/15.
 **/
public class MyGson {

  public final static String DATE_TIME_WITH_SECONDS = "yyyy-MM-dd'T'HH:mm:ss";

  public Gson getGson() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY);
    gsonBuilder.setDateFormat(DATE_TIME_WITH_SECONDS);
    gsonBuilder.registerTypeAdapter(URL.class, new NullableURLAdapter());
    gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
    return gsonBuilder.create();
  }
}
