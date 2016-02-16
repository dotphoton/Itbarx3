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
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context
				.LAYOUT_INFLATER_SERVICE);
		convertView = layoutInflater.inflate(R.layout.row_fragment_popular_screen_item, parent, false);
		TextViewListItemReg text = (TextViewListItemReg) convertView.findViewById(R.id
				.row_fragment_popular_screen_subtitle_textView);

		PostPopularPostListModel model = (PostPopularPostListModel) getItem(position);
		imgPlayIcon =(ImageView) convertView.findViewById(R.id.row_fragment_popular_screen_userPlayIcon_videoView);
		imgPlayIcon.setOnClickListener(choosenImageClickListener);


		if (model != null) {
			if(model.getPostSpeechToText().toString().length()>0)
			{
				text.setText(model.getPostSpeechToText());
			}
			else {
				text.setText(" ...");
			}
			imgPlayIcon.setTag(model.getPostID());




			// ImageView video = (ImageView) convertView.findViewById(R.id.row_fragment_popular_screen_user_videoView);

			// MediaController controller = new MediaController(context);

            /*

            if (model.getIsAdultContent().equalsIgnoreCase("false") && model.getPostURL() != null && !model.getPostURL().equalsIgnoreCase("") && model.getIsDeleted().equalsIgnoreCase("false")) {
             //   Uri uri = Uri.parse("http://itbarxapp.azurewebsites.net" + model.getPostURL());

                // video.setSurfaceTextureListener();

                Uri uri = Uri.parse("android.resource://" + ItbarxGlobal.getInstance().getPackageName() + "/" + R.raw.sample);
                video.setVideoURI(uri);
                video.setMediaController(controller);
                controller.requestFocus();
                text.setText((null != model.getPostSpeechToText()) ? model.getPostSpeechToText() : text.getText());
                video.stopPlayback();
                video.pause();
                //   video.start();
            //    video.start();
                controller.show();


            } else {
                Uri uri = Uri.parse("android.resource://" + ItbarxGlobal.getInstance().getPackageName() + "/" + R.raw.sample);

                video.setMediaController(controller);
                video.setVideoURI(uri);
                controller.requestFocus();
                //  video.start();
                video.stopPlayback();
                video.pause();
            //    video.start();
                controller.show();
                text.setText(model.getPostSpeechToText());


                //      Uri uri = Uri.parse("android.resource://" + IApplication.getContext().getPackageName() + "/" + R.raw.sample);
                //  video.setVideoURI(uri);
                //  video.start();
            }
            */

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