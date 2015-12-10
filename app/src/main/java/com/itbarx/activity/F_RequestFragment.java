package com.itbarx.activity;

import com.itbarx.R;
import com.itbarx.adapter.RequestFragmentListAdapter;
import com.itbarx.custom.component.ButtonRegular;
import com.itbarx.custom.component.TextViewRegular;
import com.itbarx.enums.Fragments;
import com.itbarx.service.ResponseEventModel;
import com.itbarx.service.error.BarxErrorModel;
import com.itbarx.listener.FollowingProcessesServiceListener;
import com.itbarx.listener.OneShotOnClickListener;
import com.itbarx.model.follow.FollowerListByFollowingIdModel;
import com.itbarx.model.follow.FollowerListModel;
import com.itbarx.model.follow.FollowingListByFollowerIdModel;
import com.itbarx.model.follow.PendingListByFollowingIdModel;
import com.itbarx.model.follow.SendPendingListByFollowerIdModel;
import com.itbarx.sl.FollowingProcessesServiceSL;
import com.itbarx.utils.TextSizeUtil;

import android.app.Fragment;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

public class F_RequestFragment extends Fragment {

	private ButtonRegular btnOpenActivity, btnRequest;
	private TextViewRegular txtToolbarRequest;
	Communicator comm;

	private ListView requestListView;
	List<PendingListByFollowingIdModel> pendingListModels;
	private T_SecondActivity t_secondActivity;

	public F_RequestFragment() {
	}

	public F_RequestFragment(T_SecondActivity t_secondActivity) {
		this.t_secondActivity = t_secondActivity;
	}

	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_request_screen, container, false);
	}

	@Override public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		//sends the message via communicator interface.
		comm = (Communicator) getActivity();
		btnOpenActivity = (ButtonRegular) t_secondActivity.findViewById(R.id.request_fragment_activity_button);
		btnOpenActivity.setOnClickListener(openActivityClickListener);
		btnRequest = (ButtonRegular) t_secondActivity.findViewById(R.id.request_fragment_request_button);
		txtToolbarRequest = (TextViewRegular) t_secondActivity.findViewById(R.id.request_fragment_toolbar_text);
		//fills up the listview
		requestListView = (ListView) t_secondActivity.findViewById(R.id.request_fragment_screen_listView);
		setTextSize();
		getPendingList(sendModel());
	}

	private void setTextSize() {
		txtToolbarRequest.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getToolbarTextSize());
		btnOpenActivity.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getFragBtnTextSize());
		btnRequest.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getFragBtnTextSize());
	}

	OneShotOnClickListener openActivityClickListener = new OneShotOnClickListener(500) {

		@Override public void onOneShotClick(View v) {
			comm.choose(Fragments.REQUEST.name());
		}
	};

	public void getPendingList(FollowerListModel followerListModel) {
		FollowingProcessesServiceSL followingProcessesServiceSL = new FollowingProcessesServiceSL(t_secondActivity.getContext(), followingProcessesServiceListener, R.string.root_service_url);
		followingProcessesServiceSL.setGetPendingList(followerListModel);
		t_secondActivity.showProgress(getString(R.string.ItbarxConnecting));

	}

	//send data to the web service
	private FollowerListModel sendModel() {

		FollowerListModel sendModel = new FollowerListModel();
		sendModel.setPage("1");
		sendModel.setRecPerPage("10");
		//   sendModel.setFollowingId(ItbarxGlobal.getInstance().getAccountModel().getUserID());
		sendModel.setFollowingId("10032");
		return sendModel;

	}

	FollowingProcessesServiceListener<String> followingProcessesServiceListener = new FollowingProcessesServiceListener<String>() {
		@Override public void add(String isAdded) {
			t_secondActivity.dismissProgress();
		}

		@Override public void updateAsFriend(String isUpdateAsFriend) {
			t_secondActivity.dismissProgress();
		}

		@Override public void updateAsBlocked(String isUpdateAsBlocked) {
			t_secondActivity.dismissProgress();
		}

		@Override public void countFollower(String count) {
			t_secondActivity.dismissProgress();
		}

		@Override public void countFollowing(String count) {
			t_secondActivity.dismissProgress();
		}

		@Override public void countPending(String count) {
			t_secondActivity.dismissProgress();
		}

		@Override public void countSendPending(String count) {
			t_secondActivity.dismissProgress();
		}

		@Override public void deleteFollow(String isDeleted) {
			t_secondActivity.dismissProgress();
		}

		@Override public void getFollowerListById(List<FollowerListByFollowingIdModel> followerListByFollowingIdModel) {
			t_secondActivity.dismissProgress();
		}

		@Override
		public void getFollowingListById(List<FollowingListByFollowerIdModel> followingListByFollowerIdModel) {
			t_secondActivity.dismissProgress();
		}

		@Override public void getPendingListById(List<PendingListByFollowingIdModel> pendingListByFollowingIdModel) {
			t_secondActivity.dismissProgress();
			pendingListModels = pendingListByFollowingIdModel;
			requestListView.setAdapter(new RequestFragmentListAdapter(t_secondActivity.getContext(), pendingListModels));
		}

		@Override
		public void getSendPendingListById(List<SendPendingListByFollowerIdModel> sendPendingListByFollowerIdModel) {
			t_secondActivity.dismissProgress();
		}

		@Override public void onComplete(ResponseEventModel<String> onComplete) {
			t_secondActivity.dismissProgress();
		}

		@Override public void onError(BarxErrorModel onError) {
			t_secondActivity.dismissProgress();
		}
	};
}