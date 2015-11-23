package com.itbarx.activity;

import com.itbarx.R;
import com.itbarx.adapter.PopularFragmentListAdapter;
import com.itbarx.application.ItbarxGlobal;
import com.itbarx.enums.Fragments;
import com.itbarx.error.common.ResponseServiceModel;

import com.itbarx.custom.component.ButtonBold;
import com.itbarx.custom.component.TextViewBold;
import com.itbarx.custom.component.TextViewRegular;
import com.itbarx.error.model.BarxErrorModel;
import com.itbarx.listener.OneShotOnClickListener;
import com.itbarx.listener.PostProcessesServiceListener;
import com.itbarx.model.account.AccountGetUserByLoginInfoModel;
import com.itbarx.model.post.PostGetPostDetailModel;
import com.itbarx.model.post.PostGetWallInfoModel;
import com.itbarx.model.post.PostPopularModel;
import com.itbarx.model.post.PostWallInfoModel;
import com.itbarx.sl.PostProcessesServiceSL;
import com.itbarx.utils.TextSizeUtil;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.VideoView;

import java.util.List;

public class F_ProfileFragment extends Fragment {

	private T_ProfileActivity t_profileActivity;
	private Communicator comm;
	private AccountGetUserByLoginInfoModel accLoginInfoModel;
	private TextViewBold txtUserName, txtItBarkUserName;
	private TextViewRegular txtLocationName, txtUserBio;
	private TextViewRegular txtProfileToolbar;
	private TextViewBold txtReBarkCount, txtFollowerCount, txtFollowingCount;
	private TextViewRegular txtBarkText, txtFollowerText, txtFollowingText;
	//private ButtonBold btnProfil;
	private TextViewBold txtEditProfile;
	private ImageView imgUserPhoto,imgEditProfile;
	private ListView lstPopular;

	ListView userProfilePopularPostsListView;
	private VideoView video;

	public F_ProfileFragment() {

	}

	public F_ProfileFragment(T_ProfileActivity t_profileActivity) {
		this.t_profileActivity = t_profileActivity;
	}

	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_profile_screen, container, false);

	}

	@Override public void onAttach(Activity activity) {
		super.onAttach(activity);

		accLoginInfoModel = ItbarxGlobal.getInstance().getAccountModel();

	}

	@Override public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		comm = (Communicator) getActivity();
		imgEditProfile = (ImageView) t_profileActivity.findViewById(R.id.profile_fragment_screen_settings_imageView);
		txtEditProfile = (TextViewBold) t_profileActivity.findViewById(R.id.profile_fragment_screen_editProfile_textView);
		txtProfileToolbar = (TextViewRegular) t_profileActivity.findViewById(R.id.profile_fragment_screen_toolbar_text);
		txtUserName = (TextViewBold) t_profileActivity.findViewById(R.id.profile_fragment_screen_username_text);
		txtLocationName = (TextViewRegular) t_profileActivity.findViewById(R.id.profile_fragment_screen_place_text);
		txtUserBio = (TextViewRegular) t_profileActivity.findViewById(R.id.profile_fragment_screen_user_bio_text);
		txtItBarkUserName = (TextViewBold) t_profileActivity.findViewById(R.id.profile_fragment_screen_itBarkUserName_text);
		//btnProfil = (ButtonBold) t_profileActivity.findViewById(R.id.profile_fragment_screen_edit_profile_button);
		txtReBarkCount = (TextViewBold) t_profileActivity.findViewById(R.id.profile_fragment_screen_reBarksCount_TextView);
		txtFollowerCount = (TextViewBold) t_profileActivity.findViewById(R.id.profile_fragment_screen_followersCount_TextView);
		txtFollowingCount = (TextViewBold) t_profileActivity.findViewById(R.id.profile_fragment_screen_followingCount_TextView);
		txtBarkText = (TextViewRegular) t_profileActivity.findViewById(R.id.profile_fragment_screen_reBarks_TextView);
		txtFollowerText = (TextViewRegular) t_profileActivity.findViewById(R.id.profile_fragment_screen_followers_TextView);
		txtFollowingText = (TextViewRegular) t_profileActivity.findViewById(R.id.profile_fragment_screen_following_TextView);
		lstPopular = (ListView) t_profileActivity.findViewById(R.id.profile_fragment_screen_listView);
		//fills up the listview
	/*    userProfilePopularPostsListView = (ListView) t_profileActivity.findViewById(R.id.profile_fragment_screen_listView);
        postPopularPostListModels = ItbarxGlobal.getPopularListModel();
        userProfilePopularPostsListView.setAdapter(new ProfilFragmentListAdapter(t_profileActivity.getContext(), postPopularPostListModels));
*/
		//control the video
        /*
        MediaController mc = new MediaController(t_profileActivity.getContext());
        video = (VideoView) t_profileActivity.findViewById(R.id.row_fragment_popular_screen_user_videoView);
        video.setMediaController(mc);
        video.stopPlayback();
        // video.start();
        mc.show();
        */
		getUserWallInfoModel(sendUserWallInfoModel());
		setText();
		setTextSize();
		getPopularList();
	//	btnProfil.setOnClickListener(openEditProfileClickListener);
		imgEditProfile.setOnClickListener(openEditProfileClickListener);

	}

	private void setText() {

		txtUserName.setText((null != accLoginInfoModel.getName() && !accLoginInfoModel.getName().equals("")) ? accLoginInfoModel.getName() : txtUserName.getText());
		txtLocationName.setText((null != accLoginInfoModel.getLocationName() && !accLoginInfoModel.getLocationName().equals("")) ? accLoginInfoModel.getLocationName() : txtLocationName.getText());
		txtUserBio.setText((null != accLoginInfoModel.getUserBio() && !accLoginInfoModel.getUserBio().equals("")) ? accLoginInfoModel.getUserBio() : txtUserBio.getText());
		txtItBarkUserName.setText((null != accLoginInfoModel.getItBarxUserName() && !accLoginInfoModel.getItBarxUserName().equals("")) ? accLoginInfoModel.getItBarxUserName() : txtItBarkUserName.getText());

	}

	private void setTextSize() {
		txtEditProfile.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getButtonTextSize());
		txtProfileToolbar.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getToolbarTextSize());
		txtUserName.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getProfileUsernameTextSize());
		txtLocationName.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getProfileUserPlaceTextSize());
		txtUserBio.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getProfileUserBioTextSize());
		txtItBarkUserName.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getProfileUserBioTextSize());
		//btnProfil.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getButtonTextSize());
		txtReBarkCount.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getProfileMiniButtonCountTextSize());
		txtFollowerCount.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getProfileMiniButtonCountTextSize());
		txtFollowingCount.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getProfileMiniButtonCountTextSize());
		txtBarkText.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getProfileMiniButtonTextSize());
		txtFollowerText.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getProfileMiniButtonTextSize());
		txtFollowingText.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getProfileMiniButtonTextSize());
	}

	//OPEN EDIT PROFILE FRAGMENT
	OneShotOnClickListener openEditProfileClickListener = new OneShotOnClickListener(500) {
		@Override public void onOneShotClick(View v) {
			comm.choose(Fragments.PROFILE.name());
		}
	};


	//TAKE POPULAR LIST
	private void getPopularList() {
		if(ItbarxGlobal.getInstance().getPopularListModel()!=null){

lstPopular.setAdapter(new PopularFragmentListAdapter(t_profileActivity, ItbarxGlobal.getInstance().getPopularListModel()));

		}

	}




	//TAKE WALL INFO

	private PostWallInfoModel sendUserWallInfoModel() {
		String id = ItbarxGlobal.getInstance().getAccountModel().getUserID();
		return new PostWallInfoModel(id, id);
	}

	private void getUserWallInfoModel(PostWallInfoModel model) {
		PostProcessesServiceSL postProcessesServiceSL = new PostProcessesServiceSL(t_profileActivity.getContext(), postProcessesServiceListener, R.string.root_service_url);
		postProcessesServiceSL.setWallInfo(model);

	}

	PostProcessesServiceListener postProcessesServiceListener = new PostProcessesServiceListener() {
		@Override public void getTimelineListForUser(List postTimelineListForUserModel) {
			t_profileActivity.dismissProgress();
		}

		@Override public void getWallListForUser(List postWallListForUserModel) {
			t_profileActivity.dismissProgress();
		}

		@Override public void getPopularPostList(List popularPostListModel) {
			t_profileActivity.dismissProgress();
		}

		@Override public void getNewPostList(List list) {
			t_profileActivity.dismissProgress();
		}

		@Override public void getWallInfo(PostGetWallInfoModel postGetWallInfoModel) {
			t_profileActivity.dismissProgress();
			if (postGetWallInfoModel != null) {
				txtReBarkCount.setText(postGetWallInfoModel.getReBarkCount());
				txtFollowerCount.setText(postGetWallInfoModel.getFollowerCount());
				txtFollowingCount.setText(postGetWallInfoModel.getFollowingCount());

			}

		}

		@Override public void getPostDetail(PostGetPostDetailModel postDetailModel) {
			t_profileActivity.dismissProgress();
		}

		@Override public void isAdded(String isAdded) {
			t_profileActivity.dismissProgress();
		}

		@Override public void onComplete(ResponseServiceModel onComplete) {
			t_profileActivity.dismissProgress();
		}

		@Override public void onError(BarxErrorModel onError) {
			t_profileActivity.dismissProgress();
		}
	};

}
