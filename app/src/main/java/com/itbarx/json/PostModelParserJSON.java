package com.itbarx.json;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itbarx.model.post.PostGetPostDetailModel;
import com.itbarx.model.post.PostGetWallInfoModel;
import com.itbarx.model.post.PostNewPostListModel;
import com.itbarx.model.post.PostPopularPostListModel;
import com.itbarx.model.post.PostTimelineListForUserModel;
import com.itbarx.model.post.PostWallListForUserModel;

import android.util.Log;

public class PostModelParserJSON {

	public List<PostTimelineListForUserModel> getPostTimelineListForUserModelFromJSON(String json) {

	List<PostTimelineListForUserModel> model = null;
	try {
		Type type = new TypeToken<List<PostTimelineListForUserModel>>() {
		}.getType();

		model = new Gson().fromJson(json, type);

	} catch (Exception ex) {
		Log.d("getPostTimelineListForUserModelFromJSON JSON PARSER", ex.getMessage());
	}

	return model;
	}

	public List<PostWallListForUserModel> getPostWallListForUserModelFromJSON(String json) {

	List<PostWallListForUserModel> model = null;
	try {
		Type type = new TypeToken<List<PostWallListForUserModel>>() {
		}.getType();

		model = new Gson().fromJson(json, type);

	} catch (Exception ex) {
		Log.d("getPostWallListForUserModelFromJSON JSON PARSER", ex.getMessage());
	}

	return model;
	}

	public List<PostPopularPostListModel> getPostPopularPostListModelFromJSON(String json) {

	List<PostPopularPostListModel> model = null;
	try {
		Type type = new TypeToken<List<PostPopularPostListModel>>() {
		}.getType();

		model = new Gson().fromJson(json, type);

	} catch (Exception ex) {
		Log.d("getPostPopularPostListModelFromJSON JSON PARSER", ex.getMessage());
	}

	return model;
	}

	public List<PostNewPostListModel> getPostNewPostListModelFromJSON(String json) {

	List<PostNewPostListModel> model = null;
	try {
		Type type = new TypeToken<List<PostNewPostListModel>>() {
		}.getType();

		model = new Gson().fromJson(json, type);

	} catch (Exception ex) {
		Log.d("getPostNewPostListModelFromJSON JSON PARSER", ex.getMessage());
	}

	return model;
	}

	public PostGetWallInfoModel getPostGetWallInfoModelFromJSON(String json) {

	PostGetWallInfoModel model = null;
	try {
		Type type = new TypeToken<PostGetWallInfoModel>() {
		}.getType();

		model = new Gson().fromJson(json, type);

	} catch (Exception ex) {
		Log.d("getPostGetWallInfoModelFromJSON JSON PARSER", ex.getMessage());
	}

	return model;
	}

	public PostGetPostDetailModel getPostGetPostDetailModelFromJSON(String json) {

	PostGetPostDetailModel model = null;
	try {
		Type type = new TypeToken<PostGetPostDetailModel>() {
		}.getType();

		model = new Gson().fromJson(json, type);

	} catch (Exception ex) {
		Log.d("getPostGetPostDetailModelFromJSON JSON PARSER", ex.getMessage());
	}

	return model;
	}
	public String getPostAddModelFromJSON(String json) {

		String model = null;
		try {
			Type type = new TypeToken<String>() {
			}.getType();

			model = new Gson().fromJson(json, type);

		} catch (Exception ex) {
			Log.d("getPostAddModelFromJSON JSON PARSER", ex.getMessage());
		}

		return model;
	}
}
