package it.tiwiz.accordion.library;


import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

class SavedState extends View.BaseSavedState {

    boolean openClosedState;

    private SavedState (Parcel source) {
        super(source);
        openClosedState = (boolean) source.readValue(null);
    }

    public SavedState (Parcelable superState) {
        super(superState);
    }

    @Override
    public void writeToParcel (Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeValue(openClosedState);
    }

    public static final Creator CREATOR = new CustomCreator();

    private static class CustomCreator implements Creator<SavedState> {
        @Override
        public SavedState createFromParcel (Parcel source) {
            return new SavedState(source);
        }

        @Override
        public SavedState[] newArray (int size) {
            return new SavedState[size];
        }
    }
}
