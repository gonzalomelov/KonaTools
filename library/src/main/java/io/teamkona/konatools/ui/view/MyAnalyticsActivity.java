package io.teamkona.konatools.ui.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import io.teamkona.konatools.MyApplication;
import io.teamkona.konatools.analytics.MyMixpanelHelper;
import io.teamkona.konatools.gson.MyGson;
import io.teamkona.konatools.session.SessionManager;
import io.teamkona.konatools.sharedpreferences.SharedPreferencesStore;

/**
 * Created by gonzalomelov on 11/26/15.
 **/
public abstract class MyAnalyticsActivity extends AppCompatActivity {

  private Tracker mTracker;
  protected MyMixpanelHelper myMixpanelHelper;

  private String runtimeClassName;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    runtimeClassName = getClass().getSimpleName();
    setupGoogleAnalytics();
    setupMixpanel();
  }

  @Override protected void onResume() {
    super.onResume();
    mTracker.setScreenName("Screen~" + runtimeClassName);
    mTracker.send(new HitBuilders.ScreenViewBuilder().build());
  }

  private void setupGoogleAnalytics() {
    MyApplication application = (MyApplication) getApplication();
    mTracker = application.getDefaultTracker();
  }

  private void setupMixpanel() {
    SessionManager sessionManager = ((MyApplication)getApplication()).getSessionManager();
    myMixpanelHelper = getMixpanelHelper(this, sessionManager);
    myMixpanelHelper.setup();
  }

  protected abstract MyMixpanelHelper getMixpanelHelper(Context context, SessionManager sessionManager);

  public SessionManager getSessionManager() {
    return ((MyApplication)getApplication()).getSessionManager();
  }
}
