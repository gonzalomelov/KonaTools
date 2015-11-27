package io.teamkona.konatools.network.common;

import android.text.TextUtils;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.net.URL;

/**
 * Created by gonzalomelov on 7/16/15.
 */
public class NullableURLAdapter extends TypeAdapter<URL> {
  @Override public void write(JsonWriter out, URL value) throws IOException {
    if (value == null) {
      out.nullValue();
    } else {
      String url = value.toString();
      out.value(url);
    }
  }

  @Override public URL read(JsonReader reader) throws IOException {
    if (reader.peek() == JsonToken.NULL) {
      reader.nextNull();
      return null;
    }

    String dateAsString = reader.nextString();
    if (TextUtils.isEmpty(dateAsString)) {
      return null;
    }

    return new URL(dateAsString);
  }
}
