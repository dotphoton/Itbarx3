package com.itbarxproject.utils;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Umut Boz on 16.09.2015.
 */
public class VideCodecUtility {

    public  static  String INSTALLATION_PATH="";
    public  static  String CACHE_DIR="/Cache/";
    private static String TAG = "VIDEO_CODEC_PERMISSION";
    public static void installBinaryFromRaw(Context context, int resId, File file) {
        final InputStream rawStream = context.getResources().openRawResource(resId);
        final OutputStream binStream = getFileOutputStream(file);

        if (rawStream != null && binStream != null) {
            pipeStreams(rawStream, binStream);

            try {
                rawStream.close();
                binStream.close();
            } catch (IOException e) {
                Log.e(TAG, "Failed to close streams!", e);
            }

            doChmod(file, 777);
        }
    }


    public static OutputStream getFileOutputStream(File file) {
        try {
            return new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            Log.e(TAG, "File not found attempting to stream file.", e);
        }
        return null;
    }
    public static void pipeStreams(InputStream is, OutputStream os) {
        byte[] buffer = new byte[1024*16];
        int count;
        try {
            while ((count = is.read(buffer)) > 0) {
                os.write(buffer, 0, count);
            }
        } catch (IOException e) {
            Log.e(TAG, "Error writing stream.", e);
        }
    }
    public static void doChmod(File file, int chmodValue) {
        final StringBuilder sb = new StringBuilder();
        sb.append("chmod");
        sb.append(' ');
        sb.append(chmodValue);
        sb.append(' ');
        sb.append(file.getAbsolutePath());

        try {
            Runtime.getRuntime().exec(sb.toString());
        } catch (IOException e) {
            Log.e(TAG, "Error performing chmod", e);
        }
    }
/*
    private void installFfmpeg() {
        File ffmpegFile = new File(CACHE_DIR, "ffmpeg");
        INSTALLATION_PATH = ffmpegFile.toString();
        Log.d(TAG, "ffmpeg install path: " + INSTALLATION_PATH);

        if (!ffmpegFile.exists()) {
            try {
                ffmpegFile.createNewFile();
            } catch (IOException e) {
                Log.e(TAG, "Failed to create new file!", e);
            }
            installBinaryFromFile(this, , ffmpegFile);
        }else{
            Log.d(TAG, "It was already installed");
        }

        ffmpegFile.setExecutable(true);
        Log.d(TAG, String.valueOf(ffmpegFile.canExecute()));
    }
    */
}
