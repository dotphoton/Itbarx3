package com.itbarxproject.activity;

import com.itbarxproject.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class F_FollowersFragment extends Fragment {

	// FiveTabFragmentsContainerActivity mainActivity;

	public F_FollowersFragment() {

	}

	/*
	 * public F_FollowersFragment(FiveTabFragmentsContainerActivity container) { mainActivity = container; }
	 */
	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.fragment_followers_screen, container, false);
		return rootView;
	}

}