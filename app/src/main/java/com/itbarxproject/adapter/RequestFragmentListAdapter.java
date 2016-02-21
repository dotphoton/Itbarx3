package com.itbarxproject.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.itbarxproject.R;
import com.itbarxproject.activity.BaseActivity;
import com.itbarxproject.activity.F_RequestFragment;
import com.itbarxproject.custom.component.TextViewListItemBold;
import com.itbarxproject.listener.OneShotOnClickListener;
import com.itbarxproject.model.follow.PendingListByFollowingIdModel;
import com.itbarxproject.utils.BarkUtility;
import com.itbarxproject.utils.FinalString;

import java.util.List;

public class RequestFragmentListAdapter extends BaseAdapter {

    Context context;
    BaseActivity activity;
    List<PendingListByFollowingIdModel> list;
    F_RequestFragment f_requestFragment;
    private String otherUserId;

    public RequestFragmentListAdapter(BaseActivity activity, List<PendingListByFollowingIdModel>
            models, F_RequestFragment f_requestFragment) {
        this.context = activity;
        this.activity = activity;
        this.f_requestFragment = f_requestFragment;
        list = models;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.row_fragment_request_screen_item, parent,
                false);
        PendingListByFollowingIdModel model = (PendingListByFollowingIdModel) getItem(position);
        if (null != model) {
            otherUserId = model.getUserID();
            LinearLayout layOut = (LinearLayout) convertView.findViewById(R.id
                    .row_fragment_request_screen_clickable_layout);
            //1. take user photo
            ImageView imgPhoto = (ImageView) convertView.findViewById(R.id
                    .row_fragment_request_screen_user_imageView);
            if (model.getUserProfilePhoto() != null && !model.getUserProfilePhoto()
                    .equalsIgnoreCase("")) {
                //photo yu al
            }
            //2.  take user name
            TextViewListItemBold txtFullname = (TextViewListItemBold) convertView.findViewById(R
                    .id.row_fragment_request_screen_fullName_textView);
            txtFullname.setText(model.getItBarxUserName());

            //3. take follow status of the user
            ImageView imgBtnFollow = (ImageView) convertView.findViewById(R.id
                    .row_fragment_request_screen_follow_imageButton);
            if (model.getUserFollowStatus().equalsIgnoreCase("true")) {
                imgBtnFollow.setImageResource(R.drawable.req_icon_unfollow);
                imgBtnFollow.setTag(FinalString.FOLLOW);
            } else {
                imgBtnFollow.setImageResource(R.drawable.req_icon_follow);
                imgBtnFollow.setTag(FinalString.UNFOLLOW);
            }
            imgBtnFollow.setOnClickListener(followUnfollowClickListener);
            imgBtnFollow.setOnClickListener(acceptClickListener);
            imgBtnFollow.setOnClickListener(denyClickListener);

            // accept request
            ImageView imgBtnAccept = (ImageView) convertView.findViewById(R.id
                    .row_fragment_request_screen_ok_imageButton);
            imgBtnAccept.setOnClickListener(acceptClickListener);

            // deny request
            ImageView imgBtnDeny = (ImageView) convertView.findViewById(R.id
                    .row_fragment_request_screen_deny_imageButton);
            imgBtnDeny.setOnClickListener(acceptClickListener);

            layOut.setOnClickListener(openProfileClickListener);
            if (!model.getUserID().equals("")) {

                layOut.setTag(model.getUserID());
            }
        }

        return convertView;
    }

    OneShotOnClickListener acceptClickListener = new OneShotOnClickListener(500) {
        @Override
        public void onOneShotClick(View v) {
            f_requestFragment.acceptRequest(otherUserId);

        }
    };
    OneShotOnClickListener denyClickListener = new OneShotOnClickListener(500) {
        @Override
        public void onOneShotClick(View v) {
f_requestFragment.denyRequest(otherUserId);
        }
    };


    OneShotOnClickListener followUnfollowClickListener = new OneShotOnClickListener(500) {
        @Override
        public void onOneShotClick(View v) {
            String tag = v.getTag().toString();
            if (tag != null) {
                if (tag.equalsIgnoreCase(FinalString.FOLLOW)) {
                    f_requestFragment.removeFollow(otherUserId);

                } else if (tag.equalsIgnoreCase(FinalString.UNFOLLOW)) {
                    f_requestFragment.addFollow(otherUserId);

                }
            }


        }
    };


    OneShotOnClickListener openProfileClickListener = new OneShotOnClickListener(500) {
        @Override
        public void onOneShotClick(View v) {
            String makerUserId = v.getTag().toString();
            Log.d("TEST", "TEST " + makerUserId);
            BarkUtility.goProfileScreen(activity, makerUserId);

        }
    };


}
