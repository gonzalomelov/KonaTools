package io.teamkona.konatools.network.services.request;

import com.google.gson.annotations.SerializedName;
import io.teamkona.konatools.network.services.Constants;

/**
 * Created by gonzalomelov on 5/6/15.
 */
public class PaginatedRequest {
  @SerializedName(Constants.OFFSET) private int offset;
  @SerializedName(Constants.LIMIT) private int limit;

  public PaginatedRequest(int offset, int limit) {
    this.offset = offset;
    this.limit = limit;
  }

  public int getOffset() {
    return offset;
  }

  public int getLimit() {
    return limit;
  }
}
