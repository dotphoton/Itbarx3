package com.itbarx.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.itbarx.R;
import com.itbarx.activity.BaseActivity;
import com.itbarx.custom.component.TextViewGridItemBold;
import com.itbarx.custom.component.TextViewRegular;
import com.itbarx.model.follow.FollowerListByFollowingIdModel;
import com.itbarx.model.send_to_fragment.LikeData;
import com.itbarx.model.send_to_fragment.ReBarksData;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ReBarkFragmentGridAdapter   extends BaseAdapter {


	private Context context;
	private ArrayList<ReBarksData> models=null;


	static int index = 1;

	public ReBarkFragmentGridAdapter(BaseActivity activity, ArrayList<ReBarksData> models){

		this.context =  activity;
		this.models=models;

	}

	@Override public int getCount() {
		return models.size();
	}

	@Override public Object getItem(int position) {
		return models.get(position);
	}

	@Override public long getItemId(int position) {
		return position;
	}

	@Override public View getView(int position, View convertView, ViewGroup parent) {

		ViewHolder holder;
		if (convertView != null) {
			holder = (ViewHolder) convertView.getTag();
		} else {
			LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = layoutInflater.inflate(R.layout.column_like_screen_item, parent, false);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}
		ReBarksData model = (ReBarksData) getItem(position);

		holder.txtName.setText((model.getItBarxUserName() == null || model.getItBarxUserName().equals("")) ? holder.txtName.getText().toString() :  WordUtils.capitalize(model.getItBarxUserName()));
		holder.txtSurname.setText((model.getName() == null || model.getName().equals("")) ? holder.txtSurname.getText().toString(): WordUtils.capitalize(model.getName()).substring(0,1)+"." );
		String strAreYouFollowing = model.getAreYouFollowing();
		String areYouFollowing = (null != strAreYouFollowing && (strAreYouFollowing.equals("true") || strAreYouFollowing.equals("false"))) ? strAreYouFollowing : "";


		if (areYouFollowing.equals("true")) {
			holder.imgAddIcon.setVisibility(View.GONE);
			holder.imgOkIcon.setVisibility(View.VISIBLE);

		}
		if (areYouFollowing.equals("false")) {
			holder.imgAddIcon.setVisibility(View.VISIBLE);
			holder.imgOkIcon.setVisibility(View.GONE);

		}

		if ((index % 2) == 0) {
			holder.imgProfilePhoto.setImageResource(R.drawable.profile_b);
			index++;
		} else {
			holder.imgProfilePhoto.setImageResource(R.drawable.profile_a);
			index++;
		}

		return convertView;
	}



	static class ViewHolder {
		@Bind(R.id.column_like_name_textView) TextViewRegular txtName;
		@Bind(R.id.column_like_surname_textView) TextViewRegular txtSurname;
		@Bind(R.id.column_like_addIcon_imageView) ImageView imgAddIcon;
		@Bind(R.id.column_like_okIcon_imageView) ImageView imgOkIcon;
		@Bind(R.id.column_like_userProfile_imageView) ImageView imgProfilePhoto;

		public ViewHolder(View view) {
			ButterKnife.bind(this, view);
		}
	}


}
