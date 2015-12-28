package io.teamkona.konatools;

import android.app.Application;
import android.content.Context;
import android.util.Pair;
import com.crashlytics.android.Crashlytics;
import com.facebook.FacebookSdk;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.google.gson.JsonDeserializer;
import com.google.gson.TypeAdapter;
import com.jakewharton.threetenabp.AndroidThreeTen;
import io.fabric.sdk.android.Fabric;
import io.teamkona.konatools.events.MyEventBus;
import io.teamkona.konatools.gson.MyGson;
import io.teamkona.konatools.network.MyOkHttpClient;
import io.teamkona.konatools.network.RetrofitHelper;
import io.teamkona.konatools.session.session.SessionManager;
import io.teamkona.konatools.sharedpreferences.SharedPreferencesStore;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by gonzalomelov on 11/25/15.
 **/
public abstract class MyApplication extends Application {

  private static Context appContext;

  private static MyEventBus bus = new MyEventBus();

  protected Tracker mTracker;

  protected RetrofitHelper apiRetrofitHelper;
  protected RetrofitHelper retrofitHelper;

  @Override public void onCreate() {
    super.onCreate();
    MyApplication.appContext = getApplicationContext();

    setupAndroidThreeTen();
    setupFabric();
    setupFacebook();
    setupRetrofit(getCustomTypeAdapters(), getCustomJsonDeserializers());
  }

  private void setupAndroidThreeTen() {
    AndroidThreeTen.init(this);
  }

  private void setupFabric() {
    if (!BuildConfig.DEBUG) {
      Fabric.with(this, new Crashlytics());
    }
  }

  private void setupFacebook() {
    FacebookSdk.sdkInitialize(getApplicationContext());
  }

  protected void setupRetrofit(List<Pair<Type, TypeAdapter>> customTypeAdapters, List<Pair<Type, JsonDeserializer>> customJsonDeserializers) {
    MyGson myGson = new MyGson(customTypeAdapters, customJsonDeserializers);
    SharedPreferencesStore sharedPreferencesStore = new SharedPreferencesStore(this, myGson);
    SessionManager sessionManager = new SessionManager(sharedPreferencesStore);
    MyOkHttpClient myOkHttpClient = new MyOkHttpClient(sessionManager);
    // FIXME Delete this
    retrofitHelper = new RetrofitHelper(myOkHttpClient, myGson, getHost());
    apiRetrofitHelper = new RetrofitHelper(myOkHttpClient, myGson, getApiHost());
  }

  protected abstract String getHost();

  protected abstract String getApiHost();

  protected abstract List<Pair<Type, TypeAdapter>> getCustomTypeAdapters();

  protected abstract List<Pair<Type, JsonDeserializer>> getCustomJsonDeserializers();

  public static Context getAppContext() {
    return appContext;
  }

  /**
   * Gets the default {@link Tracker} for this {@link Application}.
   *
   * @return tracker
   */
  synchronized public Tracker getDefaultTracker() {
    if (mTracker == null) {
      GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
      // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
      mTracker = analytics.newTracker(getGlobalTrackerConfigRes());
    }
    return mTracker;
  }

  protected abstract int getGlobalTrackerConfigRes();

  public static MyEventBus getEventBus() {
    return bus;
  }
}
