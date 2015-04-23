package it.tiwiz.accordion.library;


class XmlTags {

    static class Defaults {

        private static final int INT_NOT_SET = -1;
        private static final String STRING_NOT_SET = "";

        protected static final boolean START_COLLAPSED = false;
        protected static final int HEADER_LAYOUT = R.layout.accordion_header_label_default;
        protected static final int HEADER_BACKGROUND = INT_NOT_SET;
        protected static final String HEADER_LABEL = STRING_NOT_SET;
        protected static final int HEADER_TEXTCOLOR = INT_NOT_SET;
        protected static final int HEADER_BUTTON_BACKGROUND = R.drawable.header_button_background;
        protected static final int HEADER_OPEN_ICON = R.drawable.ic_header_open;
        protected static final int HEADER_CLOSE_ICON = R.drawable.ic_header_close;
    }

    static class Utils {

        protected boolean isSet(int valueToCheck) {
            return valueToCheck != Defaults.INT_NOT_SET;
        }

        protected boolean isSet(String valueToCheck) {
            return !valueToCheck.equalsIgnoreCase(Defaults.STRING_NOT_SET);
        }
    }
}
