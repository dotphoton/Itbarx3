package com.itbarx.activity;

import com.itbarx.R;
import com.itbarx.application.ItbarxGlobal;
import com.itbarx.custom.component.ButtonBold;
import com.itbarx.custom.component.EditTextRegular;
import com.itbarx.custom.component.TextViewBold;
import com.itbarx.custom.component.TextViewRegular;
import com.itbarx.exception.ExceptionHandler;
import com.itbarx.service.ResponseEventModel;
import com.itbarx.service.error.BarxErrorModel;
import com.itbarx.listener.AccountProcessesServiceListener;
import com.itbarx.listener.OneShotOnClickListener;
import com.itbarx.model.account.AccountGetUserByLoginInfoModel;
import com.itbarx.model.account.AccountSignUpModel;
import com.itbarx.model.account.EditProfileModel;
import com.itbarx.model.account.GetEditProfileModel;
import com.itbarx.sl.AccountProcessesServiceSL;
import com.itbarx.utils.TextSizeUtil;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

public class CreateNewAccountActivity extends BaseActivity implements Communicator {

	// ************************//
	// ---ATTRIBUTES---
	// ************************//
	private TextViewRegular txtToolbar, txtAccept, txtUserAgreement, txtRememberMe;
	private EditTextRegular edtUserName, edtEmail, edtPassword, edtRePassword;
	private ButtonBold btnDone, btnTwitter, btnFacebook;
	private TextViewBold txtViewOr, txtAddPhoto;
	private ImageView rememberMeIcon;


	@Override protected int getLayoutResourceId() {
		// TODO Auto-generated method stub
		return R.layout.activity_create_new_account_screen;
	}

	@Override protected Context getContext() {
		// TODO Auto-generated method stub
		return CreateNewAccountActivity.this;
	}
	@Override protected void exceptionHandler() {
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
	}

	@Override protected void initViews() {

		rememberMeIcon = (ImageView)findViewById(R.id.create_new_account_activity_screen_agreeToAgreement_imageView);
		String value= getIntent().getStringExtra("USER_AGREEMENT");
		if(value!=null &&value.equals("ACCEPT")){
		rememberMeIcon.setImageResource(R.drawable.editprofile_icon_check);
		}

		txtToolbar = (TextViewRegular) findViewById(R.id.create_new_account_activity_screen_toolbar_textView);
		edtUserName = (EditTextRegular) findViewById(R.id.create_new_account_activity_screen_userName_editText);
		edtEmail = (EditTextRegular) findViewById(R.id.create_new_account_activity_screen_eMail_editText);
		edtPassword = (EditTextRegular) findViewById(R.id.create_new_account_activity_screen_password_editText);
		edtRePassword = (EditTextRegular) findViewById(R.id.create_new_account_activity_screen_rePassword_editText);
		btnDone = (ButtonBold) findViewById(R.id.create_new_account_activity_screen_ok_button);
		btnTwitter = (ButtonBold) findViewById(R.id.create_new_account_activity_screen_twitter_button);
		btnFacebook = (ButtonBold) findViewById(R.id.create_new_account_activity_screen_facebook_button);
		txtViewOr = (TextViewBold) findViewById(R.id.create_new_account_activity_screen_or_textView);
		txtAddPhoto = (TextViewBold) findViewById(R.id.create_new_account_activity_screen_addPhoto_textView);
		txtAccept = (TextViewRegular) findViewById(R.id.create_new_account_activity_screen_agreeTo_textView);
		txtUserAgreement = (TextViewRegular) findViewById(R.id.create_new_account_activity_screen_userAgreement_textView);
		txtRememberMe = (TextViewRegular) findViewById(R.id.create_new_account_activity_screen_rememberMe_textView);
		rememberMeIcon = (ImageView) findViewById(R.id.create_new_account_activity_screen_rememberMe_imageView);
		txtUserAgreement.setOnClickListener(openUserAgreementClickListener);
		setCompText();
		signUp();
		Integer.parseInt(" fssfs");
	}

	private void setCompText() {

		btnDone.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getButtonTextSize());
		btnFacebook.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getButtonTextSize());
		btnTwitter.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getButtonTextSize());
		txtViewOr.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getLoginOrTextSize());
		txtToolbar.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getToolbarTextSize());
		edtUserName.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getEditBoxTextSize());
		edtPassword.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getEditBoxTextSize());
		edtEmail.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getEditBoxTextSize());
		edtRePassword.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getEditBoxTextSize());
		txtAddPhoto.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getCreateAddPhotoTextSize());
		txtAccept.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getCreateMiddleInfoTextSize());
		txtUserAgreement.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getCreateMiddleInfoTextSize());
		txtRememberMe.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getCreateMiddleInfoTextSize());

	}
	// ***********************************************//
	// ---CREATE NEW ACCOUNT ACTIVITY ACTION METHODS---
	// ***********************************************//


	OneShotOnClickListener openUserAgreementClickListener= new OneShotOnClickListener(500) {
		@Override public void onOneShotClick(View v) {

			launchSubActivity(UserAgreementActivity.class);

		}
	};

	// DONE BUTTON EVENTS

	private void signUp() {

		btnDone.setOnClickListener(newUserClickListener);
	}

	// DONE BUTTON LISTENER
	OneShotOnClickListener newUserClickListener = new OneShotOnClickListener(500) {

		@Override public void onOneShotClick(View v) {
			String strUserName = "";
			String strEmail = "";
			String strPassword = "";
			String strRePassword = "";

			if (edtUserName.getText().toString() != null) {
				strUserName = edtUserName.getText().toString();
			}
			if (edtEmail.getText().toString() != null) {
				strEmail = edtEmail.getText().toString();
			}
			if (edtPassword.getText().toString() != null) {
				strPassword = edtPassword.getText().toString();
			}
			if (edtRePassword.getText().toString() != null) {
				strRePassword = edtRePassword.getText().toString();
			}

			if (strPassword.equals(strRePassword)) {
				setSignUpAccount(new AccountSignUpModel(strUserName, strPassword, strRePassword, strEmail, ""));
			}

			// Toast.makeText(getApplicationContext(), strUserName + " " + strPassword, Toast.LENGTH_LONG).show();

		}
	};

	// ---SIGNUP---
	protected void setSignUpAccount(AccountSignUpModel signUpModel) {
		AccountProcessesServiceSL accountServiceSL = new AccountProcessesServiceSL(getContext(), accountProcessesServiceListener, R.string.root_service_url);
		accountServiceSL.setSignUpAccount(signUpModel);
		showProgress(getString(R.string.ItbarxConnecting));

	}

	AccountProcessesServiceListener accountProcessesServiceListener = new AccountProcessesServiceListener() {
		@Override public void logInAccount(AccountGetUserByLoginInfoModel loginModelResponse) {
			dismissProgress();
		}

		@Override public void signUpAccount(AccountGetUserByLoginInfoModel loginModelResponse) {
			dismissProgress();
			ItbarxGlobal global = ItbarxGlobal.setInstance(CreateNewAccountActivity.this);
			global.setAccountModel(loginModelResponse);
			launchSubActivity(TabContainer.class);

		}

		@Override public void forgotAccount(String forgotResponse) {
			dismissProgress();
		}

		@Override public void changePassByCode(AccountGetUserByLoginInfoModel loginModelResponse) {
			dismissProgress();
		}

		@Override public void getEditProfileAccount(GetEditProfileModel getEditProfileModel) {
			dismissProgress();
		}

		@Override public void editProfileAccount(EditProfileModel editProfileModel) {
			dismissProgress();
		}

		@Override public void deleteProfileAccount(String isDeleted) {
			dismissProgress();
		}

		@Override public void onComplete(ResponseEventModel onComplete) {
			dismissProgress();
		}

		@Override public void onError(BarxErrorModel onError) {
			dismissProgress();
		}
	};

	@Override public void choose(String chosen) {

	}
}
