package it.tiwiz.accordion.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;

public class DrawableHelper {

    private static final int ANDROID_VERSION = Build.VERSION.SDK_INT;
    private static final int LOLLIPOP = Build.VERSION_CODES.LOLLIPOP;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressWarnings("Deprecated")
    public static Drawable getDrawableFrom(Context context, int resId) {
        if (ANDROID_VERSION >= LOLLIPOP) {
            return context.getDrawable(resId);
        } else {
            return context.getResources().getDrawable(resId);
        }
    }
}
