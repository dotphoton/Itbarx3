package com.itbarxproject.activity;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.itbarxproject.R;
import com.itbarxproject.adapter.PopularFragmentListAdapter;
import com.itbarxproject.application.ItbarxGlobal;
import com.itbarxproject.custom.component.TextViewBold;
import com.itbarxproject.custom.component.TextViewRegular;
import com.itbarxproject.exception.ExceptionHandler;
import com.itbarxproject.service.ResponseEventModel;
import com.itbarxproject.service.error.BarxErrorModel;
import com.itbarxproject.listener.OneShotOnClickListener;
import com.itbarxproject.listener.PostProcessesServiceListener;
import com.itbarxproject.model.post.PostGetPostDetailModel;
import com.itbarxproject.model.post.PostGetWallInfoModel;
import com.itbarxproject.model.post.PostWallInfoModel;
import com.itbarxproject.sl.PostProcessesServiceSL;
import com.itbarxproject.utils.BarkUtility;
import com.itbarxproject.utils.TextSizeUtil;

import java.util.List;

public class OtherUserActivity extends BaseActivity {

	protected String user_Id;
	private TextViewBold txtUserName, txtItBarkUserName;
	private TextViewRegular txtLocationName, txtUserBio;
	private TextViewRegular txtProfileToolbar;
	private TextViewBold txtReBarkCount, txtFollowerCount, txtFollowingCount;
	private TextViewRegular txtBarkText, txtFollowerText, txtFollowingText;
	private TextViewBold txtFollow, txtUnFollow;
	//private ButtonBold btnFollow,btnUnFollow;
	private ImageView imgUserPhoto, imgFollow, imgUnFollow;
	private ListView lstPopular;
	private LinearLayout layoutFollowUnFollow;
	private String postSenderUserId;

	@Override protected int getLayoutResourceId() {
		return R.layout.activity_other_user_screen;
	}

	@Override protected Context getContext() {
		return OtherUserActivity.this;
	}
	@Override protected void exceptionHandler() {
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
	}

	@Override protected void initViews() {


		postSenderUserId = BarkUtility.getPostSenderUserId(OtherUserActivity.this);
			layoutFollowUnFollow = (LinearLayout) findViewById(R.id.other_user_activity_screen_follow_unFollow_layout);
			imgFollow = (ImageView) findViewById(R.id.other_user_activity_screen_follow_imageView);
			imgUnFollow = (ImageView) findViewById(R.id.other_user_activity_screen_unFollow_imageView);
			txtFollow = (TextViewBold) findViewById(R.id.other_user_activity_screen_follow_textView);
			txtUnFollow = (TextViewBold) findViewById(R.id.other_user_activity_screen_unFollow_textView);
			txtProfileToolbar = (TextViewRegular) findViewById(R.id.other_user_activity_screen_toolbar_textView);
			txtUserName = (TextViewBold) findViewById(R.id.other_user_activity_screen_username_text);
			txtLocationName = (TextViewRegular) findViewById(R.id.other_user_activity_screen_place_text);
			txtUserBio = (TextViewRegular) findViewById(R.id.other_user_activity_screen_user_bio_text);
			txtItBarkUserName = (TextViewBold) findViewById(R.id.other_user_activity_screen_itBarkUserName_text);
			//btnFollow = (ButtonBold) findViewById(R.id.other_user_activity_screen_follow_button);
			//btnUnFollow = (ButtonBold) findViewById(R.id.other_user_activity_screen_unFollow_button);
			txtReBarkCount = (TextViewBold) findViewById(R.id.other_user_activity_screen_reBarksCount_TextView);
			txtFollowerCount = (TextViewBold) findViewById(R.id.other_user_activity_screen_followersCount_TextView);
			txtFollowingCount = (TextViewBold) findViewById(R.id.other_user_activity_screen_followingCount_TextView);
			txtBarkText = (TextViewRegular) findViewById(R.id.other_user_activity_screen_reBarks_TextView);
			txtFollowerText = (TextViewRegular) findViewById(R.id.other_user_activity_screen_followers_TextView);
			txtFollowingText = (TextViewRegular) findViewById(R.id.other_user_activity_screen_following_TextView);
			lstPopular = (ListView)findViewById(R.id.other_user_activity_screen_listView);
		//	imgFollow.setOnClickListener(imgFollowClickListener);
		//	imgUnFollow.setOnClickListener(imgUnFollowClickListener);
			layoutFollowUnFollow.setOnClickListener(layoutFollowProcessesClickListener);
			setTextSize();
			getPopularList();
			try {
				getUserWallInfoModel(sendUserWallInfoModel(postSenderUserId));

			} catch (Exception e) {

				writeLog("ITbarx", "OtherUserActivity  initViews" + e.getMessage());
			}

	}

	//TAKE POPULAR LIST
	private void getPopularList() {
		if (ItbarxGlobal.getInstance().getPopularListModel() != null) {

			lstPopular.setAdapter(new PopularFragmentListAdapter(this, ItbarxGlobal.getInstance().getPopularListModel()));

		}

	}

	private void setTextSize() {
		txtProfileToolbar.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getToolbarTextSize());
		txtUserName.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getProfileUsernameTextSize());
		txtLocationName.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getProfileUserPlaceTextSize());
		txtUserBio.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getProfileUserBioTextSize());
		txtItBarkUserName.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getProfileUserBioTextSize());
		//	btnFollow.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getButtonTextSize());
		//	btnUnFollow.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getButtonTextSize());
		txtFollow.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getButtonTextSize());
		txtUnFollow.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getButtonTextSize());
		txtReBarkCount.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getProfileMiniButtonCountTextSize());
		txtFollowerCount.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getProfileMiniButtonCountTextSize());
		txtFollowingCount.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getProfileMiniButtonCountTextSize());
		txtBarkText.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getProfileMiniButtonTextSize());
		txtFollowerText.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getProfileMiniButtonTextSize());
		txtFollowingText.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getProfileMiniButtonTextSize());

	}
/*
	OneShotOnClickListener imgFollowClickListener = new OneShotOnClickListener(500) {
		@Override public void onOneShotClick(View v) {
			layoutFollowUnFollow.setBackground(getResources().getDrawable(R.drawable.select_unfollow_button));
			imgFollow.setVisibility(View.INVISIBLE);
			imgFollow.setVisibility(View.GONE);
			imgUnFollow.setVisibility(View.VISIBLE);
			txtFollow.setVisibility(View.INVISIBLE);
			txtFollow.setVisibility(View.GONE);
			txtUnFollow.setVisibility(View.VISIBLE);
		}
	};

	OneShotOnClickListener imgUnFollowClickListener = new OneShotOnClickListener(500) {
		@Override public void onOneShotClick(View v) {
			layoutFollowUnFollow.setBackground(getResources().getDrawable(R.drawable.select_green_button));
			imgUnFollow.setVisibility(View.INVISIBLE);
			imgUnFollow.setVisibility(View.GONE);
			imgFollow.setVisibility(View.VISIBLE);
			txtUnFollow.setVisibility(View.INVISIBLE);
			txtUnFollow.setVisibility(View.GONE);
			txtFollow.setVisibility(View.VISIBLE);
		}
	};
	*/
	OneShotOnClickListener layoutFollowProcessesClickListener = new OneShotOnClickListener(500) {
		@Override
		public void onOneShotClick(View v) {

			if (imgFollow.getVisibility() == View.VISIBLE ){
				layoutFollowUnFollow.setBackground(getResources().getDrawable(R.drawable.select_unfollow_button));
				imgFollow.setVisibility(View.INVISIBLE);
				imgFollow.setVisibility(View.GONE);
				imgUnFollow.setVisibility(View.VISIBLE);
				txtFollow.setVisibility(View.INVISIBLE);
				txtFollow.setVisibility(View.GONE);
				txtUnFollow.setVisibility(View.VISIBLE);
			}
			else{
				layoutFollowUnFollow.setBackground(getResources().getDrawable(R.drawable.select_green_button));
				imgUnFollow.setVisibility(View.INVISIBLE);
				imgUnFollow.setVisibility(View.GONE);
				imgFollow.setVisibility(View.VISIBLE);
				txtUnFollow.setVisibility(View.INVISIBLE);
				txtUnFollow.setVisibility(View.GONE);
				txtFollow.setVisibility(View.VISIBLE);

			}


		}
	};

	private PostWallInfoModel sendUserWallInfoModel(String user_Id) {
		String id = user_Id;
		return new PostWallInfoModel(id, id);
	}

	private void getUserWallInfoModel(PostWallInfoModel model) {
		PostProcessesServiceSL postProcessesServiceSL = new PostProcessesServiceSL(this, postProcessesServiceListener, R.string.root_service_url);
		postProcessesServiceSL.setWallInfo(model);

	}

	PostProcessesServiceListener postProcessesServiceListener = new PostProcessesServiceListener<String>() {
		@Override public void getTimelineListForUser(List postTimelineListForUserModel) {
			dismissProgress();
		}

		@Override public void getWallListForUser(List postWallListForUserModel) {
			dismissProgress();
		}

		@Override public void getPopularPostList(List popularPostListModel) {
			dismissProgress();
		}

		@Override public void getNewPostList(List list) {
			dismissProgress();
		}

		@Override public void getWallInfo(PostGetWallInfoModel postGetWallInfoModel) {
			dismissProgress();
			if (postGetWallInfoModel != null) {
				txtUserName.setText((null != postGetWallInfoModel.getName() && !postGetWallInfoModel.getName().equals("")) ? postGetWallInfoModel.getName() : txtUserName.getText());
				txtLocationName.setText((null != postGetWallInfoModel.getLocationName() && !postGetWallInfoModel.getLocationName().equals("")) ? postGetWallInfoModel.getLocationName() : txtLocationName.getText());
				txtUserBio.setText((null != postGetWallInfoModel.getUserBio() && !postGetWallInfoModel.getUserBio().equals("")) ? postGetWallInfoModel.getUserBio() : txtUserBio.getText());

				txtReBarkCount.setText(postGetWallInfoModel.getReBarkCount());
				txtFollowerCount.setText(postGetWallInfoModel.getFollowerCount());
				txtFollowingCount.setText(postGetWallInfoModel.getFollowingCount());

			}

		}

		@Override public void getPostDetail(PostGetPostDetailModel postDetailModel) {
			dismissProgress();
		}

		@Override public void isAdded(String isAdded) {
			dismissProgress();
		}

		@Override public void onComplete(ResponseEventModel<String> onComplete) {
			dismissProgress();
		}

		@Override public void onError(BarxErrorModel onError) {
			dismissProgress();
		}
	};

}
