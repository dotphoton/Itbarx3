package com.itbarxproject.sl;

import android.content.Context;
import android.util.Log;
import android.util.Pair;

import com.itbarxproject.R;
import com.itbarxproject.enums.GlobalDataForWS;
import com.itbarxproject.enums.ReBarkprocessesLinks;
import com.itbarxproject.error.sl.BaseServicePostClientSL;
import com.itbarxproject.json.ReBarkModelParserJSON;
import com.itbarxproject.listener.ReBarkProcessesServiceListener;
import com.itbarxproject.model.rebark.ReBarkGetPostSharedUserListByPostIdModel;
import com.itbarxproject.model.rebark.ReBarkGetSharedPostListByUserIdModel;
import com.itbarxproject.model.rebark.ReBarkSendDeleteModel;
import com.itbarxproject.model.rebark.ReBarkSendPostShareAddModel;
import com.itbarxproject.model.rebark.ReBarkSendPostSharedUserModel;
import com.itbarxproject.model.rebark.ReBarkSendSharedPostCountModel;
import com.itbarxproject.model.rebark.ReBarkSendSharedPostModel;
import com.itbarxproject.model.rebark.ReBarkSendSharedUserCountModel;
import com.itbarxproject.service.BasePostAsyncTask;
import com.itbarxproject.service.BasePostServiceSL;
import com.itbarxproject.service.ResponseEventModel;
import com.itbarxproject.service.ServiceResponseModel;
import com.itbarxproject.service.error.BarxErrorModel;
import com.itbarxproject.utils.ItbarxUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Add a class header comment!
 */
public class ReBarkSL extends BasePostServiceSL<String> {

	private static final String NAME_OF_THE_CLASS = ReBarkSL.class.getSimpleName();
	ReBarkProcessesServiceListener<String> reBarkProcessesServiceListener;

	public ReBarkSL(Context appContext, ReBarkProcessesServiceListener<String> listener, int serviceResUriId) {
		super(appContext, listener, serviceResUriId);
		this.reBarkProcessesServiceListener = listener;
		setOnServiceErrorClientListener(this);
	}
	// **********************************//
	// ---SEND DATA WEBSERVICE METHODS---
	// **********************************//

	// SHAREPOST ADD (REBARK INSERT)
	public void setAddPostShare(ReBarkSendPostShareAddModel reBarkSendPostShareAddModel) {

		List<Pair<String,String>> params = new ArrayList<>();
		params.add(new Pair(GlobalDataForWS.USER_ID.toString(), reBarkSendPostShareAddModel.getUserId()));
		params.add(new Pair(GlobalDataForWS.POST_ID.toString(), reBarkSendPostShareAddModel.getPostId()));
		params.add(new Pair(GlobalDataForWS.SHARED_TEXT.toString(), reBarkSendPostShareAddModel.getSharedText()));
		String postData = ItbarxUtils.formattedData(params);
		Log.d("ADDED REBARK ", postData+" ");
		BasePostAsyncTask<String> postClient = new BasePostAsyncTask<String>(context, NAME_OF_THE_CLASS, postData);
		postClient.addServiceClientListener(this);
		postClient.addErrorErrorServiceClientListener(this);
		String uri = getServiceUri();
		postClient.execute(uri, ReBarkprocessesLinks.SHARE_POST_ADD.toString());
	}

	// DELETE (REBARK DELETE)
	public void setDeletePostShare(ReBarkSendDeleteModel reBarkSendDeleteModel) {

		List<Pair<String,String>> params = new ArrayList<>();
		params.add(new Pair(GlobalDataForWS.SHARE_ID.toString(), reBarkSendDeleteModel.getShareId()));
		params.add(new Pair(GlobalDataForWS.POST_ID.toString(), reBarkSendDeleteModel.getPostId()));

		String postData = ItbarxUtils.formattedData(params);
		BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
		postClient.addServiceClientListener(this);
		postClient.addErrorErrorServiceClientListener(this);
		String uri = getServiceUri();
		postClient.execute(uri, ReBarkprocessesLinks.DELETE.toString());
	}

	// GET SHARED POST (KULLANICININ REBARK ETTIKLERI)
	public void setGetSharedPost(ReBarkSendSharedPostModel reBarkSendSharedPostModel) {

		List<Pair<String,String>> params = new ArrayList<>();
		params.add(new Pair(GlobalDataForWS.USER_ID.toString(), reBarkSendSharedPostModel.getUserId()));
		params.add(new Pair(GlobalDataForWS.PAGE.toString(), reBarkSendSharedPostModel.getPage()));
		params.add(new Pair(GlobalDataForWS.REC_PER_PAGE.toString(), reBarkSendSharedPostModel.getRecPerPage()));

		String postData = ItbarxUtils.formattedData(params);
		BasePostAsyncTask<String> postClient = new BasePostAsyncTask<String>(context, NAME_OF_THE_CLASS, postData);
		postClient.addServiceClientListener(this);
		postClient.addErrorErrorServiceClientListener(this);
		String uri = getServiceUri();
		postClient.execute(uri, ReBarkprocessesLinks.GET_SHARED_POST.toString());
	}

	// GET POSTSHARED USER (REBARK EDEN KULLANICILAR - POST BAZLI)
	public void setGetPostSharedUser(ReBarkSendPostSharedUserModel reBarkSendPostSharedUserModel) {

		List<Pair<String,String>> params = new ArrayList<>();
		params.add(new Pair(GlobalDataForWS.POST_ID.toString(), reBarkSendPostSharedUserModel.getPostId()));
		params.add(new Pair(GlobalDataForWS.PAGE.toString(), reBarkSendPostSharedUserModel.getPage()));
		params.add(new Pair(GlobalDataForWS.REC_PER_PAGE.toString(), reBarkSendPostSharedUserModel.getRecPerPage()));

		String postData = ItbarxUtils.formattedData(params);
		BasePostAsyncTask<String> postClient = new BasePostAsyncTask<String>(context, NAME_OF_THE_CLASS, postData);
		postClient.addServiceClientListener(this);
		postClient.addErrorErrorServiceClientListener(this);
		String uri = getServiceUri();
		postClient.execute(uri, ReBarkprocessesLinks.GET_POSTSHARED_USER.toString());
	}

	// SHARED POST COUNT (REBARK EDİLEN POST SAYISI - USER BAZLI)
	public void setSharedPostCount(ReBarkSendSharedPostCountModel reBarkSendSharedPostCountModel) {

		List<Pair<String,String>> params = new ArrayList<>();
		params.add(new Pair(GlobalDataForWS.USER_ID.toString(), reBarkSendSharedPostCountModel.getUserID()));

		String postData = ItbarxUtils.formattedData(params);
		BasePostAsyncTask<String> postClient = new BasePostAsyncTask<String>(context, NAME_OF_THE_CLASS, postData);
		postClient.addServiceClientListener(this);
		postClient.addErrorErrorServiceClientListener(this);
		String uri = getServiceUri();
		postClient.execute(uri, ReBarkprocessesLinks.POST_COUNT_SHARED_BY_USER.toString());
	}

	// COUNT OF USER THAT THEY SHARED THE POST (REBARK EDEN KULLANICI SAYISI - POST BAZLI)
	public void setUserCount(ReBarkSendSharedUserCountModel ReBarkSendSharedUserCountModel) {

		List<Pair<String,String>> params = new ArrayList<>();
		params.add(new Pair(GlobalDataForWS.POST_ID.toString(), ReBarkSendSharedUserCountModel.getPostID()));

		String postData = ItbarxUtils.formattedData(params);
		BasePostAsyncTask<String> postClient = new BasePostAsyncTask<String>(context, NAME_OF_THE_CLASS, postData);
		postClient.addServiceClientListener(this);
		postClient.addErrorErrorServiceClientListener(this);
		String uri = getServiceUri();
		postClient.execute(uri, ReBarkprocessesLinks.USER_COUNT.toString());
	}
	// ************************//
	// ---OVERRIDED METHODS---
	// ************************//

	@Override
	public void onPOSTCommit(ResponseEventModel<String> responseEvent) {
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
		else if (responseEvent.getMethodName().equalsIgnoreCase(ReBarkprocessesLinks.DELETE.toString())) {
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
			ServiceResponseModel model = ItbarxUtils.getServiceResponseArrayModelDataKey(result);
			List<ReBarkGetSharedPostListByUserIdModel> reBarkGetSharedPostListByUserIdModelResponse = null;
			if (model != null) {
				reBarkGetSharedPostListByUserIdModelResponse = new ReBarkModelParserJSON().getReBarkGetSharedPostListModelFromJSON(model.getModel());
			}
			if (reBarkGetSharedPostListByUserIdModelResponse == null) {
				BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
				errorModel.setErrMessage(context.getString(R.string.BrokenData));
				reBarkProcessesServiceListener.onError(errorModel);
			} else {
				reBarkProcessesServiceListener.getSharedPostList(reBarkGetSharedPostListByUserIdModelResponse);
			}
		}
		// GET POSTSHARED USER LIST
		else if (responseEvent.getMethodName().equalsIgnoreCase(ReBarkprocessesLinks.GET_POSTSHARED_USER.toString())) {
			ServiceResponseModel model = ItbarxUtils.getServiceResponseArrayModelDataKey(result);
			List<ReBarkGetPostSharedUserListByPostIdModel> reBarkGetPostSharedUserListByPostIdModelResponse = null;
			if (model != null) {
				reBarkGetPostSharedUserListByPostIdModelResponse = new ReBarkModelParserJSON().getReBarkGetPostSharedUserListModelFromJSON(model.getModel());
			}
			if (reBarkGetPostSharedUserListByPostIdModelResponse == null) {
				BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
				errorModel.setErrMessage(context.getString(R.string.BrokenData));
				reBarkProcessesServiceListener.onError(errorModel);
			} else {
				reBarkProcessesServiceListener.getPostSharedUserList(reBarkGetPostSharedUserListByPostIdModelResponse);
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



	@Override
	public void onError(BarxErrorModel responseServiceErrorModel) {
		reBarkProcessesServiceListener.onError(responseServiceErrorModel);

	}

}
