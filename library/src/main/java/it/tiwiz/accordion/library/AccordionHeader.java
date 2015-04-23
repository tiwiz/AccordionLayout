package it.tiwiz.accordion.library;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import static it.tiwiz.accordion.library.AccordionCommon.*;
import static it.tiwiz.accordion.library.AccordionCommon.BUTTON_DEFAULT_SIZE;

/**
 * @gide
 */
class AccordionHeader extends RelativeLayout implements View.OnClickListener{

    private boolean isAccordionOpen = true;
    private int headerLabelLayoutId = R.layout.accordion_header_label_default;
    private int btnOpenCloseAccordionSize = -1;
    private AccordionListener accordionListener;

    public AccordionHeader (Context context) {
        this(context, null);
    }

    public AccordionHeader (Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AccordionHeader (Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, null);
    }


    public AccordionHeader (Context context, AttributeSet attrs, int defStyleAttr, AccordionListener accordionListener) {
        super(context, attrs, defStyleAttr);

        setAccordionListener(accordionListener);
        btnOpenCloseAccordionSize = convertDipsToPixels(context, BUTTON_DEFAULT_SIZE);
        generateLayoutParamsForHeader();

        inflateHeaderLabelFrom(context, headerLabelLayoutId);
        createOpenCloseAccordionButtonFrom(context, btnOpenCloseAccordionSize);

        isAccordionOpen = false;
    }


    private void generateLayoutParamsForHeader () {
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, btnOpenCloseAccordionSize);
        setLayoutParams(layoutParams);
    }

    private void createOpenCloseAccordionButtonFrom (Context context, int btnOpenCloseAccordionSize) {
        LayoutParams buttonParams = new LayoutParams(btnOpenCloseAccordionSize, btnOpenCloseAccordionSize);
        buttonParams.addRule(ALIGN_PARENT_TOP);
        buttonParams.addRule(ALIGN_PARENT_RIGHT);
        ImageButton btnOpenCloseAccordion = new ImageButton(context);
        btnOpenCloseAccordion.setLayoutParams(buttonParams);
        btnOpenCloseAccordion.setOnClickListener(this);
        addView(btnOpenCloseAccordion);
    }

    private void inflateHeaderLabelFrom (Context context, int headerLabelLayoutId) {
        LayoutInflater inflater = LayoutInflater.from(context);
        LayoutParams headerLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        headerLayoutParams.addRule(ALIGN_PARENT_TOP);
        View headerView = inflater.inflate(headerLabelLayoutId, this, false);
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

    private void closeAccordion () {
        isAccordionOpen = false;
        if (accordionListener != null) {
            accordionListener.onAccordionClose();
        }
    }

    private void openAccordion () {
        isAccordionOpen = true;
        if (accordionListener != null) {
            accordionListener.onAccordionOpen();
        }
    }

    public int getHeaderHeight() {
        return btnOpenCloseAccordionSize;
    }

    public boolean getAccordionCollapsedState() {
        return isAccordionOpen;
    }

    public void setAccordianCollapsedState(boolean isAccordionOpen) {
        this.isAccordionOpen = isAccordionOpen;
    }


}
