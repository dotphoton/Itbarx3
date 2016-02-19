package com.itbarxproject.activity;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;

import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.itbarxproject.R;
import com.itbarxproject.application.ItbarxGlobal;
import com.itbarxproject.custom.component.ButtonBold;
import com.itbarxproject.custom.component.EditTextRegular;
import com.itbarxproject.custom.component.TextViewBold;
import com.itbarxproject.custom.component.TextViewRegular;
import com.itbarxproject.listener.OneShotOnClickListener;
import com.itbarxproject.utils.TextSizeUtil;

public class F_EditProfileFragment extends Fragment {

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

	private T_ProfileActivity t_profileActivity;
	private LinearLayout layoutToolbar, layoutInfoEdtBoxes, layoutSaveBtn, layoutImage, layoutPasswordEditBoxes;
	private RelativeLayout relBasicInfo, relChangePassword, relAboutMe, relAboutMeEditBoxes, relNotification, relDeletAcc;
	private View vBottomDelete, vBottomSaveBtn, vBottomNotification, vUpperNotification;
	private TextViewRegular txtToolbar, txtDeleteAcc,txtNotification;
	private TextViewBold txtBasicInfo, txtChangePass, txtAboutMe, txtChangePhoto;
	private EditTextRegular nameEdtTxt, userNameEdtTxt, locationEdtTxt, webSiteEdtTxt, eMailEdtTxt, oldPassEdtTxt, newPassEdtTxt, rePassEdtTxt, aboutMeEdtTxt;
	private ButtonBold btnSave;
	private ImageView imgBtnLogOut;


	public F_EditProfileFragment() {
	}

	public static F_EditProfileFragment newInstance(T_ProfileActivity t_profileActivity) {
		F_EditProfileFragment myFragment = new F_EditProfileFragment();
		myFragment.t_profileActivity = t_profileActivity;
		return myFragment;
	}

	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_edit_profile_screen, container, false);


	}

	@Override public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		layoutToolbar = (LinearLayout) t_profileActivity.findViewById(R.id.edit_profile_fragment_toolbar_layout);
		layoutInfoEdtBoxes = (LinearLayout) t_profileActivity.findViewById(R.id.edit_profile_fragment_screen_infoEditBoxes_layout);
		layoutPasswordEditBoxes = (LinearLayout) t_profileActivity.findViewById(R.id.edit_profile_fragment_screen_passwordEditBoxes_layout);
		layoutSaveBtn = (LinearLayout) t_profileActivity.findViewById(R.id.edit_profile_fragment_screen_saveButton_layout);
		layoutImage = (LinearLayout) t_profileActivity.findViewById(R.id.edit_profile_fragment_screen_image_layout);
		relBasicInfo = (RelativeLayout) t_profileActivity.findViewById(R.id.edit_profile_fragment_screen_basicInfoText_layout);
		relChangePassword = (RelativeLayout) t_profileActivity.findViewById(R.id.edit_profile_fragment_screen_changePasswordText_layout);
		relAboutMe = (RelativeLayout) t_profileActivity.findViewById(R.id.edit_profile_fragment_screen_aboutMeText_layout);
		relAboutMeEditBoxes = (RelativeLayout) t_profileActivity.findViewById(R.id.edit_profile_fragment_screen_aboutMeEditBox_layout);
		relNotification = (RelativeLayout) t_profileActivity.findViewById(R.id.edit_profile_fragment_screen_notification_layout);
		relDeletAcc = (RelativeLayout) t_profileActivity.findViewById(R.id.edit_profile_fragment_screen_deleteAccount_layout);
		vBottomDelete = t_profileActivity.findViewById(R.id.edit_profile_fragment_screen_bottomDeleteAccount_View);
		vBottomSaveBtn = t_profileActivity.findViewById(R.id.edit_profile_fragment_screen_bottomSavebutton_View);
		vBottomNotification = t_profileActivity.findViewById(R.id.edit_profile_fragment_screen_bottomNotification_View);
		vUpperNotification = t_profileActivity.findViewById(R.id.edit_profile_fragment_screen_upperNotification_View);
		txtToolbar = (TextViewRegular) t_profileActivity.findViewById(R.id.edit_profile_fragment_screen_toolbar_textView);
		txtDeleteAcc = (TextViewRegular) t_profileActivity.findViewById(R.id.edit_profile_fragment_screen_notification_TextView);
		txtNotification = (TextViewRegular) t_profileActivity.findViewById(R.id.edit_profile_fragment_screen_deleteAccount_TextView);
		txtBasicInfo = (TextViewBold) t_profileActivity.findViewById(R.id.edit_profile_fragment_screen_basicInfo_TextView);
		txtChangePhoto = (TextViewBold) t_profileActivity.findViewById(R.id.edit_profile_fragment_screen_changePhoto_TextView);
		txtChangePass = (TextViewBold) t_profileActivity.findViewById(R.id.edit_profile_fragment_screen_changePassword_TextView);
		txtAboutMe = (TextViewBold) t_profileActivity.findViewById(R.id.edit_profile_fragment_screen_aboutMe_TextView);
		nameEdtTxt = (EditTextRegular)  t_profileActivity.findViewById(R.id.edit_profile_fragment_screen_name_editText);
		userNameEdtTxt = (EditTextRegular)  t_profileActivity.findViewById(R.id.edit_profile_fragment_screen_userName_editText);
		locationEdtTxt = (EditTextRegular)  t_profileActivity.findViewById(R.id.edit_profile_fragment_screen_location_editText);
		webSiteEdtTxt = (EditTextRegular)  t_profileActivity.findViewById(R.id.edit_profile_fragment_screen_yourWebsite_editText);
		eMailEdtTxt = (EditTextRegular)  t_profileActivity.findViewById(R.id.edit_profile_fragment_screen_eMail_editText);
		oldPassEdtTxt = (EditTextRegular)  t_profileActivity.findViewById(R.id.edit_profile_fragment_screen_oldPassword_editText);
		newPassEdtTxt = (EditTextRegular)  t_profileActivity.findViewById(R.id.edit_profile_fragment_screen_newPassword_editText);
		rePassEdtTxt = (EditTextRegular)  t_profileActivity.findViewById(R.id.edit_profile_fragment_screen_rePassword_editText);
		aboutMeEdtTxt = (EditTextRegular)  t_profileActivity.findViewById(R.id.edit_profile_fragment_screen_aboutMe_editText);
		btnSave = (ButtonBold)  t_profileActivity.findViewById(R.id.edit_profile_fragment_screen_save_button);
		imgBtnLogOut = (ImageView) t_profileActivity.findViewById(R.id.edit_profile_fragment_screen_logOut_imageView);
		setHeight();
		setTextSize();
		Log.d("Tag", "Getting Height >> " + layoutToolbar.getLayoutParams().height);
		Log.d("Tag", "Getting Height * 5  >> " + layoutInfoEdtBoxes.getLayoutParams().height);
		imgBtnLogOut.setOnClickListener(logOutClickListener);

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

			createAndShowAlertDialog();
		}
	};
	private void createAndShowAlertDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("My Title");
		builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				//TODO
				dialog.dismiss();
			/*
					if (LoginActivity.isConnect()){
					LoginActivity.disConnect();

				}
				*/
			}
		});
		builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				//TODO
				dialog.dismiss();
			}
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}

}
