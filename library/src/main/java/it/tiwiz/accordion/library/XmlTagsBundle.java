package it.tiwiz.accordion.library;

import android.os.Parcel;
import android.os.Parcelable;

import static it.tiwiz.accordion.library.XmlTags.Defaults.*;

/**
 * @hide
 */
class XmlTagsBundle implements Parcelable {

    boolean startCollapsed = START_COLLAPSED;
    int headerLayout = HEADER_LAYOUT;
    int headerBackground = HEADER_BACKGROUND;
    int headerTextColor = HEADER_TEXTCOLOR;
    int headerButtonBackground = HEADER_BUTTON_BACKGROUND;
    int headerButtonOpenIcon = HEADER_OPEN_ICON;
    int headerButtonCloseIcon = HEADER_CLOSE_ICON;
    int headerButtonSize = HEADER_BUTTON_SIZE;
    final float defaultHeaderButtonSize = HEADER_BUTTON_SIZE_IN_DPS;
    String headerLabel = HEADER_LABEL;

    @Override
    public int describeContents () {
        return 0;
    }

    @Override
    public void writeToParcel (Parcel dest, int flags) {

    }

}
