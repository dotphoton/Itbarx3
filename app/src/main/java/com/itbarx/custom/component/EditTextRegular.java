package com.itbarx.custom.component;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.EditText;

import com.itbarx.utils.TextSizeUtil;

/**
 * TODO: Add a class header comment!
 */
public class EditTextRegular extends EditText {
    public EditTextRegular(Context context) {
        super(context);   setFont(); setEditTextSize();
    }

    public EditTextRegular(Context context, AttributeSet attrs) {
        super(context, attrs);   setFont(); setEditTextSize();
    }

    public EditTextRegular(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setFont();
        setEditTextSize();
    }

    private void setFont() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSansRegular.ttf");
            setTypeface(tf);

        }
    }

    private void setEditTextSize(){
        if (!isInEditMode()) {
            setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getEditBoxTextSize());
        }
    }
}
