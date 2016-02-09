package com.itbarx.activity;

import android.app.Dialog;
import android.app.FragmentManager;
import android.app.DownloadManager;
import android.media.AudioManager;
import android.net.Uri;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
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

import com.itbarx.R;
import com.itbarx.application.ItbarxGlobal;
import com.itbarx.common.DownloadManagerAsync;
import com.itbarx.custom.component.TextViewBold;
import com.itbarx.custom.component.TextViewRegular;
import com.itbarx.exception.ExceptionHandler;
import com.itbarx.service.ResponseEventModel;
import com.itbarx.service.error.BarxErrorModel;
import com.itbarx.listener.LikeProcessesServiceListener;
import com.itbarx.listener.OneShotOnClickListener;
import com.itbarx.listener.PostProcessesServiceListener;
import com.itbarx.listener.ReBarkProcessesServiceListener;
import com.itbarx.listener.ReplyProcessesServiceListener;
import com.itbarx.model.like.LikePostsByUserIdModel;
import com.itbarx.model.like.LikeUserListModel;
import com.itbarx.model.like.LikeUsersByPostIdModel;
import com.itbarx.model.post.PostGetPostDetailModel;
import com.itbarx.model.post.PostGetWallInfoModel;
import com.itbarx.model.post.PostNewPostListModel;
import com.itbarx.model.post.PostPopularPostListModel;
import com.itbarx.model.post.PostPostDetailModel;
import com.itbarx.model.post.PostTimelineListForUserModel;
import com.itbarx.model.post.PostWallListForUserModel;
import com.itbarx.model.rebark.ReBarkGetPostSharedUserListByPostIdModel;
import com.itbarx.model.rebark.ReBarkGetSharedPostListByUserIdModel;
import com.itbarx.model.rebark.ReBarkSendPostSharedUserModel;
import com.itbarx.model.reply.ReplyListModel;
import com.itbarx.model.reply.ReplySendModel;
import com.itbarx.model.send_to_fragment.LikeData;
import com.itbarx.model.send_to_fragment.ReBarksData;
import com.itbarx.model.send_to_fragment.ReplyData;
import com.itbarx.sl.LikeProcessesServiceSL;
import com.itbarx.sl.LikeSL;
import com.itbarx.sl.PostProcessesServiceSL;
import com.itbarx.sl.ReBarkProcessesServiceSL;
import com.itbarx.sl.ReBarkSL;
import com.itbarx.sl.ReplyProcessesServiceSL;
import com.itbarx.sl.ReplySL;
import com.itbarx.utils.BarkUtility;
import com.itbarx.utils.TextSizeUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BarkActivity extends BaseActivity implements TextureView.SurfaceTextureListener {

	@Bind(R.id.bark_activity_screen_like_icon_imageView) ImageView imgLike;
	@Bind(R.id.bark_activity_screen_reBark_icon) ImageView imgReBark;
	@Bind(R.id.bark_activity_screen_reply_icon) ImageView imgReply;
	@Bind(R.id.bark_activity_screen_side_panel) FrameLayout sidePanel;
	@Bind(R.id.bark_activity_screen_drawer_layout) DrawerLayout drawerLayout;

	private S_Fragment_Like sFragmentLike;
	private S_Fragment_ReBark sFragmentReBark;
	private S_Fragment_Reply sFragmentReply;
	private FragmentTransaction ft;
	private FragmentManager fm;
	private ViewGroup.LayoutParams params;
	private MediaPlayer mMediaPlayer;
	private PostGetPostDetailModel selectedModel;
	private String VIDEO_VIRTUAL_PATH_NAME = Environment.getExternalStorageDirectory() + "/com.itbarx";
	private String filePath = "";
	protected String POST_ID = null;
	private TextViewRegular txtToolbar, txtSubtitle;
	private TextViewBold txtLikeCount, txtReBarkCount, txtReplyCount, txtLike, txtReBark, txtReply;
	private ImageView imgVideoPlay, imgVideoPause;
	private TextureView videoView;
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

	@Override protected int getLayoutResourceId() {
		return R.layout.activity_bark_screen;
	}

	@Override protected Context getContext() {
		return BarkActivity.this;
	}

	@Override protected void exceptionHandler() {
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(BarkActivity.this));
	}

	@Override protected void initViews() {

		if ((POST_ID = BarkUtility.getPostId(BarkActivity.this)) != null) {
			ButterKnife.bind(this);
			imgVideoPlay = (ImageView) findViewById(R.id.bark_activity_screen_video_thumbnail_play_ImageView);
			imgVideoPause = (ImageView) findViewById(R.id.bark_activity_screen_video_thumbnail_pause_ImageView);
			imgVideoPause.setVisibility(View.INVISIBLE);
			imgVideoPlay.setOnClickListener(playVideoClickListener);
			imgVideoPause.setOnClickListener(pauseVideoClickListener);
			txtToolbar = (TextViewRegular) findViewById(R.id.bark_activity_screen_toolbar_textView);
			txtSubtitle = (TextViewRegular) findViewById(R.id.bark_activity_screen_subtitle_TextView);
			txtLike = (TextViewBold) findViewById(R.id.bark_activity_screen_like_text_TextView);
			txtReBark = (TextViewBold) findViewById(R.id.bark_activity_screen_rebark_text_TextView);
			txtReply = (TextViewBold) findViewById(R.id.bark_activity_screen_reply_text_TextView);
			txtLikeCount = (TextViewBold) findViewById(R.id.bark_activity_screen_like_count_TextView);
			txtReBarkCount = (TextViewBold) findViewById(R.id.bark_activity_screen_rebark_count_TextView);
			txtReplyCount = (TextViewBold) findViewById(R.id.bark_activity_screen_reply_count_TextView);
			videoView = (TextureView) findViewById(R.id.media_record_preview_main_videoView);
			setCompText();
			//SET SIDE PANEL'S WIDTH.
			sidePanel.post(new Runnable() {
				@Override public void run() {

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
				PostPostDetailModel postDetailModel = new PostPostDetailModel();
				postDetailModel.setPostID(POST_ID);
				PostProcessesServiceSL processesServiceSL = new PostProcessesServiceSL(getContext(), postProcessesServiceListener, R.string.root_service_url);
				processesServiceSL.setGetPostDetail(postDetailModel);
				showProgress(getString(R.string.ItbarxConnecting));
			} catch (Exception e) {
				writeLog("ITbarx", "BarkActivity  initViews" + e.getMessage());
			}
			//*******************************************************************************

			imgLike.setOnClickListener(new OneShotOnClickListener(500) {
				@Override public void onOneShotClick(View v) {
					//-------------------------------------------------------------------
					LikeUserListModel likeUserListModel = new LikeUserListModel("48d8ceb2-de98-4dd7-b53b-000049db2753", "1", "20");
					//LikeProcessesServiceSL likeProcessesServiceSL = new LikeProcessesServiceSL(getContext(), likeProcessesServiceListener, R.string.root_service_url);
					//likeProcessesServiceSL.setGetLikeUsersByPostId(likeUserListModel);
					LikeSL likeSL = new LikeSL(getContext(), likeProcessesServiceListener, R.string.root_service_url);
					likeSL.setGetLikeUsersByPostId(likeUserListModel);
					showProgress(getString(R.string.ItbarxConnecting));
					//-------------------------------------------------------------------

				}
			});
			imgReBark.setOnClickListener(new OneShotOnClickListener(500) {
				@Override public void onOneShotClick(View v) {
				ReBarkSendPostSharedUserModel sendUserModel = new ReBarkSendPostSharedUserModel("6B2BDE44-8B31-4874-9EA9-00000437C69F", "1", "20");
					//ReBarkProcessesServiceSL reBarkProcessesServiceSL = new ReBarkProcessesServiceSL(getContext(), reBarkProcessesServiceListener, R.string.root_service_url);
					//	reBarkProcessesServiceSL.setGetPostSharedUser(sendUserModel);
					ReBarkSL reBarkSL = new ReBarkSL(getContext(), reBarkProcessesServiceListener, R.string.root_service_url);
					reBarkSL.setGetPostSharedUser(sendUserModel);
					showProgress(getString(R.string.ItbarxConnecting));
				}
			});

			imgReply.setOnClickListener(new OneShotOnClickListener(500) {
				@Override public void onOneShotClick(View v) {
					ReplySendModel replySendModel = new ReplySendModel("40BF425A-0853-4DF0-868B-6848978E6239", "1", "10");
				//	ReplyProcessesServiceSL replyProcessesServiceSL = new ReplyProcessesServiceSL(getContext(), replyProcessesServiceListener, R.string.root_service_url);
				//	replyProcessesServiceSL.setGetPostRepliesList(replySendModel);
					ReplySL replySL = new ReplySL(getContext(), replyProcessesServiceListener, R.string.root_service_url);
					replySL.setGetPostRepliesList(replySendModel);
					showProgress(getString(R.string.ItbarxConnecting));

				}
			});

		} else {
			finish();
		}

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
		((ImageView) findViewById(R.id.bark_activity_screen_video_thumbnail_play_ImageView)).setVisibility(View.VISIBLE);

	}

	private void setBarkDetail(PostGetPostDetailModel postDetailModel) {
		selectedModel = postDetailModel;
		txtReBarkCount.setText(postDetailModel.getPostRebarkCount());
		txtLikeCount.setText(postDetailModel.getPostLikeCount());
		txtReplyCount.setText(postDetailModel.getPostReplyCount());
		txtSubtitle.setText(postDetailModel.getPostSpeechToText());
		txtToolbar.setText(postDetailModel.getItBarxUserName());

	}

	PostProcessesServiceListener<String> postProcessesServiceListener = new PostProcessesServiceListener<String>() {
		@Override public void getTimelineListForUser(List<PostTimelineListForUserModel> postTimelineListForUserModel) {

		}

		@Override public void getWallListForUser(List<PostWallListForUserModel> postWallListForUserModel) {

		}

		@Override public void getPopularPostList(List<PostPopularPostListModel> popularPostListModel) {

		}

		@Override public void getNewPostList(List<PostNewPostListModel> postNewPostListModels) {

		}

		@Override public void getWallInfo(PostGetWallInfoModel postGetWallInfoModel) {

		}

		@Override public void getPostDetail(PostGetPostDetailModel postDetailModel) {
			dismissProgress();
			setBarkDetail(postDetailModel);
			if (postDetailModel != null && postDetailModel.getPostURL().length() > 0) {
				/*
				String url ="https://itbarxmediastorage.blob.core.windows.net"+postDetailModel.getPostURL();
				int lastIndex = postDetailModel.getPostURL().lastIndexOf("/");
				String fileName=	postDetailModel.getPostURL().substring(lastIndex+1);
				filePath =VIDEO_VIRTUAL_PATH_NAME+"/"+fileName+".mp4";

				File file = new File(filePath);
				if(file.exists())
				{
					*/
					try {
						//mMediaPlayer.setDataSource(file.getAbsolutePath());
						mMediaPlayer.setDataSource(postDetailModel.getPostURL());
						mMediaPlayer.prepare();
					} catch (IOException e) {
						e.printStackTrace();
					}

			}
		}

		@Override public void isAdded(String isAdded) {

		}

		@Override public void onComplete(ResponseEventModel<String> onComplete) {

		}

		@Override public void onError(BarxErrorModel onError) {
			dismissProgress();
			finish();
		}
	};
	MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
		@Override public void onCompletion(MediaPlayer mp) {
			if (!mMediaPlayer.isPlaying()) {
				((ImageView) findViewById(R.id.bark_activity_screen_video_thumbnail_play_ImageView)).setVisibility(View.VISIBLE);
				((ImageView) findViewById(R.id.bark_activity_screen_video_thumbnail_pause_ImageView)).setVisibility(View.INVISIBLE);
				//mMediaPlayer.reset();
				isPauseClick=true;
			}

		}
	};
	OneShotOnClickListener playVideoClickListener = new OneShotOnClickListener(500) {
		@Override public void onOneShotClick(View v) {
			((ImageView) findViewById(R.id.bark_activity_screen_video_thumbnail_play_ImageView)).setVisibility(View.INVISIBLE);
			((ImageView) findViewById(R.id.bark_activity_screen_video_thumbnail_pause_ImageView)).setVisibility(View.VISIBLE);
			if (isPauseClick) {
				mMediaPlayer.start();
				isPauseClick = false;
			} else {
				mMediaPlayer.start();
			}}};
				/*if (hasVideoFile) {


				} else {
					if (selectedModel != null) {

						String rootVideoUrl = "https://itbarxmediastorage.blob.core.windows.net";
						int lastIndex = selectedModel.getPostURL().lastIndexOf("/");
						String fileName= selectedModel.getPostURL().substring(lastIndex + 1) + ".mp4";
						DownloadManagerAsync managerAsync = new DownloadManagerAsync(BarkActivity.this,
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

		@Override public void onOneShotClick(View v) {
			((ImageView) findViewById(R.id.bark_activity_screen_video_thumbnail_pause_ImageView)).setVisibility(View.INVISIBLE);
			((ImageView) findViewById(R.id.bark_activity_screen_video_thumbnail_play_ImageView)).setVisibility(View.VISIBLE);
			isPauseClick = true;
			mMediaPlayer.pause();
		}
	};
/*
	@Override public void onCompleteFileDownload(Boolean status, String file) {
		if (status) {
			File videoFile = new File(file);
			// videoView.setRotation(270);
			if (videoFile.exists()) {
				hasVideoFile=true;
				try {
					isConfigOk = true;
					mMediaPlayer.setDataSource(videoFile.getAbsolutePath());
					mMediaPlayer.prepare();
					mMediaPlayer.start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
*/
	@Override public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
		mMediaPlayer = new MediaPlayer();
		mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mMediaPlayer.setSurface(new Surface(surface));
		mMediaPlayer.setOnCompletionListener(onCompletionListener);
	}

	@Override public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

	}

	@Override public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
		return false;
	}

	@Override public void onSurfaceTextureUpdated(SurfaceTexture surface) {

	}

	@Override protected void onDestroy() {
		super.onDestroy();

		//FileUtility.deleteAllFileUnderFolder(VIDEO_VIRTUAL_PATH_NAME);
	}

	@Override public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Dialog dialog = showAlert(getString(R.string.bark_activity_screen_are_you_exit), getString(R.string.Yes), new DialogInterface.OnClickListener() {
				@Override public void onClick(DialogInterface dialog, int which) {
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

	LikeProcessesServiceListener<String> likeProcessesServiceListener = new LikeProcessesServiceListener<String>() {
		@Override public void addLike(String isAdded) {

		}

		@Override public void deleteLike(String isDeleted) {

		}

		@Override public void countLikeByUser(String count) {

		}

		@Override public void countLikeByPost(String count) {

		}

		@Override public void getLikePostByUserId(List<LikePostsByUserIdModel> likePostsByUserIdModel) {

		}

		@Override public void getLikeUsersByPostId(List<LikeUsersByPostIdModel> likeUsersByPostIdModel) {
			dismissProgress();
			likeDataList = new ArrayList<>();

			if (likeUsersByPostIdModel != null) {
				for (LikeUsersByPostIdModel models : likeUsersByPostIdModel) {
					LikeData data = new LikeData(null, models.getItBarxUserName(), models.getName(), "true");

					likeDataList.add(data);
					Log.d("like data list : ", data.getName() + " " + data.getItBarxUserName());
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
				@Override public void run() {
					drawerLayout.openDrawer(sidePanel);
				}
			});

		}

		@Override public void onComplete(ResponseEventModel<String> onComplete) {

		}

		@Override public void onError(BarxErrorModel onError) {

		}
	};

	//RE_BARK SERVICES
	ReBarkProcessesServiceListener<String> reBarkProcessesServiceListener = new ReBarkProcessesServiceListener<String>() {
		@Override public void add(String isAdded) {

		}

		@Override public void delete(String isDeleted) {

		}

		@Override
		public void getSharedPostList(List<ReBarkGetSharedPostListByUserIdModel> reBarkGetSharedPostListByUserIdModel) {

		}

		@Override
		public void getPostSharedUserList(List<ReBarkGetPostSharedUserListByPostIdModel> reBarkPostSharedUserListModel) {
			dismissProgress();
			reBarksDataList = new ArrayList<>();
			if (reBarkPostSharedUserListModel != null) {
				for (ReBarkGetPostSharedUserListByPostIdModel models : reBarkPostSharedUserListModel) {
					ReBarksData data = new ReBarksData(null, models.getSharerName(), models.getSharerName(), "true");
					reBarksDataList.add(data);
					Log.d("rebark data list : ", data.getName() + " " + data.getItBarxUserName());
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
			sFragmentReBark =  S_Fragment_ReBark.newInstance(BarkActivity.this);
			sFragmentReBark.setArguments(bundle);
			ft.add(R.id.bark_activity_screen_side_panel, sFragmentReBark);
			ft.show(sFragmentReBark);
			ft.commit();
			drawerLayout.post(new Runnable() {
				@Override public void run() {
					drawerLayout.openDrawer(sidePanel);
				}
			});

		}

		@Override public void getSharedPostCount(String count) {

		}

		@Override public void getUserCount(String count) {

		}

		@Override public void onComplete(ResponseEventModel<String> onComplete) {

		}

		@Override public void onError(BarxErrorModel onError) {

		}
	};


	//REPLY SERVICES

	ReplyProcessesServiceListener replyProcessesServiceListener = new ReplyProcessesServiceListener<String>() {
		@Override public void deleteReply(String idDeleted) {

		}

		@Override public void getPostRepliesList(List<ReplyListModel> replyListModel) {
			dismissProgress();

			replyDataList = new ArrayList<>();
			if (replyListModel != null) {
				for (ReplyListModel model : replyListModel) {
					ReplyData data = new ReplyData("", "", "", model.getPostText(), "", model.getAddedDate(), model.getItBarxUserName());
					replyDataList.add(data);
					Log.d("reply data list : ", data.getTimeAgo() + " " + data.getItBarxUserName());
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
				@Override public void run() {
					drawerLayout.openDrawer(sidePanel);
				}
			});


		}

		@Override public void addReply(String isAdded) {

		}

		@Override public void onComplete(ResponseEventModel<String> onComplete) {

		}

		@Override public void onError(BarxErrorModel onError) {

		}
	};

}
