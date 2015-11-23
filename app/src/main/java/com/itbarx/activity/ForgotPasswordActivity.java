package com.itbarx.activity;

import com.itbarx.R;
import com.itbarx.application.ItbarxGlobal;
import com.itbarx.error.common.ResponseServiceModel;

import com.itbarx.custom.component.ButtonBold;
import com.itbarx.custom.component.EditTextRegular;
import com.itbarx.custom.component.TextViewBold;
import com.itbarx.custom.component.TextViewRegular;
import com.itbarx.error.json.BarxErrorModelParser;
import com.itbarx.error.model.BarxErrorModel;
import com.itbarx.listener.AccountProcessesServiceListener;
import com.itbarx.listener.OneShotOnClickListener;
import com.itbarx.model.account.AccountForgotSendMailModel;
import com.itbarx.model.account.AccountGetUserByLoginInfoModel;
import com.itbarx.model.account.AccountSendEmailCodeModel;
import com.itbarx.model.account.EditProfileModel;
import com.itbarx.model.account.GetEditProfileModel;
import com.itbarx.sl.AccountProcessesServiceSL;
import com.itbarx.utils.TextSizeUtil;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.util.regex.Pattern;

public class ForgotPasswordActivity extends BaseActivity {

	private TextViewRegular txtToolbar, txtInfo;
	private TextViewBold txtChangePass;
	private EditTextRegular edtEmailCode, edtEmailAddress, edtNewPassword, edtRePassword;
	private ButtonBold btnSendEmailCode, btnChange;
	AccountProcessesServiceSL activityProcessesServiceSL;

	@Override protected int getLayoutResourceId() {

		return R.layout.activity_forgot_password_screen;
	}

	@Override protected Context getContext() {

		return ForgotPasswordActivity.this;
	}

	@Override protected void initViews() {
		txtToolbar = (TextViewRegular) findViewById(R.id.forgot_password_activity_screen_toolbar_textView);
		edtEmailCode = (EditTextRegular) findViewById(R.id.forgot_password_activity_screen_eMailCode_editText);
		edtEmailAddress = (EditTextRegular) findViewById(R.id.forgot_password_activity_screen_eMailAddress_editText);
		edtNewPassword = (EditTextRegular) findViewById(R.id.forgot_password_activity_screen_newPassword_editText);
		edtRePassword = (EditTextRegular) findViewById(R.id.forgot_password_activity_screen_rePassword_editText);
		btnSendEmailCode = (ButtonBold) findViewById(R.id.forgot_password_activity_screen_sendMe_eMailCode_button);
		btnChange = (ButtonBold) findViewById(R.id.forgot_password_activity_screen_change_button);
		txtChangePass = (TextViewBold) findViewById(R.id.forgot_password_activity_screen_change_password_text);
		txtInfo = (TextViewRegular) findViewById(R.id.forgot_password_activity_screen_info_textView);
		setCompText();
		btnSendEmailCode.setOnClickListener(sendCodeButtonClickListener);

	}

	private void setCompText() {

		btnSendEmailCode.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getButtonTextSize());
		btnChange.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getButtonTextSize());
		txtInfo.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getForgotUpperInfoTextSize());
		txtToolbar.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getToolbarTextSize());
		txtChangePass.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getForgotChangePasswordTextSize());

	}

	//CHANGE PASSWORD
	OneShotOnClickListener changePasswordClickListener = new OneShotOnClickListener(500) {
		@Override public void onOneShotClick(View v) {
			String codeRegex = "_{1}?([a-z0-9]+(%))+";
			String inputEmailCode = edtEmailCode.getText().toString();
			Boolean matches = Pattern.matches(codeRegex, inputEmailCode);
			if (inputEmailCode.equals("") || !matches) {
				Toast toast = Toast.makeText(getApplicationContext(), "Lütfen e-posta adresinize gönderilen kodu giriniz.", Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				return;
			}
			String inputPassword = edtNewPassword.getText().toString();
			String inputRePassword = edtRePassword.getText().toString();

			if ((inputPassword.equals("") || inputRePassword.equals("")) || (!inputPassword.equals(inputRePassword))) {
				Toast toast = Toast.makeText(getApplicationContext(), "Girdiğiniz şifreler eşleşmiyor.", Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				return;

			}
			getNewPassWithLogin(sendEmailCodeModel(inputEmailCode, inputPassword, inputRePassword));
		}

	};

	private AccountSendEmailCodeModel sendEmailCodeModel(String code, String pass, String rePass) {
		return new AccountSendEmailCodeModel(code, pass, rePass);
	}

	private void getNewPassWithLogin(AccountSendEmailCodeModel accountSendEmailCodeModel) {
		activityProcessesServiceSL = new AccountProcessesServiceSL(this.getContext(), accountProcessesServiceListener, R.string.root_service_url);
		activityProcessesServiceSL.setChangePassByCodeAccount(accountSendEmailCodeModel);
		this.showProgress(getString(R.string.ItbarxConnecting));
	}

	//SEND EMAIL CODE
	OneShotOnClickListener sendCodeButtonClickListener = new OneShotOnClickListener(500) {
		@Override public void onOneShotClick(View v) {

			String emailRegex = "[a-zA-Z0-9]+(?:(\\.|_)[A-Za-z0-9!#$%&'*+/=?^`{|}~-]+)*@(?!([a-zA-Z0-9]*\\.[a-zA-Z0-9]*\\.[a-zA-Z0-9]*\\.))(?:[A-Za-z0-9](?:[a-zA-Z0-9-]*[A-Za-z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?";
			String inputEmail = edtEmailAddress.getText().toString();
			boolean matches = Pattern.matches(emailRegex, inputEmail);

			if (!inputEmail.equals("") && matches) {

				getMailRespone(sendRequestEmailCodeModel(inputEmail));

			} else {

				Toast toast = Toast.makeText(getApplicationContext(), "Lütfen kayıt olduğunuz e-posta adresini giriniz.", Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
			}

			return;
			// String response=getForgotAccResponse();

		}
	};

	private AccountForgotSendMailModel sendRequestEmailCodeModel(String email) {
		return new AccountForgotSendMailModel(email);
	}

	private void getMailRespone(AccountForgotSendMailModel accountForgotSendMailModel) {
		activityProcessesServiceSL = new AccountProcessesServiceSL(this.getContext(), accountProcessesServiceListener, R.string.root_service_url);
		activityProcessesServiceSL.setForgotAccount(accountForgotSendMailModel);
		this.showProgress(getString(R.string.ItbarxConnecting));
	}

	private String getForgotAccResponse(String response) {
		return response;
	}

	AccountProcessesServiceListener accountProcessesServiceListener = new AccountProcessesServiceListener() {
		@Override public void logInAccount(AccountGetUserByLoginInfoModel loginModelResponse) {
			dismissProgress();
		}

		@Override public void signUpAccount(AccountGetUserByLoginInfoModel loginModelResponse) {
			dismissProgress();
		}

		@Override public void forgotAccount(String forgotResponse) {
			dismissProgress();
			//getForgotAccResponse(forgotResponse);
			if (null == forgotResponse && !forgotResponse.equalsIgnoreCase("true")) {
				Toast toast = Toast.makeText(getApplicationContext(), "Bağlantı hatası, lütfen tekrar deneyiniz.", Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				return;
			} else {
				Toast toast = Toast.makeText(getApplicationContext(), "Değişiklik kodu mail adresinize gönderilmiştir.", Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				return;
			}
		}

		@Override public void changePassByCode(AccountGetUserByLoginInfoModel loginModelResponse) {
			dismissProgress();
			if (null == loginModelResponse) {
				Toast toast = Toast.makeText(getApplicationContext(), "Bağlantı hatası, lütfen tekrar deneyiniz.", Toast.LENGTH_LONG);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				return;
			} else {
				ItbarxGlobal global = ItbarxGlobal.setInstance(ForgotPasswordActivity.this);
				global.setAccountModel(loginModelResponse);
				launchSubActivity(TabContainer.class);

			}
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

		@Override public void onComplete(ResponseServiceModel onComplete) {
			dismissProgress();
		}

		@Override public void onError(BarxErrorModel onError) {
			dismissProgress();
		}
	};

}
