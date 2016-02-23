package com.itbarxproject.activity;

import com.itbarxproject.R;
import com.itbarxproject.adapter.PopularFragmentListAdapter;
import com.itbarxproject.application.ItbarxGlobal;
import com.itbarxproject.common.LoadHttpImage;
import com.itbarxproject.enums.Fragments;
import com.itbarxproject.custom.component.TextViewBold;
import com.itbarxproject.custom.component.TextViewRegular;
import com.itbarxproject.service.ResponseEventModel;
import com.itbarxproject.service.error.BarxErrorModel;
import com.itbarxproject.listener.OneShotOnClickListener;
import com.itbarxproject.listener.PostProcessesServiceListener;
import com.itbarxproject.model.account.AccountGetUserByLoginInfoModel;
import com.itbarxproject.model.post.PostGetPostDetailModel;
import com.itbarxproject.model.post.PostGetWallInfoModel;
import com.itbarxproject.model.post.PostWallInfoModel;
import com.itbarxproject.sl.PostProcessesServiceSL;
import com.itbarxproject.utils.TextSizeUtil;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.VideoView;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class F_ProfileFragment extends Fragment {

	private T_ProfileActivity t_profileActivity;
	private Communicator comm;
	private AccountGetUserByLoginInfoModel accLoginInfoModel;
	private LinearLayout lytButton;
	private TextViewBold txtName, txtItBarkUserName;
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

	public static F_ProfileFragment newInstance(T_ProfileActivity t_profileActivity) {
		F_ProfileFragment myFragment = new F_ProfileFragment();
		myFragment.t_profileActivity = t_profileActivity;
		return myFragment;
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

		imgEditProfile = (ImageView) t_profileActivity.findViewById(R.id
				.profile_fragment_screen_settings_imageView);
		txtEditProfile = (TextViewBold) t_profileActivity.findViewById(R.id.profile_fragment_screen_editProfile_textView);
		txtProfileToolbar = (TextViewRegular) t_profileActivity.findViewById(R.id.profile_fragment_screen_toolbar_text);
		txtName = (TextViewBold) t_profileActivity.findViewById(R.id.profile_fragment_screen_name_text);
		txtLocationName = (TextViewRegular) t_profileActivity.findViewById(R.id.profile_fragment_screen_place_text);
		txtUserBio = (TextViewRegular) t_profileActivity.findViewById(R.id.profile_fragment_screen_user_bio_text);
		txtItBarkUserName = (TextViewBold) t_profileActivity.findViewById(R.id.profile_fragment_screen_itBarkUserName_text);
		lytButton = (LinearLayout) t_profileActivity.findViewById(R.id.profile_fragment_screen_follow_unFollow__buttonlayout);
		txtReBarkCount = (TextViewBold) t_profileActivity.findViewById(R.id.profile_fragment_screen_reBarksCount_TextView);
		txtFollowerCount = (TextViewBold) t_profileActivity.findViewById(R.id.profile_fragment_screen_followersCount_TextView);
		txtFollowingCount = (TextViewBold) t_profileActivity.findViewById(R.id.profile_fragment_screen_followingCount_TextView);
		txtBarkText = (TextViewRegular) t_profileActivity.findViewById(R.id.profile_fragment_screen_reBarks_TextView);
		txtFollowerText = (TextViewRegular) t_profileActivity.findViewById(R.id.profile_fragment_screen_followers_TextView);
		txtFollowingText = (TextViewRegular) t_profileActivity.findViewById(R.id.profile_fragment_screen_following_TextView);
		lstPopular = (ListView) t_profileActivity.findViewById(R.id.profile_fragment_screen_listView);
		imgUserPhoto = (ImageView)t_profileActivity.findViewById(R.id.profile_fragment_screen_user_photo_imageView);



		setImgUserPhoto();
		setTextSize();
		getUserWallInfoModel(sendUserWallInfoModel());
		getPopularList();
		lytButton.setOnClickListener(openEditProfileClickListener);
		imgEditProfile.setOnClickListener(openEditProfileClickListener);

	}

	private void setText() {

		txtName.setText((null != accLoginInfoModel.getName() && !accLoginInfoModel.getName()
				.equals("")) ? accLoginInfoModel.getName() : txtName.getText());
		txtLocationName.setText((null != accLoginInfoModel.getLocationName() && !accLoginInfoModel
				.getLocationName().equals("")) ? accLoginInfoModel.getLocationName() :
				txtLocationName.getText());
		txtUserBio.setText((null != accLoginInfoModel.getUserBio() && !accLoginInfoModel
				.getUserBio().equals("")) ? accLoginInfoModel.getUserBio() : txtUserBio.getText());
		txtItBarkUserName.setText((null != accLoginInfoModel.getItBarxUserName() &&
				!accLoginInfoModel.getItBarxUserName().equals("")) ? accLoginInfoModel
				.getItBarxUserName() : txtItBarkUserName.getText());

	}

	private  void setImgUserPhoto()
	{
		if (accLoginInfoModel.getUserProfilePhoto() != null && accLoginInfoModel.getUserProfilePhoto().length() > 0) {
			new LoadHttpImage(imgUserPhoto).execute(accLoginInfoModel.getUserProfilePhoto());
		}
	}


	private void setTextSize() {
		txtEditProfile.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getButtonTextSize());
		txtProfileToolbar.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getToolbarTextSize());
		txtName.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getProfileUsernameTextSize());
		txtLocationName.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getProfileUserPlaceTextSize());
		txtUserBio.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getProfileUserBioTextSize());
		txtItBarkUserName.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getProfileUserBioTextSize());

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
			t_profileActivity.launchSubActivity(EditProfileActivity.class);
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

	PostProcessesServiceListener postProcessesServiceListener = new PostProcessesServiceListener<String>() {
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

				String reBarkCount = (postGetWallInfoModel.getReBarkCount()!=null&& postGetWallInfoModel.getReBarkCount().trim().equals(StringUtils.EMPTY) ) ? postGetWallInfoModel.getReBarkCount() : getResources().getString(R.string.zero) ;
				String followerCount = (postGetWallInfoModel.getFollowerCount()!=null&& postGetWallInfoModel.getFollowerCount().trim().equals(StringUtils.EMPTY) ) ? postGetWallInfoModel.getFollowerCount() : getResources().getString(R.string.zero) ;
				String followingCount = (postGetWallInfoModel.getFollowingCount()!=null&& postGetWallInfoModel.getFollowingCount().trim().equals(StringUtils.EMPTY) ) ? postGetWallInfoModel.getFollowingCount() : getResources().getString(R.string.zero) ;

				txtReBarkCount.setText(reBarkCount);
				txtFollowerCount.setText(followerCount);
				txtFollowingCount.setText(followingCount);

				String name = (postGetWallInfoModel.getName()!=null&& !postGetWallInfoModel.getName().trim().equals(StringUtils.EMPTY) ) ? postGetWallInfoModel.getName() : getResources().getString(R.string.update) ;
				String locationName = (postGetWallInfoModel.getLocationName()!=null&& !postGetWallInfoModel.getLocationName().trim().equals(StringUtils.EMPTY) ) ? postGetWallInfoModel.getLocationName() : getResources().getString(R.string.update) ;
				String bio =   (postGetWallInfoModel.getUserBio()!=null&& !postGetWallInfoModel.getUserBio().trim().equals(StringUtils.EMPTY) ) ? postGetWallInfoModel.getUserBio() : getResources().getString(R.string.update) ;
				String userName =  (postGetWallInfoModel.getUserName()!=null&& !postGetWallInfoModel.getUserName().trim().equals(StringUtils.EMPTY) ) ? postGetWallInfoModel.getUserName() : getResources().getString(R.string.update) ;

				txtName.setText(name);
				txtLocationName.setText(locationName);
				txtUserBio.setText(bio);
				txtItBarkUserName.setText(userName);




			}

		}

		@Override public void getPostDetail(PostGetPostDetailModel postDetailModel) {
			t_profileActivity.dismissProgress();
		}

		@Override public void isAdded(String isAdded) {
			t_profileActivity.dismissProgress();
		}

		@Override public void onComplete(ResponseEventModel<String> onComplete) {
			t_profileActivity.dismissProgress();
		}

		@Override public void onError(BarxErrorModel onError) {
			t_profileActivity.dismissProgress();
		}
	};

}
