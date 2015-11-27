package io.teamkona.konatools.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.google.gson.reflect.TypeToken;
import io.teamkona.konatools.gson.MyGson;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gonzalomelov on 11/25/15.
 **/
public class SharedPreferencesStore {

  private Context context;
  private MyGson myGson;

  public SharedPreferencesStore(Context context, MyGson myGson) {
    this.context = context;
    this.myGson = myGson;
  }

  public void clearEntry(String key) {
    SharedPreferences.Editor prefsEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
    prefsEditor.remove(key);
    prefsEditor.apply();
  }

  public void putObject(String key, Object object) {
    SharedPreferences.Editor prefsEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
    String safeZone = myGson.getGson().toJson(object);
    prefsEditor.putString(key, safeZone);
    prefsEditor.apply();
  }

  public <T> T getObject(String key, Type typeOfT) {
    SharedPreferences prefsEditor = PreferenceManager.getDefaultSharedPreferences(context);
    String object = prefsEditor.getString(key, "");
    if (object.equals("")) {
      return null;
    } else {
      return myGson.getGson().fromJson(object, typeOfT);
    }
  }

  public <T> void putObjectIntoArrayList(String key, T t) {
    Type listType = new TypeToken<ArrayList<Class>>() {
    }.getType();

    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    String listString = prefs.getString(key, "");

    List<T> list = myGson.getGson().fromJson(listString, listType);
    if (list == null) {
      list = new ArrayList<>();
    }
    list.add(t);

    listString = myGson.getGson().toJson(list, listType);

    SharedPreferences.Editor prefsEditor = PreferenceManager.getDefaultSharedPreferences(context).edit();
    prefsEditor.putString(key, listString);

    prefsEditor.apply();
  }

  public <T> ArrayList<T> getArrayList(String key) {
    Type listType = new TypeToken<ArrayList<T>>() {
    }.getType();

    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
    String listString = prefs.getString(key, "");

    ArrayList<T> list = myGson.getGson().fromJson(listString, listType);
    return list != null ? list : new ArrayList<T>();
  }
}
