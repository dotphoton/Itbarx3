package com.itbarxproject.activity;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Environment;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;

import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegNotSupportedException;
import com.itbarxproject.R;
import com.itbarxproject.common.FileAttribute;
import com.itbarxproject.custom.component.TextViewBold;
import com.itbarxproject.exception.ExceptionHandler;
import com.itbarxproject.listener.OneShotOnClickListener;
import com.itbarxproject.nuance.NuanceOperatorHelper;
import com.itbarxproject.utils.FileUtility;
import com.itbarxproject.utils.VideoUtility;

import java.io.File;
import java.io.IOException;

/**
 * Created by Umut Boz on 13.09.2015.
 */
public class MediaPreviewActivity extends BaseActivity implements NuanceOperatorHelper.NuanceCallback,TextureView.SurfaceTextureListener {

	private String VIDEO_PATH_NAME = "/data/data/";
	private String FFMPEG_INSTALLATION_PATH = "";
	private String VIDEO_VIRTUAL_PATH_NAME = Environment.getExternalStorageDirectory() + "/com.itbarx/";
	private static final String VIDEO_EXTENSION = ".mp4";

	private MediaPlayer mMediaPlayer;
	MediaController controller;
	private TextureView videoView;
	private View mToggleButton;
	protected boolean isMediaRunning = false;

	private TextViewBold txtRecordInfo;
	protected ImageView imageView, playVideoImageView;
	protected ImageButton btnCancel, btnAccept;
	private RelativeLayout videoRelativeLayout;
	private boolean isConfigOk = false;
	boolean isConvertAudioStatus = false;
	boolean isFFmpegLoadStatus = false;

	@Override protected int getLayoutResourceId() {

		return R.layout.media_record_preview_screen;
	}

	@Override protected Context getContext() {
		// TODO Auto-generated method stub
		return MediaPreviewActivity.this;
	}
	@Override protected void exceptionHandler() {
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
	}

	@Override protected void initViews() {
		VIDEO_PATH_NAME += getContext().getPackageName().toString() + "/";
		imageView = (ImageView) findViewById(R.id.media_record_preview_pause_imageView);
		playVideoImageView = (ImageView) findViewById(R.id.media_record_preview_play_imageView);
		playVideoImageView.setOnClickListener(playVideoClickListener);
		imageView.setOnClickListener(pauseClickListener);
		txtRecordInfo = (TextViewBold) findViewById(R.id.media_record_preview_pause_textView);

		btnCancel = (ImageButton) findViewById(R.id.media_record_preview_deny_button);
		btnAccept = (ImageButton) findViewById(R.id.media_record_preview_accept_button);
		btnAccept.setOnClickListener(acceptClickListener);
		btnCancel.setOnClickListener(cancelClickListener);


		videoRelativeLayout = (RelativeLayout) findViewById(R.id.media_record_preview_main_videoView_relative);
		// videoRelativeLayout.setRotation(90);
		controller = new MediaController(getContext());
		//videoView.setOnCompletionListener(onCompletionListener);
		//controller.setAnchorView(videoRelativeLayout);

		videoView = (TextureView)findViewById(R.id.media_record_preview_main_videoView);
		videoView.setSurfaceTextureListener(this);
		videoView.setScaleX(isMirrored ? -1 : 1);
	}

	protected void stopPlayer() {
		mMediaPlayer.stop();
		mMediaPlayer.pause();
		isStop = true;
		((ImageView) findViewById(R.id.media_record_preview_play_imageView)).setVisibility(View.VISIBLE);

	}
	boolean isMirrored = true;
	MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
		@Override public void onCompletion(MediaPlayer mp) {
			if (!mMediaPlayer.isPlaying()) {
				((ImageView) findViewById(R.id.media_record_preview_play_imageView)).setVisibility(View.VISIBLE);
			}

		}
	};

	OneShotOnClickListener acceptClickListener = new OneShotOnClickListener(200) {

		@Override public void onOneShotClick(View v) {

			String lastFile = VideoUtility.getLastVideoPath(getContext());
			if (!lastFile.equalsIgnoreCase("")) {
				stopPlayer();
				final File sourceFile = new File(lastFile);
				FileAttribute fa = FileUtility.getFileAttribute(sourceFile.getAbsolutePath());
				final File target = new File(fa.path(), fa.filename() + ".wav");
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
					//String cmd="-i " + tagrgetFile.getAbsolutePath() + " -f mp3 -ab 192000 -vn "+target.getAbsolutePath();
					//String cmd="-i " + tagrgetFile.getAbsolutePath() + " -vn -acodec libmp3lame -ac 2 -ab 160k -ar 48000 " +target.getAbsolutePath();

					//aac çalışan örnek
					String cmdAac = "-i " + sourceFile.getAbsolutePath() + " -vn -acodec copy " + target.getAbsolutePath();

					//wav örnek
					//  String cmdWav="-i " + sourceFile.getAbsolutePath() + " -vn -acodec pcm_s16le -ac 2 " +target.getAbsolutePath();
					String cmdWav = "-i " + sourceFile.getAbsolutePath() + " -vn -acodec pcm_s16le -ar 16000 -ac 1 " + target.getAbsolutePath();
					// to execute "ffmpeg -version" command you just need to pass "-version"
					//-vn -ab 256
					//wma örnek command
					String cmdWma = "-i " + sourceFile.getAbsolutePath() + " -vn -ab 128k " + target.getAbsolutePath();
					ffmpeg.execute(cmdWav, new ExecuteBinaryResponseHandler() {

						@Override public void onStart() {

						}

						@Override public void onProgress(String message) {
						}

						@Override public void onFailure(String message) {
							isConvertAudioStatus = false;
						}

						@Override public void onSuccess(String message) {
							isConvertAudioStatus = true;

						}

						@Override public void onFinish() {
							dismissProgress();
							if (isConvertAudioStatus) {
								// showAlert(getString(R.string.));
								convertAudioToText(target.getAbsolutePath());
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

	};

	OneShotOnClickListener cancelClickListener = new OneShotOnClickListener(200) {

		@Override public void onOneShotClick(View v) {

			if (!isStop) {
				stopPlayer();
			}
			deleteTempFile();
			finish();
		}
	};
	OneShotOnClickListener pauseClickListener = new OneShotOnClickListener(200) {

		@Override public void onOneShotClick(View v) {

			if (mMediaPlayer.isPlaying()) {
				isStop = false;
				mMediaPlayer.pause();
				((ImageView) findViewById(R.id.media_record_preview_play_imageView)).setVisibility(View.VISIBLE);
			}
		}
	};

	OneShotOnClickListener playVideoClickListener = new OneShotOnClickListener(200) {

		@Override public void onOneShotClick(View v) {

			((ImageView) findViewById(R.id.media_record_preview_play_imageView)).setVisibility(View.INVISIBLE);
			if (!isStop) {

				mMediaPlayer.start();
			} else {
				mMediaPlayer.start();
			}
			isStop = false;
		}
	};

	boolean isPauseEnter = false;
	boolean isFileDelete = false;
	boolean isStop = false;

	@Override protected void onPause() {

		isPauseEnter = true;
		if (isMediaRunning) {
			if (videoView != null) stopPlayer();
			//iptal dolduğu için geçmiş videoyu temizle
			deleteTempFile();

		}
		super.onPause();
	}

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

	protected void convertAudioToText(String audioFile) {
		try {
			showProgress(getString(R.string.convert_to_text));
			NuanceOperatorHelper nuanceOperatorHelper = new NuanceOperatorHelper(MediaPreviewActivity.this, "tr_TR");
			nuanceOperatorHelper.execute(audioFile);
		} catch (Exception ex) {
			showAlert(getString(R.string.make_working_failure));
		}

	}

	@Override public void onCompleteSpeechConverter(String text) {
		dismissProgress();
		MediaPublishActivity.SPEECH_TEXT = text;
		launchSubActivity(MediaPublishActivity.class);
	}

	@Override
	public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
		String lastFile = VideoUtility.getLastVideoPath(getContext());
		File videoFile = new File(lastFile);
		// videoView.setRotation(270);
		if (videoFile.exists()) {

			mMediaPlayer = new MediaPlayer();
			mMediaPlayer.setSurface(new Surface(surface));
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
	@Override
	public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {return false;}
	@Override
	public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int width, int height) {}
	@Override
	public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		FileUtility.deleteAllFileUnderFolder(VIDEO_VIRTUAL_PATH_NAME);
	}
}
