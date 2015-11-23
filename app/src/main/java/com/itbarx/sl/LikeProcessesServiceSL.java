package com.itbarx.sl;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.itbarx.R;
import com.itbarx.error.common.ResponseServiceModel;
import com.itbarx.error.common.ServiceResponseModel;
import com.itbarx.enums.GlobalDataForWS;
import com.itbarx.enums.LikeProcessesLinks;
import com.itbarx.error.model.BarxErrorModel;
import com.itbarx.error.sl.BasePostServiceSL;
import com.itbarx.error.sl.BaseServicePostClientSL;
import com.itbarx.json.LikeModelParserJSON;
import com.itbarx.listener.LikeProcessesServiceListener;
import com.itbarx.model.like.LikeCountPostModel;
import com.itbarx.model.like.LikeCountUserModel;
import com.itbarx.model.like.LikeModel;
import com.itbarx.model.like.LikePostListModel;
import com.itbarx.model.like.LikePostsByUserIdModel;
import com.itbarx.model.like.LikeUserListModel;
import com.itbarx.model.like.LikeUsersByPostIdModel;
import com.itbarx.utils.ItbarxUtils;

import android.content.Context;

public class LikeProcessesServiceSL extends BasePostServiceSL<String> {

	private static final String NAME_OF_THE_CLASS = LikeProcessesServiceSL.class.getSimpleName();
	LikeProcessesServiceListener<String> likeProcessesServiceListener;

	public LikeProcessesServiceSL(Context appContext, LikeProcessesServiceListener<String> listener, int serviceResUriId) {
	super(appContext, listener, serviceResUriId);
	this.likeProcessesServiceListener = listener;
	setOnServiceErrorClientListener(this);
	}

	// **********************************//
	// ---SEND DATA WEBSERVICE METHODS---
	// **********************************//

	// ADD LIKE
	public void setAddLike(LikeModel likeModel) {

	List<NameValuePair> params = new ArrayList<NameValuePair>();
	params.add(new BasicNameValuePair(GlobalDataForWS.USER_ID.toString(), likeModel.getUserID()));
	params.add(new BasicNameValuePair(GlobalDataForWS.POST_ID.toString(), likeModel.getPostID()));

	String postData = ItbarxUtils.formattedData(params);
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, LikeProcessesLinks.ADD.toString());
	}

	// DELETE LIKE
	public void setDeleteLike(LikeModel likeModel) {

	List<NameValuePair> params = new ArrayList<NameValuePair>();
	params.add(new BasicNameValuePair(GlobalDataForWS.USER_ID.toString(), likeModel.getUserID()));
	params.add(new BasicNameValuePair(GlobalDataForWS.POST_ID.toString(), likeModel.getPostID()));

	String postData = ItbarxUtils.formattedData(params);
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, LikeProcessesLinks.DELETE.toString());
	}

	// COUNT LIKE ACOORDING TO USER (KULLANICI KAC TANE POST BEGENMİS)
	public void setCountLikeByUser(LikeCountUserModel likeCountUserModel) {

	List<NameValuePair> params = new ArrayList<NameValuePair>();
	params.add(new BasicNameValuePair(GlobalDataForWS.USER_ID.toString(), likeCountUserModel.getUserID()));
	String postData = ItbarxUtils.formattedData(params);
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, LikeProcessesLinks.LIKE_COUNT_BY_USER.toString());
	}

	// COUNT LIKE ACOORDING TO POST (POST U KAC KULLANICI BEGENMİS)
	public void setCountLikeByPost(LikeCountPostModel likeCountPostModel) {

	List<NameValuePair> params = new ArrayList<NameValuePair>();
	params.add(new BasicNameValuePair(GlobalDataForWS.POST_ID.toString(), likeCountPostModel.getPostID()));
	String postData = ItbarxUtils.formattedData(params);
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, LikeProcessesLinks.LIKE_COUNT_BY_POST.toString());
	}

	// GET POSTLIST LIKED BY THE USER (KULLANICININ BEGENDİGİ POSTLAR)
	public void setGetLikePostsByUserId(LikePostListModel likePostListModel) {

	List<NameValuePair> params = new ArrayList<NameValuePair>();
	params.add(new BasicNameValuePair(GlobalDataForWS.USER_ID.toString(), likePostListModel.getUserID()));
	params.add(new BasicNameValuePair(GlobalDataForWS.PAGE.toString(), likePostListModel.getPage()));
	params.add(new BasicNameValuePair(GlobalDataForWS.REC_PER_PAGE.toString(), likePostListModel.getRecPerPage()));
	String postData = ItbarxUtils.formattedData(params);
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, LikeProcessesLinks.GET_LIKE_POSTS_BY_USER.toString());
	}
	// GET USERLIST THAT USERS LIKED THE POST (POSTU BEGENEN KULLANICI LİSTESİ)

	public void setGetLikeUsersByPostId(LikeUserListModel likeUserListModel) {

	List<NameValuePair> params = new ArrayList<NameValuePair>();
	params.add(new BasicNameValuePair(GlobalDataForWS.POST_ID.toString(), likeUserListModel.getPostID()));
	params.add(new BasicNameValuePair(GlobalDataForWS.PAGE.toString(), likeUserListModel.getPage()));
	params.add(new BasicNameValuePair(GlobalDataForWS.REC_PER_PAGE.toString(), likeUserListModel.getRecPerPage()));
	String postData = ItbarxUtils.formattedData(params);
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, LikeProcessesLinks.GET_LIKE_USERS_BY_POST.toString());
	}

	// ************************//
	// ---OVERRIDED METHODS---
	// ************************//

	@Override
	public void onPOSTCommit(ResponseServiceModel<String> responseEvent) {
	String result = responseEvent.getResponseData();

	// ADD LIKE
	if (responseEvent.getMethodName().equalsIgnoreCase(LikeProcessesLinks.ADD.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseModelDataKey(result);
		String isAddedResponse = null;
		if (model != null) {
		isAddedResponse = new LikeModelParserJSON().getLikeModelFromJSON(model.getModel());
		}
		if (isAddedResponse == null) {
		BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
		errorModel.setErrMessage(context.getString(R.string.BrokenData));
		this.likeProcessesServiceListener.onError(errorModel);
		} else {
		this.likeProcessesServiceListener.addLike(isAddedResponse);
		}
	}
	// DELETE LIKE
	else if (responseEvent.getMethodName().equalsIgnoreCase(LikeProcessesLinks.DELETE.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseModelDataKey(result);
		String isDeletedResponse = null;
		if (model != null) {
		isDeletedResponse = new LikeModelParserJSON().getLikeModelFromJSON(model.getModel());
		}
		if (isDeletedResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
		errorModel.setErrMessage(context.getString(R.string.BrokenData));
		this.likeProcessesServiceListener.onError(errorModel);
		} else {
		this.likeProcessesServiceListener.deleteLike(isDeletedResponse);
		}
	}
	// COUNT LIKE ACOORDING TO USER (KULLANICI KAC TANE POST BEGENMİS)
	else if (responseEvent.getMethodName().equalsIgnoreCase(LikeProcessesLinks.LIKE_COUNT_BY_USER.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseModelDataKey(result);
		String countResponse = null;
		if (model != null) {
		countResponse = new LikeModelParserJSON().getLikeModelFromJSON(model.getModel());
		}
		if (countResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
		errorModel.setErrMessage(context.getString(R.string.BrokenData));
		this.likeProcessesServiceListener.onError(errorModel);
		} else {
		this.likeProcessesServiceListener.countLikeByUser(countResponse);
		}
	}
	// COUNT LIKE ACOORDING TO POST (POST U KAC KULLANICI BEGENMİS)
	else if (responseEvent.getMethodName().equalsIgnoreCase(LikeProcessesLinks.LIKE_COUNT_BY_POST.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseModelDataKey(result);
		String countResponse = null;
		if (model != null) {
		countResponse = new LikeModelParserJSON().getLikeModelFromJSON(model.getModel());
		}
		if (countResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
		errorModel.setErrMessage(context.getString(R.string.BrokenData));
		this.likeProcessesServiceListener.onError(errorModel);
		} else {
		this.likeProcessesServiceListener.countLikeByPost(countResponse);
		}
	} // GET POSTLIST LIKED BY THE USER (KULLANICININ BEGENDİGİ POSTLAR)
	else if (responseEvent.getMethodName().equalsIgnoreCase(LikeProcessesLinks.GET_LIKE_POSTS_BY_USER.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseModelDataKey(result);
		List<LikePostsByUserIdModel> likePostsByUserIdModelResponse = null;
		if (model != null) {
		likePostsByUserIdModelResponse = new LikeModelParserJSON().getLikePostsByUserIdModelFromJSON(model.getModel());
		}
		if (likePostsByUserIdModelResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
		errorModel.setErrMessage(context.getString(R.string.BrokenData));
		this.likeProcessesServiceListener.onError(errorModel);
		} else {
		this.likeProcessesServiceListener.getLikePostByUserId(likePostsByUserIdModelResponse);
		}
	} // GET USERLIST THAT USERS LIKED THE POST (POSTU BEGENEN KULLANICI LİSTESİ)
	else if (responseEvent.getMethodName().equalsIgnoreCase(LikeProcessesLinks.GET_LIKE_USERS_BY_POST.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseModelDataKey(result);
		List<LikeUsersByPostIdModel> likeUsersByPostIdModelResponse = null;
		if (model != null) {
		likeUsersByPostIdModelResponse = new LikeModelParserJSON().getLikeUsersByPostIdModelFromJSON(model.getModel());
		}
		if (likeUsersByPostIdModelResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
		errorModel.setErrMessage(context.getString(R.string.BrokenData));
		this.likeProcessesServiceListener.onError(errorModel);
		} else {
		this.likeProcessesServiceListener.getLikeUsersByPostId(likeUsersByPostIdModelResponse);
		}
	}
	}

	/*
	@Override
	public void onGETReceive(ResponseServiceModel<String> responseEvent) {
		String result = responseEvent.getResponseData();

	}
*/
	@Override
	public void onError(BarxErrorModel responseServiceErrorModel) {
	likeProcessesServiceListener.onError(responseServiceErrorModel);

	}
}
