package io.teamkona.konatools.ui;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import io.teamkona.konatools.R;

/**
 * Created by gonzalomelov on 11/1/15.
 **/
public class UiHelper {

  private static final String TAG = UiHelper.class.getSimpleName();

  /**
   * Converts dp unit to equivalent pixels, depending on device density.
   *
   * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
   * @return A float value to represent px equivalent to dp depending on device density
   */
  public static float dpToPx(Context context, float dp) {
    Resources resources = context.getResources();
    DisplayMetrics metrics = resources.getDisplayMetrics();
    float px = dp * (metrics.densityDpi / 160f);
    return px;
  }

  /**
   * Converts device specific pixels to density independent pixels.
   *
   * @param px A value in px (pixels) unit. Which we need to convert into db
   * @return A float value to represent dp equivalent to px value
   */
  public static float pxToDp(Context context, float px) {
    Resources resources = context.getResources();
    DisplayMetrics metrics = resources.getDisplayMetrics();
    float dp = px / (metrics.densityDpi / 160f);
    return dp;
  }

  /**
   * Tint the image views to the selected color
   *
   * @param context Context
   * @param colorRes Color res to tint the image views
   * @param imageViews Image views
   */
  public static void setColorStateListToDrawables(Context context, @ColorRes int colorRes, ImageView... imageViews) {
    setColorStateListToDrawables(ContextCompat.getColorStateList(context, colorRes), imageViews);
  }

  /**
   * Tint the image views to the selected color
   *
   * @param colorStateList Color state list to tint the image views
   * @param imageViews Image views
   */
  public static void setColorStateListToDrawables(ColorStateList colorStateList, ImageView... imageViews) {
    for (ImageView imageView : imageViews) {
      DrawableCompat.setTintList(DrawableCompat.wrap(imageView.getDrawable()), colorStateList);
    }
  }

  public static void setMenuItemTint(Context context, MenuItem item, int color){
    Drawable newIcon = item.getIcon();
    newIcon.mutate().setColorFilter(color, PorterDuff.Mode.SRC_IN);
    item.setIcon(newIcon);
  }

  public static Bitmap tintBitmap(Context context, Bitmap bitmap, int color) {
    Bitmap mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
    Canvas canvas = new Canvas(mutableBitmap);
    Paint paint = new Paint();
    paint.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
    canvas.drawBitmap(bitmap, 0, 0, paint);
    return mutableBitmap;
  }

  public static void slideUpFromSideTransition(Activity activity) {
    activity.overridePendingTransition(R.anim.slide_down, R.anim.no_slide);
  }

  public static void slideDownFromSideTransition(Activity activity) {
    activity.overridePendingTransition(R.anim.no_slide, R.anim.slide_up);
  }

  public static void slideInFromSideTransition(Activity activity) {
    activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
  }

  public static void slideOutFromSideTransition(Activity activity) {
    activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
  }

  public static boolean isValidEmail(CharSequence target) {
    return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
  }

  public static Drawable getErrorIconForEditText(Context context, @DrawableRes int id) {
    Drawable errorIcon = ContextCompat.getDrawable(context, id);
    errorIcon.setBounds(new Rect(0, 0, errorIcon.getIntrinsicWidth(), errorIcon.getIntrinsicHeight()));
    return errorIcon;
  }

  public static class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
      public void onItemClick(View view, int position);
    }

    GestureDetector mGestureDetector;

    public RecyclerItemClickListener(Context context, OnItemClickListener listener) {
      mListener = listener;
      mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
        @Override public boolean onSingleTapUp(MotionEvent e) {
          return true;
        }
      });
    }

    @Override public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
      View childView = view.findChildViewUnder(e.getX(), e.getY());
      if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
        mListener.onItemClick(childView, view.getChildPosition(childView));
        return true;
      }
      return false;
    }

    @Override public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) { }

    @Override
    public void onRequestDisallowInterceptTouchEvent (boolean disallowIntercept){}
  }
}
