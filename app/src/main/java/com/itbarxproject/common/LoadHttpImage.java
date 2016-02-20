package com.itbarxproject.common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import com.itbarxproject.R;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by 02483564 on 19.2.2016.
 */
public class LoadHttpImage extends AsyncTask<String, String, Bitmap> {
    private static final String TAG = "AsyncUploadImage ";
    public  LoadHttpImage(ImageView imageView)
    {
        img = imageView;
    }
    ImageView img;
    Bitmap bitmap;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }
    protected Bitmap doInBackground(String... args) {
        try {

            String data = args[0];
            if(data.startsWith("http"))
            {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());
            }
            else
            {
                data=data.substring(data.indexOf(",") + 1);
                //String data2 = Base64.encodeToString(data.getBytes(), Base64.DEFAULT);
               // InputStream stream = new ByteArrayInputStream(data2.getBytes());
                byte[] decodedString = Base64.decode(data, Base64.URL_SAFE);
               // InputStream stream = new ByteArrayInputStream(Base64.decode(data2.getBytes(), Base64.DEFAULT));
                bitmap= BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                //bitmap = BitmapFactory.decodeStream(stream);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    protected void onPostExecute(Bitmap image) {


        if (image != null) {
            img.setImageBitmap(image);
            Log.i(TAG, "image download ok！！！");
        } else {
            img.setBackgroundResource(R.drawable.video_circle);
            Log.i(TAG, "image download false！！！");
        }

    }
}