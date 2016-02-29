package com.itbarxproject.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.itbarxproject.R;
import com.itbarxproject.activity.BaseActivity;
import com.itbarxproject.activity.T_SecondActivity;
import com.itbarxproject.common.LoadHttpImage;
import com.itbarxproject.custom.component.TextViewListItemBold;
import com.itbarxproject.custom.component.TextViewListItemReg;
import com.itbarxproject.listener.OneShotOnClickListener;
import com.itbarxproject.model.activity.ActivityListModel;
import com.itbarxproject.utils.BarkUtility;

import java.util.List;

/**
 * Created by Photon on 31.08.2015.
 */
public class ActivityFragmentListAdapter extends BaseAdapter {

	Context context;
	List<ActivityListModel> list;
	RelativeLayout relClickable;
	T_SecondActivity activity;

	public ActivityFragmentListAdapter(BaseActivity activity, List<ActivityListModel> models) {
		context = activity;
		this.activity =(T_SecondActivity)activity;
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

		convertView = layoutInflater.inflate(R.layout.row_fragment_acvtivity_screen_activities_item, parent, false);
		ActivityListModel model = (ActivityListModel) getItem(position);
		if (model != null) {
			ImageView imgPhoto = (ImageView) convertView.findViewById(R.id.row_fragment_activity_screen_activities_imageView);
			if (model.getUserProfilePhoto() != null && !model.getUserProfilePhoto().equalsIgnoreCase("")) {
				//photo yu al
				new LoadHttpImage(imgPhoto).execute(model.getUserProfilePhoto());
			}

			 relClickable = (RelativeLayout) convertView.findViewById(R.id.row_fragment_activity_screen_clickable_layout);
			TextViewListItemBold txtFullname = (TextViewListItemBold) convertView.findViewById(R.id.row_fragment_activity_screen_activities_fullName_textView);
			txtFullname.setText(model.getItBarxUserName());

			TextViewListItemReg txtAction = (TextViewListItemReg) convertView.findViewById(R.id.row_fragment_activity_screen_activities_action_textView);
			txtAction.setText(model.getActivityText().replace(model.getItBarxUserName(), ""));


			relClickable.setOnClickListener(openProfilDetailClickListener);
			if (model!=null&&!model.getMakerUserId().equalsIgnoreCase(""))
			{
				relClickable.setTag(model.getMakerUserId());

			}
		}
		return convertView;
	}




	OneShotOnClickListener openProfilDetailClickListener = new OneShotOnClickListener(500) {
		@Override
		public void onOneShotClick(View v) {
			relClickable.setBackgroundColor(Color.argb(0, 242, 242, 242));
			//activity.launchSubActivity(OtherUserActivity.class);
			String makerUserId = v.getTag().toString();
			Log.d("TEST", "TEST " + makerUserId);
			BarkUtility.goProfileScreen(activity, makerUserId);


		}
	};


}