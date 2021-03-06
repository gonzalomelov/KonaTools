package io.teamkona.konatools.ui.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import io.teamkona.konatools.MyApplication;

/**
 * Created by gonzalomelov on 11/26/15.
 **/
public abstract class MyAnalyticsActivity extends AppCompatActivity {

  private Tracker mTracker;

  private String runtimeClassName;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    runtimeClassName = getClass().getSimpleName();
    setupGoogleAnalytics();
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
}
