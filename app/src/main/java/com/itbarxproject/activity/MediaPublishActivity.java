package com.itbarxproject.activity;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.itbarxproject.R;
import com.itbarxproject.application.ItbarxGlobal;
import com.itbarxproject.common.FileAttribute;
import com.itbarxproject.custom.component.ButtonBold;
import com.itbarxproject.custom.component.TextViewRegular;
import com.itbarxproject.exception.ExceptionHandler;
import com.itbarxproject.listener.ReplyProcessesServiceListener;
import com.itbarxproject.model.post.PostPopularPostListModel;
import com.itbarxproject.model.reply.ReplyAddModel;
import com.itbarxproject.model.reply.ReplyListModel;
import com.itbarxproject.service.ResponseEventModel;
import com.itbarxproject.service.error.BarxErrorModel;
import com.itbarxproject.listener.OneShotOnClickListener;
import com.itbarxproject.listener.PostProcessesServiceListener;
import com.itbarxproject.model.post.PostAddPostModel;
import com.itbarxproject.model.post.PostGetPostDetailModel;
import com.itbarxproject.model.post.PostGetWallInfoModel;
import com.itbarxproject.model.post.PostNewPostListModel;
import com.itbarxproject.model.post.PostTimelineListForUserModel;

import com.itbarxproject.sl.PostProcessesServiceSL;
import com.itbarxproject.sl.ReplySL;
import com.itbarxproject.utils.BarkUtility;
import com.itbarxproject.utils.Base64Utility;
import com.itbarxproject.utils.FileUtility;
import com.itbarxproject.utils.VideoUtility;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Umut Boz on 13.09.2015.
 */
public class MediaPublishActivity extends BaseActivity {

	protected String POST_ID = null;
	public static String SPEECH_TEXT = "";
	private String VIDEO_PATH_NAME = "/Pictures/";
	private static final String VIDEO_EXTENSION = ".mp4";
	private String VIDEO_VIRTUAL_PATH_NAME = Environment.getExternalStorageDirectory() + "/com.itbarx/";
	private MediaPlayer mMediaPlayer;
	MediaController controller;
	private VideoView videoView;
	protected boolean isMediaRunning = false;

	protected EditText txtBarkInfo;
	protected ImageView imageViewLogo;
	protected ButtonBold btnPublish;
	@Override protected int getLayoutResourceId() {

		return R.layout.media_record_publish_screen;
	}

	@Override protected Context getContext() {
		// TODO Auto-generated method stub
		return MediaPublishActivity.this;
	}
	@Override protected void exceptionHandler() {
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
	}

	@Override protected void initViews() {
		if(getIntent().getStringExtra(BarkUtility.POST_ID_KEY)!=null)
		{
			POST_ID = getIntent().getStringExtra(BarkUtility.POST_ID_KEY);
			((TextViewRegular) findViewById(R.id.media_record_publish_toolbar_text)).setText(getString(R.string.reply));

		}
		txtBarkInfo = (EditText) findViewById(R.id.media_record_publish_bark_textView);
		btnPublish = (ButtonBold)findViewById(R.id.media_record_publish_button);
		btnPublish.setOnClickListener(publishBarkClickListener);
		imageViewLogo = (ImageView) findViewById(R.id.media_record_publish_main_information_imageView);
		String lastVideoFile = VideoUtility.getLastVideoPath(getContext());
		FileAttribute fileAttribute = FileUtility.getFileAttribute(lastVideoFile);
		Uri imageUri = Uri.fromFile(new File(fileAttribute.path(),fileAttribute.filename()+".jpg"));

/*
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Bitmap.Config.ARGB_8888;
		Bitmap bitmap = BitmapFactory.decodeFile(new File(fileAttribute.path(),fileAttribute.filename()+".jpg").getAbsolutePath(), options);
		Matrix mat = new Matrix();
		mat.postRotate(270);
		mat.preScale(1, -1);


		DisplayMetrics dm = new DisplayMetrics();
		MediaPublishActivity.this.getWindowManager().getDefaultDisplay().getMetrics(dm);
		int width = dm.widthPixels;
		int height = width * bitmap.getHeight() / bitmap.getWidth(); //mainImage is the Bitmap I'm drawing


		Bitmap bMapRotate = Bitmap.createBitmap(bitmap, 0, 0,
				bitmap.getWidth(), bitmap.getHeight(), mat, true);
	*/

		//imageViewLogo.setImageBitmap(bMapRotate);
		imageViewLogo.setAdjustViewBounds(true);
		imageViewLogo.setScaleType(ImageView.ScaleType.FIT_XY);
		imageViewLogo.setImageURI(imageUri);
		txtBarkInfo.setText(SPEECH_TEXT);
		txtBarkInfo.setHint(SPEECH_TEXT);
		imageViewLogo.setRotation(270);
		//imageViewLogo.setScaleX(-1);
		imageViewLogo.setScaleY(-1f);



      /*
		controller = new MediaController(getContext());
        videoView.setOnCompletionListener(onCompletionListener);
        //controller.setAnchorView(videoRelativeLayout);
        String file = VideoUtility.getLastVideoPath(getContext());
        File videoFile = new File(Environment.getExternalStorageDirectory(),file);
       // videoView.setRotation(270);
        if(videoFile.exists())
        {

            videoView.setVideoPath(videoFile.getAbsolutePath());

            videoView.setMediaController(controller);
            videoView.stopPlayback();
            controller.requestFocus();
            isConfigOk =true;
            controller.show();
        }
    */

	}

	OneShotOnClickListener publishBarkClickListener = new OneShotOnClickListener(200) {

		@Override public void onOneShotClick(View v) {

			String lastVideoFile = VideoUtility.getLastVideoPath(getContext());

			try {

				String sendVideoData = Base64Utility.encodeFileToBase64Binary(lastVideoFile);

				if(POST_ID==null)
				{
					PostAddPostModel postAddPostModel = new PostAddPostModel();
					postAddPostModel.setPostSenderIp("127.0.0.1");
					postAddPostModel.setPostAddedTimeZoneId("2");
					postAddPostModel.setPostSenderUserId(ItbarxGlobal.getInstance().getAccountModel().getUserID());
					postAddPostModel.setPostSpeechText(txtBarkInfo.getText().toString());
					postAddPostModel.setVideoBytes(sendVideoData);

					PostProcessesServiceSL processesServiceSL = new PostProcessesServiceSL(getContext(),postProcessesServiceListener,R.string.root_service_url);
					processesServiceSL.setAddPost(postAddPostModel);
					showProgress(getString(R.string.publish_bark));
				}else
				{
					ReplySL replySL = new ReplySL(getContext(), replyProcessesServiceListener, R.string.root_service_url);
					ReplyAddModel replyAddModel = new ReplyAddModel();
					replyAddModel.setPostID(POST_ID);
					replyAddModel.setPostSpeechToText(txtBarkInfo.getText().toString());
					replyAddModel.setPostSenderUserId(ItbarxGlobal.getInstance().getAccountModel().getUserID());
					replyAddModel.setPostSenderIp("127.0.0.1");
					replyAddModel.setPostText(txtBarkInfo.getText().toString());
					replyAddModel.setPostReplyByte(sendVideoData);
					replySL.setAddReply(replyAddModel);
					showProgress(getString(R.string.publish_reply_bark));
				}



			} catch (IOException e) {
				Log.d("ITbarx", "publishBarkClickListener " + e.getMessage());

			}

		}
	};



	boolean isPauseEnter = false;
	boolean isFileDelete = false;
	boolean isStop = false;

	void deleteTempFile() {
		String lastFile = VideoUtility.getLastVideoPath(getContext());
		if (!lastFile.equalsIgnoreCase("")) {
			File deleteFile = new File(Environment.getExternalStorageDirectory(), lastFile);
			if (deleteFile.exists()) {
				deleteFile.delete();
				isFileDelete = true;
			}

		}
	}

	//FileUtility.deleteAllFileUnderFolder(VIDEO_VIRTUAL_PATH_NAME);
	PostProcessesServiceListener<String> postProcessesServiceListener = new PostProcessesServiceListener<String>() {
		@Override
		public void getTimelineListForUser(List<PostTimelineListForUserModel> postTimelineListForUserModel) {

		}

		@Override
		public void getWallListForUser(List<PostPopularPostListModel> popularPostListModel) {

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

		}

		@Override
		public void isAdded(String isAdded) {
			dismissProgress();
			FileUtility.deleteAllFileUnderFolder(VIDEO_VIRTUAL_PATH_NAME);
			finish();
		}

		@Override
		public void onComplete(ResponseEventModel<String> onComplete) {

		}

		@Override
		public void onError(BarxErrorModel onError) {
			dismissProgress();
		}
	};
	ReplyProcessesServiceListener replyProcessesServiceListener = new ReplyProcessesServiceListener<String>() {
		@Override public void deleteReply(String idDeleted) {

		}
		@Override public void getPostRepliesList(List<ReplyListModel> replyListModel) {

		}
		@Override public void addReply(String isAdded) {
			dismissProgress();
			FileUtility.deleteAllFileUnderFolder(VIDEO_VIRTUAL_PATH_NAME);
			finish();
		}

		@Override public void onComplete(ResponseEventModel<String> onComplete) {

		}

		@Override public void onError(BarxErrorModel onError) {
			dismissProgress();
			showAlert(onError.getErrMessage());
		}
	};

	@Override
	protected void onDestroy() {
		super.onDestroy();


	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			FileUtility.deleteAllFileUnderFolder(VIDEO_VIRTUAL_PATH_NAME);
			finish();
			return  true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
