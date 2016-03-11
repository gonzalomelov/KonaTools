package io.teamkona.konatools.file;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by gonzalomelo on 1/2/16.
 */
public class ImageHelper {

  public static Bitmap getImageByUri(Activity activity, Uri imageUri) {
    if (imageUri == null) {
      return null;
    }
    try {
      File image = new File(UriHelper.get().getPathOfExternalImageToUpload(activity, imageUri));

      Bitmap cameraBmp = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), Uri.fromFile(image));

      if (cameraBmp == null) {
        return null;
      }

      Matrix m = new Matrix();
      m.postRotate(neededRotation(image));

      cameraBmp = Bitmap.createBitmap(cameraBmp, 0, 0, cameraBmp.getWidth(), cameraBmp.getHeight(), m, true);

      return cameraBmp;
    } catch (IOException e) {
      return null;
    }
  }

  public static int neededRotation(File file) {
    try {
      ExifInterface exif = new ExifInterface(file.getAbsolutePath());
      int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

      if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
        return 270;
      }
      if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
        return 180;
      }
      if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
        return 90;
      }
      return 0;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return 0;
  }
}
