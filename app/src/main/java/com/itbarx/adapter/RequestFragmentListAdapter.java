package com.itbarx.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.itbarx.R;
import com.itbarx.activity.BaseActivity;
import com.itbarx.custom.component.TextViewBold;
import com.itbarx.custom.component.TextViewListItemBold;
import com.itbarx.listener.OneShotOnClickListener;
import com.itbarx.model.follow.PendingListByFollowingIdModel;
import com.itbarx.utils.BarkUtility;

import java.util.List;

public class RequestFragmentListAdapter extends BaseAdapter {

	Context context;
	BaseActivity activity;
	List<PendingListByFollowingIdModel> list;

	public RequestFragmentListAdapter(BaseActivity activity, List<PendingListByFollowingIdModel> models) {
		this.context = activity;
		this.activity =activity;
		list = models;
	}

	@Override public int getCount() {
		return list.size();
	}

	@Override public Object getItem(int position) {
		return list.get(position);
	}

	@Override public long getItemId(int position) {
		return position;
	}

	@Override public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = layoutInflater.inflate(R.layout.row_fragment_request_screen_item, parent, false);
		PendingListByFollowingIdModel model = (PendingListByFollowingIdModel) getItem(position);
		if (null != model) {

			LinearLayout layOut =(LinearLayout)convertView.findViewById(R.id.row_fragment_request_screen_clickable_layout);
			//1. take user photo
			ImageView imgPhoto = (ImageView) convertView.findViewById(R.id.row_fragment_request_screen_user_imageView);
			if (model.getUserProfilePhoto() != null && !model.getUserProfilePhoto().equalsIgnoreCase("")) {
				//photo yu al
			}
			//2.  take user name
			TextViewListItemBold txtFullname = (TextViewListItemBold) convertView.findViewById(R.id.row_fragment_request_screen_fullName_textView);
			txtFullname.setText(model.getItBarxUserName());

			//3. take follow status of the user
			ImageView imgBtnFollow = (ImageView) convertView.findViewById(R.id.row_fragment_request_screen_follow_imageButton);
			if (model.getUserFollowStatus().equalsIgnoreCase("true")) {
				imgBtnFollow.setImageResource(R.drawable.req_icon_follow);
			} else {
				imgBtnFollow.setImageResource(R.drawable.req_icon_unfollow);
			}
			layOut.setOnClickListener(openProfileClickListener);
			if (!model.getUserID().equals("")){

				layOut.setTag(model.getUserID());
			}
		}

		return convertView;
	}

	OneShotOnClickListener openProfileClickListener = new OneShotOnClickListener(500) {
		@Override
		public void onOneShotClick(View v) {
			String makerUserId = v.getTag().toString();
			Log.d("TEST", "TEST " + makerUserId);
			BarkUtility.goProfileScreen(activity, makerUserId);

		}
	};
}
