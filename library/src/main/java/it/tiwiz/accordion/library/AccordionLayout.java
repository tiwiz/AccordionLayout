package it.tiwiz.accordion.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class AccordionLayout extends LinearLayout implements AccordionListener{

    private int layoutHeightWhenOpened = -1;
    private AccordionHeader accordionHeader;

    public AccordionLayout (Context context) {
        this(context, null);
    }

    public AccordionLayout (Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AccordionLayout (Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
        createHeaderView(context, attrs, defStyleAttr);
    }

    private void createHeaderView (Context context, AttributeSet attrs, int defStyleAttr) {
        final XmlTagsBundle xmlBundle = loadXmlSettingsFrom(context, attrs);
        accordionHeader = new AccordionHeader(context, attrs, defStyleAttr, this);
        addView(accordionHeader);
    }

    private XmlTagsBundle loadXmlSettingsFrom (Context context, AttributeSet attrs) {
        XmlTagsBundle bundle = new XmlTagsBundle();
//        TypedArray values = context.obtainStyledAttributes(attrs,)
        return null;
    }

    @Override
    protected void onLayout (boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (layoutHeightWhenOpened == -1)
            layoutHeightWhenOpened = getMeasuredHeight();

        if (accordionHeader.getAccordionCollapsedState()) {
            onAccordionOpen();
        } else {
            onAccordionClose();
        }
    }

    @Override
    public void onAccordionOpen () {
        adjustLayoutHeightTo(layoutHeightWhenOpened);
    }

    @Override
    public void onAccordionClose () {
        adjustLayoutHeightTo(accordionHeader.getHeaderHeight());
    }

    private void adjustLayoutHeightTo(int newHeight) {
        RelativeLayout.LayoutParams internalParams = (RelativeLayout.LayoutParams) getLayoutParams();
        internalParams.height = newHeight;
        setLayoutParams(internalParams);
    }


    @Override
    protected Parcelable onSaveInstanceState () {
        Parcelable superState = super.onSaveInstanceState();
        SavedState savedState = new SavedState(superState);
        savedState.openClosedState = accordionHeader.getAccordionCollapsedState();
        return savedState;
    }

    @Override
    protected void onRestoreInstanceState (Parcelable state) {
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        accordionHeader.setAccordianCollapsedState(savedState.openClosedState);
    }

    @Override
    protected void dispatchSaveInstanceState (SparseArray<Parcelable> container) {
        dispatchFreezeSelfOnly(container);
    }

    @Override
    protected void dispatchRestoreInstanceState (SparseArray<Parcelable> container) {
        dispatchThawSelfOnly(container);
    }
}
