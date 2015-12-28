package io.teamkona.konatools.network.services.request;

/**
 * Created by gonzalomelo on 12/23/15.
 */
public class SortedRequest {
  private int sort;

  public SortedRequest(int sort) {
    this.sort = sort;
  }

  public int getSort() {
    return sort;
  }

  public void setSort(int sort) {
    this.sort = sort;
  }
}
