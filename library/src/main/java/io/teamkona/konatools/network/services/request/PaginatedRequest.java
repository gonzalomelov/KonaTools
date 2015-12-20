package io.teamkona.konatools.network.services.request;

import com.google.gson.annotations.SerializedName;
import io.teamkona.konatools.network.services.Constants;

/**
 * Created by gonzalomelov on 5/6/15.
 */
public class PaginatedRequest {
  @SerializedName(Constants.OFFSET) private Integer offset;
  @SerializedName(Constants.LIMIT) private Integer limit;

  public PaginatedRequest() {
  }

  public PaginatedRequest(Integer offset, Integer limit) {
    this.offset = offset;
    this.limit = limit;
  }

  public Integer getOffset() {
    return offset;
  }

  public Integer getLimit() {
    return limit;
  }
}
