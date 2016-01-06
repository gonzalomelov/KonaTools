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

  public String getBirthplace() {
    return birthplace;
  }

  public void setBirthplace(String birthplace) {
    this.birthplace = birthplace;
  }

  public String getHowTravel() {
    return howTravel;
  }

  public void setHowTravel(String howTravel) {
    this.howTravel = howTravel;
  }

  public interface GenderOptions {
    String MALE = "M";
    String FEMALE = "F";
  }

  @Retention(RetentionPolicy.SOURCE) @StringDef({ GenderOptions.MALE, GenderOptions.FEMALE }) public @interface Gender {
  }

  @SerializedName(Constants.ID) private String id;
  private String email;
  private String name;
  private String facebookId;
  private String profilePicture;
  private String gender;
  private String birthdate;
  private String birthplace;
  private String howTravel;

  public User() {
  }

  public User(String id, String email, String name, String facebookId, String profilePicture, String gender, String birthdate,
      String birthplace, String howTravel) {
    this.id = id;
    this.email = email;
    this.name = name;
    this.facebookId = facebookId;
    this.profilePicture = profilePicture;
    this.gender = gender;
    this.birthdate = birthdate;
    this.birthplace = birthplace;
    this.howTravel = howTravel;
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

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(String birthdate) {
    this.birthdate = birthdate;
  }
}
