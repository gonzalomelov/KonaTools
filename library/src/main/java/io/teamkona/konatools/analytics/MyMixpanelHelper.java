package io.teamkona.konatools.analytics;

import android.content.Context;
import android.support.annotation.NonNull;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import io.teamkona.konatools.session.SessionManager;

/**
 * Created by gonzalomelov on 10/25/15.
 **/
public abstract class MyMixpanelHelper {

  protected Context context;

  public MyMixpanelHelper(Context context) {
    this.context = context;
  }

  public abstract MixpanelAPI setup(SessionManager sessionManager);

  protected abstract String getMixpanelToken();

  public void trackSignUpOrSignInFacebook(@NonNull MixpanelAPI mixpanel) {
    mixpanel.track("SignUp or SignIn - Facebook");
  }

  public void trackSignUpEmail(@NonNull MixpanelAPI mixpanel) {
    mixpanel.track("SignUp - Email");
  }

  public void trackSignInEmail(@NonNull MixpanelAPI mixpanel) {
    mixpanel.track("SignIn - Email");
  }
}
