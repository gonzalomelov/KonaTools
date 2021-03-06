package io.teamkona.konatools.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.IntentCompat;

/**
 * Created by gonzalomelo on 1/1/16.
 */
public class NavigationUtils {
  public static void navigateToActivity(@NonNull Activity activity, @NonNull Intent intent) {
    navigateToActivity(activity, intent, null);
  }

  public static void navigateToActivity(@NonNull Activity activity, @NonNull Intent intent, @Nullable Bundle extras) {
    activity.startActivity(intent, extras);
  }

  public static void navigateToActivityClearingStack(@NonNull Activity activity, @NonNull Intent intent) {
    navigateToActivityClearingStack(activity, intent, null);
  }

  public static void navigateToActivityClearingStack(@NonNull Activity activity, @NonNull Intent intent, @Nullable Bundle extras) {
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK);
    activity.startActivity(intent, extras);
  }

  public static void navigateToActivityWithoutAddingToHistory(@NonNull Activity activity, @NonNull Intent intent) {
    intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
    activity.startActivity(intent);
  }

  public static void navigateToActivityForResult(@NonNull Activity activity, @NonNull Intent intent, int resultCode) {
    activity.startActivityForResult(intent, resultCode);
  }

  public static void navigateToActivityUsingUpTransition(@NonNull Activity activity, @NonNull Intent intent) {
    activity.startActivity(intent);
    UiHelper.slideUpFromSideTransition(activity);
  }

  public static void navigateToActivityUsingSideTransition(@NonNull Activity activity, @NonNull Intent intent) {
    activity.startActivity(intent);
    UiHelper.slideInFromSideTransition(activity);
  }
}
