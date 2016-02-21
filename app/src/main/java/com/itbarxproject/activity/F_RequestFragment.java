package com.itbarxproject.activity;

import com.itbarxproject.R;
import com.itbarxproject.adapter.RequestFragmentListAdapter;
import com.itbarxproject.application.ItbarxGlobal;
import com.itbarxproject.custom.component.ButtonRegular;
import com.itbarxproject.custom.component.TextViewRegular;
import com.itbarxproject.enums.Fragments;
import com.itbarxproject.model.follow.FollowUserModel;
import com.itbarxproject.service.ResponseEventModel;
import com.itbarxproject.service.error.BarxErrorModel;
import com.itbarxproject.listener.FollowingProcessesServiceListener;
import com.itbarxproject.listener.OneShotOnClickListener;
import com.itbarxproject.model.follow.FollowerListByFollowingIdModel;
import com.itbarxproject.model.follow.FollowerListModel;
import com.itbarxproject.model.follow.FollowingListByFollowerIdModel;
import com.itbarxproject.model.follow.PendingListByFollowingIdModel;
import com.itbarxproject.model.follow.SendPendingListByFollowerIdModel;
import com.itbarxproject.sl.FollowingProcessesServiceSL;
import com.itbarxproject.utils.FinalString;
import com.itbarxproject.utils.TextSizeUtil;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
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
    private String userID;
    private ListView requestListView;
    List<PendingListByFollowingIdModel> pendingListModels;
    private T_SecondActivity t_secondActivity;

    public F_RequestFragment() {
    }

    public static F_RequestFragment newInstance(T_SecondActivity t_secondActivity) {
        F_RequestFragment myFragment = new F_RequestFragment();
        myFragment.t_secondActivity = t_secondActivity;
        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {

        return inflater.inflate(R.layout.fragment_request_screen, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        //sends the message via communicator interface.
        comm = (Communicator) getActivity();
        btnOpenActivity = (ButtonRegular) t_secondActivity.findViewById(R.id
				.request_fragment_activity_button);
        btnOpenActivity.setOnClickListener(openActivityClickListener);
        btnRequest = (ButtonRegular) t_secondActivity.findViewById(R.id
				.request_fragment_request_button);
        txtToolbarRequest = (TextViewRegular) t_secondActivity.findViewById(R.id
				.request_fragment_toolbar_text);
        //fills up the listview
        requestListView = (ListView) t_secondActivity.findViewById(R.id
				.request_fragment_screen_listView);
        setTextSize();
        getPendingList(sendModel());
    }

    private void setTextSize() {
        txtToolbarRequest.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getToolbarTextSize
				());
        btnOpenActivity.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getFragBtnTextSize());
        btnRequest.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getFragBtnTextSize());
    }

    OneShotOnClickListener openActivityClickListener = new OneShotOnClickListener(500) {

        @Override
        public void onOneShotClick(View v) {
            comm.choose(Fragments.REQUEST.name());
        }
    };

    public void getPendingList(FollowerListModel followerListModel) {
        FollowingProcessesServiceSL followingProcessesServiceSL = new FollowingProcessesServiceSL
				(t_secondActivity.getContext(), followingProcessesServiceListener, R.string
						.root_service_url);
        followingProcessesServiceSL.setGetPendingList(followerListModel);

        t_secondActivity.showProgress(getString(R.string.ItbarxConnecting));

    }

    //send data to the web service
    private FollowerListModel sendModel() {

        FollowerListModel sendModel = new FollowerListModel();
        sendModel.setPage("1");
        sendModel.setRecPerPage("10");
        setUserID(ItbarxGlobal.getInstance().getAccountModel().getUserID());
        sendModel.setFollowingId(ItbarxGlobal.getInstance().getAccountModel().getUserID());
        //sendModel.setFollowingId(getUserID());
       // sendModel.setFollowingId("10032");
        return sendModel;

    }

    public void addFollow(String id) {

        FollowUserModel model = new FollowUserModel();
        model.setFollowingID(getUserID());
        model.setFollowerID(id);
        FollowingProcessesServiceSL followingProcessesServiceSL = new FollowingProcessesServiceSL
				(t_secondActivity.getContext(), followingProcessesServiceListener, R.string
						.root_service_url);
        followingProcessesServiceSL.setAdd(model);
    }

    public void removeFollow(String id) {

        FollowUserModel model = new FollowUserModel();
        model.setFollowingID(getUserID());
        model.setFollowerID(id);
        FollowingProcessesServiceSL followingProcessesServiceSL = new FollowingProcessesServiceSL
                (t_secondActivity.getContext(), followingProcessesServiceListener, R.string
                        .root_service_url);
        followingProcessesServiceSL.setDeleteFollow(model);

    }

    public void acceptRequest(String id){
        FollowUserModel model = new FollowUserModel();
        model.setFollowingID(getUserID());
        model.setFollowerID(id);
        FollowingProcessesServiceSL followingProcessesServiceSL = new FollowingProcessesServiceSL
                (t_secondActivity.getContext(), followingProcessesServiceListener, R.string
                        .root_service_url);
        followingProcessesServiceSL.setUpdateAsFriend(model);
    }

    public void denyRequest(String id){
        FollowUserModel model = new FollowUserModel();
        model.setFollowingID(getUserID());
        model.setFollowerID(id);
        FollowingProcessesServiceSL followingProcessesServiceSL = new FollowingProcessesServiceSL
                (t_secondActivity.getContext(), followingProcessesServiceListener, R.string
                        .root_service_url);
        followingProcessesServiceSL.setUpdateAsBlocked(model);

    }

    FollowingProcessesServiceListener<String> followingProcessesServiceListener = new
			FollowingProcessesServiceListener<String>() {
        @Override
        public void add(String isAdded) {
            t_secondActivity.dismissProgress();
            if (isAdded==null ||isAdded.equalsIgnoreCase(FinalString.NULL))
            {
                Log.d("Request Fragment", "Add Follow request has been responded as error.");
            }
            else if (isAdded.equalsIgnoreCase(FinalString.TRUE)){
                Log.d("Request Fragment", "Add Follow is accomplished.");
                getPendingList(sendModel());
            }
            else if (isAdded.equalsIgnoreCase(FinalString.FALSE))
            {
                Log.d("Request Fragment", "Add Follow is failed.");
            }


        }

        @Override
        public void updateAsFriend(String isUpdateAsFriend) {
            t_secondActivity.dismissProgress();
            if (isUpdateAsFriend==null ||isUpdateAsFriend.equalsIgnoreCase(FinalString.NULL))
            {
                Log.d("Request Fragment", "Update As Friend process has been responded as error.");
            }
            else if (isUpdateAsFriend.equalsIgnoreCase(FinalString.TRUE)){
                Log.d("Request Fragment", "Update As Friend is accomplished.");
                getPendingList(sendModel());
            }
            else if (isUpdateAsFriend.equalsIgnoreCase(FinalString.FALSE))
            {
                Log.d("Request Fragment", "Update As Friend is failed.");
            }


        }

        @Override
        public void updateAsBlocked(String isUpdateAsBlocked) {
            t_secondActivity.dismissProgress();
            if (isUpdateAsBlocked==null ||isUpdateAsBlocked.equalsIgnoreCase(FinalString.NULL))
            {
                Log.d("Request Fragment", "Update As Blocked process has been responded as error.");
            }
            else if (isUpdateAsBlocked.equalsIgnoreCase(FinalString.TRUE)){
                Log.d("Request Fragment", "Update As Blocked is accomplished.");
                getPendingList(sendModel());
            }
            else if (isUpdateAsBlocked.equalsIgnoreCase(FinalString.FALSE))
            {
                Log.d("Request Fragment", "Update As Blocked is failed.");
            }


        }

        @Override
        public void countFollower(String count) {
            t_secondActivity.dismissProgress();
        }

        @Override
        public void countFollowing(String count) {
            t_secondActivity.dismissProgress();
        }

        @Override
        public void countPending(String count) {
            t_secondActivity.dismissProgress();
        }

        @Override
        public void countSendPending(String count) {
            t_secondActivity.dismissProgress();
        }

        @Override
        public void deleteFollow(String isDeleted) {
            t_secondActivity.dismissProgress();
            if (isDeleted==null ||isDeleted.equalsIgnoreCase(FinalString.NULL))
            {
                Log.d("Request Fragment", "Delete Follow request has been responded as error.");
            }
            else if (isDeleted.equalsIgnoreCase(FinalString.TRUE)){
                Log.d("Request Fragment", "Delete Follow is accomplished.");
            }
            else if (isDeleted.equalsIgnoreCase(FinalString.FALSE))
            {
                Log.d("Request Fragment", "Delete Follow is failed.");
            }
            getPendingList(sendModel());
        }

        @Override
        public void getFollowerListById(List<FollowerListByFollowingIdModel>
												followerListByFollowingIdModel) {
            t_secondActivity.dismissProgress();
        }

        @Override
        public void getFollowingListById(List<FollowingListByFollowerIdModel>
												 followingListByFollowerIdModel) {
            t_secondActivity.dismissProgress();
        }

        @Override
        public void getPendingListById(List<PendingListByFollowingIdModel>
											   pendingListByFollowingIdModel) {
            t_secondActivity.dismissProgress();
           if (pendingListByFollowingIdModel!=null && pendingListByFollowingIdModel.size()>0) {

               pendingListModels = pendingListByFollowingIdModel;
               requestListView.setAdapter(new RequestFragmentListAdapter(t_secondActivity,
                       pendingListModels, F_RequestFragment.this));
           }
        }

        @Override
        public void getSendPendingListById(List<SendPendingListByFollowerIdModel> sendPendingListByFollowerIdModel) {
            t_secondActivity.dismissProgress();
        }

        @Override
        public void onComplete(ResponseEventModel<String> onComplete) {
            t_secondActivity.dismissProgress();
        }

        @Override
        public void onError(BarxErrorModel onError) {
            t_secondActivity.dismissProgress();
        }
    };

    public String getUserID() {
        return userID;
    }

    public F_RequestFragment setUserID(String userID) {
        this.userID = userID;
        return this;
    }
}