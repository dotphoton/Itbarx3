package com.itbarxproject.activity;

import android.app.Dialog;
import android.app.FragmentManager;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.facebook.login.widget.LoginButton;
import com.itbarxproject.R;
import com.itbarxproject.application.ItbarxGlobal;
import com.itbarxproject.common.LoadHttpImage;
import com.itbarxproject.custom.component.TextViewBold;
import com.itbarxproject.custom.component.TextViewRegular;
import com.itbarxproject.exception.ExceptionHandler;
import com.itbarxproject.model.like.LikeModel;
import com.itbarxproject.model.like.LikePostListModel;
import com.itbarxproject.model.rebark.ReBarkSendPostShareAddModel;
import com.itbarxproject.model.rebark.ReBarkSendPostSharedUserModel;
import com.itbarxproject.service.ResponseEventModel;
import com.itbarxproject.service.error.BarxErrorModel;
import com.itbarxproject.listener.LikeProcessesServiceListener;
import com.itbarxproject.listener.OneShotOnClickListener;
import com.itbarxproject.listener.PostProcessesServiceListener;
import com.itbarxproject.listener.ReBarkProcessesServiceListener;
import com.itbarxproject.listener.ReplyProcessesServiceListener;
import com.itbarxproject.model.like.LikePostsByUserIdModel;
import com.itbarxproject.model.like.LikeUserListModel;
import com.itbarxproject.model.like.LikeUsersByPostIdModel;
import com.itbarxproject.model.post.PostGetPostDetailModel;
import com.itbarxproject.model.post.PostGetWallInfoModel;
import com.itbarxproject.model.post.PostNewPostListModel;
import com.itbarxproject.model.post.PostPopularPostListModel;
import com.itbarxproject.model.post.PostPostDetailModel;
import com.itbarxproject.model.post.PostTimelineListForUserModel;
import com.itbarxproject.model.post.PostWallListForUserModel;
import com.itbarxproject.model.rebark.ReBarkGetPostSharedUserListByPostIdModel;
import com.itbarxproject.model.rebark.ReBarkGetSharedPostListByUserIdModel;
import com.itbarxproject.model.reply.ReplyListModel;
import com.itbarxproject.model.reply.ReplySendModel;
import com.itbarxproject.model.send_to_fragment.LikeData;
import com.itbarxproject.model.send_to_fragment.ReBarksData;
import com.itbarxproject.model.send_to_fragment.ReplyData;
import com.itbarxproject.sl.LikeProcessesServiceSL;
import com.itbarxproject.sl.LikeSL;
import com.itbarxproject.sl.PostProcessesServiceSL;
import com.itbarxproject.sl.ReBarkSL;
import com.itbarxproject.sl.ReplySL;
import com.itbarxproject.utils.BarkUtility;
import com.itbarxproject.utils.FinalString;
import com.itbarxproject.utils.TextSizeUtil;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BarkActivity extends BaseActivity implements TextureView.SurfaceTextureListener {

    @Bind(R.id.bark_activity_screen_like_icon_imageView)
    ImageView imgLike;
    @Bind(R.id.bark_activity_screen_reBark_icon)
    ImageView imgReBark;
    @Bind(R.id.bark_activity_screen_reply_icon)
    ImageView imgReply;
    @Bind(R.id.bark_activity_screen_side_panel)
    FrameLayout sidePanel;
    @Bind(R.id.bark_activity_screen_drawer_layout)
    DrawerLayout drawerLayout;

    private S_Fragment_Like sFragmentLike;
    private S_Fragment_ReBark sFragmentReBark;
    private S_Fragment_Reply sFragmentReply;
    private FragmentTransaction ft;
    private FragmentManager fm;
    private ViewGroup.LayoutParams params;
    private MediaPlayer mMediaPlayer;
    private PostGetPostDetailModel selectedModel;
    private String VIDEO_VIRTUAL_PATH_NAME = Environment.getExternalStorageDirectory() + "/com" +
            ".itbarx";
    private String filePath = "";
    protected String POST_ID = null;
    private String userID;
    private String postText;
    private TextViewRegular txtToolbar, txtSubtitle;
    private TextViewBold txtLikeCount, txtReBarkCount, txtReplyCount, txtLike, txtReBark, txtReply;
    private ImageView imgVideoPlay, imgVideoPause, imgThumbnail;
    private TextureView videoView;
    private View likeClickable, replyClickable, reBarkClickable;

    boolean isEnterMediaDataSource =false;

    protected boolean isMediaRunning = false;
    boolean isMirrored = true;
    boolean isPauseEnter = false;
    boolean isFileDelete = false;
    boolean isStop = false;
    private boolean isConfigOk = false;

    private static final int WIDTH_5_4;
    private ArrayList<LikeData> likeDataList;
    private ArrayList<ReBarksData> reBarksDataList;
    private ArrayList<ReplyData> replyDataList;
    private static final String KEY_LIKE_DATA_LIST;
    private static final String KEY_REBARK_DATA_LIST;
    private static final String KEY_REPLY_DATA_LIST;

    static {
        WIDTH_5_4 = (int) (ItbarxGlobal.getDisplayPxWidth() / 5 * 4);
        KEY_LIKE_DATA_LIST = "KeyLikeData";
        KEY_REBARK_DATA_LIST = "KeyReBarkData";
        KEY_REPLY_DATA_LIST = "KeyReplyData";
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_bark_screen;
    }

    @Override
    protected Context getContext() {
        return BarkActivity.this;
    }

    @Override
    protected void exceptionHandler() {
      //  Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(BarkActivity.this));
    }

    @Override
    protected void initViews() {


        if ((POST_ID = BarkUtility.getPostId(BarkActivity.this)) != null) {
            ButterKnife.bind(this);
            setUserID(ItbarxGlobal.getUserId());
            imgVideoPlay = (ImageView) findViewById(R.id
                    .bark_activity_screen_video_thumbnail_play_ImageView);
            imgThumbnail = (ImageView) findViewById(R.id
                    .bark_activity_screen_video_thumbnail_ImageView);
            imgVideoPause = (ImageView) findViewById(R.id
                    .bark_activity_screen_video_thumbnail_pause_ImageView);
            imgVideoPause.setVisibility(View.INVISIBLE);
            imgVideoPlay.setOnClickListener(playVideoClickListener);
            imgVideoPause.setOnClickListener(pauseVideoClickListener);
            txtToolbar = (TextViewRegular) findViewById(R.id.bark_activity_screen_toolbar_textView);
            txtSubtitle = (TextViewRegular) findViewById(R.id
                    .bark_activity_screen_subtitle_TextView);
            txtLike = (TextViewBold) findViewById(R.id.bark_activity_screen_like_text_TextView);
            txtReBark = (TextViewBold) findViewById(R.id.bark_activity_screen_rebark_text_TextView);
            txtReply = (TextViewBold) findViewById(R.id.bark_activity_screen_reply_text_TextView);
            txtLikeCount = (TextViewBold) findViewById(R.id
                    .bark_activity_screen_like_count_TextView);
            txtReBarkCount = (TextViewBold) findViewById(R.id
                    .bark_activity_screen_rebark_count_TextView);
            txtReplyCount = (TextViewBold) findViewById(R.id
                    .bark_activity_screen_reply_count_TextView);
            videoView = (TextureView) findViewById(R.id.media_record_preview_main_videoView);
            likeClickable = (View) findViewById(R.id.bark_activity_screen_like_clickable_view);
            replyClickable = (View) findViewById(R.id.bark_activity_screen_reply_clickable_view);
            reBarkClickable = (View) findViewById(R.id.bark_activity_screen_rebark_clickable_view);
            setCompText();
            //SET SIDE PANEL'S WIDTH.
            sidePanel.post(new Runnable() {
                @Override
                public void run() {

                    params = sidePanel.getLayoutParams();
                    params.width = WIDTH_5_4;
                    sidePanel.setLayoutParams(params);
                }
            });

            //START FRAGMENT MANAGER
            fm = getFragmentManager();
            videoView.setSurfaceTextureListener(this);
            videoView.setScaleX(isMirrored ? -1 : 1);

            //*******************************************************************************
            try {
                //Kullanıcın daha önceden beğenmiş olduğu postların listesi
              //  getLikeListByUser();


               refreshPostDetail();

            } catch (Exception e) {
                writeLog("ITbarx", "BarkActivity  initViews" + e.getMessage());
            }
            txtLike.setOnClickListener(likeTextClickListener);
            txtLikeCount.setOnClickListener(likeTextClickListener);
            likeClickable.setOnClickListener(likeTextClickListener);
            replyClickable.setOnClickListener(replyTextClickListener);
            txtReplyCount.setOnClickListener(replyTextClickListener);
            txtReply.setOnClickListener(replyTextClickListener);
            reBarkClickable.setOnClickListener(reBarkClikableListener);
            txtReBark.setOnClickListener(reBarkClikableListener);
            txtReBarkCount.setOnClickListener(reBarkClikableListener);
            //*******************************************************************************

            imgLike.setOnClickListener(new OneShotOnClickListener(1000) {
                @Override
                public void onOneShotClick(View v) {
                    LikeModel model = new LikeModel();
                    model.setUserId(ItbarxGlobal.getInstance().getAccountModel().getUserID());
                    model.setPostId(POST_ID);
                    LikeSL likeSL = new LikeSL(getContext(), likeProcessesServiceListener, R
                            .string.root_service_url);


                    if (selectedModel.getLikedBefore().equalsIgnoreCase("1")) {
                        likeSL.setDeleteLike(model);
                        showProgress(getString(R.string.ItbarxConnecting));
                    } else {
                        // zero is default value for user liked.
                        likeSL.setAddLike(model);
                        showProgress(getString(R.string.ItbarxConnecting));

                    }


                }
            });
            imgReBark.setOnClickListener(new OneShotOnClickListener(500) {
                @Override
                public void onOneShotClick(View v) {
                    if (selectedModel.getSharedBefore().equalsIgnoreCase("1"))
                    {
                        return;
                    }
                    ReBarkSendPostShareAddModel model = new ReBarkSendPostShareAddModel();
                    model.setUserId(ItbarxGlobal.getInstance().getAccountModel().getUserID());
                    model.setPostId(POST_ID);
                    model.setSharedText(getPostText());
                    ReBarkSL reBarkSL = new ReBarkSL(BarkActivity.this,
                            reBarkProcessesServiceListener, R
                            .string.root_service_url);
                    reBarkSL.setAddPostShare(model);

                }
            });

            imgReply.setOnClickListener(new OneShotOnClickListener(500) {
                @Override
                public void onOneShotClick(View v) {

                    launchSubActivityAddString(ReplyRecordActivity.class, BarkUtility
                            .POST_ID_KEY, POST_ID);
                    finish();
                    showProgress(getString(R.string.ItbarxConnecting));
                }
            });

        } else {
            finish();
        }

    }

    OneShotOnClickListener reBarkClikableListener = new OneShotOnClickListener(500) {
        @Override
        public void onOneShotClick(View v) {

            if(selectedModel.getPostRebarkCount().equalsIgnoreCase("0"))
            {
                return;
            }
            ReBarkSendPostSharedUserModel sendUserModel = new
                    ReBarkSendPostSharedUserModel(POST_ID, "1",
                    "20");

            ReBarkSL reBarkSL = new ReBarkSL(getContext(), reBarkProcessesServiceListener,
                    R.string.root_service_url);
            reBarkSL.setGetPostSharedUser(sendUserModel);
            showProgress(getString(R.string.ItbarxConnecting));
        }
    };

    OneShotOnClickListener replyTextClickListener = new OneShotOnClickListener(500) {
        @Override
        public void onOneShotClick(View v) {
            if(selectedModel.getPostReplyCount().equalsIgnoreCase("0"))
            {
                return;
            }
            ReplySendModel replySendModel = new ReplySendModel
                    (POST_ID, "1", "10");

            ReplySL replySL = new ReplySL(getContext(), replyProcessesServiceListener, R
                    .string.root_service_url);
            replySL.setGetPostRepliesList(replySendModel);

        }
    };

    private OneShotOnClickListener likeTextClickListener = new OneShotOnClickListener(500) {
        @Override
        public void onOneShotClick(View v) {
            if(selectedModel.getPostLikeCount().equalsIgnoreCase("0"))
            {
                return;
            }
            //-------------------------------------------------------------------
            LikeUserListModel likeUserListModel = new LikeUserListModel(POST_ID, "1",
                    "1000");
            //LikeProcessesServiceSL likeProcessesServiceSL = new LikeProcessesServiceSL
            // (getContext(), likeProcessesServiceListener, R.string.root_service_url);
            //likeProcessesServiceSL.setGetLikeUsersByPostId(likeUserListModel);
            LikeSL likeSL = new LikeSL(getContext(), likeProcessesServiceListener, R
                    .string.root_service_url);
            likeSL.setGetLikeUsersByPostId(likeUserListModel);
            showProgress(getString(R.string.ItbarxConnecting));
            //-------------------------------------------------------------------


        }
    };

    void getLikeListByUser() {
        if(selectedModel.getPostLikeCount().equalsIgnoreCase("0"))
        {
            return;
        }
        LikePostListModel model = new LikePostListModel();
        model.setPage("1");
        model.setRecPerPage("1");

        model.setUserId(ItbarxGlobal.getInstance().getAccountModel().getUserID());
        LikeSL likeProcessesServiceSL = new LikeSL(BarkActivity.this,
                likeProcessesServiceListener, R.string.root_service_url);
        likeProcessesServiceSL.setGetLikePostsByUserId(model);
    }

    private void setCompText() {
        txtToolbar.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getToolbarTextSize());
        txtSubtitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getBarkSubtitleTextSize());
        txtLike.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getBarkMiniBtnTextSize());
        txtReBark.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getBarkMiniBtnTextSize());
        txtReply.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getBarkMiniBtnTextSize());
        txtLikeCount.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getBarkCountTextSize());
        txtReBarkCount.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getBarkCountTextSize());
        txtReplyCount.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getBarkCountTextSize());

    }

    protected void stopPlayer() {
        mMediaPlayer.stop();
        mMediaPlayer.pause();
        isStop = true;
        ((ImageView) findViewById(R.id.bark_activity_screen_video_thumbnail_play_ImageView))
                .setVisibility(View.VISIBLE);

    }

    private void setBarkDetail(PostGetPostDetailModel postDetailModel) {
        selectedModel = postDetailModel;
        txtReBarkCount.setText(postDetailModel.getPostRebarkCount());
        txtLikeCount.setText(postDetailModel.getPostLikeCount());
        txtReplyCount.setText(postDetailModel.getPostReplyCount());
        txtSubtitle.setText(postDetailModel.getPostSpeechToText());
        txtToolbar.setText(postDetailModel.getItBarxUserName());

        if(selectedModel.getLikedBefore().equalsIgnoreCase("0"))
        {
            imgLike.setImageResource(R.drawable.bark_like_icon);
        }
        else
        {
            imgLike.setImageResource(R.drawable.iconlikehover);
        }
        if(selectedModel.getSharedBefore().equalsIgnoreCase("0"))
        {
            imgReBark.setImageResource(R.drawable.bark_rebark_icon);
        }
        else
        {
            imgReBark.setImageResource(R.drawable.iconrebarkhover);
        }

    }
    void refreshPostDetail()
    {
        PostPostDetailModel postDetailModel = new PostPostDetailModel();
        postDetailModel.setPostID(POST_ID);
        PostProcessesServiceSL processesServiceSL = new PostProcessesServiceSL(getContext
                (), postProcessesServiceListener, R.string.root_service_url);
        processesServiceSL.setGetPostDetail(postDetailModel);
        showProgress(getString(R.string.ItbarxConnecting));
    }
    PostProcessesServiceListener<String> postProcessesServiceListener = new
            PostProcessesServiceListener<String>() {
                @Override
                public void getTimelineListForUser(List<PostTimelineListForUserModel>
                                                           postTimelineListForUserModel) {
                    dismissProgress();
                }

                @Override
                public void getWallListForUser(List<PostWallListForUserModel>
                                                       postWallListForUserModel) {
                    dismissProgress();
                }

                @Override
                public void getPopularPostList(List<PostPopularPostListModel>
                                                       popularPostListModel) {
                    dismissProgress();
                }

                @Override
                public void getNewPostList(List<PostNewPostListModel> postNewPostListModels) {
                    dismissProgress();
                }

                @Override
                public void getWallInfo(PostGetWallInfoModel postGetWallInfoModel) {
                    dismissProgress();
                }

                @Override
                public void getPostDetail(PostGetPostDetailModel postDetailModel) {
                    dismissProgress();
                    setBarkDetail(postDetailModel);
                    setPostText((postDetailModel != null && !postDetailModel.getPostSpeechToText
                            ().equals("")) ?
                            postDetailModel.getPostSpeechToText() : StringUtils.EMPTY);

                    if (postDetailModel.getPostRebarkCount() != null && !postDetailModel
                            .getPostRebarkCount().equals(StringUtils.EMPTY)) {
                        txtReBarkCount.setText(postDetailModel.getPostRebarkCount());
                    }
                    if (postDetailModel.getPostLikeCount() != null && !postDetailModel
                            .getPostLikeCount().equals(StringUtils.EMPTY)) {
                        txtLikeCount.setText(postDetailModel.getPostLikeCount());
                    }
                    if (postDetailModel.getPostReplyCount() != null && !postDetailModel
                            .getPostReplyCount().equals(StringUtils.EMPTY)) {
                        txtReplyCount.setText(postDetailModel.getPostReplyCount());
                    }


                    if (postDetailModel != null && postDetailModel.getPostURL().length() > 0) {
                        videoView.setVisibility(View.INVISIBLE);
                        if (postDetailModel.getPostPictureURL() != null) {
                            try {
                                new LoadHttpImage(imgThumbnail).execute(postDetailModel
                                        .getPostPictureURL
                                                ());

                            } catch (Exception e) {

                            }
                        } else {

                        }
                /*
				String url ="https://itbarxmediastorage.blob.core.windows.net"+postDetailModel
				.getPostURL();
				int lastIndex = postDetailModel.getPostURL().lastIndexOf("/");
				String fileName=	postDetailModel.getPostURL().substring(lastIndex+1);
				filePath =VIDEO_VIRTUAL_PATH_NAME+"/"+fileName+".mp4";

				File file = new File(filePath);
				if(file.exists())
				{
					*/
                        if(!isEnterMediaDataSource) {
                            try {
                                //mMediaPlayer.setDataSource(file.getAbsolutePath());
                                mMediaPlayer.setDataSource(postDetailModel.getPostURL());
                                mMediaPlayer.prepare();
                                isEnterMediaDataSource = true;
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    }


                }

                @Override
                public void isAdded(String isAdded) {
                    dismissProgress();
                }

                @Override
                public void onComplete(ResponseEventModel<String> onComplete) {

                }

                @Override
                public void onError(BarxErrorModel onError) {
                    dismissProgress();
                    finish();
                }
            };

    MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            if (!mMediaPlayer.isPlaying()) {
                ((ImageView) findViewById(R.id
                        .bark_activity_screen_video_thumbnail_play_ImageView)).setVisibility(View
                        .VISIBLE);
                ((ImageView) findViewById(R.id
                        .bark_activity_screen_video_thumbnail_pause_ImageView)).setVisibility(View
                        .INVISIBLE);
                //mMediaPlayer.reset();
                isPauseClick = true;
            }

        }
    };
    OneShotOnClickListener playVideoClickListener = new OneShotOnClickListener(500) {
        @Override
        public void onOneShotClick(View v) {
            ((ImageView) findViewById(R.id.bark_activity_screen_video_thumbnail_play_ImageView))
                    .setVisibility(View.INVISIBLE);
            ((ImageView) findViewById(R.id.bark_activity_screen_video_thumbnail_pause_ImageView))
                    .setVisibility(View.VISIBLE);

            imgThumbnail.setVisibility(View.GONE);
            videoView.setVisibility(View.VISIBLE);
            if (isPauseClick) {

                mMediaPlayer.start();
                isPauseClick = false;
            } else {
                mMediaPlayer.start();
            }
        }
    };
				/*if (hasVideoFile) {


				} else {
					if (selectedModel != null) {

						String rootVideoUrl = "https://itbarxmediastorage.blob.core.windows.net";
						int lastIndex = selectedModel.getPostURL().lastIndexOf("/");
						String fileName= selectedModel.getPostURL().substring(lastIndex + 1) + "
						.mp4";
						DownloadManagerAsync managerAsync = new DownloadManagerAsync(BarkActivity
						.this,
								fileName);

						managerAsync.execute(rootVideoUrl +selectedModel.getPostURL());

					}
/*
				}
			}

			if (!isStop) {

				mMediaPlayer.start();
			} else {
				mMediaPlayer.start();
			}
			isStop = false;

		}*/

    boolean isPauseClick = false;
    OneShotOnClickListener pauseVideoClickListener = new OneShotOnClickListener(500) {

        @Override
        public void onOneShotClick(View v) {
            ((ImageView) findViewById(R.id.bark_activity_screen_video_thumbnail_pause_ImageView))
                    .setVisibility(View.INVISIBLE);
            ((ImageView) findViewById(R.id.bark_activity_screen_video_thumbnail_play_ImageView))
                    .setVisibility(View.VISIBLE);
            isPauseClick = true;
            mMediaPlayer.pause();
        }
    };

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setSurface(new Surface(surface));
        mMediaPlayer.setOnCompletionListener(onCompletionListener);
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //FileUtility.deleteAllFileUnderFolder(VIDEO_VIRTUAL_PATH_NAME);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Dialog dialog = showAlert(getString(R.string.bark_activity_screen_are_you_exit),
                    getString(R.string.Yes), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mMediaPlayer.stop();
                            mMediaPlayer.release();
                            finish();
                        }
                    }, getString(R.string.No), null);
            dialog.show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    LikeProcessesServiceListener<String> likeProcessesServiceListener = new
            LikeProcessesServiceListener<String>() {
                @Override
                public void addLike(String isAdded) {
                    dismissProgress();

                    if (isAdded == null || isAdded.equalsIgnoreCase("true")) {
                refreshPostDetail();
                    }

                }

                @Override
                public void deleteLike(String isDeleted) {
                    dismissProgress();
                    if (isDeleted == null || isDeleted.equalsIgnoreCase("true")) {
                        refreshPostDetail();
                    }

                }

                @Override
                public void countLikeByUser(String count) {
                    dismissProgress();
                }

                @Override
                public void countLikeByPost(String count) {
                    dismissProgress();
                }

                @Override
                public void getLikePostByUserId(List<LikePostsByUserIdModel>
                                                        likePostsByUserIdModel) {
                    dismissProgress();
                  /*
                    userLikedThisPost = false;
                    String postId = POST_ID;
                    List<String> likedPosts = new ArrayList<>();

                    if (likePostsByUserIdModel != null) {
                        for (LikePostsByUserIdModel likedPostModel : likePostsByUserIdModel) {
                            String id = likedPostModel.getPostID();
                            if (id != null && !id.equals("")) {
                                likedPosts.add(id);

                            }
                        }
                        if (likedPosts.size() > 0) {
                            for (String pID : likedPosts) {
                                if (postId.equals(pID)) {
                                    userLikedThisPost = true;
                                }

                            }

                        }

                    }
*/
                }

                @Override
                public void getLikeUsersByPostId(List<LikeUsersByPostIdModel>
                                                         likeUsersByPostIdModel) {
                    dismissProgress();
                    likeDataList = new ArrayList<>();

                    if (likeUsersByPostIdModel != null) {
                        for (LikeUsersByPostIdModel models : likeUsersByPostIdModel) {
                            LikeData data = new LikeData(null, models.getItBarxUserName(), models
                                    .getName
                                            (), "true",getUserID());

                            likeDataList.add(data);
                            Log.d("like data list : ", data.getName() + " " + data
                                    .getItBarxUserName());
                        }
                    }
                    Log.d("like data list size : ", likeDataList.size() + "");

                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList(KEY_LIKE_DATA_LIST, likeDataList);

                    ft = fm.beginTransaction();
                    if (sFragmentReply != null && sFragmentReply.isAdded()) {
                        ft.remove(sFragmentReply);
                    }
                    if (sFragmentReBark != null && sFragmentReBark.isAdded()) {
                        ft.remove(sFragmentReBark);
                    }
                    if (sFragmentLike != null && sFragmentLike.isAdded()) {
                        ft.remove(sFragmentLike);

                    }
                    sFragmentLike = S_Fragment_Like.newInstance(BarkActivity.this);
                    sFragmentLike.setArguments(bundle);
                    ft.add(R.id.bark_activity_screen_side_panel, sFragmentLike);
                    ft.show(sFragmentLike);
                    ft.commit();

                    drawerLayout.post(new Runnable() {
                        @Override
                        public void run() {
                            drawerLayout.openDrawer(sidePanel);
                        }
                    });

                }

                @Override
                public void onComplete(ResponseEventModel<String> onComplete) {

                }

                @Override
                public void onError(BarxErrorModel onError) {

                }
            };

    //RE_BARK SERVICES
    ReBarkProcessesServiceListener<String> reBarkProcessesServiceListener = new
            ReBarkProcessesServiceListener<String>() {
                @Override
                public void add(String isAdded) {
                    dismissProgress();

                    if (isAdded == null || isAdded.equalsIgnoreCase("true")) {
                       refreshPostDetail();
                    }


                }

                @Override
                public void delete(String isDeleted) {
                    dismissProgress();

                }

                @Override
                public void getSharedPostList(List<ReBarkGetSharedPostListByUserIdModel>
                                                      reBarkGetSharedPostListByUserIdModel) {
                    dismissProgress();
                }

                @Override
                public void getPostSharedUserList(List<ReBarkGetPostSharedUserListByPostIdModel>
                                                          reBarkPostSharedUserListModel) {
                    dismissProgress();
                    reBarksDataList = new ArrayList<>();
                    if (reBarkPostSharedUserListModel != null) {
                        for (ReBarkGetPostSharedUserListByPostIdModel models :
                                reBarkPostSharedUserListModel) {
                            ReBarksData data = new ReBarksData(null, models.getSharerName(),
                                    models.getSharerName(), "true",getUserID());
                            reBarksDataList.add(data);
                            Log.d("rebark data list : ", data.getName() + " " + data
                                    .getItBarxUserName());
                        }
                    }
                    Log.d("reBarkData list size : ", reBarksDataList.size() + "");

                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList(KEY_REBARK_DATA_LIST, reBarksDataList);
                    ft = fm.beginTransaction();
                    if (sFragmentReply != null && sFragmentReply.isAdded()) {
                        ft.remove(sFragmentReply);
                    }
                    if (sFragmentReBark != null && sFragmentReBark.isAdded()) {
                        ft.remove(sFragmentReBark);
                    }
                    if (sFragmentLike != null && sFragmentLike.isAdded()) {
                        ft.remove(sFragmentLike);

                    }
                    sFragmentReBark = S_Fragment_ReBark.newInstance(BarkActivity.this);
                    sFragmentReBark.setArguments(bundle);
                    ft.add(R.id.bark_activity_screen_side_panel, sFragmentReBark);
                    ft.show(sFragmentReBark);
                    ft.commit();
                    drawerLayout.post(new Runnable() {
                        @Override
                        public void run() {
                            drawerLayout.openDrawer(sidePanel);
                        }
                    });

                }

                @Override
                public void getSharedPostCount(String count) {

                }

                @Override
                public void getUserCount(String count) {

                }

                @Override
                public void onComplete(ResponseEventModel<String> onComplete) {

                }

                @Override
                public void onError(BarxErrorModel onError) {

                }
            };


    //REPLY SERVICES

    ReplyProcessesServiceListener replyProcessesServiceListener = new
            ReplyProcessesServiceListener<String>() {
                @Override
                public void deleteReply(String idDeleted) {

                }

                @Override
                public void getPostRepliesList(List<ReplyListModel> replyListModel) {
                    dismissProgress();

                    replyDataList = new ArrayList<>();
                    if (replyListModel != null) {
                        for (ReplyListModel model : replyListModel) {
                            ReplyData data = new ReplyData("", "", "", model.getPostText(), "",
                                    model.getAddedDate(), model.getItBarxUserName());
                            replyDataList.add(data);
                            Log.d("reply data list : ", data.getTimeAgo() + " " + data
                                    .getItBarxUserName());
                        }

                    }
                    Log.d("reply data list size : ", replyDataList.size() + "");

                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList(KEY_REPLY_DATA_LIST, replyDataList);
                    ft = fm.beginTransaction();
                    if (sFragmentReply != null && sFragmentReply.isAdded()) {
                        ft.remove(sFragmentReply);
                    }
                    if (sFragmentReBark != null && sFragmentReBark.isAdded()) {
                        ft.remove(sFragmentReBark);
                    }
                    if (sFragmentLike != null && sFragmentLike.isAdded()) {
                        ft.remove(sFragmentLike);

                    }
                    sFragmentReply = S_Fragment_Reply.newInstance(BarkActivity.this);
                    sFragmentReply.setArguments(bundle);
                    ft.add(R.id.bark_activity_screen_side_panel, sFragmentReply);
                    ft.show(sFragmentReply);
                    ft.commit();
                    drawerLayout.post(new Runnable() {
                        @Override
                        public void run() {
                            drawerLayout.openDrawer(sidePanel);
                        }
                    });


                }

                @Override
                public void addReply(String isAdded) {

                }

                @Override
                public void onComplete(ResponseEventModel<String> onComplete) {

                }

                @Override
                public void onError(BarxErrorModel onError) {

                }
            };

    public String getUserID() {
        return userID;
    }

    public BarkActivity setUserID(String userID) {
        this.userID = userID;
        return this;
    }

    public String getPostText() {
        return postText;
    }

    public BarkActivity setPostText(String postText) {
        this.postText = postText;
        return this;
    }
}
