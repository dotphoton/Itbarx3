package com.itbarxproject.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.itbarxproject.R;
import com.itbarxproject.application.ItbarxGlobal;
import com.itbarxproject.enums.Fragments;
import com.itbarxproject.exception.ExceptionHandler;

public class T_RecordActivity extends BaseActivity implements Communicator {

    /*
	public interface OnResumeListener {
        public void onResume ();
    }
*/

	public String lastFragmentName;

	@Override protected int getLayoutResourceId() {
		return R.layout.t_record_activity;
	}

	@Override protected Context getContext() {
		return T_RecordActivity.this;
	}
	@Override protected void exceptionHandler() {
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
	}

	@Override protected void initViews() {
		setFragments(F_RecordStartFragment.newInstance(T_RecordActivity.this));
	}

	//adds fragments into activity.
	protected void setFragments(Fragment fragment1) {
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(R.id.start1_fragment_container, fragment1, "start");
		// fragmentTransaction.add(R.id.recording1_fragment_container, fragment2, "recording");
		fragmentTransaction.commit();
	}

	@Override public void choose(String chosen) {

		if (chosen == Fragments.START.name()) {

			lastFragmentName = Fragments.START.name();
			FrameLayout start = (FrameLayout) this.findViewById(R.id.start1_fragment_container);
			//FrameLayout record = (FrameLayout) this.findViewById(R.id.recording1_fragment_container);
			//record.setVisibility(View.INVISIBLE);
			//record.setVisibility(View.GONE);
			start.setVisibility(View.VISIBLE);
		}

		if (chosen == Fragments.RECORDING.name()) {

			lastFragmentName = Fragments.RECORDING.name();

            /*FrameLayout start = (FrameLayout) this.findViewById(R.id.start1_fragment_container);
            FrameLayout record = (FrameLayout) this.findViewById(R.id.recording1_fragment_container);
            start.setVisibility(View.INVISIBLE);
            start.setVisibility(View.GONE);
            record.setVisibility(View.VISIBLE);
            */
		}

	}

	/*
	@Override public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {

			if (lastFragmentName == Fragments.RECORDING.name()) {
				choose(Fragments.START.name());
			}

		}
		return true;
	}
	*/
	@Override
	public void onActivityResult(int correlationId, int resultCode, Intent data) {
		if(resultCode==111)
		{
			ItbarxGlobal.getInstance().tabHost.setCurrentTab(0);

			//recordStartFragment.initPreview(null);
			//choose(Fragments.START.name());
		}
		super.onActivityResult(correlationId, resultCode, data);
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
