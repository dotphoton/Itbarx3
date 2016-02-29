package com.itbarxproject.activity;

import com.itbarxproject.R;
import com.itbarxproject.custom.component.ButtonBold;
import com.itbarxproject.custom.component.TextViewRegular;
import com.itbarxproject.exception.ExceptionHandler;
import com.itbarxproject.listener.OneShotOnClickListener;
import com.itbarxproject.utils.TextSizeUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;

public class UserAgreementActivity extends Activity {

	private TextViewRegular  txtToolbar,txtAgreement;
	private ButtonBold btnAccept;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayoutResourceId());
		initViews();
	}

	protected int getLayoutResourceId() {
		// TODO Auto-generated method stub
		return R.layout.activity_user_agreement_screen;
	}

	protected Context getContext() {
		// TODO Auto-generated method stub
		return UserAgreementActivity.this;
	}



	protected void initViews() {
		setContentView(getLayoutResourceId());
		txtToolbar = (TextViewRegular) findViewById(R.id.activity_userAgreement_screen_toolbar_Text);
		txtAgreement = (TextViewRegular) findViewById(R.id.activity_userAgreement_screen_agreement_Text);
		btnAccept = (ButtonBold) findViewById(R.id.activity_userAgreement_screen_accept_button);
		btnAccept.setOnClickListener(agreementAcceptClickListener);
		setCompText();

	}


	OneShotOnClickListener agreementAcceptClickListener = new OneShotOnClickListener(500) {
		@Override public void onOneShotClick(View v) {


			Intent returnIntent = new Intent();
			returnIntent.putExtra("USER_AGREEMENT", "ACCEPT");
			setResult(Activity.RESULT_OK,returnIntent);
			finish();

			//(CreateNewAccountActivity.class, "USER_AGREEMENT", "ACCEPT");
			//Intent returnIntent = getIntent();
			//returnIntent.ad
			//returnIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			//returnIntent.put("USER_AGREEMENT", "ACCEPT");
			//returnIntent.setClass(getContext(), CreateNewAccountActivity.class);

			//setResult(Activity.RESULT_OK, returnIntent);
			//finish();
		}
	};

	private void setCompText() {

		btnAccept.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getButtonTextSize());
		txtToolbar.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getToolbarTextSize());
		txtAgreement.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getBarkCountTextSize());


	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {


			Intent returnIntent = new Intent();
			returnIntent.putExtra("USER_AGREEMENT", "CANCEL");
			setResult(Activity.RESULT_CANCELED, returnIntent);

			finish();
			/*
			Intent returnIntent = getIntent();
			setResult(Activity.RESULT_CANCELED, returnIntent);
			finish();
			*/
			//launchSubActivityAddString(CreateNewAccountActivity.class,"USER_AGREEMENT","CANCEL");

			//finish();
		}
		return super.onKeyDown(keyCode, event);
	}

}
