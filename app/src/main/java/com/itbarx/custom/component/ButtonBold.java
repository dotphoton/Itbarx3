package com.itbarx.custom.component;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * TODO: Add a class header comment!
 */
public class ButtonBold extends Button {

    public ButtonBold(Context context) {
        super(context);
        setFont();
    }

    public ButtonBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont();
    }

    public ButtonBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setFont();
    }

    private void setFont() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSansSemibold.ttf");
            setTypeface(tf);

        }

    }

}