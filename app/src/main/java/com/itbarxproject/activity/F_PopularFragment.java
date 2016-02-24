package com.itbarxproject.activity;

import com.itbarxproject.R;
import com.itbarxproject.adapter.PopularFragmentListAdapter;
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
import com.itbarxproject.model.post.PostPopularModel;
import com.itbarxproject.model.post.PostTimelineListForUserModel;

import com.itbarxproject.sl.PostProcessesServiceSL;
import com.itbarxproject.utils.TextSizeUtil;

import android.app.Fragment;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

public class F_PopularFragment extends Fragment {

	private ButtonRegular btnOpenTimeline, btnPopular;
	private Communicator comm;
	T_HomeActivity t_homeActivity;
	private List<PostPopularPostListModel> postPopularPostListModels;
	//VideoView video;
	private ListView reqVidListView;
	private TextViewRegular txtPopularToolbar;

	public F_PopularFragment() {

	}

public static F_PopularFragment newInstance(T_HomeActivity t_homeActivity) {
	F_PopularFragment myFragment = new F_PopularFragment();
myFragment.t_homeActivity = t_homeActivity;
	return myFragment;
}


	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_popular_screen, container, false);

	}

	@Override public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

	}

	@Override public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		//sends the message via communicator interface.
		comm = (Communicator) getActivity();
		btnOpenTimeline = (ButtonRegular) getActivity().findViewById(R.id.popular_fragment_timeline_button);
		btnOpenTimeline.setOnClickListener(openTimelineClickListener);
		btnPopular = (ButtonRegular) t_homeActivity.findViewById(R.id.popular_fragment_popular_button);


		//fills up the listview
		reqVidListView = (ListView) getActivity().findViewById(R.id.popular_fragment_screen_listview);
		getPopularList(sendModel());

		//video = (VideoView)t_homeActivity.findViewById(R.id.row_fragment_popular_screen_user_videoView);
		//Uri uri = Uri.parse("android.resource://" + t_homeActivity.getPackageName() + "/" + R.raw.sample);
		//video.setVideoURI(uri);
		//video.start();
		setTextSize();
	}

	private void setTextSize() {
	//	txtPopularToolbar.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getToolbarTextSize());
		btnOpenTimeline.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getFragBtnTextSize());
		btnPopular.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getFragBtnTextSize());

	}

	private PostPopularModel sendModel() {
		PostPopularModel model = new PostPopularModel();
		//	PostPopularModel model = new  PostPopularModel("10027","1","10");
		AccountGetUserByLoginInfoModel loginInfoModel = ItbarxGlobal.getInstance().getAccountModel();
		if (loginInfoModel != null) {
			model.setUserID(loginInfoModel.getUserID());
			model.setPage("1");
			model.setRecPerPage("10");
		}
		return model;
	}

	public void getPopularList(PostPopularModel model) {

		PostProcessesServiceSL postProcessesServiceSL = new PostProcessesServiceSL(t_homeActivity.getContext(), postProcessesServiceListener, R.string.root_service_url);
		postProcessesServiceSL.setPopular(model);
		t_homeActivity.showProgress(getString(R.string.ItbarxConnecting));
	}

	OneShotOnClickListener openTimelineClickListener = new OneShotOnClickListener(500) {

		@Override public void onOneShotClick(View v) {
			comm.choose(Fragments.POPULAR.name());
		}
	};

	PostProcessesServiceListener<String> postProcessesServiceListener = new PostProcessesServiceListener<String>() {
		@Override public void getTimelineListForUser(List<PostTimelineListForUserModel> postTimelineListForUserModel) {
			t_homeActivity.dismissProgress();
		}

		@Override public void getWallListForUser(List<PostPopularPostListModel> popularPostListModel) {
			t_homeActivity.dismissProgress();
		}

		@Override public void getPopularPostList(List<PostPopularPostListModel> popularPostListModel) {
			t_homeActivity.dismissProgress();
			postPopularPostListModels = popularPostListModel;
			ItbarxGlobal.getInstance().setPopularListModel(popularPostListModel);
			reqVidListView.setAdapter(new PopularFragmentListAdapter(t_homeActivity, postPopularPostListModels));

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

		@Override public void onComplete(ResponseEventModel<String> onComplete) {
			t_homeActivity.dismissProgress();
		}

		@Override public void onError(BarxErrorModel onError) {
			t_homeActivity.dismissProgress();
		}
	};

}