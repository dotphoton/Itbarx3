package com.itbarxproject.exception;

import android.content.Context;
import android.widget.TextView;

import com.itbarxproject.R;
import com.itbarxproject.activity.BaseActivity;
import com.itbarxproject.utils.FinalString;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ExceptionActivity extends BaseActivity {
	@Bind(R.id.activity_exception_screen_textView) TextView txtErrors;

	@Override protected int getLayoutResourceId() {
		return R.layout.activity_exception;
	}

	@Override protected Context getContext() {
		return ExceptionActivity.this;
	}

	@Override protected void exceptionHandler() {

	}

	@Override protected void initViews() {
		ButterKnife.bind(this);
		txtErrors.setText(getIntent().getStringExtra(FinalString.ERROR));

	}
}
