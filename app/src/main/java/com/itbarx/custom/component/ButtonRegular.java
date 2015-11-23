package com.itbarx.custom.component;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

public class ButtonRegular extends Button {

	public ButtonRegular(Context context) {
	super(context);
		setFont();
	}

	public ButtonRegular(Context context, AttributeSet attrs) {
	super(context, attrs);
		setFont();
	}

	public ButtonRegular(Context context, AttributeSet attrs, int defStyle) {
	super(context, attrs, defStyle);
		setFont();
	}

	private void    setFont() {
	if (!isInEditMode()) {
		Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSansRegular.ttf");
		setTypeface(tf);

	}

	}

}
