package com.itbarxproject.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
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

	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}
	@Override protected void exceptionHandler() {
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
	}

	@Override protected void initViews() {
		setFragment(F_ProfileFragment.newInstance(T_ProfileActivity.this),  F_EditProfileFragment.newInstance(T_ProfileActivity.this));

	}

	protected void setFragment(Fragment fragment1, Fragment fragment2) {
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.profile1_fragment_container, fragment1, "profile");
		fragmentTransaction.add(R.id.edit_profile1_fragment_container, fragment2, "editProfile");
		fragmentTransaction.commit();
	}

	@Override public void choose(String chosen) {
		if (chosen == Fragments.PROFILE.name()) {
			FrameLayout prof = (FrameLayout) this.findViewById(R.id.profile1_fragment_container);
			FrameLayout edit = (FrameLayout) this.findViewById(R.id.edit_profile1_fragment_container);
			prof.setVisibility(View.INVISIBLE);
			prof.setVisibility(View.GONE);
			edit.setVisibility(View.VISIBLE);
		}

		if (chosen == Fragments.EDIT_PROFILE.name()) {

			FrameLayout prof = (FrameLayout) this.findViewById(R.id.popular1_fragment_container);
			FrameLayout edit = (FrameLayout) this.findViewById(R.id.timeline1_fragment_container);
			edit.setVisibility(View.INVISIBLE);
			edit.setVisibility(View.GONE);
			prof.setVisibility(View.VISIBLE);
		}

	}
}
