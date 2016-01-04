package io.teamkona.konatools.session;

/**
 * Created by gonzalomelov on 8/19/15.
 **/
public class Session {
  private String authToken;
  private User user;

  public Session(String authToken, User user) {
    this.authToken = authToken;
    this.user = user;
  }

  public String getAuthToken() {
    return authToken;
  }

  public void setAuthToken(String authToken) {
    this.authToken = authToken;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
