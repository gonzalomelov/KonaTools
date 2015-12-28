package io.teamkona.konatools.db;

import android.content.Context;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by gonzalomelo on 12/27/15.
 */
public class RealmHelper {

  private static final long REALM_VERSION = 0;

  public static RealmConfiguration configuration(Context context) {
    return new RealmConfiguration.Builder(context).name(Realm.DEFAULT_REALM_NAME).schemaVersion(REALM_VERSION).build();
  }

  public static Realm get(Context context) {
    return Realm.getInstance(RealmHelper.configuration(context));
  }
}
