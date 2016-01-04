package io.teamkona.konatools.file;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;

/**
 * Created by gonzalomelo on 1/2/16.
 */
public class FileHelper {

  public static String getPath(Uri uri, Context context) {
    String[] data = { MediaStore.Images.Media.DATA };
    CursorLoader loader = new CursorLoader(context, uri, data, null, null, null);
    Cursor cursor = loader.loadInBackground();
    int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
    cursor.moveToFirst();
    return cursor.getString(columnIndex);
  }

  public static File createImageFile() {
    return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
        String.valueOf(Calendar.getInstance().getTimeInMillis()) + ".jpg");
  }
}
