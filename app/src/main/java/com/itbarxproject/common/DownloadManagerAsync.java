package com.itbarxproject.common;

import android.os.AsyncTask;
import android.util.Log;

import com.itbarxproject.activity.BarkActivity;
import com.itbarxproject.activity.BaseActivity;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by 02483564 on 15.10.2015.
 */
public class DownloadManagerAsync extends AsyncTask<String, String, Boolean> {

    private DownloadManagerCallback callback;


    public static interface DownloadManagerCallback {
        public void onCompleteFileDownload(Boolean status, String file);
    }

    String localData= null;
    BaseActivity baseActivity;
    public DownloadManagerAsync(BaseActivity activity,String LocalData)
    {
        callback = (DownloadManagerCallback)activity;
        baseActivity =(BarkActivity)activity;
        localData = LocalData;
    }
    /**
     * Before starting background thread Show Progress Bar Dialog
     * */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        baseActivity.showProgress("bark yükleniyor");
    }


    protected Boolean downloadFile(String urlx)
    {
        Boolean status =false;
        int count;
        try {
            URL url = new URL(urlx);
            URLConnection conection = url.openConnection();
            conection.connect();

            // this will be useful so that you can show a tipical 0-100%
            // progress bar
            int lenghtOfFile = conection.getContentLength();

            // download the file
            InputStream input = new BufferedInputStream(url.openStream(),
                    8192);

            // Output stream
            OutputStream output = new FileOutputStream(localData);

            byte data[] = new byte[1024];

            long total = 0;

            while ((count = input.read(data)) != -1) {
                total += count;
                // publishing the progress....
                // After this onProgressUpdate will be called
                publishProgress("" + (int) ((total * 100) / lenghtOfFile));

                // writing data to file
                output.write(data, 0, count);
            }

            // flushing output
            output.flush();

            // closing streams
            output.close();
            input.close();
            status=true;
        } catch (Exception e) {
            Log.d("Error: ", e.getMessage());
            status =false;
        }
        return status;
    }
    /**
     * Downloading file in background thread
     * */
    @Override
    protected Boolean doInBackground(String... f_url) {


        return downloadFile(f_url[0]);
    }

    /**
     * Updating progress bar
     * */
    protected void onProgressUpdate(String... progress) {
        // setting progress percentage
        baseActivity.setProgress(Integer.parseInt(progress[0]));
    }


    /**
     * After completing background task Dismiss the progress dialog
     * **/
    @Override
    protected void onPostExecute(Boolean status) {
        // dismiss the dialog after the file was downloaded
        baseActivity.dismissProgress();
        if(status)
        {
                callback.onCompleteFileDownload(status,localData);
        }
        else
        {
            callback.onCompleteFileDownload(status,localData);
        }
    }

}
