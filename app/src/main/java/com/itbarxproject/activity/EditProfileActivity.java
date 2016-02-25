package com.itbarxproject.activity;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.itbarxproject.R;
import com.itbarxproject.application.ItbarxGlobal;
import com.itbarxproject.common.LoadHttpImage;
import com.itbarxproject.common.UploadHttpImage;
import com.itbarxproject.common.UserSharedPrefrences;
import com.itbarxproject.custom.component.ButtonBold;
import com.itbarxproject.custom.component.EditTextRegular;
import com.itbarxproject.custom.component.TextViewBold;
import com.itbarxproject.custom.component.TextViewRegular;
import com.itbarxproject.listener.AccountProcessesServiceListener;
import com.itbarxproject.listener.OneShotOnClickListener;
import com.itbarxproject.model.account.AccountGetUserByLoginInfoModel;
import com.itbarxproject.model.account.AccountSignUpModel;
import com.itbarxproject.model.account.EditProfileModel;
import com.itbarxproject.model.account.GetEditProfileIdModel;
import com.itbarxproject.model.account.GetEditProfileModel;
import com.itbarxproject.service.ResponseEventModel;
import com.itbarxproject.service.error.BarxErrorModel;
import com.itbarxproject.sl.AccountProcessesServiceSL;
import com.itbarxproject.utils.TextSizeUtil;

public class EditProfileActivity extends BaseActivity {

	UploadHttpImage uploadHttpImage;
	private int IMAGE_UPLOAD_REQUEST_CODE = 111;
	private String DECODE_BASE64_IMAGE = null;

	private static float PX_HEIGHT;
	private static int HEIGHT_BTN_EDT_TOOL;
	private static int UNIT_TEN;
	private static int UNIT_VIEW;

	static {

		PX_HEIGHT = ItbarxGlobal.getDisplayPxHeight();
		float tablessHeight = PX_HEIGHT - (PX_HEIGHT / 10);
		float height = tablessHeight / 10.6f;
		HEIGHT_BTN_EDT_TOOL = Math.round(height);
		UNIT_TEN = Math.round((height / 5));
		UNIT_VIEW = Math.round((height / 3));
	}


	private LinearLayout layoutToolbar, layoutInfoEdtBoxes, layoutSaveBtn, layoutImage, layoutPasswordEditBoxes;
	private RelativeLayout relBasicInfo, relChangePassword, relAboutMe, relAboutMeEditBoxes, relNotification, relDeletAcc;
	private View vBottomDelete, vBottomSaveBtn, vBottomNotification, vUpperNotification;
	private TextViewRegular txtToolbar, txtDeleteAcc,txtNotification;
	private TextViewBold txtBasicInfo, txtChangePass, txtAboutMe, txtChangePhoto;
	private EditTextRegular nameEdtTxt, userNameEdtTxt, locationEdtTxt, webSiteEdtTxt, eMailEdtTxt, oldPassEdtTxt, newPassEdtTxt, rePassEdtTxt, aboutMeEdtTxt;
	private ButtonBold btnSave;
	private ImageView imgBtnLogOut,imgUserPhoto;

	private AccountGetUserByLoginInfoModel logonModel;





	private  void setImgUserPhoto()
	{

		if (logonModel.getUserProfilePhoto() != null && logonModel.getUserProfilePhoto().length() > 0) {
			new LoadHttpImage(imgUserPhoto).execute(logonModel.getUserProfilePhoto());
		}
	}

//Set text size of the components
	private void setTextSize() {
		txtToolbar.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getToolbarTextSize());
		nameEdtTxt.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getEditBoxTextSize());
		userNameEdtTxt.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getEditBoxTextSize());
		locationEdtTxt.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getEditBoxTextSize());
		webSiteEdtTxt.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getEditBoxTextSize());
		eMailEdtTxt.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getEditBoxTextSize());
		oldPassEdtTxt.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getEditBoxTextSize());
		newPassEdtTxt.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getEditBoxTextSize());
		rePassEdtTxt.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getEditBoxTextSize());
		aboutMeEdtTxt.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getEditBoxTextSize());
		btnSave.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getButtonTextSize());
		txtChangePhoto.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getEditProfileChangePhotoTextSize());
		txtDeleteAcc.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getEditProfileNotifDeleteTextSize());
		txtNotification.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getEditProfileNotifDeleteTextSize());
		txtBasicInfo.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getEditProfileHeadingTextSize());
		txtChangePass.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getEditProfileHeadingTextSize());
		txtAboutMe.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getEditProfileHeadingTextSize());

	}

	//Set height of the components.
	private void setHeight() {
		layoutToolbar.getLayoutParams().height = HEIGHT_BTN_EDT_TOOL;
		layoutInfoEdtBoxes.getLayoutParams().height = HEIGHT_BTN_EDT_TOOL * 5;
		layoutPasswordEditBoxes.getLayoutParams().height = HEIGHT_BTN_EDT_TOOL * 3;
		layoutSaveBtn.getLayoutParams().height = HEIGHT_BTN_EDT_TOOL;
		layoutImage.getLayoutParams().height = HEIGHT_BTN_EDT_TOOL * 2;
		relBasicInfo.getLayoutParams().height = UNIT_TEN * 3;
		relChangePassword.getLayoutParams().height = UNIT_TEN * 3;
		relAboutMe.getLayoutParams().height = UNIT_TEN * 3;
		relAboutMeEditBoxes.getLayoutParams().height = UNIT_TEN * 12;
		relNotification.getLayoutParams().height = HEIGHT_BTN_EDT_TOOL;
		relDeletAcc.getLayoutParams().height = HEIGHT_BTN_EDT_TOOL;
		vBottomDelete.getLayoutParams().height = UNIT_VIEW;
		vBottomSaveBtn.getLayoutParams().height = UNIT_VIEW;
		vBottomNotification.getLayoutParams().height = UNIT_VIEW;
		vUpperNotification.getLayoutParams().height = UNIT_VIEW;
	}

	OneShotOnClickListener logOutClickListener = new OneShotOnClickListener(500) {
		@Override
		public void onOneShotClick(View v) {

			areYoulogOffAccount();
		}
	};


	@Override
	protected int getLayoutResourceId() {
		return R.layout.fragment_edit_profile_screen;
	}

	@Override
	protected Context getContext() {
		return EditProfileActivity.this;
	}
ScrollView scrollView;
	@Override
	protected void initViews() {
		logonModel = ItbarxGlobal.getInstance().getAccountModel();
		scrollView = (ScrollView)findViewById(R.id.edit_profile1_fragment_container_scrollview);
		layoutToolbar = (LinearLayout) findViewById(R.id.edit_profile_fragment_toolbar_layout);
		layoutInfoEdtBoxes = (LinearLayout) findViewById(R.id.edit_profile_fragment_screen_infoEditBoxes_layout);
		layoutPasswordEditBoxes = (LinearLayout) findViewById(R.id.edit_profile_fragment_screen_passwordEditBoxes_layout);
		layoutSaveBtn = (LinearLayout) findViewById(R.id.edit_profile_fragment_screen_saveButton_layout);
		layoutImage = (LinearLayout) findViewById(R.id.edit_profile_fragment_screen_image_layout);
		relBasicInfo = (RelativeLayout) findViewById(R.id.edit_profile_fragment_screen_basicInfoText_layout);
		relChangePassword = (RelativeLayout) findViewById(R.id.edit_profile_fragment_screen_changePasswordText_layout);
		relAboutMe = (RelativeLayout) findViewById(R.id.edit_profile_fragment_screen_aboutMeText_layout);
		relAboutMeEditBoxes = (RelativeLayout) findViewById(R.id.edit_profile_fragment_screen_aboutMeEditBox_layout);
		relNotification = (RelativeLayout) findViewById(R.id.edit_profile_fragment_screen_notification_layout);
		relDeletAcc = (RelativeLayout) findViewById(R.id.edit_profile_fragment_screen_deleteAccount_layout);
		vBottomDelete = findViewById(R.id.edit_profile_fragment_screen_bottomDeleteAccount_View);
		vBottomSaveBtn = findViewById(R.id.edit_profile_fragment_screen_bottomSavebutton_View);
		vBottomNotification = findViewById(R.id.edit_profile_fragment_screen_bottomNotification_View);
		vUpperNotification = findViewById(R.id.edit_profile_fragment_screen_upperNotification_View);
		txtToolbar = (TextViewRegular) findViewById(R.id.edit_profile_fragment_screen_toolbar_textView);
		txtDeleteAcc = (TextViewRegular) findViewById(R.id.edit_profile_fragment_screen_notification_TextView);
		txtNotification = (TextViewRegular) findViewById(R.id.edit_profile_fragment_screen_deleteAccount_TextView);
		txtBasicInfo = (TextViewBold) findViewById(R.id.edit_profile_fragment_screen_basicInfo_TextView);
		txtChangePhoto = (TextViewBold) findViewById(R.id.edit_profile_fragment_screen_changePhoto_TextView);
		txtChangePass = (TextViewBold) findViewById(R.id.edit_profile_fragment_screen_changePassword_TextView);
		txtAboutMe = (TextViewBold) findViewById(R.id.edit_profile_fragment_screen_aboutMe_TextView);
		nameEdtTxt = (EditTextRegular)  findViewById(R.id.edit_profile_fragment_screen_name_editText);
		userNameEdtTxt = (EditTextRegular)  findViewById(R.id.edit_profile_fragment_screen_userName_editText);
		locationEdtTxt = (EditTextRegular)  findViewById(R.id.edit_profile_fragment_screen_location_editText);
		webSiteEdtTxt = (EditTextRegular)  findViewById(R.id.edit_profile_fragment_screen_yourWebsite_editText);
		eMailEdtTxt = (EditTextRegular)  findViewById(R.id.edit_profile_fragment_screen_eMail_editText);
		oldPassEdtTxt = (EditTextRegular)  findViewById(R.id.edit_profile_fragment_screen_oldPassword_editText);
		newPassEdtTxt = (EditTextRegular)  findViewById(R.id.edit_profile_fragment_screen_newPassword_editText);
		rePassEdtTxt = (EditTextRegular)  findViewById(R.id.edit_profile_fragment_screen_rePassword_editText);
		aboutMeEdtTxt = (EditTextRegular)  findViewById(R.id.edit_profile_fragment_screen_aboutMe_editText);
		btnSave = (ButtonBold)  findViewById(R.id.edit_profile_fragment_screen_save_button);
		imgBtnLogOut = (ImageView) findViewById(R.id.edit_profile_fragment_screen_logOut_imageView);
		setHeight();
		setTextSize();
		Log.d("Tag", "Getting Height >> " + layoutToolbar.getLayoutParams().height);
		Log.d("Tag", "Getting Height * 5  >> " + layoutInfoEdtBoxes.getLayoutParams().height);
		imgBtnLogOut.setOnClickListener(logOutClickListener);
		imgUserPhoto =(ImageView)findViewById(R.id.edit_profile_fragment_screen_user_photo_imageView);
		imgUserPhoto.setOnClickListener(new OneShotOnClickListener(500) {
			@Override
			public void onOneShotClick(View v) {
				uploadHttpImage.openImgageGallery(IMAGE_UPLOAD_REQUEST_CODE);
			}
		});
		setImgUserPhoto();
		scrollView.smoothScrollTo(0, 0);
		closeKeyboard();
		//For image Upload
		uploadHttpImage = new UploadHttpImage(getContext());


		if(logonModel.getItBarxUserName()!=null &&!logonModel.getItBarxUserName().equalsIgnoreCase("") )
		{
			userNameEdtTxt.setText(logonModel.getItBarxUserName());
		}

		if(logonModel.getName()!=null &&!logonModel.getName().equalsIgnoreCase("") )
		{
			nameEdtTxt.setText(logonModel.getName());
		}
		if(logonModel.getUserEmail()!=null &&!logonModel.getUserEmail().equalsIgnoreCase("") )
		{
			eMailEdtTxt.setText(logonModel.getUserEmail());
		}
		if(logonModel.getUserwebsite()!=null &&!logonModel.getUserwebsite().equalsIgnoreCase("") )
		{
			eMailEdtTxt.setText(logonModel.getUserwebsite());
		}
		if(logonModel.getUserBio()!=null &&!logonModel.getUserBio().equalsIgnoreCase("") )
		{
			aboutMeEdtTxt.setText(logonModel.getUserBio());
		}
	btnSave.setOnClickListener(new OneShotOnClickListener(500) {
		@Override
		public void onOneShotClick(View v) {
			setEditProfilRequest();
		}
	});
		getEditProfilFromWs();
	}

	@Override
	protected void exceptionHandler() {

	}
/*
{
UserId:"10028",
Name :"",
UserName : "",
Location : "",
WebSite : "",
OldPassword : "",
NewPassword : "",
ConfirmPassword : "",
OldPhotoUrl : "",
NewPhotoBytes : "",
IsNotificationActive : "",
UserBio : ""
}

 */
	protected void setEditProfilUI(EditProfileModel model) {


		if(model.getUserName()!=null)
		{
			userNameEdtTxt.setText(model.getUserName());
		}
		if(model.getName()!=null)
		{
			nameEdtTxt.setText(model.getName());
		}
		if(model.getUserBio()!=null)
		{
			aboutMeEdtTxt.setText(model.getUserBio());
		}
		if(model.getWebSite()!=null)
		{
			webSiteEdtTxt.setText(model.getWebSite());
		}
		if(model.getWebSite()!=null)
		{
			webSiteEdtTxt.setText(model.getWebSite());
		}


			//epm.setNewPhotoBase64String(DECODE_BASE64_IMAGE);


	}



	protected void setEditProfilRequest() {

		EditProfileModel epm = new EditProfileModel();
		epm.setUserName(userNameEdtTxt.getText().toString());
		epm.setUserBio(aboutMeEdtTxt.getText().toString());
		epm.setWebSite(webSiteEdtTxt.getText().toString());
		epm.setName(nameEdtTxt.getText().toString());
		epm.setConfirmPassword(rePassEdtTxt.getText().toString());
		epm.setNewPassword(newPassEdtTxt.getText().toString());
		epm.setUserId(logonModel.getUserID());
		epm.setOldPassword(logonModel.getUserPassword());

		if(DECODE_BASE64_IMAGE!=null)
		{
			epm.setNewPhotoBase64String(DECODE_BASE64_IMAGE);
		}
		//epm.setConfirmPassword();

		AccountProcessesServiceSL accountServiceSL = new AccountProcessesServiceSL(getContext(), accountProcessesServiceListener, R.string.root_service_url);
		accountServiceSL.setEditProfile(epm);
		showProgress(getString(R.string.ItbarxConnecting));

	}
	protected void getEditProfilFromWs() {
		AccountProcessesServiceSL accountServiceSL = new AccountProcessesServiceSL(getContext(), accountProcessesServiceListener, R.string.root_service_url);
		accountServiceSL.setGetEditProfile(new GetEditProfileIdModel(logonModel.getUserID()));
		showProgress(getString(R.string.ItbarxConnecting));

	}
	AccountProcessesServiceListener accountProcessesServiceListener = new AccountProcessesServiceListener(){

		@Override
		public void onComplete(ResponseEventModel onComplete) {

		}

		@Override
		public void onError(BarxErrorModel onError) {
			dismissProgress();
			showAlert(onError.getErrMessage());
		}

		@Override
		public void logInAccount(AccountGetUserByLoginInfoModel loginModelResponse) {

		}

		@Override
		public void signUpAccount(AccountGetUserByLoginInfoModel loginModelResponse) {

		}

		@Override
		public void forgotAccount(String forgotResponse) {

		}

		@Override
		public void changePassByCode(AccountGetUserByLoginInfoModel loginModelResponse) {

		}

		@Override
		public void getEditProfileAccount(GetEditProfileModel getEditProfileModel) {
			dismissProgress();
			if(getEditProfileModel.getUserBio()!=null &&!getEditProfileModel.getUserBio().equalsIgnoreCase("") )
			{
				aboutMeEdtTxt.setText(getEditProfileModel.getUserBio());
			}
			if(getEditProfileModel.getUserProfilePhoto()!=null &&!getEditProfileModel.getUserProfilePhoto().equalsIgnoreCase("") )
			{
				setImgUserPhoto(getEditProfileModel.getUserProfilePhoto());
			}
		}

		@Override
		public void editProfileAccount(EditProfileModel editProfileModel) {
			dismissProgress();

			if(editProfileModel.getData()!=null&&!editProfileModel.getData().equalsIgnoreCase(""))
			{
				getEditProfilFromWs();
			}
		}

		@Override
		public void deleteProfileAccount(String isDeleted) {

		}
	};
	private  void setImgUserPhoto(String imageUrl)
	{
		if (imageUrl != null && imageUrl.length() > 0) {
			new LoadHttpImage(imgUserPhoto).execute(imageUrl);
		}
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == IMAGE_UPLOAD_REQUEST_CODE && resultCode == RESULT_OK) {

			uploadHttpImage.setPath(data);
			DECODE_BASE64_IMAGE = uploadHttpImage.getImageBase64Decoded(imgUserPhoto);
			if (DECODE_BASE64_IMAGE != null) {
				//imgUserPhoto.setVisibility(View.VISIBLE);
				//txtAddPhoto.setText("");
				//UserSharedPrefrences.saveUserPhoto(getContext(), DECODE_BASE64_IMAGE);
			}
		}
	}
}
