package io.teamkona.konatools.wrappers;

/**
 * Created by guille on 18/11/15.
 */
public class SelectableItemWrapper {
  Object item;
  BindableBoolean selected;

  public SelectableItemWrapper(Object item, BindableBoolean selected) {
    this.item = item;
    this.selected = selected;
  }

  public Object getItem() {
    return item;
  }

  public void setItem(Object item) {
    this.item = item;
  }

  public BindableBoolean getSelected() {
    return selected;
  }

  public void setSelected(Boolean selected) {
    this.selected.set(selected);
  }
}
