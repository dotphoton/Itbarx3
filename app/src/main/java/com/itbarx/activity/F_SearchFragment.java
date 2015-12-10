package com.itbarx.activity;

import com.itbarx.R;
import com.itbarx.adapter.SearchFragmentGridAdapter;
import com.itbarx.application.ItbarxGlobal;
import com.itbarx.custom.component.EditTextRegular;
import com.itbarx.custom.component.TextViewRegular;
import com.itbarx.service.ResponseEventModel;
import com.itbarx.service.error.BarxErrorModel;
import com.itbarx.listener.FollowingProcessesServiceListener;
import com.itbarx.listener.OneShotOnClickListener;
import com.itbarx.listener.SearchProcessesServiceListener;
import com.itbarx.model.follow.FollowerListByFollowingIdModel;
import com.itbarx.model.follow.FollowerListModel;
import com.itbarx.model.search.SearchModel;
import com.itbarx.model.search.SearchUserListResultModel;
import com.itbarx.sl.FollowingProcessesServiceSL;
import com.itbarx.sl.SearchProcessesServiceSL;
import com.itbarx.utils.TextSizeUtil;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class F_SearchFragment extends Fragment {

	private FollowingProcessesServiceSL followingProcessesServiceSL;
	private SearchProcessesServiceSL searchProcessesServiceSL;
	private TextViewRegular txtToolbar;
	private EditTextRegular searchPersonEdtTxt;
	private GridView gridView;
	private LinearLayout layoutToolbar;
	private ImageView imgSearchIcon;

	private T_SearchActivity t_searchActivity;

	public F_SearchFragment() {
	}

	public F_SearchFragment(T_SearchActivity t_searchActivity) {
		this.t_searchActivity = t_searchActivity;
	}

	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_search_screen, container, false);
	}

	@Override public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		layoutToolbar = (LinearLayout) t_searchActivity.findViewById(R.id.search_toolbar_layout);
		txtToolbar = (TextViewRegular) t_searchActivity.findViewById(R.id.search_fragment__toolbar_textView);
		searchPersonEdtTxt = (EditTextRegular) t_searchActivity.findViewById(R.id.search_fragment_screen_search_edittext);
		gridView = (GridView) t_searchActivity.findViewById(R.id.search_fragment_screen_gridView);
		imgSearchIcon = (ImageView) t_searchActivity.findViewById(R.id.fragment_search_screen_search_imageView);
		imgSearchIcon.setOnClickListener(searchPersonClickListener);
		getFriends(sendFollowerListModel());
		setTextSize();

	}

	private SearchModel sendSearchModel(String user){
		return new SearchModel(ItbarxGlobal.getInstance().getAccountModel().getUserID(),user,"1","10");

	}
	
	private void getSearchedUser(SearchModel model){
		searchProcessesServiceSL =  new SearchProcessesServiceSL(t_searchActivity.getContext(), searchProcessesServiceListener, R.string.root_service_url);
		searchProcessesServiceSL.setSearchUser(model);
		t_searchActivity.showProgress(getString(R.string.ItbarxConnecting));
	}

	OneShotOnClickListener searchPersonClickListener = new OneShotOnClickListener(500) {
		@Override public void onOneShotClick(View v) {
		String user=	searchPersonEdtTxt.getText().toString().trim();
			if(user!=null&&!user.equals("")){
				InputMethodManager imm = (InputMethodManager)t_searchActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(t_searchActivity.getCurrentFocus().getWindowToken(), 0);
				getSearchedUser(sendSearchModel(user));
				
			}
		}
	};

	private void setTextSize() {
		txtToolbar.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getToolbarTextSize());
		searchPersonEdtTxt.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getEditBoxTextSize());
	}

	private FollowerListModel sendFollowerListModel() {
		return new FollowerListModel("10028", "10028", "1", "10");

	}

	private void getFriends(FollowerListModel model) {
		 followingProcessesServiceSL = new FollowingProcessesServiceSL(t_searchActivity.getContext(), followingProcessesServiceListener, R.string.root_service_url);
		followingProcessesServiceSL.setFollowerList(model);
		t_searchActivity.showProgress(getString(R.string.ItbarxConnecting));
	}

	FollowingProcessesServiceListener followingProcessesServiceListener = new FollowingProcessesServiceListener<String>() {
		@Override public void add(String isAdded) {
			t_searchActivity.dismissProgress();
		}

		@Override public void updateAsFriend(String isUpdateAsFriend) {
			t_searchActivity.dismissProgress();
		}

		@Override public void updateAsBlocked(String isUpdateAsBlocked) {
			t_searchActivity.dismissProgress();
		}

		@Override public void countFollower(String count) {
			t_searchActivity.dismissProgress();
		}

		@Override public void countFollowing(String count) {
			t_searchActivity.dismissProgress();
		}

		@Override public void countPending(String count) {
			t_searchActivity.dismissProgress();
		}

		@Override public void countSendPending(String count) {
			t_searchActivity.dismissProgress();
		}

		@Override public void deleteFollow(String isDeleted) {
			t_searchActivity.dismissProgress();
		}

		@Override public void getFollowerListById(List followerListByFollowingIdModel) {

			t_searchActivity.dismissProgress();
			gridView.setAdapter(new SearchFragmentGridAdapter(t_searchActivity, followerListByFollowingIdModel));
		}

		@Override public void getFollowingListById(List followingListByFollowerIdModel) {
			t_searchActivity.dismissProgress();
		}

		@Override public void getPendingListById(List pendingListByFollowingIdModel) {
			t_searchActivity.dismissProgress();
		}

		@Override public void getSendPendingListById(List sendPendingListByFollowerIdModel) {
			t_searchActivity.dismissProgress();
		}

		@Override public void onComplete(ResponseEventModel<String> onComplete) {
			t_searchActivity.dismissProgress();
		}

		@Override public void onError(BarxErrorModel onError) {
			t_searchActivity.dismissProgress();
		}
	};
	
	
	SearchProcessesServiceListener searchProcessesServiceListener = new SearchProcessesServiceListener<String>() {
		@Override public void getSearchUserForAutoCompleteList(List searchUserForAutoCompleteResultModel) {
			t_searchActivity.dismissProgress();
		}

		@Override public void getSearchUserList(List searchUserListResultModel) {
			t_searchActivity.dismissProgress();
			if (searchUserListResultModel!=null) {
				List<SearchUserListResultModel> searchUserListResultModels = searchUserListResultModel;
				List<FollowerListByFollowingIdModel> followerListByFollowingIdModelForSearchUser = new ArrayList<FollowerListByFollowingIdModel>();
				FollowerListByFollowingIdModel followerListModel;
				for (SearchUserListResultModel model : 		searchUserListResultModels) {
					followerListModel = new FollowerListByFollowingIdModel();
					followerListModel.setName(model.getName());
					followerListModel.setItBarxUserName(model.getItBarxUserName());
					followerListModel.setIsYourFollower(model.getIsYourFollower());
					followerListModel.setUserID(model.getUserID());
					followerListByFollowingIdModelForSearchUser.add(followerListModel);
				}

				gridView.setAdapter(new SearchFragmentGridAdapter(t_searchActivity, followerListByFollowingIdModelForSearchUser));

			}
			
		}

		@Override public void onComplete(ResponseEventModel<String> onComplete) {
			t_searchActivity.dismissProgress();
		}

		@Override public void onError(BarxErrorModel onError) {
			t_searchActivity.dismissProgress();
		}
	};
}