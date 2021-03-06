package com.itbarxproject.activity;

import com.itbarxproject.R;
import com.itbarxproject.adapter.TimelineFragmentListAdapter;
import com.itbarxproject.application.ItbarxGlobal;
import com.itbarxproject.custom.component.ButtonRegular;
import com.itbarxproject.custom.component.TextViewRegular;
import com.itbarxproject.enums.Fragments;
import com.itbarxproject.model.post.PostPopularPostListModel;
import com.itbarxproject.service.ResponseEventModel;
import com.itbarxproject.service.error.BarxErrorModel;
import com.itbarxproject.listener.OneShotOnClickListener;
import com.itbarxproject.listener.PostProcessesServiceListener;
import com.itbarxproject.model.account.AccountGetUserByLoginInfoModel;
import com.itbarxproject.model.post.PostGetPostDetailModel;
import com.itbarxproject.model.post.PostGetWallInfoModel;
import com.itbarxproject.model.post.PostNewPostListModel;
import com.itbarxproject.model.post.PostTimelineListForUserModel;
import com.itbarxproject.model.post.PostTimelineModel;

import com.itbarxproject.sl.PostProcessesServiceSL;
import com.itbarxproject.utils.TextSizeUtil;

import android.app.Fragment;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class F_TimelineFragment extends Fragment {

	Button btnOpenPopular, btnTimeline;
	Communicator comm;
	T_HomeActivity t_homeActivity;
	List<PostTimelineListForUserModel> postTimelineListForUserModels;
	ListView timelineListView;
	TextViewRegular txtTimelineToolbar;

	public F_TimelineFragment() {
	}

	public static F_TimelineFragment newInstance(T_HomeActivity t_homeActivity) {
		F_TimelineFragment myFragment = new F_TimelineFragment();
		myFragment.t_homeActivity = t_homeActivity;
		return myFragment;
	}
	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_timeline_screen, container, false);
	}

	@Override public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// t1 = (TextView) getActivity().findViewById(R.id.txt1);
		comm = (Communicator) getActivity();
		btnOpenPopular = (ButtonRegular) t_homeActivity.findViewById(R.id.timeline_fragment_popular_button);
		btnOpenPopular.setOnClickListener(openPopularClickListener);
		btnTimeline = (ButtonRegular) t_homeActivity.findViewById(R.id.timeline_fragment_timeline_button);

		//fills up the listView
		timelineListView = (ListView) getActivity().findViewById(R.id.timeline_fragment_screen_ListView);
		getTimelineList(sendModel());
		setTextSize();
	}

	private void setTextSize() {

		btnOpenPopular.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getFragBtnTextSize());
		btnTimeline.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getFragBtnTextSize());

	}

	private PostTimelineModel sendModel() {
		PostTimelineModel model = new PostTimelineModel();
		//	PostPopularModel model = new  PostPopularModel("10027","1","10");
		AccountGetUserByLoginInfoModel loginInfoModel = ItbarxGlobal.getInstance().getAccountModel();
		if (loginInfoModel != null) {
			model.setUserID(loginInfoModel.getUserID());
			model.setPage("1");
			model.setRecPerPage("10");
		}
		return model;
	}

	public void getTimelineList(PostTimelineModel model) {

		PostProcessesServiceSL postProcessesServiceSL = new PostProcessesServiceSL(t_homeActivity.getContext(), postProcessesServiceListener, R.string.root_service_url);
		postProcessesServiceSL.setTimeline(model);
		t_homeActivity.showProgress(getString(R.string.ItbarxConnecting));
	}

	OneShotOnClickListener openPopularClickListener = new OneShotOnClickListener(500) {

		@Override public void onOneShotClick(View v) {
			comm.choose(Fragments.TIMELINE.name());
		}
	};

	PostProcessesServiceListener<String> postProcessesServiceListener = new PostProcessesServiceListener<String>() {

		@Override public void onComplete(ResponseEventModel<String> onComplete) {
			t_homeActivity.dismissProgress();
		}

		@Override public void onError(BarxErrorModel onError) {
			t_homeActivity.dismissProgress();
		}

		@Override public void getTimelineListForUser(List<PostTimelineListForUserModel> postTimelineListForUserModel) {
			t_homeActivity.dismissProgress();
			postTimelineListForUserModels = postTimelineListForUserModel;
			timelineListView.setAdapter(new TimelineFragmentListAdapter(t_homeActivity, postTimelineListForUserModels));
		}

		@Override public void getWallListForUser(List<PostPopularPostListModel> popularPostListModel) {
			t_homeActivity.dismissProgress();
		}

		@Override public void getPopularPostList(List<PostPopularPostListModel> popularPostListModel) {
			t_homeActivity.dismissProgress();
		}

		@Override public void getNewPostList(List<PostNewPostListModel> postNewPostListModels) {
			t_homeActivity.dismissProgress();
		}

		@Override public void getWallInfo(PostGetWallInfoModel postGetWallInfoModel) {
			t_homeActivity.dismissProgress();
		}

		@Override public void getPostDetail(PostGetPostDetailModel postDetailModel) {
			t_homeActivity.dismissProgress();
		}

		@Override public void isAdded(String isAdded) {
			t_homeActivity.dismissProgress();
		}
	};

}