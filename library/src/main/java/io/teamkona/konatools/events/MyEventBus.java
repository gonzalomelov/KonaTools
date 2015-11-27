package io.teamkona.konatools.events;

import android.os.Handler;
import android.os.Looper;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by gonzalomelov on 8/19/15.
 **/
public class MyEventBus extends Bus {
  private final Handler handler = new Handler(Looper.getMainLooper());

  public MyEventBus() {
    super(ThreadEnforcer.ANY);
  }

  @Override public void post(final Object event) {
    if (Looper.myLooper() == Looper.getMainLooper()) {
      super.post(event);
    } else {
      handler.post(new Runnable() {
        @Override public void run() {
          MyEventBus.super.post(event);
        }
      });
    }
  }
}