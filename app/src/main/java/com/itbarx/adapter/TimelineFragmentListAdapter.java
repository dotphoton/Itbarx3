package com.itbarx.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.itbarx.R;
import com.itbarx.activity.BarkActivity;
import com.itbarx.activity.BaseActivity;
import com.itbarx.custom.component.TextViewBold;
import com.itbarx.custom.component.TextViewListItemReg;
import com.itbarx.custom.component.TextViewRegular;
import com.itbarx.listener.OneShotOnClickListener;
import com.itbarx.model.post.PostTimelineListForUserModel;
import com.itbarx.utils.BarkUtility;
import com.itbarx.utils.TextSizeUtil;



import java.util.List;

public class TimelineFragmentListAdapter extends BaseAdapter {

	BaseActivity activity;
	Context context;
	List<PostTimelineListForUserModel> list;


	public TimelineFragmentListAdapter(BaseActivity activity, List<PostTimelineListForUserModel> postTimelineListForUserModels) {
		this.activity = activity;
		this.context =(Context)activity;
		this.list = postTimelineListForUserModels;
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

		convertView = layoutInflater.inflate(R.layout.row_fragment_timeline_screen_item, parent, false);
		//user name
		TextViewBold txtFullname = (TextViewBold) convertView.findViewById(R.id.row_fragment_timeline_screen_fullName_TextView);
		txtFullname.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getTimelineUsernameTextSize());
		//post time info
		TextViewRegular txtTimeInfo = (TextViewRegular) convertView.findViewById(R.id.row_fragment_timeline_screen_time_info_TextView);
		txtTimeInfo.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getTimelineMinsTextSize());
		//text to speech
		TextViewListItemReg txtSubs = (TextViewListItemReg) convertView.findViewById(R.id.row_fragment_timeline_screen_subtitles_TextView);
		//add like count
		TextViewBold txtLike = (TextViewBold) convertView.findViewById(R.id.row_fragment_timeline_screen_like_TextView);
		txtLike.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getTimelineMiniButtonTextSize());
		//add rebark / share count
		TextViewBold txtReBark = (TextViewBold) convertView.findViewById(R.id.row_fragment_timeline_screen_rebark_TextView);
		txtReBark.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getTimelineMiniButtonTextSize());
		//add reply count
		TextViewBold txtReply = (TextViewBold) convertView.findViewById(R.id.row_fragment_timeline_screen_reply_TextView);
		txtReply.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getTimelineMiniButtonTextSize());
		//video image view
		//   MediaController controller = new MediaController(context);
		ImageView videoPlayImg = (ImageView) convertView.findViewById(R.id.row_fragment_timeline_screen_video_thumbnail_play_ImageView);

		// VideoView video = (VideoView) convertView.findViewById(R.id.row_fragment_timeline_screen_VideoView);

		PostTimelineListForUserModel model = (PostTimelineListForUserModel) getItem(position);
		if (model != null) {
			//small user photo
			ImageView imgPhoto = (ImageView) convertView.findViewById(R.id.row_fragment_timeline_screen_user_imageView);

			if (model.getPostOwnerPhoto() != null && !model.getPostOwnerPhoto().equalsIgnoreCase("")) {
				//photo yu al
			}
			//user name
			txtFullname.setText(model.getPostOwner());
			//post time info
			String addedDate = model.getAddedDate().replace("T", " ").substring(0, model.getAddedDate().indexOf("."));
			txtTimeInfo.setText(addedDate + " " + " eklendi.");
			//posted video

			videoPlayImg.setTag(model.getPostID());
			videoPlayImg.setOnClickListener(playClickListener);

			if (model.getIsAdultContent().equalsIgnoreCase("false") && model.getPostURL() != null && !model.getPostURL().equalsIgnoreCase("") && model.getIsDeleted().equalsIgnoreCase("false")) {
				Uri uri = Uri.parse("http://itbarxapp.azurewebsites.net" + model.getPostURL());
				//    video.setVideoURI(uri);
				//   video.start();

			} else {
				//  Uri uri = Uri.parse("android.resource://" + ItbarxGlobal.getInstance().getPackageName() + "/" + R.raw.sample);
				//  video.setVideoURI(uri);
				//  video.start();
			}
			//text to speech
			txtSubs.setText((model.getPostSpeechToText() != null) ? model.getPostSpeechToText() : txtSubs.getText());

			//add like count
			txtLike.setText((model.getPostLikeCount() != null && !model.getPostLikeCount().equals("")) ? model.getPostLikeCount() : "0");

			//add rebark / share count
			txtReBark.setText((model.getPostShareCount() != null && !model.getPostShareCount().equals("")) ? model.getPostShareCount() : "0");

			//add reply count
			txtReply.setText((model.getPostReplyCount() != null && !model.getPostReplyCount().equals("")) ? model.getPostReplyCount() : "0");

		}
		return convertView;
	}

	OneShotOnClickListener playClickListener = new OneShotOnClickListener(500) {
		@Override public void onOneShotClick(View v) {

			String postId = v.getTag().toString();
			BarkUtility.goBarkDetail(activity,postId);
		}
/*
			PostTimelineListForUserModel model = null;
            if (v.getTag()!=null) {
                ImageView img = (ImageView)v;
                model=(PostTimelineListForUserModel)img.getTag();
                barkStarter.start(true);
            }
        }*/
	};
}
