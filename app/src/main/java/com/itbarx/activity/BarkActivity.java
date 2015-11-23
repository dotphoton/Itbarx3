package com.itbarx.activity;

import android.app.Dialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;

import com.itbarx.R;
import com.itbarx.common.DownloadManagerAsync;
import com.itbarx.custom.component.TextViewBold;
import com.itbarx.custom.component.TextViewRegular;
import com.itbarx.error.common.ResponseServiceModel;
import com.itbarx.error.model.BarxErrorModel;
import com.itbarx.listener.OneShotOnClickListener;
import com.itbarx.listener.PostProcessesServiceListener;
import com.itbarx.model.post.PostGetPostDetailModel;
import com.itbarx.model.post.PostGetWallInfoModel;
import com.itbarx.model.post.PostNewPostListModel;
import com.itbarx.model.post.PostPopularPostListModel;
import com.itbarx.model.post.PostPostDetailModel;
import com.itbarx.model.post.PostTimelineListForUserModel;
import com.itbarx.model.post.PostWallListForUserModel;
import com.itbarx.sl.PostProcessesServiceSL;
import com.itbarx.utils.BarkUtility;
import com.itbarx.utils.Base64Utility;
import com.itbarx.utils.FileUtility;
import com.itbarx.utils.TextSizeUtil;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * TODO: Add a class header comment!
 */
public class BarkActivity extends BaseActivity implements DownloadManagerAsync.DownloadManagerCallback,TextureView.SurfaceTextureListener  {

	private MediaPlayer mMediaPlayer;
	private PostGetPostDetailModel selectedModel;
	private String VIDEO_VIRTUAL_PATH_NAME = Environment.getExternalStorageDirectory() + "/com.itbarx";
	private String filePath="";
	protected String POST_ID = null;
	private TextViewRegular txtToolbar, txtSubtitle;
	private TextViewBold txtLikeCount, txtReBarkCount, txtReplyCount, txtLike, txtReBark, txtReply;
	private ImageView imgVideoPlay,imgVideoPause;
	private TextureView videoView;
	protected boolean isMediaRunning = false;
	boolean isMirrored = true;
	boolean isPauseEnter = false;
	boolean isFileDelete = false;
	boolean isStop = false;
	private boolean isConfigOk = false;
	private boolean hasVideoFile=false;
	@Override protected int getLayoutResourceId() {
		return R.layout.activity_bark_screen;
	}

	@Override protected Context getContext() {
		return BarkActivity.this;
	}

	@Override protected void initViews() {

		if((POST_ID =BarkUtility.getPostId(BarkActivity.this))!=null)
		{
			imgVideoPlay = (ImageView)findViewById(R.id.bark_activity_screen_video_thumbnail_play_ImageView);
			imgVideoPause = (ImageView)findViewById(R.id.bark_activity_screen_video_thumbnail_pause_ImageView);
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
			videoView= (TextureView) findViewById(R.id.media_record_preview_main_videoView);
			setCompText();
			videoView.setSurfaceTextureListener(this);
			videoView.setScaleX(isMirrored ? -1 : 1);

			try {
				PostPostDetailModel postDetailModel = new PostPostDetailModel();
				postDetailModel.setPostID(POST_ID);
				PostProcessesServiceSL processesServiceSL = new PostProcessesServiceSL(getContext(),postProcessesServiceListener,R.string.root_service_url);
				processesServiceSL.setGetPostDetail(postDetailModel);
				showProgress(getString(R.string.ItbarxConnecting));
			} catch (Exception e) {

				writeLog("ITbarx","BarkActivity  initViews" + e.getMessage());
			}

		}
		else
		{
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
	private  void  setBarkDetail(PostGetPostDetailModel postDetailModel)
	{
		selectedModel = postDetailModel;
		txtReBarkCount.setText(postDetailModel.getPostRebarkCount());
		txtLikeCount.setText(postDetailModel.getPostLikeCount());
		txtReplyCount.setText(postDetailModel.getPostReplyCount());
		txtSubtitle.setText(postDetailModel.getPostSpeechToText());
		txtToolbar.setText(postDetailModel.getItBarxUserName());

	}
	PostProcessesServiceListener<String> postProcessesServiceListener = new PostProcessesServiceListener<String>() {
		@Override
		public void getTimelineListForUser(List<PostTimelineListForUserModel> postTimelineListForUserModel) {

		}

		@Override
		public void getWallListForUser(List<PostWallListForUserModel> postWallListForUserModel) {

		}

		@Override
		public void getPopularPostList(List<PostPopularPostListModel> popularPostListModel) {

		}

		@Override
		public void getNewPostList(List<PostNewPostListModel> postNewPostListModels) {

		}

		@Override
		public void getWallInfo(PostGetWallInfoModel postGetWallInfoModel) {

		}

		@Override
		public void getPostDetail(PostGetPostDetailModel postDetailModel) {
			dismissProgress();
			setBarkDetail(postDetailModel);
			if(postDetailModel!=null&&postDetailModel.getPostURL().length()>0){
				String url ="https://itbarxmediastorage.blob.core.windows.net"+postDetailModel.getPostURL();
				filePath =VIDEO_VIRTUAL_PATH_NAME+postDetailModel.getPostURL().substring(0,8)+".mp4";

				File file = new File(filePath);
				if(file.exists())
				{
					try {
						mMediaPlayer.setDataSource(file.getAbsolutePath());
						mMediaPlayer.prepare();
					} catch (IOException e) {
						e.printStackTrace();
					}
					hasVideoFile =true;

				}


			}
		}

		@Override
		public void isAdded(String isAdded) {


		}

		@Override
		public void onComplete(ResponseServiceModel<String> onComplete) {

		}

		@Override
		public void onError(BarxErrorModel onError) {
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
			}

		}
	};
	OneShotOnClickListener playVideoClickListener = new OneShotOnClickListener(500) {
		@Override
		public void onOneShotClick(View v) {
			((ImageView) findViewById(R.id.bark_activity_screen_video_thumbnail_play_ImageView)).setVisibility(View.INVISIBLE);
			((ImageView) findViewById(R.id.bark_activity_screen_video_thumbnail_pause_ImageView)).setVisibility(View.VISIBLE);
			if(isPauseClick)
			{
				mMediaPlayer.start();
				isPauseClick=false;
			}
			else {
				if (hasVideoFile) {

					mMediaPlayer.start();
				} else {
					if (selectedModel != null) {
						DownloadManagerAsync managerAsync = new DownloadManagerAsync(BarkActivity.this,
								filePath.indexOf("/") == 0 ? filePath : "/" + filePath);
						String rootVideoUrl = "https://itbarxmediastorage.blob.core.windows.net";
						managerAsync.execute(rootVideoUrl + (selectedModel.getPostURL().indexOf("/") == 0 ? selectedModel.getPostURL() : "/" + selectedModel.getPostURL()));
					}

				}
			}
/*
			if (!isStop) {

				mMediaPlayer.start();
			} else {
				mMediaPlayer.start();
			}
			isStop = false;
			*/
		}
	} ;
	boolean isPauseClick =false;
	OneShotOnClickListener pauseVideoClickListener = new OneShotOnClickListener(500) {

		@Override
		public void onOneShotClick(View v) {
			((ImageView) findViewById(R.id.bark_activity_screen_video_thumbnail_pause_ImageView)).setVisibility(View.INVISIBLE);
			((ImageView) findViewById(R.id.bark_activity_screen_video_thumbnail_play_ImageView)).setVisibility(View.VISIBLE);
			isPauseClick=true;
				mMediaPlayer.pause();
		}
	};
	@Override
	public void onCompleteFileDownload(Boolean status, String file) {
		if(status){
			File videoFile = new File(file);
			// videoView.setRotation(270);
			if (videoFile.exists()) {


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

	@Override
	public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
		mMediaPlayer = new MediaPlayer();
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
			Dialog dialog = showAlert(getString(R.string.bark_activity_screen_are_you_exit), getString(R.string.Yes), new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					mMediaPlayer.stop();
					mMediaPlayer.release();
					finish();
				}
			},getString(R.string.No),null);
			dialog.show();
			return  true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
