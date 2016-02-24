package com.itbarxproject.sl;

import java.util.ArrayList;
import java.util.List;

import com.itbarxproject.R;
import com.itbarxproject.application.ItbarxGlobal;
import com.itbarxproject.service.ResponseEventModel;
import com.itbarxproject.service.ServiceResponseModel;
import com.itbarxproject.enums.GlobalDataForWS;
import com.itbarxproject.enums.PostProcessLinks;
import com.itbarxproject.service.error.BarxErrorModel;
import com.itbarxproject.service.BasePostServiceSL;
import com.itbarxproject.error.sl.BaseServicePostClientSL;
import com.itbarxproject.json.PostModelParserJSON;
import com.itbarxproject.listener.PostProcessesServiceListener;
import com.itbarxproject.model.post.PostAddPostModel;
import com.itbarxproject.model.post.PostGetPostDetailModel;
import com.itbarxproject.model.post.PostGetWallInfoModel;
import com.itbarxproject.model.post.PostNewModel;
import com.itbarxproject.model.post.PostNewPostListModel;
import com.itbarxproject.model.post.PostPopularModel;
import com.itbarxproject.model.post.PostPopularPostListModel;
import com.itbarxproject.model.post.PostPostDetailModel;
import com.itbarxproject.model.post.PostTimelineListForUserModel;
import com.itbarxproject.model.post.PostTimelineModel;
import com.itbarxproject.model.post.PostWallInfoModel;

import com.itbarxproject.model.post.PostWallModel;
import com.itbarxproject.utils.ItbarxUtils;

import android.content.Context;
import android.util.Pair;

public class PostProcessesServiceSL extends BasePostServiceSL<String> {

	private static final String NAME_OF_THE_CLASS = PostProcessesServiceSL.class.getSimpleName();
	PostProcessesServiceListener<String> postProcessesServiceListener;

	public PostProcessesServiceSL(Context appContext, PostProcessesServiceListener<String> listener, int serviceResUriId) {
	super(appContext, listener, serviceResUriId);
	postProcessesServiceListener = listener;
	setOnServiceErrorClientListener(this);
	}

	// **********************************//
	// ---SEND DATA WEBSERVICE METHODS---
	// **********************************//

	// TIMELINE
	public void setTimeline(PostTimelineModel postTimelineModel) {

	List<Pair<String,String>> params = new ArrayList<Pair<String,String>>();
	params.add(new Pair(GlobalDataForWS.USER_ID.toString(), postTimelineModel.getUserID()));
	params.add(new Pair(GlobalDataForWS.PAGE.toString(), postTimelineModel.getPage()));
	params.add(new Pair(GlobalDataForWS.REC_PER_PAGE.toString(), postTimelineModel.getRecPerPage()));
	String postData = ItbarxUtils.formattedData(params);
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, PostProcessLinks.GET_TIMELINE.toString());
	}

	// WALL
	public void setWall(PostWallModel postWallModel) {

	List<Pair<String,String>> params = new ArrayList<Pair<String,String>>();
	params.add(new Pair(GlobalDataForWS.USER_ID.toString(), postWallModel.getUserID()));
	params.add(new Pair(GlobalDataForWS.PAGE.toString(), postWallModel.getPage()));
	params.add(new Pair(GlobalDataForWS.REC_PER_PAGE.toString(), postWallModel.getRecPerPage()));
	String postData = ItbarxUtils.formattedData(params);
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, PostProcessLinks.GET_WALL.toString());
	}

	// POPULAR POST
	public void setPopular(PostPopularModel postPopularModel) {

	List<Pair<String,String>> params = new ArrayList<Pair<String,String>>();
	params.add(new Pair(GlobalDataForWS.USER_ID.toString(), postPopularModel.getUserID()));
	params.add(new Pair(GlobalDataForWS.PAGE.toString(), postPopularModel.getPage()));
	params.add(new Pair(GlobalDataForWS.REC_PER_PAGE.toString(), postPopularModel.getRecPerPage()));
	String postData = ItbarxUtils.formattedData(params);
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, PostProcessLinks.GET_POPULAR.toString());
	}

	// NEW POST
	public void setNew(PostNewModel postNewModel) {

	List<Pair<String,String>> params = new ArrayList<Pair<String,String>>();
	params.add(new Pair(GlobalDataForWS.USER_ID.toString(), postNewModel.getUserID()));
	params.add(new Pair(GlobalDataForWS.PAGE.toString(), postNewModel.getPage()));
	params.add(new Pair(GlobalDataForWS.REC_PER_PAGE.toString(), postNewModel.getRecPerPage()));
	String postData = ItbarxUtils.formattedData(params);
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, PostProcessLinks.GET_NEW_POST.toString());
	}

	// WALL INFO
	public void setWallInfo(PostWallInfoModel postWallInfoModel) {

	List<Pair<String,String>> params = new ArrayList<Pair<String,String>>();
	params.add(new Pair(GlobalDataForWS.SEARCHER_ID.toString(), postWallInfoModel.getSearcherID()));
	params.add(new Pair(GlobalDataForWS.USER_ID.toString(), postWallInfoModel.getUserID()));
	String postData = ItbarxUtils.formattedData(params);
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, PostProcessLinks.GET_WALL_INFO.toString());
	}

	// POST DETAIL
	public void setGetPostDetail(PostPostDetailModel postPostDetailModel) {

	List<Pair<String,String>> params = new ArrayList<Pair<String,String>>();
	params.add(new Pair(GlobalDataForWS.POST_ID.toString(), postPostDetailModel.getPostID()));
		params.add(new Pair(GlobalDataForWS.USER_ID.toString(), ItbarxGlobal.getInstance().getAccountModel().getUserID()));
	String postData = ItbarxUtils.formattedData(params);
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, PostProcessLinks.GET_POST_DETAIL.toString());
	}

	// ADD POST
	public void setAddPost(PostAddPostModel postAddPostModel) {

	List<Pair<String,String>> params = new ArrayList<Pair<String,String>>();
	params.add(new Pair(GlobalDataForWS.POST_SPEECH_TEXT.toString(), postAddPostModel.getPostSpeechText()));
	params.add(new Pair(GlobalDataForWS.POST_SENDER_USER_ID.toString(), postAddPostModel.getPostSenderUserId()));
	params.add(new Pair(GlobalDataForWS.POST_SENDER_IP.toString(), postAddPostModel.getPostSenderIp()));
	params.add(new Pair(GlobalDataForWS.VIDEO_BYTES.toString(), postAddPostModel.getVideoBytes()));
		params.add(new Pair(GlobalDataForWS.POST_ADDED_TIME_ZONE_ID.toString(), postAddPostModel.getPostAddedTimeZoneId()));

	String postData = ItbarxUtils.formattedData(params);
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, PostProcessLinks.ADD_POST.toString());
	}
	// ************************//
	// ---OVERRIDED METHODS---
	// ************************//

	@Override
	public void onPOSTCommit(ResponseEventModel<String> responseEvent) {
	String result = responseEvent.getResponseData();

	// ---GET TIMELINE LIST FOR USER---
	if (responseEvent.getMethodName().equalsIgnoreCase(PostProcessLinks.GET_TIMELINE.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseArrayModelDataKey(result);
		List<PostTimelineListForUserModel> postTimelineListForUserModelResponse = null;
		if (model != null) {
		postTimelineListForUserModelResponse = new PostModelParserJSON().getPostTimelineListForUserModelFromJSON(model.getModel());
		}
		if (postTimelineListForUserModelResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
		errorModel.setErrMessage(context.getString(R.string.BrokenData));
		postProcessesServiceListener.onError(errorModel);
		} else {
		postProcessesServiceListener.getTimelineListForUser(postTimelineListForUserModelResponse);
		}
	} // ---GET WALL LIST FOR USER---
	else if (responseEvent.getMethodName().equalsIgnoreCase(PostProcessLinks.GET_WALL.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseArrayModelDataKey(result);
		List<PostPopularPostListModel> postWallListForUserModelResponse = null;
		if (model != null) {
			postWallListForUserModelResponse = new PostModelParserJSON().getPostWallListForUserModelFromJSON(model.getModel());
		}
		if (postWallListForUserModelResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
		errorModel.setErrMessage(context.getString(R.string.BrokenData));
		postProcessesServiceListener.onError(errorModel);
		} else {
		postProcessesServiceListener.getWallListForUser(postWallListForUserModelResponse);
		}

	} // ---GET POPULAR POST LIST FOR USER---
	else if (responseEvent.getMethodName().equalsIgnoreCase(PostProcessLinks.GET_POPULAR.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseArrayModelDataKey(result);
		List<PostPopularPostListModel> postPopularPostListModelResponse = null;
		if (model != null) {
		postPopularPostListModelResponse = new PostModelParserJSON().getPostPopularPostListModelFromJSON(model.getModel());
		}
		if (postPopularPostListModelResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
		errorModel.setErrMessage(context.getString(R.string.BrokenData));
		postProcessesServiceListener.onError(errorModel);
		} else {
		postProcessesServiceListener.getPopularPostList(postPopularPostListModelResponse);
		}

	} // ---GET NEW POST LIST FOR USER---
	else if (responseEvent.getMethodName().equalsIgnoreCase(PostProcessLinks.GET_NEW_POST.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseArrayModelDataKey(result);
		List<PostNewPostListModel> postNewPostListModelResponse = null;
		if (model != null) {
		postNewPostListModelResponse = new PostModelParserJSON().getPostNewPostListModelFromJSON(model.getModel());
		}
		if (postNewPostListModelResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
		errorModel.setErrMessage(context.getString(R.string.BrokenData));
		postProcessesServiceListener.onError(errorModel);
		} else {
		postProcessesServiceListener.getNewPostList(postNewPostListModelResponse);
		}

	}
	// GET WALL INFO
	else if (responseEvent.getMethodName().equalsIgnoreCase(PostProcessLinks.GET_WALL_INFO.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseModelDataKey(result);
		PostGetWallInfoModel postGetWallInfoModelResponse = null;
		if (model != null) {
		postGetWallInfoModelResponse = new PostModelParserJSON().getPostGetWallInfoModelFromJSON(model.getModel());
		}
		if (postGetWallInfoModelResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
		errorModel.setErrMessage(context.getString(R.string.BrokenData));
		postProcessesServiceListener.onError(errorModel);
		} else {
		postProcessesServiceListener.getWallInfo(postGetWallInfoModelResponse);
		}
	}
	// GET POST DETAIL
	else if (responseEvent.getMethodName().equalsIgnoreCase(PostProcessLinks.GET_POST_DETAIL.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseModelDataKey(result);
		PostGetPostDetailModel postGetPostDetailModelResponse = null;
		if (model != null) {
		postGetPostDetailModelResponse = new PostModelParserJSON().getPostGetPostDetailModelFromJSON(model.getModel());
		}
		if (postGetPostDetailModelResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
		errorModel.setErrMessage(context.getString(R.string.BrokenData));
		postProcessesServiceListener.onError(errorModel);
		} else {
		postProcessesServiceListener.getPostDetail(postGetPostDetailModelResponse);
		}
	}
	else if (responseEvent.getMethodName().equalsIgnoreCase(PostProcessLinks.ADD_POST.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseModelDataKey(result);
		String isAddedModelResponse = null;
		if (model != null) {
			isAddedModelResponse = new PostModelParserJSON().getPostAddModelFromJSON(model.getModel());
		}
		if (isAddedModelResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
			errorModel.setErrMessage(context.getString(R.string.BrokenData));
			postProcessesServiceListener.onError(errorModel);
		} else {
			postProcessesServiceListener.isAdded(isAddedModelResponse);
		}
	}
	}



	@Override
	public void onError(BarxErrorModel responseServiceErrorModel) {
	postProcessesServiceListener.onError(responseServiceErrorModel);

	}

}
