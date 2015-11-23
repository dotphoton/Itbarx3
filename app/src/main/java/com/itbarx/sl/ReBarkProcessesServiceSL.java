package com.itbarx.sl;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.itbarx.R;
import com.itbarx.error.common.ResponseServiceModel;
import com.itbarx.error.common.ServiceResponseModel;
import com.itbarx.enums.GlobalDataForWS;
import com.itbarx.enums.ReBarkprocessesLinks;
import com.itbarx.error.model.BarxErrorModel;
import com.itbarx.error.sl.BasePostServiceSL;
import com.itbarx.error.sl.BaseServicePostClientSL;
import com.itbarx.json.ReBarkModelParserJSON;
import com.itbarx.listener.ReBarkProcessesServiceListener;
import com.itbarx.model.rebark.ReBarkDeleteModel;
import com.itbarx.model.rebark.ReBarkGetPostSharedUserListModel;
import com.itbarx.model.rebark.ReBarkGetSharedPostListModel;
import com.itbarx.model.rebark.ReBarkPostShareAddModel;
import com.itbarx.model.rebark.ReBarkPostSharedUserCountModel;
import com.itbarx.model.rebark.ReBarkPostSharedUserModel;
import com.itbarx.model.rebark.ReBarkSharedPostCountModel;
import com.itbarx.model.rebark.ReBarkSharedPostModel;
import com.itbarx.utils.ItbarxUtils;

import android.content.Context;

public class ReBarkProcessesServiceSL extends BasePostServiceSL<String> {

	private static final String NAME_OF_THE_CLASS = ReBarkProcessesServiceSL.class.getSimpleName();
	ReBarkProcessesServiceListener<String> reBarkProcessesServiceListener;

	public ReBarkProcessesServiceSL(Context appContext, ReBarkProcessesServiceListener<String> listener, int serviceResUriId) {
	super(appContext, listener, serviceResUriId);
	this.reBarkProcessesServiceListener = listener;
	setOnServiceErrorClientListener(this);
	}
	// **********************************//
	// ---SEND DATA WEBSERVICE METHODS---
	// **********************************//

	// SHAREPOST ADD (REBARK INSERT)
	public void setAddPostShare(ReBarkPostShareAddModel reBarkPostShareAddModel) {

	List<NameValuePair> params = new ArrayList<NameValuePair>();
	params.add(new BasicNameValuePair(GlobalDataForWS.USER_ID.toString(), reBarkPostShareAddModel.getUserID()));
	params.add(new BasicNameValuePair(GlobalDataForWS.POST_ID.toString(), reBarkPostShareAddModel.getPostID()));
	params.add(new BasicNameValuePair(GlobalDataForWS.SHARED_TEXT.toString(), reBarkPostShareAddModel.getSharedText()));
	String postData = ItbarxUtils.formattedData(params);
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, ReBarkprocessesLinks.SHARE_POST_ADD.toString());
	}

	// DELETE (REBARK DELETE)
	public void setDeletePostShare(ReBarkDeleteModel reBarkDeleteModel) {

	List<NameValuePair> params = new ArrayList<NameValuePair>();
	params.add(new BasicNameValuePair(GlobalDataForWS.SHARE_ID.toString(), reBarkDeleteModel.getShareID()));
	params.add(new BasicNameValuePair(GlobalDataForWS.POST_ID.toString(), reBarkDeleteModel.getPostID()));

	String postData = ItbarxUtils.formattedData(params);
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, ReBarkprocessesLinks.DELETE.toString());
	}

	// GET SHARED POST (KULLANICININ REBARK ETTIKLERI)
	public void setGetSharedPost(ReBarkSharedPostModel reBarkSharedPostModel) {

	List<NameValuePair> params = new ArrayList<NameValuePair>();
	params.add(new BasicNameValuePair(GlobalDataForWS.USER_ID.toString(), reBarkSharedPostModel.getUserID()));
	params.add(new BasicNameValuePair(GlobalDataForWS.PAGE.toString(), reBarkSharedPostModel.getPage()));
	params.add(new BasicNameValuePair(GlobalDataForWS.REC_PER_PAGE.toString(), reBarkSharedPostModel.getRecPerPage()));

	String postData = ItbarxUtils.formattedData(params);
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, ReBarkprocessesLinks.GET_SHARED_POST.toString());
	}

	// GET POSTSHARED USER (REBARK EDEN KULLANICILAR - POST BAZLI)
	public void setGetPostSharedUser(ReBarkPostSharedUserModel reBarkPostSharedUserModel) {

	List<NameValuePair> params = new ArrayList<NameValuePair>();
	params.add(new BasicNameValuePair(GlobalDataForWS.POST_ID.toString(), reBarkPostSharedUserModel.getPostID()));
	params.add(new BasicNameValuePair(GlobalDataForWS.PAGE.toString(), reBarkPostSharedUserModel.getPage()));
	params.add(new BasicNameValuePair(GlobalDataForWS.REC_PER_PAGE.toString(), reBarkPostSharedUserModel.getRecPerPage()));

	String postData = ItbarxUtils.formattedData(params);
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, ReBarkprocessesLinks.GET_POSTSHARED_USER.toString());
	}

	// SHARED POST COUNT (REBARK EDİLEN POST SAYISI - USER BAZLI)
	public void setSharedPostCount(ReBarkSharedPostCountModel reBarkSharedPostCountModel) {

	List<NameValuePair> params = new ArrayList<NameValuePair>();
	params.add(new BasicNameValuePair(GlobalDataForWS.USER_ID.toString(), reBarkSharedPostCountModel.getUserID()));

	String postData = ItbarxUtils.formattedData(params);
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, ReBarkprocessesLinks.POST_COUNT_SHARED_BY_USER.toString());
	}

	// COUNT OF USER THAT THEY SHARED THE POST (REBARK EDEN KULLANICI SAYISI - POST BAZLI)
	public void setUserCount(ReBarkPostSharedUserCountModel ReBarkPostSharedUserCountModel) {

	List<NameValuePair> params = new ArrayList<NameValuePair>();
	params.add(new BasicNameValuePair(GlobalDataForWS.POST_ID.toString(), ReBarkPostSharedUserCountModel.getPostID()));

	String postData = ItbarxUtils.formattedData(params);
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, ReBarkprocessesLinks.USER_COUNT.toString());
	}
	// ************************//
	// ---OVERRIDED METHODS---
	// ************************//

	@Override
	public void onPOSTCommit(ResponseServiceModel<String> responseEvent) {
	String result = responseEvent.getResponseData();
	// SHAREPOST ADD
	if (responseEvent.getMethodName().equalsIgnoreCase(ReBarkprocessesLinks.SHARE_POST_ADD.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseModelDataKey(result);
		String isAddedResponse = null;
		if (model != null) {
		isAddedResponse = new ReBarkModelParserJSON().getReBarkModelFromJSON(model.getModel());
		}
		if (isAddedResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
		errorModel.setErrMessage(context.getString(R.string.BrokenData));
		reBarkProcessesServiceListener.onError(errorModel);
		} else {
		reBarkProcessesServiceListener.add(isAddedResponse);
		}
	}
	// SHAREPOST DELETE
	else if (responseEvent.getMethodName().equalsIgnoreCase(ReBarkprocessesLinks.SHARE_POST_ADD.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseModelDataKey(result);
		String isDeletedResponse = null;
		if (model != null) {
		isDeletedResponse = new ReBarkModelParserJSON().getReBarkModelFromJSON(model.getModel());
		}
		if (isDeletedResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
		errorModel.setErrMessage(context.getString(R.string.BrokenData));
		reBarkProcessesServiceListener.onError(errorModel);
		} else {
		reBarkProcessesServiceListener.delete(isDeletedResponse);
		}
	}
	// GET SHAREDPOST LIST
	else if (responseEvent.getMethodName().equalsIgnoreCase(ReBarkprocessesLinks.GET_SHARED_POST.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseModelDataKey(result);
		List<ReBarkGetSharedPostListModel> reBarkGetSharedPostListModelResponse = null;
		if (model != null) {
		reBarkGetSharedPostListModelResponse = new ReBarkModelParserJSON().getReBarkGetSharedPostListModelFromJSON(model.getModel());
		}
		if (reBarkGetSharedPostListModelResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
		errorModel.setErrMessage(context.getString(R.string.BrokenData));
		reBarkProcessesServiceListener.onError(errorModel);
		} else {
		reBarkProcessesServiceListener.getSharedPostList(reBarkGetSharedPostListModelResponse);
		}
	}
	// GET POSTSHARED USER LIST
	else if (responseEvent.getMethodName().equalsIgnoreCase(ReBarkprocessesLinks.GET_POSTSHARED_USER.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseModelDataKey(result);
		List<ReBarkGetPostSharedUserListModel> reBarkGetPostSharedUserListModelResponse = null;
		if (model != null) {
		reBarkGetPostSharedUserListModelResponse = new ReBarkModelParserJSON().getReBarkGetPostSharedUserListModelFromJSON(model.getModel());
		}
		if (reBarkGetPostSharedUserListModelResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
		errorModel.setErrMessage(context.getString(R.string.BrokenData));
		reBarkProcessesServiceListener.onError(errorModel);
		} else {
		reBarkProcessesServiceListener.getPostSharedUserList(reBarkGetPostSharedUserListModelResponse);
		}
	}
	// GET SHAREDPOST COUNT BY USERID
	else if (responseEvent.getMethodName().equalsIgnoreCase(ReBarkprocessesLinks.POST_COUNT_SHARED_BY_USER.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseModelDataKey(result);
		String getReBarkSharedPostCountResponse = null;
		if (model != null) {
		getReBarkSharedPostCountResponse = new ReBarkModelParserJSON().getReBarkModelFromJSON(model.getModel());
		}
		if (getReBarkSharedPostCountResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
		errorModel.setErrMessage(context.getString(R.string.BrokenData));
		reBarkProcessesServiceListener.onError(errorModel);
		} else {
		reBarkProcessesServiceListener.getSharedPostCount(getReBarkSharedPostCountResponse);
		}
	}
	// GET USER COUNT
	else if (responseEvent.getMethodName().equalsIgnoreCase(ReBarkprocessesLinks.USER_COUNT.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseModelDataKey(result);
		String getUserCountResponse = null;
		if (model != null) {
		getUserCountResponse = new ReBarkModelParserJSON().getReBarkModelFromJSON(model.getModel());
		}
		if (getUserCountResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
		errorModel.setErrMessage(context.getString(R.string.BrokenData));
		reBarkProcessesServiceListener.onError(errorModel);
		} else {
		reBarkProcessesServiceListener.getUserCount(getUserCountResponse);
		}
	}
	}

	/*
	@Override
	public void onGETReceive(ResponseServiceModel<String> responseEvent) {
	// TODO Auto-generated method stub

	}
	*/

	@Override
	public void onError(BarxErrorModel responseServiceErrorModel) {
	reBarkProcessesServiceListener.onError(responseServiceErrorModel);

	}

}
