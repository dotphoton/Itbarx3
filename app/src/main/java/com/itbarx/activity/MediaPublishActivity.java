package com.itbarx.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.itbarx.BuildConfig;
import com.itbarx.R;
import com.itbarx.application.ItbarxGlobal;
import com.itbarx.common.FileAttribute;
import com.itbarx.custom.component.ButtonBold;
import com.itbarx.error.common.ResponseServiceModel;
import com.itbarx.error.model.BarxErrorModel;
import com.itbarx.listener.OneShotOnClickListener;
import com.itbarx.listener.PostProcessesServiceListener;
import com.itbarx.model.post.PostAddPostModel;
import com.itbarx.model.post.PostGetPostDetailModel;
import com.itbarx.model.post.PostGetWallInfoModel;
import com.itbarx.model.post.PostNewPostListModel;
import com.itbarx.model.post.PostPopularPostListModel;
import com.itbarx.model.post.PostTimelineListForUserModel;
import com.itbarx.model.post.PostWallListForUserModel;
import com.itbarx.sl.PostProcessesServiceSL;
import com.itbarx.utils.Base64Utility;
import com.itbarx.utils.FileUtility;
import com.itbarx.utils.VideoUtility;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Umut Boz on 13.09.2015.
 */
public class MediaPublishActivity extends BaseActivity {

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

	@Override protected void initViews() {

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
			PostAddPostModel postAddPostModel = new PostAddPostModel();
			postAddPostModel.setPostSenderIp("127.0.0.1");
			postAddPostModel.setPostAddedTimeZoneId("2");
			postAddPostModel.setPostSenderUserId(ItbarxGlobal.getInstance().getAccountModel().getUserID());
			postAddPostModel.setPostSpeechText(txtBarkInfo.getText().toString());
			try {

				postAddPostModel.setVideoBytes(Base64Utility.encodeFileToBase64Binary(lastVideoFile));
				PostProcessesServiceSL processesServiceSL = new PostProcessesServiceSL(getContext(),postProcessesServiceListener,R.string.root_service_url);
				processesServiceSL.setAddPost(postAddPostModel);
				showProgress("Bark yayınlanıyor...");
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

		}

		@Override
		public void isAdded(String isAdded) {
			dismissProgress();
			FileUtility.deleteAllFileUnderFolder(VIDEO_VIRTUAL_PATH_NAME);
			finish();
		}

		@Override
		public void onComplete(ResponseServiceModel<String> onComplete) {

		}

		@Override
		public void onError(BarxErrorModel onError) {
			dismissProgress();
		}
	};
	@Override
	protected void onDestroy() {
		super.onDestroy();


	}

}
