package io.teamkona.konatools.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by gonzalomelo on 1/23/16.
 */
public class Utils {
  static public class Operations {

    /**
     * Checks to see if the device is online before carrying out any operations.
     */
    public static boolean isOnline(Context context) {
      ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
      NetworkInfo netInfo = cm.getActiveNetworkInfo();
      if (netInfo != null && netInfo.isConnectedOrConnecting()) {
        return true;
      }
      return false;
    }
  }
}
