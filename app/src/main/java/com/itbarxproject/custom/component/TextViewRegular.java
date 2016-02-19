package com.itbarxproject.custom.component;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Photon on 1.09.2015.
 */
public class TextViewRegular extends TextView {
    public TextViewRegular(Context context) {
        super(context);
        setFont();
    }

    public TextViewRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont();
    }

    public TextViewRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setFont();
    }

    private void setFont() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSansRegular.ttf");
            setTypeface(tf);
        }
    }
}