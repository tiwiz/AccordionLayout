package it.tiwiz.accordion.library;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;


/**
 * @gide
 */
class AccordionHeader extends RelativeLayout implements View.OnClickListener{

    private XmlTagsBundle bundle;
    private boolean isAccordionOpen = true;
    private AccordionListener accordionListener;
    private ImageButton btnOpenCloseAccordion;
    private OpenCloseDrawable buttonDrawable;

    public AccordionHeader (Context context) {
        this(context, null);
    }

    public AccordionHeader (Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AccordionHeader (Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, null, new XmlTagsBundle());
    }

    public AccordionHeader (Context context, AttributeSet attrs, int defStyleAttr,
                            @Nullable AccordionListener accordionListener,
                            @NonNull XmlTagsBundle bundle) {
        super(context, attrs, defStyleAttr);
        this.bundle = bundle;
        isAccordionOpen = !bundle.startCollapsed;
        buttonDrawable = new OpenCloseDrawable(context, bundle.headerButtonOpenIcon, bundle.headerButtonCloseIcon);
        setAccordionListener(accordionListener);
        generateLayoutParamsForHeader();

        inflateHeaderLabelFrom(context);
        createOpenCloseAccordionButtonFrom(context);

    }

    protected void generateLayoutParamsForHeader () {
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, bundle.headerButtonSize);
        setLayoutParams(layoutParams);
    }

    protected void createOpenCloseAccordionButtonFrom (Context context) {
        LayoutParams buttonParams = new LayoutParams(bundle.headerButtonSize, bundle.headerButtonSize);
        buttonParams.addRule(ALIGN_PARENT_TOP);
        buttonParams.addRule(ALIGN_PARENT_RIGHT);
        btnOpenCloseAccordion = new ImageButton(context);
        btnOpenCloseAccordion.setImageDrawable(getDrawableForCollapsedState());
        Drawable background = DrawableHelper.getDrawableFrom(context, bundle.headerButtonBackground);
        btnOpenCloseAccordion.setBackground(background);
        btnOpenCloseAccordion.setLayoutParams(buttonParams);
        btnOpenCloseAccordion.setOnClickListener(this);
        addView(btnOpenCloseAccordion);
    }

    private Drawable getDrawableForCollapsedState () {
        return buttonDrawable.getDrawableFor(isAccordionOpen);
    }

    protected void inflateHeaderLabelFrom (Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        LayoutParams headerLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        headerLayoutParams.addRule(ALIGN_PARENT_TOP);
        View headerView = inflater.inflate(bundle.headerLayout, this, false);
        headerView.setLayoutParams(headerLayoutParams);
        headerView.setOnClickListener(this);
        addView(headerView);
    }

    public void setAccordionListener(AccordionListener accordionListener) {
        this.accordionListener = accordionListener;
    }

    @Override
    public void onClick (View v) {
        if (isAccordionOpen) {
            closeAccordion();
        } else {
            openAccordion();
        }
    }

    protected void closeAccordion () {
        isAccordionOpen = false;
        updateButtonBackground();
        if (accordionListener != null) {
            accordionListener.onAccordionClose();
        }
    }

    protected void openAccordion () {
        isAccordionOpen = true;
        updateButtonBackground();
        if (accordionListener != null) {
            accordionListener.onAccordionOpen();
        }
    }

    private void updateButtonBackground () {
        btnOpenCloseAccordion.setImageDrawable(getDrawableForCollapsedState());
    }

    public int getHeaderHeight() {
        return bundle.headerButtonSize;
    }

    public boolean getAccordionCollapsedState() {
        return isAccordionOpen;
    }

    public void setAccordianCollapsedState(boolean isAccordionOpen) {
        this.isAccordionOpen = isAccordionOpen;
    }
}
