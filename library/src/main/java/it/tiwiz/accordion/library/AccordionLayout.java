package it.tiwiz.accordion.library;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class AccordionLayout extends LinearLayout implements AccordionListener {

    protected int layoutHeightWhenOpened = -1;
    protected AccordionHeader accordionHeader;

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

    protected void createHeaderView (Context context, AttributeSet attrs, int defStyleAttr) {
        final XmlTagsBundle xmlBundle = loadXmlSettingsFrom(context, attrs);
        accordionHeader = new AccordionHeader(context, attrs, defStyleAttr, this, xmlBundle);
        addView(accordionHeader);
    }

    protected XmlTagsBundle loadXmlSettingsFrom (Context context, AttributeSet attrs) {
        XmlTagsBundle bundle = new XmlTagsBundle();
        try {
            TypedArray values = context.obtainStyledAttributes(attrs, R.styleable.AccordionLayout);
            // Honestly, I could have used less attributes
            bundle.startCollapsed = values.getBoolean(R.styleable.AccordionLayout_startCollapsed, bundle.startCollapsed);
            bundle.headerBackground = values.getInteger(R.styleable.AccordionLayout_header_background, bundle.headerBackground);
            bundle.headerLayout = values.getInteger(R.styleable.AccordionLayout_header_layout, bundle.headerLayout);
            bundle.headerLabel = values.getString(R.styleable.AccordionLayout_header_label);
            bundle.headerTextStyle = values.getColor(R.styleable.AccordionLayout_header_textStyle, bundle.headerTextStyle);
            bundle.headerButtonBackground = values.getInteger(R.styleable.AccordionLayout_header_button_background, bundle.headerButtonBackground);
            bundle.headerButtonOpenIcon = values.getInteger(R.styleable.AccordionLayout_header_button_openIcon, bundle.headerButtonOpenIcon);
            bundle.headerButtonCloseIcon = values.getInteger(R.styleable.AccordionLayout_header_button_closeIcon, bundle.headerButtonCloseIcon);
            float buttonSizeInDps = values.getDimension(R.styleable.AccordionLayout_header_button_size, bundle.defaultHeaderButtonSize);
            bundle.headerButtonSize = XmlTags.Utils.convertDipsToPixels(context, buttonSizeInDps);
            values.recycle();
        } catch (Exception e) {
            //Avoids crashes with some devices in case attributes are not declared
        }
        bundle = XmlTags.Utils.fixPossibleNullData(context, bundle);
        return bundle;
    }

    @Override
    protected void onLayout (boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if (layoutHeightWhenOpened == -1){
            layoutHeightWhenOpened = getMeasuredHeight();
        }

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

    private void adjustLayoutHeightTo (int newHeight) {
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
