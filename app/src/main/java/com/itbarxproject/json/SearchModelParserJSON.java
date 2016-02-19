package com.itbarxproject.json;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itbarxproject.model.search.SearchUserForAutoCompleteResultModel;
import com.itbarxproject.model.search.SearchUserListResultModel;

import android.util.Log;

public class SearchModelParserJSON {

	public List<SearchUserForAutoCompleteResultModel> getSearchUserForAutoCompleteResultModelFromJSON(String json) {

	List<SearchUserForAutoCompleteResultModel> model = null;
	try {
		Type type = new TypeToken<List<SearchUserForAutoCompleteResultModel>>() {
		}.getType();

		model = new Gson().fromJson(json, type);

	} catch (Exception ex) {
		Log.d("getSearchUserForAutoCompleteResultModelFromJSON JSON PARSER", ex.getMessage());
	}

	return model;
	}

	public List<SearchUserListResultModel> getSearchUserListResultModelFromJSON(String json) {

	List<SearchUserListResultModel> model = null;
	try {
		Type type = new TypeToken<List<SearchUserListResultModel>>() {
		}.getType();

		model = new Gson().fromJson(json, type);

	} catch (Exception ex) {
		Log.d("getSearchUserListResultModelFromJSON JSON PARSER", ex.getMessage());
	}

	return model;
	}

}
