package com.itbarxproject.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.itbarxproject.R;
import com.itbarxproject.enums.Fragments;
import com.itbarxproject.exception.ExceptionHandler;

public class T_SecondActivity extends BaseActivity implements Communicator{
	@Override protected int getLayoutResourceId() {
		return R.layout.t_second_activity;
	}

	@Override protected Context getContext() {
		return T_SecondActivity.this;
	}
	@Override protected void exceptionHandler() {
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
	}
	@Override protected void initViews() {

		setFragments(F_ActivityFragment.newInstance(T_SecondActivity.this), F_RequestFragment.newInstance(T_SecondActivity.this));
	}

	//adds fragments into activity.
	protected void setFragments(Fragment fragment1, Fragment fragment2) {
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.activity1_fragment_container, fragment1, "activity");
		fragmentTransaction.add(R.id.request1_fragment_container, fragment2, "request");
		fragmentTransaction.commit();
	}

	@Override public void choose(String chosen) {

		if (chosen == Fragments.ACTIVITY.name()) {

			FrameLayout act = (FrameLayout) this.findViewById(R.id.activity1_fragment_container);
			FrameLayout req = (FrameLayout) this.findViewById(R.id.request1_fragment_container);
			act.setVisibility(View.INVISIBLE);
			act.setVisibility(View.GONE);
			req.setVisibility(View.VISIBLE);
		}

		if (chosen == Fragments.REQUEST.name()) {

			FrameLayout act = (FrameLayout) this.findViewById(R.id.activity1_fragment_container);
			FrameLayout req = (FrameLayout) this.findViewById(R.id.request1_fragment_container);
			req.setVisibility(View.INVISIBLE);
			req.setVisibility(View.GONE);
			act.setVisibility(View.VISIBLE);
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


}
