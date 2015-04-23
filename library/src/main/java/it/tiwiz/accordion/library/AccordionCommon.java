package it.tiwiz.accordion.library;

import android.content.Context;
import android.util.TypedValue;

/**
 * @hide
 */
class AccordionCommon {
    protected static final float BUTTON_DEFAULT_SIZE = 48f;

    /**
     * Converts the gives DPs into pixels for rendering on the screen correctly
     */
    protected static int convertDipsToPixels(Context context, float dps) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dps,
                context.getResources().getDisplayMetrics());
    }
}

