package io.teamkona.konatools.file;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * Created by gonzalomelo on 1/2/16.
 */
public class UriHelper {

  private static UriHelper instance = new UriHelper();

  public static UriHelper get() {
    return instance;
  }

  public String getPathOfExternalImageToUpload(Context context, Uri uri) {
    String res = null;
    String[] proj = {MediaStore.Images.Media.DATA};
    Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);
    if (cursor == null) {
      return uri.getPath();
    }
    if (cursor.moveToFirst()) {
      int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
      res = cursor.getString(column_index);
    }
    cursor.close();
    return res;
  }

  public String getMimeType(Context context, Uri uri) {
    String res = context.getContentResolver().getType(uri);
    if (res == null) {
      res = "image/jpeg";
    }
    return res;
  }
}

