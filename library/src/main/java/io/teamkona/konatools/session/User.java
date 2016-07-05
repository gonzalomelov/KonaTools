package io.teamkona.konatools.session;

import android.support.annotation.StringDef;
import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import io.teamkona.konatools.network.services.Constants;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by gonzalomelov on 9/15/15.
 **/
public class User {

  @SerializedName(Constants.ID) private String id;
  private String email;
  private String name;
  private String facebookId;
  private String profilePicture;
  private String gender;
  private String birthdate;
  private String birthplace;
  private String howTravel;
  private double[] currentPosition;
  private String currentHostel;
  private String placeId;

  public User() {
  }

  public User(String id, String email, String name, String facebookId, String profilePicture, String gender, String birthdate,
      String birthplace, String howTravel, double[] currentPosition, String currentHostel, String placeId) {
    this.id = id;
    this.email = email;
    this.name = name;
    this.facebookId = facebookId;
    this.profilePicture = profilePicture;
    this.gender = gender;
    this.birthdate = birthdate;
    this.birthplace = birthplace;
    this.howTravel = howTravel;
    this.currentPosition = currentPosition;
    this.currentHostel = currentHostel;
    this.placeId = placeId;
  }

  // custom methods

  public String getCurrentPositionAsCommaSeparatedString() {
    if (currentPosition == null || currentPosition.length == 0) return null;
    return TextUtils.join(",", new String[] { String.valueOf(currentPosition[1]), String.valueOf(currentPosition[0]) });
  }

  // getters and setters

  public String getBirthplace() {
    return birthplace;
  }

  public String getHowTravel() {
    return howTravel;
  }

  public String getId() {
    return id;
  }

  public String getEmail() {
    return email;
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

  public String getProfilePicture() {
    return profilePicture;
  }

  public void setProfilePicture(String profilePicture) {
    this.profilePicture = profilePicture;
  }

  public String getGender() {
    return gender;
  }

  public String getBirthdate() {
    return birthdate;
  }

  public double[] getCurrentPosition() {
    return currentPosition;
  }

  public void setCurrentPosition(double[] currentPosition) {
    this.currentPosition = currentPosition;
  }

  public String getCurrentHostel() {
    return currentHostel;
  }

  public void setCurrentHostel(String currentHostel) {
    this.currentHostel = currentHostel;
  }

  public String getPlaceId() {
    return placeId;
  }

  public void setPlaceId(String placeId) {
    this.placeId = placeId;
  }

  public interface GenderOptions {
    String MALE = "M";
    String FEMALE = "F";
  }

  @Retention(RetentionPolicy.SOURCE) @StringDef({ GenderOptions.MALE, GenderOptions.FEMALE }) public @interface Gender {
  }
}
