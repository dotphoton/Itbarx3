package com.itbarxproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.itbarxproject.R;
import com.itbarxproject.activity.BaseActivity;

import com.itbarxproject.custom.component.TextViewRegular;
import com.itbarxproject.model.send_to_fragment.LikeData;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LikeFragmentGridAdapter extends BaseAdapter {


	private Context context;
	private ArrayList<LikeData> models=null;


	static int index = 1;

	public LikeFragmentGridAdapter(BaseActivity activity, ArrayList<LikeData> models){

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
		LikeData model = (LikeData) getItem(position);

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

/*
	private static class ViewHolder {
		public  final TextViewGridItemBold txtName;
		public final TextViewGridItemBold txtSurname;
		public final ImageView imgAddIcon;
		public final ImageView imgOkIcon;
		public final ImageView imgProfilePhoto;
		public ViewHolder(TextViewGridItemBold bananaView, TextViewGridItemBold phoneView,ImageView imgAddIcon,ImageView imgOkIcon, ImageView imgProfilePhoto) {
			this.txtName = bananaView;
			this.txtSurname = phoneView;
			this.imgAddIcon =imgAddIcon;
			this.imgOkIcon = imgOkIcon;
			this.imgProfilePhoto = imgProfilePhoto;
		}
	}
	*/
}


