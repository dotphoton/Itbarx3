package com.itbarx.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.itbarx.R;
import com.itbarx.application.ItbarxGlobal;
import com.itbarx.custom.component.ButtonBold;
import com.itbarx.custom.component.EditTextRegular;
import com.itbarx.custom.component.TextViewBold;
import com.itbarx.custom.component.TextViewRegular;
import com.itbarx.service.ResponseEventModel;
import com.itbarx.service.error.BarxErrorModel;
import com.itbarx.listener.AccountProcessesServiceListener;
import com.itbarx.listener.OneShotOnClickListener;
import com.itbarx.model.account.AccountForgotSendMailModel;
import com.itbarx.model.account.AccountGetUserByLoginInfoModel;
import com.itbarx.model.account.AccountSendEmailCodeModel;
import com.itbarx.model.account.AccountSignUpModel;
import com.itbarx.model.account.DeleteProfileModel;
import com.itbarx.model.account.EditProfileModel;
import com.itbarx.model.account.GetEditProfileIdModel;
import com.itbarx.model.account.GetEditProfileModel;
import com.itbarx.model.account.LoginModel;
import com.itbarx.sl.AccountProcessesServiceSL;
import com.itbarx.utils.TextSizeUtil;

public class LoginActivity extends BaseActivity {

	// ************************//
	// ---ATTRIBUTES---
	// ************************//
	private ButtonBold btnLogIn, btnCreateNewAcc, btnForgotPwd, btnFacebook, btnGooglePlus;
	private TextViewRegular txtToolbar;
	private TextViewBold txtViewRembMe, txtViewOr;
	private EditTextRegular edtUserName, edtPassword;
	private String strUserName, strPassword;
	private ImageView unCheckedRemMeImageV,checkedRemMeImageV;

	@Override protected int getLayoutResourceId() {

		return R.layout.activity_login_screen;
	}

	@Override protected Context getContext() {
		// TODO Auto-generated method stub
		return LoginActivity.this;
	}

	@Override protected void initViews() {

		edtUserName = (EditTextRegular) findViewById(R.id.login_activity_screen_username_edittext);
		edtPassword = (EditTextRegular) findViewById(R.id.login_activity_screen_password_edittext);
		btnLogIn = (ButtonBold) findViewById(R.id.login_activity_screen_login_button);
		btnForgotPwd = (ButtonBold) findViewById(R.id.login_activity_screen_forgotpassword_button);
		btnCreateNewAcc = (ButtonBold) findViewById(R.id.login_activity_screen_createnewaccount_button);
		btnGooglePlus = (ButtonBold) findViewById(R.id.login_activity_screen_googlePlus_button);
		btnFacebook = (ButtonBold) findViewById(R.id.login_activity_screen_facebook_button);
		txtViewOr = (TextViewBold) findViewById(R.id.login_activity_screen_or_plaintext);
		txtViewRembMe = (TextViewBold) findViewById(R.id.login_activity_screen_rememberMe_plaintext);
		txtToolbar = (TextViewRegular) findViewById(R.id.login_activity_screen_toolbar_textView);
		unCheckedRemMeImageV = (ImageView) findViewById(R.id.login_activity_screen_rememberMe_unchecked_icon);
		checkedRemMeImageV = (ImageView) findViewById(R.id.login_activity_screen_rememberMe_checked_icon);

		strUserName = "";
		strPassword = "";

		//SET FONTS
		setCompFonts();
		//START OTHER ACTIVITIES
		logInStart();
		forgotStart();
		createNewAccStart();
		googlePlusStart();
		facebookStart();
		//REMEMBER ME METHODS
		checkedRemMeStart();
		unCheckedRemMeStart();

	}

	//----------------------------------------------------------------------------------------------------------------------
	//LOGIN COMPONENTS SETUP METHODS
	private void setCompFonts() {
		btnLogIn.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getButtonTextSize());
		btnForgotPwd.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getButtonTextSize());
		btnCreateNewAcc.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getButtonTextSize());
		btnFacebook.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getButtonTextSize());
		btnGooglePlus.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getButtonTextSize());
		txtViewOr.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getLoginOrTextSize());
		txtViewRembMe.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getLoginRememberMeTextSize());
		txtToolbar.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getToolbarTextSize());
		edtUserName.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getEditBoxTextSize());
		edtPassword.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getEditBoxTextSize());
	}

	// *************************************//
	// ---LOGIN ACTIVITY ACTION METHODS---
	// *************************************//

	// LOGIN BUTTON EVENTS
	private void logInStart() {

		btnLogIn.setOnClickListener(logInClickListener);
	}

	// FORGOT BUTTON EVENTS
	private void forgotStart() {

		btnForgotPwd.setOnClickListener(forgotPwdCreateClickListener);
	}

	// CREATE NEW ACCOUNT BUTTON EVENTS
	private void createNewAccStart() {

		btnCreateNewAcc.setOnClickListener(newUserCreateClickListener);
	}

	// GOOGLE+ BUTTON EVENTS
	private void googlePlusStart() {

		btnGooglePlus.setOnClickListener(googlePlusClickListener);
	}

	// FACEBOOK BUTTON EVENTS
	private void facebookStart() {

		btnFacebook.setOnClickListener(facebookClickListener);
	}

	private void checkedRemMeStart(){
		checkedRemMeImageV.setOnClickListener(checkedClickListener);
	}
	private void unCheckedRemMeStart(){
		unCheckedRemMeImageV.setOnClickListener(unCheckedClickListener);
	}


	// ************************//
	// ---EVENT LISTENERS---
	// ************************//

	// LOGIN BUTTON LISTENER
	OneShotOnClickListener logInClickListener = new OneShotOnClickListener(500) {

		@Override public void onOneShotClick(View v) {

			strUserName = edtUserName.getText().toString();
			strPassword = edtPassword.getText().toString();
			if (strUserName.equals("")) {
				Toast.makeText(getApplicationContext(), "Kullanıcı adınızı giriniz!", Toast.LENGTH_LONG).show();
				return;
			}
			if (strPassword.equals("")) {
				Toast.makeText(getApplicationContext(), "Şifrenizi giriniz!", Toast.LENGTH_LONG).show();
				return;
			}

			setLogInAccount(new LoginModel(strUserName, strPassword));
		}
	};

	// FORGOT BUTTON LISTENER
	OneShotOnClickListener forgotPwdCreateClickListener = new OneShotOnClickListener(500) {

		@Override public void onOneShotClick(View v) {
			launchSubActivity(ForgotPasswordActivity.class);

		}
	};

	// CREATE NEW ACCOUNT BUTTON LISTENER
	OneShotOnClickListener newUserCreateClickListener = new OneShotOnClickListener(500) {

		@Override public void onOneShotClick(View v) {

			launchSubActivity(CreateNewAccountActivity.class);
		}
	};

	// FACEBOOK BUTTON LISTENER
	OneShotOnClickListener facebookClickListener = new OneShotOnClickListener(500) {

		@Override public void onOneShotClick(View v) {

		}
	};

	// TWITTER BUTTON LISTENER
	OneShotOnClickListener googlePlusClickListener = new OneShotOnClickListener(500) {

		@Override public void onOneShotClick(View v) {
			SharedPreferences sp1=getContext().getSharedPreferences("Login", 0);

			String unm=sp1.getString("Unm", null);
			String pass = sp1.getString("Psw", null);
			Toast.makeText(getApplicationContext(), unm+" "+pass, Toast.LENGTH_LONG).show();

		}
	};
// REMEMBER ME UNCHECKED LISTENER
	OneShotOnClickListener unCheckedClickListener = new OneShotOnClickListener(500) {
	@Override public void onOneShotClick(View v) {
		unCheckedRemMeImageV.setVisibility(View.INVISIBLE);
		unCheckedRemMeImageV.setVisibility(View.GONE);
		checkedRemMeImageV.setVisibility(View.VISIBLE);

	}
};

	// REMEMBER ME UNCHECKED LISTENER
	OneShotOnClickListener checkedClickListener = new OneShotOnClickListener(500) {
		@Override public void onOneShotClick(View v) {


			checkedRemMeImageV.setVisibility(View.INVISIBLE);
			checkedRemMeImageV.setVisibility(View.GONE);
			unCheckedRemMeImageV.setVisibility(View.VISIBLE);
		}
	};

	private  void closeKeyboard()
	{
		View view = this.getCurrentFocus();
		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}
	// REMEMBER ME CHECKED LISTENER

	// *************************************//
	// ---WEB SERVICES METHODS---
	// *************************************//

	// ************************************************************************************************************************************************//

	// ****************************************//
	// ---ACCOUNT SEND DATA SERVICE METHODS----//
	// ****************************************//

	// ---LOGIN---
	protected void setLogInAccount(LoginModel loginModel) {
		AccountProcessesServiceSL accountServiceSL = new AccountProcessesServiceSL(getContext(), accountProcessesServiceListener, R.string.root_service_url);
		accountServiceSL.setLogInAccount(loginModel);
		showProgress(getString(R.string.ItbarxConnecting));
	}

	// ---SIGNUP---
	protected void setSignUpAccount(AccountSignUpModel signUpModel) {
		AccountProcessesServiceSL accountServiceSL = new AccountProcessesServiceSL(getContext(), accountProcessesServiceListener, R.string.root_service_url);
		accountServiceSL.setSignUpAccount(signUpModel);
		showProgress(getString(R.string.ItbarxConnecting));

	}

	// ---FORGOT---
	protected void setForgotAccount(AccountForgotSendMailModel accountForgotSendMailModel) {
		AccountProcessesServiceSL accountServiceSL = new AccountProcessesServiceSL(getContext(), accountProcessesServiceListener, R.string.root_service_url);
		accountServiceSL.setForgotAccount(accountForgotSendMailModel);

	}

	// ---FORGOT---
	protected void setChangePassByCodeAccount(AccountSendEmailCodeModel accountSendEmailCodeModel) {
		AccountProcessesServiceSL accountServiceSL = new AccountProcessesServiceSL(getContext(), accountProcessesServiceListener, R.string.root_service_url);
		accountServiceSL.setChangePassByCodeAccount(accountSendEmailCodeModel);

	}

	// ---GET EDIT PROFILE---
	protected void setGetEditProfile(GetEditProfileIdModel getEditProfileIdModel) {
		AccountProcessesServiceSL accountServiceSL = new AccountProcessesServiceSL(getContext(), accountProcessesServiceListener, R.string.root_service_url);
		accountServiceSL.setGetEditProfile(getEditProfileIdModel);

	}

	// ---EDIT PROFILE---
	protected void setEditProfile(EditProfileModel editProfileModel) {
		AccountProcessesServiceSL accountServiceSL = new AccountProcessesServiceSL(getContext(), accountProcessesServiceListener, R.string.root_service_url);
		accountServiceSL.setEditProfile(editProfileModel);

	}

	// --- DELETE PROFILE---
	protected void setDeleteProfile(DeleteProfileModel deleteProfileModel) {
		AccountProcessesServiceSL accountServiceSL = new AccountProcessesServiceSL(getContext(), accountProcessesServiceListener, R.string.root_service_url);
		accountServiceSL.setDeleteProfile(deleteProfileModel);
		;

	}

	// **************************************//
	// ---ACCOUNT SERVICE LISTENER METHODS---
	// **************************************//
	AccountProcessesServiceListener<String> accountProcessesServiceListener = new AccountProcessesServiceListener<String>() {

		@Override public void onError(BarxErrorModel onError) {

			dismissProgress();
			showAlert(onError.getErrMessage());
		}

		@Override public void onComplete(ResponseEventModel<String> onComplete) {
			dismissProgress();

		}

		@Override public void logInAccount(AccountGetUserByLoginInfoModel loginModelResponse) {
			dismissProgress();

			ItbarxGlobal global = ItbarxGlobal.setInstance(LoginActivity.this);
			global.setAccountModel(loginModelResponse);
			SharedPreferences sp=getSharedPreferences("Login", 0);
			SharedPreferences.Editor Ed=sp.edit();
			Ed.putString("Unm",strUserName );
			Ed.putString("Psw", strPassword);
			Ed.commit();
			closeKeyboard();
			launchSubActivity(TabContainer.class);
			// cağırmak
			// ItbarxGlobal.getInstance().getAccountModel().

		}

		@Override public void signUpAccount(AccountGetUserByLoginInfoModel loginModelResponse) {
			dismissProgress();

		}

		@Override public void forgotAccount(String ok) {
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

	};

}
