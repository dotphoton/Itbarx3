package com.itbarxproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.itbarxproject.R;
import com.itbarxproject.activity.BaseActivity;
import com.itbarxproject.common.LoadHttpImage;
import com.itbarxproject.custom.component.TextViewGridItemBold;
import com.itbarxproject.listener.OneShotOnClickListener;
import com.itbarxproject.model.follow.FollowerListByFollowingIdModel;
import com.itbarxproject.utils.BarkUtility;

import java.util.List;

/**
 * TODO: Add a class header comment!
 */
public class SearchFragmentGridAdapter extends BaseAdapter {
private	Context context;
private	BaseActivity activity;
private	List<FollowerListByFollowingIdModel> list = null;
private	TextViewGridItemBold txtName, txtSurname;
	private ImageView imgAddIcon, imgOkIcon, imgProfilePhoto;

	static int index = 1;

	public SearchFragmentGridAdapter(BaseActivity activity, List<FollowerListByFollowingIdModel> models) {
		this.activity = activity;
		this.context = (Context) activity;
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
		convertView = layoutInflater.inflate(R.layout.column_fragment_friends_search_screen_item, parent, false);

		FollowerListByFollowingIdModel model = (FollowerListByFollowingIdModel) getItem(position);

		if (null != model) {

			txtName = (TextViewGridItemBold) convertView.findViewById(R.id.column_fragment_search_screen_name_textView);
			txtSurname = (TextViewGridItemBold) convertView.findViewById(R.id.column_fragment_search_screen_surname_textView);
			imgAddIcon = (ImageView) convertView.findViewById(R.id.column_fragment_search_screen_addIcon_imageView);
			imgOkIcon = (ImageView) convertView.findViewById(R.id.column_fragment_search_screen_okIcon_imageView);
			imgProfilePhoto = (ImageView) convertView.findViewById(R.id.column_fragment_search_screen_userProfile_imageView);
			imgProfilePhoto.setTag( model.getUserID());
			imgProfilePhoto.setOnClickListener(openUserProfileClickListener);
			txtName.setText((model.getItBarxUserName() == null || model.getItBarxUserName().equals("")) ? txtName.getText().toString() : model.getItBarxUserName());
			txtSurname.setText((model.getName() == null || model.getName().equals("")) ? txtSurname.getText().toString() : model.getName());

			String strIsYourFollower = model.getIsYourFollower().toString();
			String isYourFollower = (null != strIsYourFollower && (strIsYourFollower.equals("1") || strIsYourFollower.equals("0"))) ? strIsYourFollower : "";
			if (isYourFollower.equals("1")) {
				imgAddIcon.setVisibility(View.GONE);
				imgOkIcon.setVisibility(View.VISIBLE);

			}
			if (isYourFollower.equals("0")) {
				imgAddIcon.setVisibility(View.VISIBLE);
				imgOkIcon.setVisibility(View.GONE);

			}
/*
			if ((index % 2) == 0) {
				imgProfilePhoto.setImageResource(R.drawable.profile_b);
				index++;
			} else {
				imgProfilePhoto.setImageResource(R.drawable.profile_a);
				index++;
			}
*/
			if (model.getUserProfilePhoto() != null && !model.getUserProfilePhoto().equalsIgnoreCase("")) {
				//photo yu al
				new LoadHttpImage(imgProfilePhoto).execute(model.getUserProfilePhoto());
			}
			/*
            int i=0;
            FollowerListByFollowingIdModel[] modelArray=(FollowerListByFollowingIdModel[])list.toArray();
            while (modelArray.length>i){
            if(i/2==0){
            imgProfilePhoto.setImageResource(R.drawable.profile_a);
                i++;
            }
            else {
                imgProfilePhoto.setImageResource(R.drawable.profile_b);
                i++;
            }}
*/

		}
		return convertView;
	}

	OneShotOnClickListener openUserProfileClickListener = new OneShotOnClickListener(500) {
		@Override public void onOneShotClick(View v) {
			String userId = v.getTag().toString();
			BarkUtility.goProfileScreen(activity, userId);



		}

	};









}
