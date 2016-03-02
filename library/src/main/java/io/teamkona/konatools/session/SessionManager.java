package io.teamkona.konatools.session;

import android.support.annotation.NonNull;
import io.teamkona.konatools.sharedpreferences.SharedPreferencesStore;

/**
 * Created by gonzalomelov on 8/19/15.
 **/
public class SessionManager {

  private final String SESSION_KEY = "session";

  private SharedPreferencesStore sharedPreferencesStore;

  private SessionListener sessionListener;

  public SessionManager(@NonNull SessionListener sessionListener, @NonNull SharedPreferencesStore sharedPreferencesStore) {
    this.sessionListener = sessionListener;
    this.sharedPreferencesStore = sharedPreferencesStore;
  }

  public Session createSession(@NonNull String authToken, @NonNull User user) {
    if (getSession() != null) throw new IllegalStateException("Session already exists");
    // Store session in shared_prefs
    Session session = new Session(authToken, user);
    setSession(session);
    // Notify the listener
    sessionListener.onSessionCreated(session);
    return session;
  }

  public void deleteSession() {
    // Remove session from shared_prefs
    sharedPreferencesStore.clearEntry(SESSION_KEY);
    // Notify the listener
    sessionListener.onSessionDeleted();
  }

  private Session getSession() {
    return sharedPreferencesStore.getObject(SESSION_KEY, Session.class);
  }

  private void setSession(Session session) {
    sharedPreferencesStore.putObject(SESSION_KEY, session);
  }

  public boolean isSessionAlive() {
    return getSession() != null;
  }

  public @NonNull String getAuthToken() {
    if (getSession() == null) throw new IllegalStateException("Session doesn't exists");
    return getSession().getAuthToken();
  }

  public @NonNull User getUser() {
    if (getSession() == null) throw new IllegalStateException("Session doesn't exists");
    return getSession().getUser();
  }

  public void setUserName(String userName) {
    Session session = getSession();
    User user = session.getUser();
    user.setName(userName);
    setSession(session);
  }

  public void setProfilePicture(String profilePicture) {
    Session session = getSession();
    User user = session.getUser();
    user.setProfilePicture(profilePicture);
    setSession(session);
  }

  public void setCurrentPosition(double latitude, double longitude) {
    Session session = getSession();
    User user = session.getUser();
    user.setCurrentPosition(new double[] { latitude, longitude });
    setSession(session);
  }

  public interface SessionListener {
    void onSessionCreated(Session session);

    void onSessionDeleted();
  }
}
