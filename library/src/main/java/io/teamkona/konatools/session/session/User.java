package io.teamkona.konatools.session.session;

import android.support.annotation.StringDef;
import com.google.gson.annotations.SerializedName;
import io.teamkona.konatools.network.services.Constants;
import java.net.URL;
import org.threeten.bp.LocalDateTime;

/**
 * Created by gonzalomelov on 9/15/15.
 **/
public class User {

  public static final String MALE = "M";
  public static final String FEMALE = "F";

  @StringDef({ User.MALE, User.FEMALE }) public @interface Gender {
  }

  @SerializedName(Constants.ID) private String id;
  private String email;
  private String name;
  private String firstName;
  private String lastName;
  private String facebookId;
  private URL profilePicture;
  private @Gender String gender;
  private LocalDateTime birthdate;

  public User() {
  }

  public User(String id, String email, String name, String firstName, String lastName, String facebookId, URL profilePicture,
      @Gender String gender, LocalDateTime birthdate) {
    this.id = id;
    this.email = email;
    this.name = name;
    this.firstName = firstName;
    this.lastName = lastName;
    this.facebookId = facebookId;
    this.profilePicture = profilePicture;
    this.gender = gender;
    this.birthdate = birthdate;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFacebookId() {
    return facebookId;
  }

  public void setFacebookId(String facebookId) {
    this.facebookId = facebookId;
  }

  public URL getProfilePicture() {
    return profilePicture;
  }

  public void setProfilePicture(URL profilePicture) {
    this.profilePicture = profilePicture;
  }

  public @Gender String getGender() {
    return gender;
  }

  public void setGender(@Gender String gender) {
    this.gender = gender;
  }

  public LocalDateTime getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(LocalDateTime birthdate) {
    this.birthdate = birthdate;
  }
}
