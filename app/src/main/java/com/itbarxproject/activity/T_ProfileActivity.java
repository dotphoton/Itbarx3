package com.itbarxproject.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.itbarxproject.R;
import com.itbarxproject.enums.Fragments;
import com.itbarxproject.exception.ExceptionHandler;

/**
 * TODO: Add a class header comment!
 */
public class T_ProfileActivity extends BaseActivity implements Communicator {

	// String followerCount;

	@Override protected int getLayoutResourceId() {
		return R.layout.t_profile_activity;
	}

	@Override protected Context getContext() {
		return T_ProfileActivity.this;
	}

	@Override public void onAttachFragment(Fragment fragment) {
		super.onAttachFragment(fragment);

	}
	F_ProfileFragment f_profileFragment =null;
	public static boolean isEditProfilChange = false;

	public static  int EDIT_PROFIL_REQUEST_CODE =111;

	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}
	@Override protected void exceptionHandler() {
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
	}

	@Override protected void initViews() {

		f_profileFragment = F_ProfileFragment.newInstance(T_ProfileActivity.this);
		setFragment(f_profileFragment);

	}

	protected void setFragment(Fragment fragment1) {
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.profile1_fragment_container, fragment1, "profile");
		fragmentTransaction.commit();
	}
	FrameLayout prof=null;
	@Override public void choose(String chosen) {
		if (chosen == Fragments.PROFILE.name()) {
			prof = (FrameLayout) this.findViewById(R.id.profile1_fragment_container);
			FrameLayout edit = (FrameLayout) this.findViewById(R.id.edit_profile1_fragment_container);
			prof.setVisibility(View.INVISIBLE);
			prof.setVisibility(View.GONE);
			edit.setVisibility(View.VISIBLE);
		}

	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			areYouExitApp();
			return  true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onActivityResult(int correlationId, int resultCode, Intent data) {

		if(EDIT_PROFIL_REQUEST_CODE==correlationId)
		{
			if(resultCode == Activity.RESULT_OK){

				if(f_profileFragment!=null)
				{
					f_profileFragment.setImgUserPhoto();

					f_profileFragment.getUserWallInfoModel(f_profileFragment.sendUserWallInfoModel());

				}
			}

		}


	}
}
