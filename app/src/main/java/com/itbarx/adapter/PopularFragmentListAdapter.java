package com.itbarx.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.itbarx.R;

import com.itbarx.activity.BaseActivity;
import com.itbarx.application.ItbarxGlobal;
import com.itbarx.common.LoadHttpImage;
import com.itbarx.custom.component.TextViewListItemReg;
import com.itbarx.custom.component.TextViewRegular;
import com.itbarx.listener.OneShotOnClickListener;
import com.itbarx.model.post.PostPopularPostListModel;
import com.itbarx.utils.BarkUtility;

import java.util.List;

public class PopularFragmentListAdapter extends BaseAdapter {

	Context context;
	BaseActivity activity;
	List<PostPopularPostListModel> list;
	private ImageView imgPlayIcon;
	private ImageView imgThumbnail;

	public PopularFragmentListAdapter(BaseActivity activity, List<PostPopularPostListModel> models) {
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


		LayoutInflater	layoutInflater = (LayoutInflater) context.getSystemService(Context
					.LAYOUT_INFLATER_SERVICE);


			convertView = layoutInflater.inflate(R.layout.row_fragment_popular_screen_item, parent, false);
			PostPopularPostListModel model = (PostPopularPostListModel) getItem(position);

//row_fragment_popular_screen_thumbnail

			if (model != null) {
				TextViewListItemReg text = (TextViewListItemReg) convertView.findViewById(R.id
						.row_fragment_popular_screen_subtitle_textView);
				imgPlayIcon = (ImageView) convertView.findViewById(R.id.row_fragment_popular_screen_userPlayIcon_videoView);
				imgPlayIcon.setOnClickListener(choosenImageClickListener);
				imgThumbnail = (ImageView) convertView.findViewById(R.id.row_fragment_popular_screen_thumbnail);
				if (model.getPostSpeechToText().toString().length() > 0) {
					((TextViewListItemReg) convertView.findViewById(R.id
							.row_fragment_popular_screen_subtitle_textView)).setText(model.getPostSpeechToText());
				} else {
					text.setText(" ...");
				}
				imgPlayIcon.setTag(model.getPostID());
				if (model.getPostPictureURL() != null && model.getPostPictureURL().length() > 0) {
					new LoadHttpImage(imgThumbnail).execute(model.getPostPictureURL());
				} else {
					imgThumbnail.setImageResource(R.drawable.thumbnail);//add
				}
			}


		return convertView;
	}

	OneShotOnClickListener choosenImageClickListener = new OneShotOnClickListener(500) {
		@Override public void onOneShotClick(View v) {
			String postId = v.getTag().toString();
			BarkUtility.goBarkDetail(activity, postId);
		}
	};





}