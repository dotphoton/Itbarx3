package com.itbarx.activity;

import com.itbarx.R;
import com.itbarx.adapter.ActivityFragmentListAdapter;
import com.itbarx.application.ItbarxGlobal;
import com.itbarx.custom.component.ButtonRegular;
import com.itbarx.custom.component.TextViewRegular;
import com.itbarx.enums.Fragments;
import com.itbarx.service.ResponseEventModel;
import com.itbarx.service.error.BarxErrorModel;
import com.itbarx.listener.ActivityProcessesServiceListener;
import com.itbarx.listener.OneShotOnClickListener;
import com.itbarx.model.activity.ActivityListModel;
import com.itbarx.model.activity.ActivityModel;
import com.itbarx.sl.ActivityProcessesServiceSL;
import com.itbarx.utils.TextSizeUtil;

import android.app.Fragment;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

public class F_ActivityFragment extends Fragment {

	private ButtonRegular btnOpenRequest, btnActivity;
	private TextViewRegular txtToolbarActivity;
	Communicator comm;
	private ListView activityListView;
	List<ActivityListModel> activityListModels;
	private T_SecondActivity t_secondActivity;

	public F_ActivityFragment() {

	}

	public static F_ActivityFragment newInstance(T_SecondActivity t_secondActivity) {
		F_ActivityFragment myFragment = new F_ActivityFragment();
		myFragment.t_secondActivity = t_secondActivity;
		return myFragment;
	}

	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		return inflater.inflate(R.layout.fragment_activity_screen, container, false);
	}

	@Override public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		//sends the message via communicator interface.
		comm = (Communicator) getActivity();
		btnOpenRequest = (ButtonRegular) t_secondActivity.findViewById(R.id.activity_fragment_request_button);
		btnOpenRequest.setOnClickListener(openRequestClickListener);
		btnActivity = (ButtonRegular) t_secondActivity.findViewById(R.id.activity_fragment_activity_button);
		txtToolbarActivity = (TextViewRegular) t_secondActivity.findViewById(R.id.activity_fragment_toolbar_text);
		//fills up the listview
		activityListView = (ListView) t_secondActivity.findViewById(R.id.activity_fragment_screen_activities_listview);

		//activityListView.setAdapter(null);
		getActivityList(sendModel());
		setTextSize();

	}

	private void setTextSize() {
		txtToolbarActivity.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getToolbarTextSize());
		btnActivity.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getFragBtnTextSize());
		btnOpenRequest.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getFragBtnTextSize());
	}

	//send data to web service
	private ActivityModel sendModel() {
		ActivityModel requestModel = new ActivityModel();
		requestModel.setPage("1");
		requestModel.setRecPerPage("10");
		//requestModel.setUserID("10027");
		//	requestModel.setUserID("10032");
		requestModel.setUserID(ItbarxGlobal.getInstance().getAccountModel().getUserID());
		return requestModel;
	}

	public void getActivityList(ActivityModel activityModel) {
		ActivityProcessesServiceSL activityProcessesServiceSL = new ActivityProcessesServiceSL(t_secondActivity.getContext(), activityProcessesServiceListener, R.string.root_service_url);
		activityProcessesServiceSL.setActivityList(activityModel);
		t_secondActivity.showProgress(getString(R.string.ItbarxConnecting));
	}

	OneShotOnClickListener openRequestClickListener = new OneShotOnClickListener(500) {

		@Override public void onOneShotClick(View v) {
			comm.choose(Fragments.ACTIVITY.name());
		}
	};

	ActivityProcessesServiceListener<String> activityProcessesServiceListener = new ActivityProcessesServiceListener<String>() {

		@Override public void onComplete(ResponseEventModel<String> onComplete) {
			t_secondActivity.dismissProgress();

		}

		@Override public void onError(BarxErrorModel onError) {
			t_secondActivity.dismissProgress();

		}

		@Override public void getActivityList(List<ActivityListModel> activityListModel) {
			t_secondActivity.dismissProgress();
			activityListModels = activityListModel;
			activityListView.setAdapter(new ActivityFragmentListAdapter(t_secondActivity.getContext(), activityListModel));
		}
	};


}