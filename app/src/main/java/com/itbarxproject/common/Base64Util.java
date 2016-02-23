package com.itbarxproject.common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 02483564 on 23.2.2016.
 */
public class Base64Util {

    public static String getEncodedImageFile(String filePath)
    {
        String encodedString = null;
        String file_extn = filePath.substring(filePath.lastIndexOf(".")+1);
        try {
            if (file_extn.equals("img") || file_extn.equals("jpg") || file_extn.equals("jpeg") || file_extn.equals("gif") || file_extn.equals("png")) {
                //FINE
                Bitmap bm = BitmapFactory.decodeFile(filePath);
                ByteArrayOutputStream bao = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 75, bao);
                byte[] byteArray = bao.toByteArray();
                //generate base64 string of image


                encodedString = Base64.encodeToString(byteArray, Base64.DEFAULT);
            }
            else{
                Log.v("Base64Util", "NOT IN REQUIRED FORMAT for IMAGE");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.v("Base64Util", "BYTE DECODE PROBLEM" + e.getMessage());
        }

        return encodedString;
    }

    public static Bitmap getDecodedImageFile(String encodedBase64String)
    {
        Bitmap bitmap =null;
        try
        {
            InputStream stream = new ByteArrayInputStream(Base64.decode(encodedBase64String.getBytes(), Base64.DEFAULT));
            bitmap = BitmapFactory.decodeStream(stream);

        }
        catch (Exception e)
        {
            Log.v("Bitmap", " getDecodedImageFile BYTE DECODE PROBLEM" + e.getMessage());
        }
        return bitmap;
    }
    public  static  String  getEncodeBase4BinaryImage(String filePath)
    {
        byte[] bytes;
        byte[] buffer = new byte[8192];
        int bytesRead;
        InputStream inputStream = null;
        ByteArrayOutputStream output =null;//You can get an inputStream using any IO API
        try {
            inputStream = new FileInputStream(filePath);
             output = new ByteArrayOutputStream();
            try {
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
               return null;
            }
        } catch (FileNotFoundException e) {
            return null;
        }


        bytes = output.toByteArray();
        String encodedString = Base64.encodeToString(bytes, Base64.DEFAULT);

        return  encodedString;
    }
}
