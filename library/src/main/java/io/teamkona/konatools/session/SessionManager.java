package io.teamkona.konatools.session;

import android.support.annotation.NonNull;
import io.teamkona.konatools.sharedpreferences.SharedPreferencesStore;

/**
 * Created by gonzalomelov on 8/19/15.
 **/
public class SessionManager {

  private final String SESSION_KEY = "session";

  private SharedPreferencesStore sharedPreferencesStore;

  public SessionManager(@NonNull SharedPreferencesStore sharedPreferencesStore) {
    this.sharedPreferencesStore = sharedPreferencesStore;
  }

  public void setSession(@NonNull Session session) {
    sharedPreferencesStore.putObject(SESSION_KEY, session);
  }

  public Session getSession() {
    return sharedPreferencesStore.getObject(SESSION_KEY, Session.class);
  }

  public void deleteSession() {
    sharedPreferencesStore.clearEntry(SESSION_KEY);
  }

  public boolean isSessionAlive() {
    return getSession() != null;
  }
}
