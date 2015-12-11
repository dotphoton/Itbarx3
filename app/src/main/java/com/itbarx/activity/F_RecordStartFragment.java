package com.itbarx.activity;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;

import com.itbarx.R;
import com.itbarx.application.ItbarxGlobal;
import com.itbarx.custom.component.TextViewBold;
import com.itbarx.custom.component.TextViewRegular;
import com.itbarx.enums.Fragments;
import com.itbarx.listener.OneShotOnClickListener;
import com.itbarx.utils.TextSizeUtil;
import com.itbarx.utils.VideoUtility;

import java.io.IOException;

/**
 * TODO: Add a class header comment!
 */
public class F_RecordStartFragment extends Fragment implements TextureView.SurfaceTextureListener {



	private T_RecordActivity t_recordActivity;
	private TextViewRegular txtToolbarRecord;
	private TextViewBold txtStartRecord;
	private ImageView btnStart;

	private MediaPlayer mMediaPlayer;
	private Camera mCamera;
	private TextureView videoView;
	Communicator comm;

	public F_RecordStartFragment() {
	}

	public static F_RecordStartFragment newInstance(T_RecordActivity t_recordActivity) {
		F_RecordStartFragment myFragment = new F_RecordStartFragment();
		myFragment.t_recordActivity = t_recordActivity;
		return myFragment;
	}

	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_record_start_screen, container, false);
	}

	@Override public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		comm = (Communicator) getActivity();
		videoView = (TextureView)t_recordActivity.findViewById(R.id.record_start_fragment_main_information_textureView);
		txtToolbarRecord = (TextViewRegular) t_recordActivity.findViewById(R.id.record_start_fragment_toolbar_text);
		txtStartRecord = (TextViewBold) t_recordActivity.findViewById(R.id.record_start_fragment_start_textView);
		btnStart = (ImageView) t_recordActivity.findViewById(R.id.record_start_fragment_start_imageView);
		btnStart.setOnClickListener(startRecordingClickListener);


		videoView.setSurfaceTextureListener(this);
		//videoView.setScaleX(isMirrored ? -1 : 1);
		setTextSize();
	}

	private void setTextSize() {
		txtToolbarRecord.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getToolbarTextSize());
		txtStartRecord.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getRecordStartTextSize());

	}

	OneShotOnClickListener startRecordingClickListener = new OneShotOnClickListener(500) {

		@Override public void onOneShotClick(View v) {

			stopCamera();
			Intent intent =new Intent(t_recordActivity,MediaRecorderActivity.class);
			t_recordActivity.startActivityForResult(intent,111);
			//t_recordActivity.launchSubActivityForResult(MediaRecorderActivity.class);
		}
	};

	void  stopCamera()
	{
		if(mCamera!=null)
		{
			mCamera.stopPreview();
			mCamera.setPreviewCallback(null);
			mCamera.release();
			mCamera =null;

		}
	}
	@Override
	public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
		initPreview(surface);

	}

	@Override
	public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

	}

	@Override
	public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
		stopCamera();
		return true;
	}

	@Override
	public void onSurfaceTextureUpdated(SurfaceTexture surface) {

	}

	@Override
	public void onDestroy() {
		stopCamera();
		super.onDestroy();
	}

	public void  initPreview(SurfaceTexture surface)
	{
		try
		{
			if (mCamera == null) {
				int camId = VideoUtility.getCameraType();
				mCamera = Camera.open(camId);

				Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
				Camera.getCameraInfo(camId, cameraInfo);

				int rotation = t_recordActivity.getWindowManager().getDefaultDisplay().getRotation();
				int degrees = 0;
				switch (rotation) {
					case Surface.ROTATION_0:
						degrees = 0;
						break; //Natural orientation
					case Surface.ROTATION_90:
						degrees = 90;
						break; //Landscape left
					case Surface.ROTATION_180:
						degrees = 180;
						break;//Upside down
					case Surface.ROTATION_270:
						degrees = 270;
						break;//Landscape right
				}
				int rotate = (cameraInfo.orientation - degrees + 360) % 360;
				int newOrientation = 0;
				if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
					newOrientation = (360 - newOrientation) % 360; // compensate the mirror
				}
				mCamera.setDisplayOrientation(newOrientation + 90);
				//STEP #2: Set the 'rotation' parameter
			/*
			Camera.Parameters params = mCamera.getParameters();
			params.setRotation(180);
			mCamera.setParameters(params);

			*/
				try {
					if(surface==null)
					{
						mCamera.setPreviewTexture(videoView.getSurfaceTexture());
					}
					else
					{
						mCamera.setPreviewTexture(surface);
					}


					mCamera.startPreview();
				} catch (IOException ioe) {
					// Something bad happened
				}
			}
			else
			{

			}
		}
		catch (Exception ex)
		{

		}

	}
}
