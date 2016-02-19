package com.itbarxproject.activity;

import com.itbarxproject.R;
import com.itbarxproject.custom.component.ButtonBold;
import com.itbarxproject.custom.component.TextViewRegular;
import com.itbarxproject.exception.ExceptionHandler;
import com.itbarxproject.listener.OneShotOnClickListener;
import com.itbarxproject.utils.TextSizeUtil;

import android.content.Context;
import android.content.Intent;
import android.util.TypedValue;
import android.view.View;

public class UserAgreementActivity extends BaseActivity {

	private TextViewRegular  txtToolbar,txtAgreement;
	private ButtonBold btnAccept;
	Intent intent;

	@Override protected int getLayoutResourceId() {
		// TODO Auto-generated method stub
		return R.layout.activity_user_agreement_screen;
	}

	@Override protected Context getContext() {
		// TODO Auto-generated method stub
		return UserAgreementActivity.this;
	}
	@Override protected void exceptionHandler() {
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
	}

	@Override protected void initViews() {
		txtToolbar = (TextViewRegular) findViewById(R.id.activity_userAgreement_screen_toolbar_Text);
		txtAgreement = (TextViewRegular) findViewById(R.id.activity_userAgreement_screen_agreement_Text);
		btnAccept = (ButtonBold) findViewById(R.id.activity_userAgreement_screen_accept_button);
		btnAccept.setOnClickListener(agreementAcceptClickListener);
		setCompText();

	}


	OneShotOnClickListener agreementAcceptClickListener = new OneShotOnClickListener(500) {
		@Override public void onOneShotClick(View v) {

			intent	= new Intent(UserAgreementActivity.this, CreateNewAccountActivity.class);
			intent.putExtra("USER_AGREEMENT","ACCEPT");
			startActivity(intent);
		}
	};

	private void setCompText() {

		btnAccept.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getButtonTextSize());
		txtToolbar.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getToolbarTextSize());
		txtAgreement.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getBarkCountTextSize());


	}

}
