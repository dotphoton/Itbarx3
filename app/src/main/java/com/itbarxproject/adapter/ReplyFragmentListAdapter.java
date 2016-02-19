package com.itbarxproject.adapter;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.itbarxproject.R;
import com.itbarxproject.activity.BaseActivity;
import com.itbarxproject.custom.component.TextViewBold;
import com.itbarxproject.custom.component.TextViewRegular;
import com.itbarxproject.model.send_to_fragment.ReplyData;
import com.itbarxproject.utils.DateUtility;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * TODO: Add a class header comment!
 */
public class ReplyFragmentListAdapter extends BaseAdapter {

	Context context;
	ArrayList<ReplyData> models;

	public ReplyFragmentListAdapter(BaseActivity activity, ArrayList<ReplyData> models) {
		context = activity;
		this.models = models;

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
			convertView = layoutInflater.inflate(R.layout.row_fragment_reply_screen_item, parent, false);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		}
		ReplyData model = (ReplyData) getItem(position);

		holder.txtName.setText((model.getItBarxUserName() == null || model.getItBarxUserName().equals("")) ? holder.txtName.getText().toString() : WordUtils.capitalize(model.getItBarxUserName()));
		holder.txtPlace.setText((model.getPlace() == null || model.getPlace().equals("")) ? holder.txtPlace.getText().toString() : WordUtils.capitalize(model.getPlace()));
		holder.txtPostReply.setText((model.getReplyPostText() == null || model.getReplyPostText().equals("")) ? holder.txtPostReply.getText().toString() : model.getReplyPostText());
		if (model.getTimeAgo() != null || !model.getTimeAgo().equals("")) {
			Date replyDate = DateUtility.itBarxDateParser(model.getTimeAgo());
			Pair<String, Long> pair = DateUtility.getDateDiff(replyDate, new Date(System.currentTimeMillis()));
			holder.txtTime.setText(pair.second + " " + pair.first);
		}

		return convertView;
	}

	static class ViewHolder {
		@Bind(R.id.row_fragment_reply_screen_name_textView) TextViewBold txtName;
		@Bind(R.id.row_fragment_reply_screen_time_textView) TextViewRegular txtTime;
		@Bind(R.id.row_fragment_reply_screen_place_textView) TextViewRegular txtPlace;
		@Bind(R.id.row_fragment_reply_screen_postReply_textView) TextViewRegular txtPostReply;
		@Bind(R.id.row_fragment_reply_screen_thumbnail_imageView) ImageView imgThumbnail;
		@Bind(R.id.row_fragment_reply_screen_video_thumbnail_play_ImageView) ImageView imgPlayIcon;
		@Bind(R.id.row_fragment_reply_screen_profilePhoto_imageView) ImageView imgProfilePhoto;

		public ViewHolder(View view) {
			ButterKnife.bind(this, view);
		}
	}
}
