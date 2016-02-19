package com.itbarxproject.activity;

import com.itbarxproject.R;
import com.itbarxproject.exception.ExceptionHandler;

import android.content.Context;

public class NewUserDoneActivity extends BaseActivity {

	@Override protected int getLayoutResourceId() {
		// TODO Auto-generated method stub
		return R.layout.activity_new_user_done_screen;
	}

	@Override protected Context getContext() {
		// TODO Auto-generated method stub
		return NewUserDoneActivity.this;
	}
	@Override protected void exceptionHandler() {
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
	}

	@Override protected void initViews() {
		// TODO Auto-generated method stub

	}


}
