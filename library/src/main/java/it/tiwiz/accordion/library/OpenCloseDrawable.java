package it.tiwiz.accordion.library;

import android.content.Context;
import android.graphics.drawable.Drawable;

import static it.tiwiz.accordion.library.DrawableHelper.*;

public class OpenCloseDrawable{

    private final Drawable openDrawable, closeDrawable;

    public OpenCloseDrawable(final Context context, int openDrawableId, int closeDrawableId) {
        openDrawable = getDrawableFrom(context, openDrawableId);
        closeDrawable = getDrawableFrom(context, closeDrawableId);
    }


    public Drawable getDrawableFor(boolean collapsedState) {
        return (collapsedState ? openDrawable : closeDrawable);
    }
}
