package com.itbarx.custom.component;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextViewBold extends TextView {

	public TextViewBold(Context context) {
	super(context);
		setFont();
	}

	public TextViewBold(Context context, AttributeSet attrs) {
	super(context, attrs);
		setFont();
	}

	public TextViewBold(Context context, AttributeSet attrs, int defStyle) {
	super(context, attrs, defStyle);
		setFont();
	}

	private void 	setFont() {
	if (!isInEditMode()) {
		Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/OpenSansSemibold.ttf");
		setTypeface(tf);

	}

	}

}
