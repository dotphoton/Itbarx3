package com.itbarxproject.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.util.TypedValue;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;

import com.itbarxproject.R;
import com.itbarxproject.custom.component.TextViewBold;
import com.itbarxproject.custom.component.TextViewRegular;
import com.itbarxproject.listener.OneShotOnClickListener;
import com.itbarxproject.utils.BarkUtility;
import com.itbarxproject.utils.TextSizeUtil;
import com.itbarxproject.utils.VideoUtility;

import java.io.IOException;

/**
 * Created by 02483564 on 20.2.2016.
 */
public class ReplyRecordActivity extends BaseActivity implements TextureView.SurfaceTextureListener{
    protected String POST_ID = null;

    private TextViewRegular txtToolbarRecord;
    private TextViewBold txtStartRecord;
    private ImageView btnStart;

    private MediaPlayer mMediaPlayer;
    private Camera mCamera;
    private TextureView videoView;
    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_record_start_screen;
    }

    @Override
    protected Context getContext() {
        return ReplyRecordActivity.this;
    }
//record_start_fragment_toolbar_text
    @Override
    protected void initViews() {
        if(getIntent().getStringExtra(BarkUtility.POST_ID_KEY)==null)
        {
            finish();
        }
        POST_ID = getIntent().getStringExtra(BarkUtility.POST_ID_KEY);
        videoView = (TextureView)findViewById(R.id.record_start_fragment_main_information_textureView);
        txtToolbarRecord = (TextViewRegular) findViewById(R.id.record_start_fragment_toolbar_text);
        txtStartRecord = (TextViewBold) findViewById(R.id.record_start_fragment_start_textView);
        btnStart = (ImageView) findViewById(R.id.record_start_fragment_start_imageView);
        btnStart.setOnClickListener(startRecordingClickListener);
        videoView.setSurfaceTextureListener(this);
        //videoView.setScaleX(isMirrored ? -1 : 1);
        setTextSize();
    }

    private void setTextSize() {
        txtToolbarRecord.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getToolbarTextSize());
        txtStartRecord.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getRecordStartTextSize());
        txtToolbarRecord.setText(getString(R.string.rebark));
    }
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

    OneShotOnClickListener startRecordingClickListener = new OneShotOnClickListener(500) {

        @Override public void onOneShotClick(View v) {

            stopCamera();
            //Intent intent =new Intent(t_recordActivity,MediaRecorderActivity.class);
            //t_recordActivity.startActivityForResult(intent,111);
            //t_recordActivity.launchSubActivityForResult(MediaRecorderActivity.class);
        }
    };
    public void  initPreview(SurfaceTexture surface)
    {
        try
        {
            if (mCamera == null) {
                int camId = VideoUtility.getCameraType();
                mCamera = Camera.open(camId);

                Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
                Camera.getCameraInfo(camId, cameraInfo);

                int rotation = getWindowManager().getDefaultDisplay().getRotation();
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
    @Override
    public void onDestroy() {
        stopCamera();
        super.onDestroy();
    }
    @Override
    protected void exceptionHandler() {

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
}
