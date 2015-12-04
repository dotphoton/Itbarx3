package com.itbarx.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.itbarx.R;
import com.itbarx.adapter.ReBarkFragmentGridAdapter;
import com.itbarx.model.send_to_fragment.LikeData;
import com.itbarx.model.send_to_fragment.ReBarksData;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class S_Fragment_ReBark extends Fragment {
	private BarkActivity activity;
	private ArrayList<ReBarksData> dataList;
	private static final String KEY_REBARK_DATA_LIST;
	@Bind(R.id.reBark_fragment_screen_gridView) GridView gridView;

	static {
		KEY_REBARK_DATA_LIST = "KeyReBarkData";
	}

	public S_Fragment_ReBark() {

	}
	public S_Fragment_ReBark(BarkActivity activity) {
this.activity = activity;
	}


	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view= inflater.inflate(R.layout.fragment_rebark_screen, container, false);
		ButterKnife.bind(this, view);
		return view;
	}

	@Override public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		dataList = new ArrayList<>();
		if(null!=getArguments()) {
			dataList = getArguments().getParcelableArrayList(KEY_REBARK_DATA_LIST);
			Log.d("fragment datalist size:", dataList.size() + "");
		}

		gridView.setAdapter(new ReBarkFragmentGridAdapter(activity, dataList));
	}
}
