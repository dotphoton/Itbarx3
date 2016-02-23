package com.itbarxproject.activity;

import com.itbarxproject.R;
import com.itbarxproject.application.ItbarxGlobal;
import com.itbarxproject.common.Base64Util;
import com.itbarxproject.common.UploadHttpImage;
import com.itbarxproject.common.UserSharedPrefrences;
import com.itbarxproject.custom.component.ButtonBold;
import com.itbarxproject.custom.component.EditTextRegular;
import com.itbarxproject.custom.component.TextViewBold;
import com.itbarxproject.custom.component.TextViewRegular;
import com.itbarxproject.exception.ExceptionHandler;
import com.itbarxproject.service.ResponseEventModel;
import com.itbarxproject.service.error.BarxErrorModel;
import com.itbarxproject.listener.AccountProcessesServiceListener;
import com.itbarxproject.listener.OneShotOnClickListener;
import com.itbarxproject.model.account.AccountGetUserByLoginInfoModel;
import com.itbarxproject.model.account.AccountSignUpModel;
import com.itbarxproject.model.account.EditProfileModel;
import com.itbarxproject.model.account.GetEditProfileModel;
import com.itbarxproject.sl.AccountProcessesServiceSL;
import com.itbarxproject.utils.TextSizeUtil;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.util.Base64;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class CreateNewAccountActivity extends BaseActivity implements Communicator {

	// ************************//
	// ---ATTRIBUTES---
	// ************************//
	private TextViewRegular txtToolbar, txtAccept, txtUserAgreement, txtRememberMe;
	private EditTextRegular edtUserName, edtEmail, edtPassword, edtRePassword;
	private ButtonBold btnDone, btnTwitter, btnFacebook;
	private TextViewBold txtViewOr, txtAddPhoto;
	private ImageView rememberMeIcon;
	private RelativeLayout addPhotoRelativeLayout;
	private ImageView imageViewPhoto, imgViewRemovePhoto;

	private boolean isAgreement = false;

	UploadHttpImage uploadHttpImage;
	private int IMAGE_UPLOAD_REQUEST_CODE = 111;
	private String DECODE_BASE64_IMAGE = null;


	@Override
	protected int getLayoutResourceId() {
		// TODO Auto-generated method stub
		return R.layout.activity_create_new_account_screen;
	}

	@Override
	protected Context getContext() {
		// TODO Auto-generated method stub
		return CreateNewAccountActivity.this;
	}

	@Override
	protected void exceptionHandler() {
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		UserSharedPrefrences.saveUserPhoto(getContext(),"");
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onResume() {
		super.onResume();
		String value = getIntent().getStringExtra("USER_AGREEMENT");
		if (value != null && value.equals("ACCEPT")) {
			((ImageView) findViewById(R.id.create_new_account_activity_screen_agreeToAgreement_imageView)).setImageResource(R.drawable.req_icon_request_ok);
			isAgreement = true;

		DECODE_BASE64_IMAGE = UserSharedPrefrences.getUserPhoto(getContext());
			if(DECODE_BASE64_IMAGE!=null && !DECODE_BASE64_IMAGE.equalsIgnoreCase(""))
			{
				imgViewRemovePhoto.setVisibility(View.VISIBLE);
				txtAddPhoto.setText("");

				Bitmap bitmap = Base64Util.getDecodedImageFile(DECODE_BASE64_IMAGE);
				imageViewPhoto.setImageBitmap(bitmap);


			}
			else
			{
				imgViewRemovePhoto.setVisibility(View.INVISIBLE);
				txtAddPhoto.setText(getString(R.string.createnewaccount_activity_screen_addphoto_text));
			}
		} else {
			isAgreement = false;
			((ImageView) findViewById(R.id.create_new_account_activity_screen_agreeToAgreement_imageView)).setImageResource(R.drawable.icon_user_agreement_not_checked);
		}
	}

	@Override
	protected void initViews() {

		rememberMeIcon = (ImageView) findViewById(R.id.create_new_account_activity_screen_agreeToAgreement_imageView);


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
		imageViewPhoto = (ImageView) findViewById(R.id.activity_createnewaccount_screen_addphoto_image);
		addPhotoRelativeLayout = (RelativeLayout) findViewById(R.id.activity_createnewaccount_screen_addphoto_relative);
		addPhotoRelativeLayout.setOnClickListener(new OneShotOnClickListener(500) {
			@Override
			public void onOneShotClick(View v) {

				uploadHttpImage.openImgageGallery(IMAGE_UPLOAD_REQUEST_CODE);
			}
		});
		imgViewRemovePhoto = (ImageView) findViewById(R.id.activity_createnewaccount_screen_removephoto_image);
		imgViewRemovePhoto.setOnClickListener(new OneShotOnClickListener(500) {
			@Override
			public void onOneShotClick(View v) {
				imageViewPhoto.setImageBitmap(null);
				DECODE_BASE64_IMAGE = null;
				imgViewRemovePhoto.setVisibility(View.INVISIBLE);
				txtAddPhoto.setText(getString(R.string.createnewaccount_activity_screen_addphoto_text));
			}
		});

		setCompText();
		signUp();

		//For image Upload
		uploadHttpImage = new UploadHttpImage(getContext());

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


	OneShotOnClickListener openUserAgreementClickListener = new OneShotOnClickListener(500) {
		@Override
		public void onOneShotClick(View v) {

			launchSubActivity(UserAgreementActivity.class);
			//finish();

		}
	};

	// DONE BUTTON EVENTS

	private void signUp() {

		btnDone.setOnClickListener(newUserClickListener);
	}

	// DONE BUTTON LISTENER
	OneShotOnClickListener newUserClickListener = new OneShotOnClickListener(500) {

		@Override
		public void onOneShotClick(View v) {

			if (!isAgreement) {
				showAlert("Kullanıcı kaydınızın tamamlanması için kullanıcı şartlarımızı kabul etmenizi rica ederiz");
				return;
			}
			String strUserName = "";
			String strEmail = "";
			String strPassword = "";
			String strRePassword = "";

			if (edtUserName.getText().toString().length() == 0 || edtEmail.getText().toString().length() == 0
					|| edtPassword.getText().toString().length() == 0 || edtRePassword.getText().toString().length() == 0) {
				showAlert("Lütfen gerekli alanları boş geçmeyiniz!");
				return;
			}

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
				setSignUpAccount(new AccountSignUpModel(strUserName, strPassword, strRePassword, strEmail, DECODE_BASE64_IMAGE == null ? "" : DECODE_BASE64_IMAGE));

				// Toast.makeText(getApplicationContext(), strUserName + " " + strPassword, Toast.LENGTH_LONG).show();

			}
		}

	};

		// ---SIGNUP---
		protected void setSignUpAccount(AccountSignUpModel signUpModel) {
			AccountProcessesServiceSL accountServiceSL = new AccountProcessesServiceSL(getContext(), accountProcessesServiceListener, R.string.root_service_url);
			accountServiceSL.setSignUpAccount(signUpModel);
			showProgress(getString(R.string.ItbarxConnecting));

		}

		AccountProcessesServiceListener accountProcessesServiceListener = new AccountProcessesServiceListener() {
			@Override
			public void logInAccount(AccountGetUserByLoginInfoModel loginModelResponse) {
				dismissProgress();
			}

			@Override
			public void signUpAccount(AccountGetUserByLoginInfoModel loginModelResponse) {
				dismissProgress();
				ItbarxGlobal global = ItbarxGlobal.setInstance(CreateNewAccountActivity.this);
				global.setAccountModel(loginModelResponse);
				ItbarxGlobal.setUserId(loginModelResponse.getUserID());
				UserSharedPrefrences.saveEmail(getContext(), loginModelResponse.getUserEmail());
				UserSharedPrefrences.saveUserName(getContext(), loginModelResponse.getItBarxUserName());
				UserSharedPrefrences.savePassword(getContext(), loginModelResponse.getUserPassword());
				UserSharedPrefrences.saveLogIn(getContext());

				UserSharedPrefrences.saveLogOnModel(getContext(), loginModelResponse);
				closeKeyboard();
				launchSubActivity(TabContainer.class);

			}

			@Override
			public void forgotAccount(String forgotResponse) {
				dismissProgress();
			}

			@Override
			public void changePassByCode(AccountGetUserByLoginInfoModel loginModelResponse) {
				dismissProgress();
			}

			@Override
			public void getEditProfileAccount(GetEditProfileModel getEditProfileModel) {
				dismissProgress();
			}

			@Override
			public void editProfileAccount(EditProfileModel editProfileModel) {
				dismissProgress();
			}

			@Override
			public void deleteProfileAccount(String isDeleted) {
				dismissProgress();
			}

			@Override
			public void onComplete(ResponseEventModel onComplete) {
				dismissProgress();
			}

			@Override
			public void onError(BarxErrorModel onError) {
				dismissProgress();
				showAlert(onError.getErrMessage());
			}
		};

		@Override
		public void choose(String chosen) {

		}

		@Override
		public void onActivityResult(int requestCode, int resultCode, Intent data) {

			if (requestCode == IMAGE_UPLOAD_REQUEST_CODE && resultCode == RESULT_OK) {

				uploadHttpImage.setPath(data);
				DECODE_BASE64_IMAGE = uploadHttpImage.getImageBase64Decoded(imageViewPhoto);
				if (DECODE_BASE64_IMAGE != null) {
					imgViewRemovePhoto.setVisibility(View.VISIBLE);
					txtAddPhoto.setText("");
					UserSharedPrefrences.saveUserPhoto(getContext(),DECODE_BASE64_IMAGE);
				}
			}
		}
	}
