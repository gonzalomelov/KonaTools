package io.teamkona.konatools.wrappers;

import android.databinding.BaseObservable;

/**
 * Created by guille on 19/11/15.
 */
// FIXME Remove. Use ObservableBoolean
public class BindableBoolean extends BaseObservable {
  public static final boolean DEFAULT = false;

  private Boolean value;

  public Boolean get() {
    return value != null ? value : DEFAULT;
  }

  public void set(Boolean value) {
    if (this.value != value) {
      this.value = value;
      notifyChange();
    }
  }
}
