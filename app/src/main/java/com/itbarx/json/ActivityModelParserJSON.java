package com.itbarx.json;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itbarx.model.activity.ActivityListModel;

import android.util.Log;

public class ActivityModelParserJSON {

	public List<ActivityListModel> getActivityListModelFromJSON(String json) {

	List<ActivityListModel> model = null;
	try {
		Type type = new TypeToken<List<ActivityListModel>>() {
		}.getType();

		model = new Gson().fromJson(json, type);

	} catch (Exception ex) {
		Log.d("getActivityListModelFromJSON JSON PARSER", ex.getMessage());
	}

	return model;
	}

}
