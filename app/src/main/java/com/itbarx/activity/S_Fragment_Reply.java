package com.itbarx.activity;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ListView;

import com.itbarx.R;

import com.itbarx.adapter.ReplyFragmentListAdapter;
import com.itbarx.model.send_to_fragment.ReplyData;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class S_Fragment_Reply extends Fragment {

	private BarkActivity activity;
	private ArrayList<ReplyData> dataList;
	private static final String KEY_REPLY_DATA_LIST;
	@Bind(R.id.reply_side_fragment_listView) ListView listView;

	static {
		KEY_REPLY_DATA_LIST = "KeyReplyData";
	}

	public S_Fragment_Reply() {
		// Required empty public constructor
	}
	public S_Fragment_Reply(BarkActivity activity) {
		this.activity = activity  ;
	}


	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_reply_screen, container, false);
		ButterKnife.bind(this, view);
		return view;
	}
	@Override public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		dataList = new ArrayList<>();
		if(null!=getArguments()) {
			dataList = getArguments().getParcelableArrayList(KEY_REPLY_DATA_LIST);
			Log.d("fragment datalist size:", dataList.size() + "");
		}

		listView.setAdapter(new ReplyFragmentListAdapter(activity, dataList));
	}
}
