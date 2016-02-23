package com.itbarxproject.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

/**
 * Created by 02483564 on 23.2.2016.
 */
public class UploadHttpImage {

    Context context;
    Activity activity;
    public  String imageUri =null;
    public UploadHttpImage(Context mContext)
    {
        context = mContext;
        activity =(Activity)mContext;
    }
    public    void openImgageGallery(int requestCodeForResult)
    {
        Intent intent = new Intent();

        intent.setType("image/*");

        intent.setAction(Intent.ACTION_GET_CONTENT);

         activity.startActivityForResult(Intent.createChooser(intent, "Select file to upload "), requestCodeForResult);
    }

    public String setPath(Intent data) {

        Uri selectedImageUri = data.getData();
        imageUri = RealPathUtil.getRealPathFromURI_API19(context,selectedImageUri);

        return imageUri;

    }

    public String getImageBase64Decoded(ImageView previewImgView) {

        String decodedData = null;

        if(imageUri!=null)
        {
            String file_extn = imageUri.substring(imageUri.lastIndexOf(".")+1);
            try {
                if (file_extn.equals("img") || file_extn.equals("JPG")  || file_extn.equals("jpg") || file_extn.equals("jpeg") || file_extn.equals("gif") || file_extn.equals("png") || file_extn.equals("PNG")) {

                    if(previewImgView!=null)
                    {
                        Bitmap bm = BitmapFactory.decodeFile(imageUri);
                        previewImgView.setImageBitmap(bm);
                    }

                    decodedData = Base64Util.getEncodedImageFile(imageUri);
                }
                else{
                    Log.v("IMAGE_UPLOAD","NOT IN REQUIRED FORMAT for IMAGE");
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                Log.v("IMAGE_UPLOAD", "BYTE DECODE PROBLEM" + e.getMessage());
            }
        }
        return decodedData;

    }
}
