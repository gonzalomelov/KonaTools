package io.teamkona.konatools.analytics;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import io.teamkona.konatools.session.SessionManager;
import io.teamkona.konatools.session.User;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by gonzalomelov on 10/25/15.
 **/
public abstract class MyMixpanelHelper {

  private final String TAG = MyMixpanelHelper.class.getSimpleName();

  private Context context;
  private SessionManager sessionManager;

  public MyMixpanelHelper(Context context, SessionManager sessionManager) {
    this.context = context;
    this.sessionManager = sessionManager;
  }

  public MixpanelAPI setup() {
    String projectToken = getMixpanelToken();
    MixpanelAPI mixpanel = MixpanelAPI.getInstance(context, projectToken);
    if (sessionManager.isSessionAlive()) {
      User user = sessionManager.getSession().getUser();
      mixpanel.identify(user.getEmail());
      mixpanel.getPeople().identify(user.getEmail());
      mixpanel.getPeople().set("$email", user.getEmail());
      mixpanel.getPeople().set("name", user.getName());
    }
    return mixpanel;
  }

  protected abstract String getMixpanelToken();

  public void trackSignUpFacebook(@NonNull MixpanelAPI mixpanel, @NonNull User user) {
    try {
      JSONObject props = new JSONObject();
      props.put("Email", user.getEmail());
      props.put("Name", user.getName());
      props.put("FacebookId", user.getFacebookId());
      props.put("ProfilePicture", user.getProfilePicture());
      props.put("Gender", user.getGender());
      props.put("Birthdate", user.getBirthdate());
      mixpanel.track("SignUp or SignIn - Facebook", props);
    } catch (JSONException e) {
      Log.e(TAG, "Unable to add properties to JSONObject", e);
    }
  }

  public void trackSignUpEmail(@NonNull MixpanelAPI mixpanel, @NonNull User user) {
    try {
      JSONObject props = new JSONObject();
      props.put("Email", user.getEmail());
      props.put("Name", user.getName());
      mixpanel.track("SignUp - Email", props);
    } catch (JSONException e) {
      Log.e(TAG, "Unable to add properties to JSONObject", e);
    }
  }

  public void trackSignInEmail(@NonNull MixpanelAPI mixpanel, @NonNull User user) {
    try {
      JSONObject props = new JSONObject();
      props.put("Email", user.getEmail());
      mixpanel.track("SignUp - Email", props);
    } catch (JSONException e) {
      Log.e(TAG, "Unable to add properties to JSONObject", e);
    }
  }
}
