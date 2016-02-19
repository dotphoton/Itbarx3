package com.itbarxproject.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Camera;

/**
 * Created by Umut Boz on 12.09.2015.
 */
public class VideoUtility {

    protected static   boolean cameraFront =true;

    public String getVideRecordPath(Context context){

        return DiskUtility.getInternalPath(context) + DiskUtility.DIR_SEPORATOR + "Barx" + DiskUtility.DIR_SEPORATOR;
    }

    protected static int findBackFacingCamera() {
        int cameraId = -1;
        // Search for the back facing camera
        // get the number of cameras
        int numberOfCameras = Camera.getNumberOfCameras();
        // for every camera check
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
                cameraId = i;
                cameraFront = false;
                break;
            }

        }

        return cameraId;

    }
    public static void saveLastVideoPath(Context context,String videoPath) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "VideoPathPreferences", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("VideoPath", videoPath);
        editor.commit();
    }
    public static String getLastVideoPath(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "VideoPathPreferences", 0);
        return sharedPreferences.getString("VideoPath", "");
    }

    public static void saveLastVoicePath(Context context,String voicePath) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "VoicePathPreferences", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("VoicePath", voicePath);
        editor.commit();
    }
    public static String getLastVoicePath(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "VoicePathPreferences", 0);
        return sharedPreferences.getString("VoicePath", "");
    }
    public static int getCameraType() {
        // if the camera preview is the front
        int camId =-1;
        if (cameraFront) {
            int cameraId = findFrontFacingCamera();
            if (cameraId >= 0) {
                // open the frontFacingCamera
             camId =cameraId;
            }
            else
            {
                camId = findBackFacingCamera();
            }
        } else {
            camId = findBackFacingCamera();
        }
        return  camId;
    }


    protected static int findFrontFacingCamera() {

        int cameraId = -1;
        // Search for the front facing camera
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i = 0; i < numberOfCameras; i++) {
            Camera.CameraInfo info = new Camera.CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
                cameraId = i;
                cameraFront = true;
                break;
            }
        }
        return cameraId;
    }

    private boolean hasCamera(Context context) {
        // check if the device has camera
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            return true;
        } else {
            return false;
        }
    }



}
