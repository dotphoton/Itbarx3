package com.itbarx.utils;

import android.content.Context;

import com.itbarx.activity.BarkActivity;
import com.itbarx.activity.BaseActivity;
import com.itbarx.activity.OtherUserActivity;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Umut on 12.10.2015.
 */
public class BarkUtility {

    protected final static String POST_ID_KEY ="POST_ID";
    protected final static String USER_ID_KEY ="USER_ID";

    public static  void  goBarkDetail(BaseActivity activity,String postId)
    {
        List<NameValuePair> extras = new ArrayList<NameValuePair>();
        extras.add(new BasicNameValuePair(POST_ID_KEY,postId));
        activity.launchSubActivityAddStringExtra(BarkActivity.class, extras);
    }

    public static String getPostId(BarkActivity barkActivity)
    {
        if (barkActivity.getIntent().hasExtra(POST_ID_KEY)) {
            String   postId = barkActivity.getIntent().getStringExtra(POST_ID_KEY);
            return postId;
        }
        else
        {
            return null;
        }
    }

    public static  void  goOtherUserProfil(BaseActivity activity,String userId)
    {
        List<NameValuePair> extras = new ArrayList<NameValuePair>();
        extras.add(new BasicNameValuePair(USER_ID_KEY,userId));
        activity.launchSubActivityAddStringExtra(OtherUserActivity.class, extras);
    }

    public static String getUserId(OtherUserActivity otherUserActivity)
    {
        if (otherUserActivity.getIntent().hasExtra(USER_ID_KEY)) {
            String   userId = otherUserActivity.getIntent().getStringExtra(USER_ID_KEY);
            return userId;
        }
        else
        {
            return null;
        }
    }


}
