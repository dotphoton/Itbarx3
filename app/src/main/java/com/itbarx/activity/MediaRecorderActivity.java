package com.itbarx.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.PowerManager;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegNotSupportedException;
import com.itbarx.R;
import com.itbarx.application.ItbarxGlobal;
import com.itbarx.common.FileAttribute;
import com.itbarx.custom.component.TextViewBold;
import com.itbarx.exception.ExceptionHandler;
import com.itbarx.listener.OneShotOnClickListener;
import com.itbarx.utils.DateUtility;
import com.itbarx.utils.FileUtility;
import com.itbarx.utils.VideoUtility;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by Umut Boz on 13.09.2015.
 */
public class MediaRecorderActivity extends BaseActivity implements SurfaceHolder.Callback {




	private String VIDEO_PATH_NAME = "/data/data/";
	private String VIDEO_VIRTUAL_PATH_NAME = Environment.getExternalStorageDirectory() + "/com.itbarx/";
	private static final String VIDEO_EXTENSION = ".mp4";
	private MediaRecorder mMediaRecorder;
	private Camera mCamera;
	private SurfaceView mSurfaceView;
	private SurfaceHolder mHolder;
	private View mToggleButton;
	private boolean mInitSuccesful;
	private boolean isSuccess = false;
	protected boolean isMediaRunning = false;
	private TextView secondTextView;
	private TextViewBold txtRecordInfo;
	protected ImageView imageView;
	protected ImageView btnCancel, btnAccept;
	private boolean isOperationComplete = false;

	boolean isConvertImageStatus = false;
	boolean isFFmpegLoadStatus = false;
	private int rotation = 0;

	@Override protected int getLayoutResourceId() {

		return R.layout.media_record_recording_screen2;
	}

	@Override protected Context getContext() {
		// TODO Auto-generated method stub
		return MediaRecorderActivity.this;
	}
	@Override protected void exceptionHandler() {
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
	}

	@Override protected void initViews() {

		VIDEO_PATH_NAME += getContext().getPackageName().toString() + "/";
		imageView = (ImageView) findViewById(R.id.record_recording_fragment_pause_imageView);
		imageView.setOnClickListener(pauseClickListener);
		txtRecordInfo = (TextViewBold) findViewById(R.id.record_start_fragment_start_textView);
		secondTextView = (TextView) findViewById(R.id.record_recording_fragment_second_textview);
		btnCancel = (ImageView) findViewById(R.id.record_recording_fragment_deny_button);
		btnAccept = (ImageView) findViewById(R.id.record_recording_fragment_accept_button);
		btnAccept.setOnClickListener(acceptClickListener);
		btnCancel.setOnClickListener(cancelClickListener);

		mSurfaceView = (SurfaceView) findViewById(R.id.record_recording_fragment_camera_preview);
		mHolder = mSurfaceView.getHolder();
		mHolder.addCallback(this);
		mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

		beginStartTimer();
	}

	protected void stopRecorder() {
		if (isStop) return;
		if (isFileDelete) return;
		if (mMediaRecorder == null) return;
		mMediaRecorder.stop();
		mMediaRecorder.reset();
		timer.cancel();
		timer.purge();
		runOnUiThread(new Runnable() {
			public void run() {
				((TextViewBold) findViewById(R.id.record_start_fragment_start_textView)).setText(getString(R.string.finish_recording));
				((ImageView) findViewById(R.id.record_recording_fragment_pause_imageView)).setVisibility(View.INVISIBLE);
			}
		});
		isStop = true;
		/*
        try {
            if(isPauseEnter)return;
         //   initRecorder(mHolder.getSurface());
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
	}

	Timer startTimer = null;

	protected void beginStartTimer() {
		startTimer = new Timer();
		startTimer.schedule(beginTimerTask, 0, 2000);
	}

	TimerTask beginTimerTask = new TimerTask() {
		@Override public void run() {
			if (mMediaRecorder != null) {
				startTimer.cancel();
				startTimer.purge();
				startRecorder();
			}

		}
	};

	protected void startRecorder() {
		mMediaRecorder.start();
		isMediaRunning = true;
		runOnUiThread(new Runnable() {
			public void run() {
				((TextViewBold) findViewById(R.id.record_start_fragment_start_textView)).setText(getString(R.string.recording));
				((ImageView) findViewById(R.id.record_recording_fragment_pause_imageView)).setVisibility(View.VISIBLE);
				runTimer();
			}
		});

	}

	OneShotOnClickListener acceptClickListener = new OneShotOnClickListener(200) {

		@Override public void onOneShotClick(View v) {

			isSuccess = true;
			if (!isStop) {
				stopRecorder();
			}
			extractImageFromVideo();
			//launchSubActivity(MediaPreviewActivity.class);
			//rotateVideo();
		}
	};
	OneShotOnClickListener cancelClickListener = new OneShotOnClickListener(200) {

		@Override public void onOneShotClick(View v) {

			if (!isStop) {
				stopRecorder();
			}
			deleteTempFile();
			setResult(111);
			finish();
		}
	};
	OneShotOnClickListener pauseClickListener = new OneShotOnClickListener(200) {

		@Override public void onOneShotClick(View v) {

			if (isMediaRunning) {

				stopRecorder();
				v.setEnabled(false);

			}
		}
	};

	/* Init the MediaRecorder, the order the methods are called is vital to
	 * its correct functioning */
	private void initRecorder(Surface surface) throws IOException {
		if (mInitSuccesful) return;
		// It is very important to unlock the camera before doing setCamera
		// or it will results in a black preview
		if (mCamera == null) {
			int camId = VideoUtility.getCameraType();
			mCamera = Camera.open(camId);

			Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
			Camera.getCameraInfo(camId, cameraInfo);

			int rotation = MediaRecorderActivity.this.getWindowManager().getDefaultDisplay().getRotation();
			int degrees = 0;
			switch (rotation) {
				case Surface.ROTATION_0: degrees = 0; break; //Natural orientation
				case Surface.ROTATION_90: degrees = 90; break; //Landscape left
				case Surface.ROTATION_180: degrees = 180; break;//Upside down
				case Surface.ROTATION_270: degrees = 270; break;//Landscape right
			}
			int rotate = (cameraInfo.orientation - degrees + 360) % 360;

			int newOrientation=0;
			if ( cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT ){
				newOrientation = ( 360 - newOrientation ) % 360; // compensate the mirror
			}
			mCamera.setDisplayOrientation( newOrientation +90);
			//STEP #2: Set the 'rotation' parameter
			Camera.Parameters params = mCamera.getParameters();
			params.setRotation(180);
			mCamera.setParameters(params);

			mCamera.unlock();



/*
			int result;

			if (camId==1)
			{
				// cameraType=CAMERATYPE.FRONT;

				result = (cameraInfo.orientation + degrees) % 360;
				result = (360 - result) % 360; // compensate the mirror

			}
			else
			{ // back-facing

				result = (cameraInfo.orientation - degrees + 360) % 360;

			}
			// displayRotate=result;
			mCamera.setDisplayOrientation(result);
	*/

			//params.setRotation(90);

		}

		if (mMediaRecorder == null) mMediaRecorder = new MediaRecorder();
		mMediaRecorder.setPreviewDisplay(surface);
		mMediaRecorder.setCamera(mCamera);

		//mMediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_LOW));
		//mMediaRecorder.setOrientationHint(0);


			if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
				mMediaRecorder.setOrientationHint(270);
			} else if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
				mMediaRecorder.setOrientationHint(0);
			}
			else
			{
				mMediaRecorder.setOrientationHint(0);
			}

		mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
		mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
		mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
		mMediaRecorder.setAudioEncodingBitRate(96000);
		mMediaRecorder.setAudioSamplingRate(44100);

        /*
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);

        // Audio Settings
        mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        */
		AtomicLong idCounter = new AtomicLong();
		String getDate = DateUtility.toShortTimeString(DateUtility.getNowDate()).replaceAll(":", "");
		String videoFileName = ItbarxGlobal.getInstance().getAccountModel().getItBarxUserName() + "_" + getDate + "_" + String.valueOf(idCounter.getAndIncrement());
		//  VIDEO_PATH_NAME +=videoFileName + VIDEO_EXTENSION;
		String path = VIDEO_VIRTUAL_PATH_NAME + videoFileName + VIDEO_EXTENSION;
		VideoUtility.saveLastVideoPath(getContext(), path);

		File file = new File(path);
		// "touch" the file
		if (!file.exists()) {
			File parent = file.getParentFile();
			if (parent != null) if (!parent.exists()) if (!parent.mkdirs()) throw new IOException("Cannot create " +
					"parent directories for file: " + file);

			file.createNewFile();
		}

		mMediaRecorder.setOutputFile(file.getAbsolutePath());

		// No limit. Check the space on disk!
		mMediaRecorder.setMaxDuration(14000);
		mMediaRecorder.setVideoFrameRate(15);

		mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT);

		try {
			mMediaRecorder.prepare();

		} catch (IllegalStateException e) {
			// This is thrown if the previous calls are not called with the
			// proper order
			e.printStackTrace();
		}

		mInitSuccesful = true;
	}
	public void setCameraDisplayOrientation(Camera camera) {
		Camera.CameraInfo info = new Camera.CameraInfo();
		Camera.getCameraInfo(1, info);

		int rotation = MediaRecorderActivity.this.getWindowManager().getDefaultDisplay().getRotation();
		int degrees = 0;
		switch (rotation) {
			case Surface.ROTATION_0: degrees = 0; break;
			case Surface.ROTATION_90: degrees = 90; break;
			case Surface.ROTATION_180: degrees = 180; break;
			case Surface.ROTATION_270: degrees = 270; break;
		}

		int result;
		if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
			result = (info.orientation + degrees) % 360;
			result = (360 - result) % 360;  // compensate the mirror
		} else {  // back-facing
			result = (info.orientation - degrees + 360) % 360;
		}

		camera.setDisplayOrientation(result);
	}
	@Override public void surfaceCreated(SurfaceHolder holder) {
		try {
			if (!mInitSuccesful) initRecorder(mHolder.getSurface());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override public void surfaceDestroyed(SurfaceHolder holder) {
		shutdown();
	}

	@Override public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

/*

		Camera.Parameters parameters = mCamera.getParameters();
		Display display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();

		if(display.getRotation() == Surface.ROTATION_0)
		{
			parameters.setPreviewSize(height, width);
			mCamera.setDisplayOrientation(90);
		}

		if(display.getRotation() == Surface.ROTATION_90)
		{
			parameters.setPreviewSize(width, height);
		}

		if(display.getRotation() == Surface.ROTATION_180)
		{
			parameters.setPreviewSize(height, width);
		}

		if(display.getRotation() == Surface.ROTATION_270)
		{
			parameters.setPreviewSize(width, height);
			mCamera.setDisplayOrientation(180);
		}

		mCamera.setParameters(parameters);
		*/
	}

	/* SANIYE TIMER */
	public void runTimer() {
		timerCount = 15;
		timer = new Timer();
		timer.schedule(timerTask, 0, 1000);

	}

	int timerCount = 0;
	Timer timer;
	TimerTask timerTask = new TimerTask() {
		@Override public void run() {
			runOnUiThread(new Runnable() {
				public void run() {
					// If there are stories, add them to the table
					try {
						if (timerCount < 1) {
							stopRecorder();
							//videoyu durdur.
						} else {
							timerCount--;

							((TextView) findViewById(R.id.record_recording_fragment_second_textview)).setText(String.valueOf(timerCount));
						}
					} catch (final Exception ex) {
					}
				}
			});

			Log.d("timer run", DateUtility.ConvertToDateString(DateUtility.getNowDate()));
		}
	};

	private void shutdown() {
		// Release MediaRecorder and especially the Camera as it's a shared
		// object that can be used by other applications
		mMediaRecorder.reset();
		mMediaRecorder.release();
		mCamera.release();

		// once the objects have been released they can't be reused
		mMediaRecorder = null;
		mCamera = null;
	}

	boolean isPauseEnter = false;
	boolean isFileDelete = false;
	boolean isStop = false;

	@Override protected void onPause() {

		isPauseEnter = true;
		if (isMediaRunning) {
			if (mMediaRecorder != null) stopRecorder();
			//iptal dolduğu için geçmiş videoyu temizle
			deleteTempFile();

		}
		super.onPause();
	}

	void deleteTempFile() {
		if (isSuccess) return;
		String lastFile = VideoUtility.getLastVideoPath(getContext());
		if (!lastFile.equalsIgnoreCase("")) {
			File deleteFile = new File(Environment.getExternalStorageDirectory(), lastFile);
			if (deleteFile.exists()) {
				deleteFile.delete();
				isFileDelete = true;
			}

		}
	}

	protected void extractImageFromVideo() {
		String lastFile = VideoUtility.getLastVideoPath(getContext());
		if (!lastFile.equalsIgnoreCase("")) {
			final File sourceFile = new File(lastFile);
			FileAttribute fa = FileUtility.getFileAttribute(sourceFile.getAbsolutePath());
			final File target = new File(fa.path(), fa.filename() + ".jpg");
			final File targetRotate = new File(fa.path(), fa.filename() + "r.mp4");
			File ffmpegPath = new File("/data/data/com.itbarx/files/ffmpeg");
			String[] command = new String[] {"/system/bin/chmod", "777", ffmpegPath.getAbsolutePath()};

			try {
				try {
					FileUtility.chmod(ffmpegPath, 0777);
				} catch (Exception e) {
					e.printStackTrace();
				}

				Process process = Runtime.getRuntime().exec(command);
				final FFmpeg ffmpeg = FFmpeg.getInstance(getContext());
				//-i moviefile.mpeg -f mp3 audiofile.mp3
				ffmpeg.loadBinary(new LoadBinaryResponseHandler() {

					@Override public void onStart() {

					}

					@Override public void onFailure() {

						isFFmpegLoadStatus = false;
					}

					@Override public void onSuccess() {
						isFFmpegLoadStatus = true;
					}

					@Override public void onFinish() {
						if (isFFmpegLoadStatus) showProgress(getString(R.string.make_working));
					}
				});
				//  String cmdJPG="-ss 00:00:02 -i " + sourceFile.getAbsolutePath() + " -vf scale=800:-1 -vframes 1 " +target.getAbsolutePath();
				//'transpose=3
				String cmdJPG = "-ss 00:00:02 -i " + sourceFile.getAbsolutePath() + " -vframes 1 " + target.getAbsolutePath();
				String rotateMP4 ="-i "+sourceFile.getAbsolutePath() +"-vf \"rotate=-3*PI/180\" "+targetRotate.getAbsolutePath();
				ffmpeg.execute(cmdJPG, new ExecuteBinaryResponseHandler() {

					@Override public void onStart() {

					}

					@Override public void onProgress(String message) {
					}

					@Override public void onFailure(String message) {
						isConvertImageStatus = false;
					}

					@Override public void onSuccess(String message) {
						isConvertImageStatus = true;

					}

					@Override public void onFinish() {
						dismissProgress();
						if (isConvertImageStatus) {
							// showAlert(getString(R.string.));
							launchSubActivity(MediaPreviewActivity.class);
						} else {
							showAlert(getString(R.string.make_working_failure));
						}
					}
				});

			} catch (IOException e) {
				e.printStackTrace();
			} catch (FFmpegNotSupportedException e) {
				// Handle if FFmpeg is not supported by device

			} catch (FFmpegCommandAlreadyRunningException e) {
				// Handle if FFmpeg is already running
			}

		}

	}
/*
	protected void rotateVideo() {
		new Runnable()
		{

			@Override
			public void run() {


		try
		{
			String lastFile = VideoUtility.getLastVideoPath(getContext());
			File sourceFile = new File(lastFile);
			FileAttribute fa = FileUtility.getFileAttribute(sourceFile.getAbsolutePath());
		String	workFolder  = fa.path();
			File targetFile = new File(workFolder, fa.filename() + "_r.mp4");

			String commandStr = "ffmpeg -y -i " + sourceFile.getAbsolutePath() + " strict experimental -vf transpose=1 -s 160x120 -r 30 -aspect 4:3 -ab 48000 -ac 2 -ar 22050 -b 2097k  " + targetFile.getAbsolutePath();
			showProgress("FFmpeg4Android Transcoding in progress...");
			LoadJNI vk = new LoadJNI();

				// complex comman
				vk.run(GeneralUtils.utilConvertToComplex(commandStr), workFolder, getApplicationContext());

		}

		catch (CommandValidationException ex) {
			Log.e(Prefs.TAG, "vk run exeption.", ex);

		}
		finally {
			dismissProgress();
			launchSubActivity(MediaPreviewActivity.class);
		}
			}
		};

	}

	*/



	@Override protected void onResume() {

		if (isPauseEnter) {
			finish();
		}
		super.onResume();
	}

	@Override protected void onRestart() {
		super.onRestart();
		if (isPauseEnter) {
			finish();
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (!isStop) {
				stopRecorder();
			}
			deleteTempFile();
			setResult(111);
			finish();
			return  true;
		}
		return super.onKeyDown(keyCode, event);
	}

/*
	public class TranscdingBackground extends AsyncTask<String, Integer, Integer>
	{

		String vkLogPath;
		ProgressDialog progressDialog;
		Boolean commandValidationFailedFlag=true;
		File targetFile;
		File sourceFile;
		String workFolder ;

		public TranscdingBackground () {

			String lastFile = VideoUtility.getLastVideoPath(getContext());
			sourceFile = new File(lastFile);
			FileAttribute fa = FileUtility.getFileAttribute(sourceFile.getAbsolutePath());
			workFolder  = fa.path();
			targetFile = new File(workFolder, fa.filename() + "_r.mp4");
			vkLogPath = new File(workFolder,"vk.log").getAbsolutePath();

		}



		@Override
		protected void onPreExecute() {
			progressDialog = new ProgressDialog(MediaRecorderActivity.this);
			progressDialog.setMessage("FFmpeg4Android Transcoding in progress...");
			progressDialog.show();

		}

		protected Integer doInBackground(String... paths) {
			Log.i(Prefs.TAG, "doInBackground started...");

			// delete previous log
			//GeneralUtils.deleteFileUtil(workFolder + "/vk.log");

			PowerManager powerManager = (PowerManager)MediaRecorderActivity.this.getSystemService(Activity.POWER_SERVICE);
			PowerManager.WakeLock wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "VK_LOCK");
			Log.d(Prefs.TAG, "Acquire wake lock");
			wakeLock.acquire();


			String commandStr = "ffmpeg -y -i " + sourceFile.getAbsolutePath() + " strict experimental -vf transpose=1 -s 160x120 -r 30 -aspect 4:3 -ab 48000 -ac 2 -ar 22050 -b 2097k  " + targetFile.getAbsolutePath();

			///////////// Set Command using code (overriding the UI EditText) /////
			//String commandStr = "ffmpeg -y -i /sdcard/videokit/in.mp4 -strict experimental -s 320x240 -r 30 -aspect 3:4 -ab 48000 -ac 2 -ar 22050 -vcodec mpeg4 -b 2097152 /sdcard/videokit/out.mp4";
			//String[] complexCommand = {"ffmpeg", "-y" ,"-i", "/sdcard/videokit/in.mp4","-strict","experimental","-s", "160x120","-r","25", "-vcodec", "mpeg4", "-b", "150k", "-ab","48000", "-ac", "2", "-ar", "22050", "/sdcard/videokit/out.mp4"};
			///////////////////////////////////////////////////////////////////////


			LoadJNI vk = new LoadJNI();
			try {

				// complex command
				//vk.run(complexCommand, workFolder, getApplicationContext());

				//vk.run(GeneralUtils.utilConvertToComplex(commandStr), workFolder, getApplicationContext());

				// running without command validation
				//
				vk.run(GeneralUtils.utilConvertToComplex(commandStr), workFolder, getApplicationContext(), false);

				// copying vk.log (internal native log) to the videokit folder
				//GeneralUtils.copyFileToFolder(vkLogPath, workFolder);

		} catch (CommandValidationException e) {
				Log.e(Prefs.TAG, "vk run exeption.", e);
			commandValidationFailedFlag = true;
				commandValidationFailedFlag = false;
			} catch (Throwable e) {
				Log.e(Prefs.TAG, "vk run exeption.", e);
				commandValidationFailedFlag = true;
			}
			finally {
				if (wakeLock.isHeld())
					wakeLock.release();
				else{
					Log.i(Prefs.TAG, "Wake lock is already released, doing nothing");
				}
			}
			Log.i(Prefs.TAG, "doInBackground finished");
			return Integer.valueOf(0);
		}

		protected void onProgressUpdate(Integer... progress) {
		}

		@Override
		protected void onCancelled() {
			Log.i(Prefs.TAG, "onCancelled");
			//progressDialog.dismiss();
			super.onCancelled();
		}


		@Override
		protected void onPostExecute(Integer result) {
			Log.i(Prefs.TAG, "onPostExecute");
			progressDialog.dismiss();
			super.onPostExecute(result);



			launchSubActivity(MediaPreviewActivity.class);
		}

	}
*/

}
