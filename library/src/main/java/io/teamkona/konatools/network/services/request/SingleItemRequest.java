package io.teamkona.konatools.network.services.request;

import com.google.gson.annotations.SerializedName;
import io.teamkona.konatools.network.services.Constants;

/**
 * Created by gonzalomelov on 9/3/15.
 **/
public class SingleItemRequest {
  @SerializedName(Constants.ID) private String id;

  public SingleItemRequest(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
}
