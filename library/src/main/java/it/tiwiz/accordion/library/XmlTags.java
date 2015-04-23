package it.tiwiz.accordion.library;


import android.content.Context;
import android.util.TypedValue;

import static it.tiwiz.accordion.library.XmlTags.Defaults.HEADER_BUTTON_SIZE_IN_DPS;
import static it.tiwiz.accordion.library.XmlTags.Defaults.HEADER_LABEL;
import static it.tiwiz.accordion.library.XmlTags.Defaults.HEADER_LAYOUT;

class XmlTags {

    static class Defaults {

        private static final int INT_NOT_SET = -1;
        private static final String STRING_NOT_SET = "";

        protected static final boolean START_COLLAPSED = false;
        protected static final int HEADER_LAYOUT = R.layout.accordion_header_label_default;
        protected static final int HEADER_BACKGROUND = R.drawable.header_button_background;
        protected static final String HEADER_LABEL = STRING_NOT_SET;
        protected static final int HEADER_TEXTSTYLE = INT_NOT_SET;
        protected static final int HEADER_BUTTON_BACKGROUND = R.drawable.header_button_background;
        protected static final int HEADER_OPEN_ICON = R.drawable.ic_header_open;
        protected static final int HEADER_CLOSE_ICON = R.drawable.ic_header_close;
        protected static final float HEADER_BUTTON_SIZE_IN_DPS = 48f;
        protected static final int HEADER_BUTTON_SIZE = INT_NOT_SET;
    }

    static class Utils {

        protected static boolean isSet(int valueToCheck) {
            return valueToCheck != Defaults.INT_NOT_SET;
        }

        protected static boolean isSet(String valueToCheck) {
            return !valueToCheck.equalsIgnoreCase(Defaults.STRING_NOT_SET);
        }

        protected static boolean isDefaultLayoutForHeader(int headerLayoutId) {
            return headerLayoutId == HEADER_LAYOUT;
        }


        /**
         * Converts the gives DPs into pixels for rendering on the screen correctly
         */
        protected static int convertDipsToPixels(Context context, float dps) {
            return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dps,
                    context.getResources().getDisplayMetrics());
        }


        public static XmlTagsBundle fixPossibleNullData(Context context, XmlTagsBundle bundle) {
            if (bundle.headerLabel == null) {
                bundle.headerLabel = HEADER_LABEL;
            }

            if (!isSet(bundle.headerButtonSize)) {
                bundle.headerButtonSize = convertDipsToPixels(context, HEADER_BUTTON_SIZE_IN_DPS);
            }

            return bundle;
        }
    }
}
