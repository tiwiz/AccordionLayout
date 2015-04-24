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
import android.widget.TextView;

import static it.tiwiz.accordion.library.XmlTags.Utils.*;


/**
 * @gide
 */
class AccordionHeader extends RelativeLayout implements View.OnClickListener{

    private XmlTagsBundle bundle;
    private boolean isAccordionOpen = true;
    private AccordionListener accordionListener;
    private ImageButton btnOpenCloseAccordion;
    private OpenCloseDrawable buttonDrawable;
    private View headerView;
    private Context context;

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
        this.context = context;
        this.bundle = bundle;
        isAccordionOpen = !bundle.startCollapsed;
        buttonDrawable = new OpenCloseDrawable(context, bundle.headerButtonOpenIcon, bundle.headerButtonCloseIcon);
        setAccordionListener(accordionListener);
        generateLayoutParamsForHeader();

        inflateHeaderLabel();
        createOpenCloseAccordionButton();

    }

    protected void generateLayoutParamsForHeader () {
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, bundle.headerButtonSize);
        setLayoutParams(layoutParams);
    }

    protected void createOpenCloseAccordionButton () {
        LayoutParams buttonParams = new LayoutParams(bundle.headerButtonSize, bundle.headerButtonSize);
        buttonParams.addRule(ALIGN_PARENT_TOP);
        buttonParams.addRule(ALIGN_PARENT_RIGHT);
        btnOpenCloseAccordion = new ImageButton(context);
        btnOpenCloseAccordion.setImageDrawable(getDrawableForCollapsedState());
        resetHeaderButtonBackground();
        btnOpenCloseAccordion.setLayoutParams(buttonParams);
        btnOpenCloseAccordion.setOnClickListener(this);
        addView(btnOpenCloseAccordion);
    }

    private void resetHeaderButtonBackground () {
        Drawable background = DrawableHelper.getDrawableFrom(context, bundle.headerButtonBackground);
        btnOpenCloseAccordion.setBackground(background);
    }

    private Drawable getDrawableForCollapsedState () {
        return buttonDrawable.getDrawableFor(isAccordionOpen);
    }

    protected void inflateHeaderLabel () {
        LayoutInflater inflater = LayoutInflater.from(context);
        LayoutParams headerLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        headerLayoutParams.addRule(ALIGN_PARENT_TOP);
        if (shallUseDefaultLayout()) {
            headerView = inflateDefaultLayoutFrom(inflater);
        } else {
            headerView = inflater.inflate(bundle.headerLayout, this, false);
        }
        headerView.setLayoutParams(headerLayoutParams);
        headerView.setOnClickListener(this);
        addView(headerView);
    }

    private boolean shallUseDefaultLayout () {
        return isDefaultLayoutForHeader(bundle.headerLayout);
    }

    private View inflateDefaultLayoutFrom(LayoutInflater inflater) {
        View headerView = inflater.inflate(bundle.headerLayout, this, false);
        Drawable background = DrawableHelper.getDrawableFrom(context, bundle.headerBackground);
        headerView.setBackground(background);
        TextView label = (TextView) headerView.findViewById(R.id.accordionLabel);
        if (isSet(bundle.headerLabel)) {
            label.setText(bundle.headerLabel);
        }
        if (isSet(bundle.headerTextStyle)) {
            label.setTextAppearance(context, bundle.headerTextStyle);
        }
        return headerView;
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
        updateButtonBackground();
    }

    public int getHeaderLayout () {
        return bundle.headerLayout;
    }
    
    public void setHeaderLayout (int headerLayout) {
        bundle.headerLayout = headerLayout;
        inflateHeaderLabel();
        bringChildToFront(btnOpenCloseAccordion);
    }
}
