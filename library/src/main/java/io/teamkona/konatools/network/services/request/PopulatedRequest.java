package io.teamkona.konatools.network.services.request;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gonzalomelov on 9/22/15.
 **/
public class PopulatedRequest {
  private List<String> populations;

  public PopulatedRequest() {
    this.populations = new ArrayList<>();
  }

  public PopulatedRequest(List<String> populations) {
    this.populations = populations;
  }

  public List<String> getPopulations() {
    return populations;
  }

  public void setPopulations(List<String> populations) {
    this.populations = populations;
  }
}
