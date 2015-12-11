package com.itbarx.activity;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.itbarx.R;
import com.itbarx.adapter.LikeFragmentGridAdapter;
import com.itbarx.model.send_to_fragment.LikeData;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class S_Fragment_Like extends Fragment {
	private BarkActivity activity;
	private  ArrayList<LikeData> dataList;
	private static final String KEY_LIKE_DATA_LIST;
	@Bind(R.id.like_fragment_screen_gridView)	 GridView gridView;
//private GridView gridView;
	static {
		KEY_LIKE_DATA_LIST = "KeyLikeData";
	}

	public S_Fragment_Like() {
		// Required empty public constructor
	}

	public static S_Fragment_Like newInstance(BarkActivity activity) {
		S_Fragment_Like myFragment = new S_Fragment_Like();
		myFragment.activity = activity;
		return myFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {        // Inflate the layout for this fragment

		View view =inflater.inflate(R.layout.fragment_like_screen, container, false);
		ButterKnife.bind(this, view);
		return view;
	}

	@Override public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		dataList = new ArrayList<>();
		if(null!=getArguments()) {
			dataList = getArguments().getParcelableArrayList(KEY_LIKE_DATA_LIST);
			Log.d("fragment datalist size:", dataList.size() + "");
		}
		//gridView =(GridView)activity.findViewById(R.id.like_fragment_screen_gridView);
		gridView.setAdapter(new LikeFragmentGridAdapter(activity,dataList));
	}
}