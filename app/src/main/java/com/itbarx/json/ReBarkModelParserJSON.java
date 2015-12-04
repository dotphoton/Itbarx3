package com.itbarx.json;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itbarx.model.rebark.ReBarkGetPostSharedUserListByPostIdModel;
import com.itbarx.model.rebark.ReBarkGetSharedPostListByUserIdModel;

import android.util.Log;

public class ReBarkModelParserJSON {

	public String getReBarkModelFromJSON(String json) {
		String isOK = null;
	try {
		Type type = new TypeToken<String>() {
		}.getType();
		Gson gson = new Gson();
		isOK = gson.fromJson(json, type);
	} catch (Exception ex) {
		Log.d("getReBarkPostShareModelFromJSON JSON PARSER", ex.getMessage());
	}
	return isOK;
	}

	public List<ReBarkGetSharedPostListByUserIdModel> getReBarkGetSharedPostListModelFromJSON(String json) {

	List<ReBarkGetSharedPostListByUserIdModel> model = null;
	try {
		Type type = new TypeToken<List<ReBarkGetSharedPostListByUserIdModel>>() {
		}.getType();

		model = new Gson().fromJson(json, type);

	} catch (Exception ex) {
		Log.d("getReBarkGetSharedPostListModelFromJSON JSON PARSER", ex.getMessage());
	}

	return model;
	}

	public List<ReBarkGetPostSharedUserListByPostIdModel> getReBarkGetPostSharedUserListModelFromJSON(String json) {

	List<ReBarkGetPostSharedUserListByPostIdModel> model = null;
	try {
		Type type = new TypeToken<List<ReBarkGetPostSharedUserListByPostIdModel>>() {
		}.getType();

		model = new Gson().fromJson(json, type);

	} catch (Exception ex) {
		Log.d("getReBarkGetPostSharedUserListModelFromJSON JSON PARSER", ex.getMessage());
	}

	return model;
	}


}
