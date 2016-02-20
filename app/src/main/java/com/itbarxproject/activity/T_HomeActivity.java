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

public class T_HomeActivity extends BaseActivity implements Communicator {
	@Override protected int getLayoutResourceId() {
		return R.layout.t_home_activity;
	}

	@Override protected Context getContext() {
		return T_HomeActivity.this;
	}
	@Override protected void exceptionHandler() {
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
	}

	@Override protected void initViews() {

		//setFragments(new F_PopularFragment(T_HomeActivity.this), new F_TimelineFragment(T_HomeActivity.this));
		setFragments(F_PopularFragment.newInstance(T_HomeActivity.this), F_TimelineFragment.newInstance(T_HomeActivity.this));
	}

	//adds fragments into activity.
	protected void setFragments(Fragment fragment1, Fragment fragment2) {
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.popular1_fragment_container, fragment1, "popular");
		fragmentTransaction.add(R.id.timeline1_fragment_container, fragment2, "timeline");
		fragmentTransaction.commit();

	}

	@Override public void choose(String chosen) {
		//FragmentManager fm = getFragmentManager();
		//FragmentTransaction ft = fm.beginTransaction();
		if (chosen == Fragments.POPULAR.name()) {
			// F_TimelineFragment time = (F_TimelineFragment) fm.findFragmentById(R.id.popular1_fragment_container);
			// F_PopularFragment p = (F_PopularFragment) fm.findFragmentById(R.id.timeline1_fragment_container);
			// ft.replace(R.id.popular1_fragment_container, new F_TimelineFragment());
			// ft.remove(popular);
			// time.changeText(no);
			FrameLayout pop = (FrameLayout) this.findViewById(R.id.popular1_fragment_container);
			FrameLayout tim = (FrameLayout) this.findViewById(R.id.timeline1_fragment_container);
			pop.setVisibility(View.INVISIBLE);
			pop.setVisibility(View.GONE);
			tim.setVisibility(View.VISIBLE);
		}

		if (chosen == Fragments.TIMELINE.name()) {

			FrameLayout pop = (FrameLayout) this.findViewById(R.id.popular1_fragment_container);
			FrameLayout tim = (FrameLayout) this.findViewById(R.id.timeline1_fragment_container);
			tim.setVisibility(View.INVISIBLE);
			tim.setVisibility(View.GONE);
			pop.setVisibility(View.VISIBLE);
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
