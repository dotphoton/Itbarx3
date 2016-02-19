package com.itbarxproject.nuance;

/**
 * Created by Umut Boz on 07.10.2015.
 */

import android.os.AsyncTask;
import android.util.Log;

import com.itbarxproject.activity.BaseActivity;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;

import java.net.URI;
import java.util.List;


public class NuanceOperatorHelper extends AsyncTask<String,Void,Boolean> {


    private NuanceCallback callback;

    public static interface NuanceCallback {
        public void onCompleteSpeechConverter(String text);
    }

    /* NUANCE TEST */
        private String selectLanguageCode="tr_TR";
        private String audioFile ="";
        private BaseActivity baseActivity;
        private List<String> responseDataList=null;

        public NuanceOperatorHelper(BaseActivity activity,String mLanguageCode)
        {
            callback = (NuanceCallback)activity;
            baseActivity = (BaseActivity)activity;
            selectLanguageCode = mLanguageCode;

        }

        boolean sendStatus()
        {

            boolean status =false;

            DictationHTTPClient  dictationHttpClient = new DictationHTTPClient(selectLanguageCode,audioFile);
            try {
                HttpClient httpclient = dictationHttpClient.getHttpClient();
                InputStreamEntity reqEntity = dictationHttpClient.setAudioContent();
                URI uri = dictationHttpClient.getURI();
                HttpPost httppost = dictationHttpClient.getHeader(uri,1);    //fileSize);

                httppost.setEntity(reqEntity);
                // Add a debug statement here showing all values being used for this request

                dictationHttpClient.printSettings();



                Log.d("ITbarx", "----------------- Send Audio ----------------------");

                Log.d("ITbarx", "executing request " + httppost.getRequestLine());
                HttpResponse response = httpclient.execute(httppost);
                Log.d("ITbarx", "----------------- Processing Response ----------------------");
                responseDataList=    dictationHttpClient.processResponse(response);
                for(String data :responseDataList)
                {
                    Log.d("ITbarx",data);
                }
                // Log.d("ITbarx", responseData);

                status =true;
            } catch (Exception ex) {
//System.out.println(e.toString());
                ex.printStackTrace(System.out);
                status =false;
            } finally {
                // When HttpClient instance is no longer needed,
                // shut down the connection manager to ensure
                // immediate deallocation of all system resources
                if (dictationHttpClient != null && dictationHttpClient.httpclient != null)
                    dictationHttpClient.httpclient.getConnectionManager().shutdown();
                status =true;
            }
            return status;
        }




    @Override
    protected Boolean doInBackground(String... AudioFile) {
        audioFile =AudioFile[0];
        return  sendStatus();
    }

    @Override
    protected void onPostExecute(Boolean result) {

        if(result)
        {
            if(responseDataList!=null&&responseDataList.size()>0)
            {
                callback.onCompleteSpeechConverter(responseDataList.get(0));
            }else
            {
                callback.onCompleteSpeechConverter(null);
            }
        }
        else
        {
            callback.onCompleteSpeechConverter(null);
        }

    }
}
