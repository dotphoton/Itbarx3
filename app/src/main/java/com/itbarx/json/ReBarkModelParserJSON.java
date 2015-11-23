package com.itbarx.json;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itbarx.model.rebark.ReBarkGetPostSharedUserListModel;
import com.itbarx.model.rebark.ReBarkGetSharedPostListModel;

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

	public List<ReBarkGetSharedPostListModel> getReBarkGetSharedPostListModelFromJSON(String json) {

	List<ReBarkGetSharedPostListModel> model = null;
	try {
		Type type = new TypeToken<List<ReBarkGetSharedPostListModel>>() {
		}.getType();

		model = new Gson().fromJson(json, type);

	} catch (Exception ex) {
		Log.d("getReBarkGetSharedPostListModelFromJSON JSON PARSER", ex.getMessage());
	}

	return model;
	}

	public List<ReBarkGetPostSharedUserListModel> getReBarkGetPostSharedUserListModelFromJSON(String json) {

	List<ReBarkGetPostSharedUserListModel> model = null;
	try {
		Type type = new TypeToken<List<ReBarkGetPostSharedUserListModel>>() {
		}.getType();

		model = new Gson().fromJson(json, type);

	} catch (Exception ex) {
		Log.d("getReBarkGetPostSharedUserListModelFromJSON JSON PARSER", ex.getMessage());
	}

	return model;
	}


}
