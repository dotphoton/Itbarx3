package com.itbarx.error.json;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itbarx.error.common.ItbarxServiceResponseModel;

import java.lang.reflect.Type;



/**
 * Created by Umut Boz on 19.09.2015.
 */
public class ItbarxServiceResponseModelParser {

    public  ItbarxServiceResponseModel getItbarxServiceResponseModelFormJson(String json) {
        ItbarxServiceResponseModel model = null;
        try {
            Type type = new TypeToken<ItbarxServiceResponseModel>() {
            }.getType();
            Gson gson = new Gson();
            model = gson.fromJson(json, type);
        } catch (Exception ex) {

            int d = Log.d("getBillinVerifyModelFromJSON JSON PARSER", ex.getMessage());
        }
        return model;
    }
}
