package io.teamkona.konatools.gson;

import android.util.Pair;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import io.realm.RealmObject;
import io.teamkona.konatools.network.common.LocalDateTimeAdapter;
import io.teamkona.konatools.network.common.LocalTimeAdapter;
import io.teamkona.konatools.network.common.NullableURLAdapter;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.LocalTime;

/**
 * Created by gonzalomelov on 11/25/15.
 **/
public class MyGson {

  public final static String DATE_TIME_WITH_SECONDS = "yyyy-MM-dd'T'HH:mm:ss";

  private List<Pair<Type, JsonDeserializer>> customTypeAdapters;

  public MyGson() {
    customTypeAdapters = new ArrayList<>();
  }

  public MyGson(List<Pair<Type, JsonDeserializer>> customTypeAdapters) {
    this.customTypeAdapters = customTypeAdapters;
  }

  public Gson getGson() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.setExclusionStrategies(new ExclusionStrategy() {
      @Override
      public boolean shouldSkipField(FieldAttributes f) {
        return f.getDeclaringClass().equals(RealmObject.class);
      }

      @Override
      public boolean shouldSkipClass(Class<?> clazz) {
        return false;
      }
    });
    gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY);
    gsonBuilder.setDateFormat(DATE_TIME_WITH_SECONDS);
    gsonBuilder.registerTypeAdapter(URL.class, new NullableURLAdapter());
    gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter());
    gsonBuilder.registerTypeAdapter(LocalTime.class, new LocalTimeAdapter());
    for (Pair<Type, JsonDeserializer> typeAdapterPair : customTypeAdapters) {
      gsonBuilder.registerTypeAdapter(typeAdapterPair.first, typeAdapterPair.second);
    }
    return gsonBuilder.create();
  }
}
