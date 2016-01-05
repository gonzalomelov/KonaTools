package io.teamkona.konatools.session;

import android.support.annotation.StringDef;
import com.google.gson.annotations.SerializedName;
import io.teamkona.konatools.network.services.Constants;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by gonzalomelov on 9/15/15.
 **/
public class User {

  public interface GenderOptions {
    String MALE = "M";
    String FEMALE = "F";
  }

  @Retention(RetentionPolicy.SOURCE) @StringDef({ GenderOptions.MALE, GenderOptions.FEMALE }) public @interface Gender {
  }

  @SerializedName(Constants.ID) private String id;
  private String email;
  private String name;
  private String firstName;
  private String lastName;
  private String facebookId;
  private String profilePicture;
  private @Gender String gender;
  private String birthdate;

  public User() {
  }

  public User(String id, String email, String name, String firstName, String lastName, String facebookId, String profilePicture,
      @Gender String gender, String birthdate) {
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

  public String getProfilePicture() {
    return profilePicture;
  }

  public void setProfilePicture(String profilePicture) {
    this.profilePicture = profilePicture;
  }

  public @Gender String getGender() {
    return gender;
  }

  public void setGender(@Gender String gender) {
    this.gender = gender;
  }

  public String getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(String birthdate) {
    this.birthdate = birthdate;
  }
}