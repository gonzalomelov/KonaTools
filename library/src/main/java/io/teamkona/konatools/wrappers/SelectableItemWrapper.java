package io.teamkona.konatools.wrappers;

import android.databinding.BaseObservable;

/**
 * Created by guille on 18/11/15.
 */
public class SelectableItemWrapper extends BaseObservable {
  Object item;
  Boolean selected;

  public SelectableItemWrapper(Object item, Boolean selected) {
    this.item = item;
    this.selected = selected;
  }

  public Object getItem() {
    return item;
  }

  public void setItem(Object item) {
    this.item = item;
  }

  public Boolean isSelected() {
    return selected;
  }

  public void setSelected(Boolean selected) {
    this.selected = selected;
    notifyChange();
  }
}
