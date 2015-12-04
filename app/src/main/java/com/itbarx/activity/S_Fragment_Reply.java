package com.itbarx.activity;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.itbarx.R;


public class S_Fragment_Reply extends Fragment {

	private BarkActivity activity;

	public S_Fragment_Reply() {
		// Required empty public constructor
	}
	public S_Fragment_Reply(BarkActivity activity) {
		this.activity = activity  ;
	}


	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_reply_screen, container, false);
	}

}
