package io.teamkona.konatools;

import android.app.Application;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.Pair;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;
import com.facebook.FacebookSdk;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.squareup.okhttp.Interceptor;
import io.fabric.sdk.android.Fabric;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.teamkona.konatools.events.MyEventBus;
import io.teamkona.konatools.gson.MyGson;
import io.teamkona.konatools.network.MyOkHttpClient;
import io.teamkona.konatools.network.RetrofitHelper;
import io.teamkona.konatools.session.SessionManager;
import io.teamkona.konatools.sharedpreferences.SharedPreferencesStore;
import java.lang.reflect.Type;
import java.util.List;
import timber.log.Timber;

/**
 * Created by gonzalomelov on 11/25/15.
 **/
public abstract class MyApplication extends Application implements SessionManager.SessionListener {

  private Tracker tracker;
  private RetrofitHelper apiRetrofitHelper;
  private MyEventBus eventBus;
  private MyGson myGson;
  private SharedPreferencesStore sharedPreferencesStore;
  private SessionManager sessionManager;

  @Override public void onCreate() {
    super.onCreate();
    setupAndroidThreeTen();
    setupFabric();
    setupTimber();
    setupFacebook();
    setupRealmConfiguration();
  }

  private void setupAndroidThreeTen() {
    AndroidThreeTen.init(this);
  }

  private void setupFabric() {
    CrashlyticsCore core = new CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build();
    Fabric.with(this, new Crashlytics.Builder().core(core).build());
  }

  protected abstract void setupTimber();

  private void setupFacebook() {
    FacebookSdk.sdkInitialize(getApplicationContext());
  }

  private void setupRealmConfiguration() {
    RealmConfiguration.Builder builder = new RealmConfiguration.Builder(this)
        .name(Realm.DEFAULT_REALM_NAME)
        .schemaVersion(getRealmVersion());
    builder = addRealmMigrations(builder);
    RealmConfiguration config = builder.build();
    Realm.setDefaultConfiguration(config);
  }

  protected abstract long getRealmVersion();

  protected abstract RealmConfiguration.Builder addRealmMigrations(RealmConfiguration.Builder builder);

  protected abstract String getApiHost();

  protected abstract List<Pair<Type, TypeAdapter>> getCustomTypeAdapters();

  protected abstract List<Pair<Type, JsonSerializer>> getCustomJsonSerializers();

  protected abstract List<Pair<Type, JsonDeserializer>> getCustomJsonDeserializers();

  protected abstract int getGlobalTrackerConfigRes();

  synchronized public Tracker getDefaultTracker() {
    if (tracker == null) {
      GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
      // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
      tracker = analytics.newTracker(getGlobalTrackerConfigRes());
    }
    return tracker;
  }

  public RetrofitHelper getApiRetrofitHelper() {
    if (apiRetrofitHelper == null) {
      MyOkHttpClient myOkHttpClient = new MyOkHttpClient();
      MyGson customMyGson = new MyGson(getCustomTypeAdapters(), getCustomJsonSerializers(), getCustomJsonDeserializers());
      apiRetrofitHelper = new RetrofitHelper(myOkHttpClient, customMyGson, getApiHost(), getAuthInterceptor());
    }
    return apiRetrofitHelper;
  }

  protected abstract Interceptor getAuthInterceptor();

  synchronized public MyEventBus getEventBus() {
    if (eventBus == null) {
      eventBus = new MyEventBus();
    }
    return eventBus;
  }

  synchronized public MyGson getMyGson() {
    if (myGson == null) {
      myGson = new MyGson();
    }
    return myGson;
  }

  synchronized public SharedPreferencesStore getSharedPreferencesStore() {
    if (sharedPreferencesStore == null) {
      sharedPreferencesStore = new SharedPreferencesStore(this, getMyGson());
    }
    return sharedPreferencesStore;
  }

  synchronized public SessionManager getSessionManager() {
    if (sessionManager == null) {
      sessionManager = new SessionManager(this, getSharedPreferencesStore());
    }
    return sessionManager;
  }

  public static class CrashlyticsTree extends Timber.Tree {
    private static final String CRASHLYTICS_KEY_PRIORITY = "priority";
    private static final String CRASHLYTICS_KEY_TAG = "tag";
    private static final String CRASHLYTICS_KEY_MESSAGE = "message";

    @Override protected void log(int priority, @Nullable String tag, @Nullable String message, @Nullable Throwable t) {
      if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) {
        return;
      }

      Crashlytics.setInt(CRASHLYTICS_KEY_PRIORITY, priority);
      Crashlytics.setString(CRASHLYTICS_KEY_TAG, tag);
      Crashlytics.setString(CRASHLYTICS_KEY_MESSAGE, message);

      if (t == null) {
        Crashlytics.logException(new Exception(message));
      } else {
        Crashlytics.logException(t);
      }
    }
  }
}
