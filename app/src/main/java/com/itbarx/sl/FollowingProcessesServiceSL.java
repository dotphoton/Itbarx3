package com.itbarx.sl;

import java.util.ArrayList;
import java.util.List;



import com.itbarx.R;
import com.itbarx.service.ResponseEventModel;
import com.itbarx.service.ServiceResponseModel;
import com.itbarx.enums.FollowingProcessLinks;
import com.itbarx.enums.GlobalDataForWS;
import com.itbarx.service.error.BarxErrorModel;
import com.itbarx.service.BasePostServiceSL;
import com.itbarx.error.sl.BaseServicePostClientSL;
import com.itbarx.json.FollowModelParserJSON;
import com.itbarx.listener.FollowingProcessesServiceListener;
import com.itbarx.model.follow.FollowUserModel;
import com.itbarx.model.follow.FollowerListByFollowingIdModel;
import com.itbarx.model.follow.FollowerListModel;
import com.itbarx.model.follow.FollowerModel;
import com.itbarx.model.follow.FollowingListByFollowerIdModel;
import com.itbarx.model.follow.FollowingListModel;
import com.itbarx.model.follow.FollowingModel;
import com.itbarx.model.follow.PendingListByFollowingIdModel;
import com.itbarx.model.follow.SendPendingListByFollowerIdModel;
import com.itbarx.utils.ItbarxUtils;

import android.content.Context;
import android.util.Pair;

public class FollowingProcessesServiceSL extends BasePostServiceSL<String> {

	// private final String NAME_OF_THE_CLASS = this.getClass().getSimpleName();
	private static final String NAME_OF_THE_CLASS = FollowingProcessesServiceSL.class.getSimpleName();

	FollowingProcessesServiceListener<String> followingProcessesServiceListener;

	public FollowingProcessesServiceSL(Context appContext, FollowingProcessesServiceListener<String> listener, int serviceResUriId) {
	super(appContext, listener, serviceResUriId);
	followingProcessesServiceListener = listener;
	setOnServiceErrorClientListener(this);
	}

	// **********************************//
	// ---SEND DATA WEBSERVICE METHODS---
	// **********************************//

	// ---ADD---
	public void setAdd(FollowUserModel followUserModel) {

	List<Pair<String,String>> params = new ArrayList<Pair<String,String>>();
	params.add(new Pair(GlobalDataForWS.FOLLOWER_ID.toString(), followUserModel.getFollowerID()));
	params.add(new Pair(GlobalDataForWS.FOLLOWING_ID.toString(), followUserModel.getFollowingID()));
	String postData = ItbarxUtils.formattedData(params);
	// Post service CALL
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, FollowingProcessLinks.ADD_LINK.toString());
	}

	// ---UPDATE_AS_FRIEND---
	public void setUpdateAsFriend(FollowUserModel followUserModel) {

	List<Pair<String,String>> params = new ArrayList<Pair<String,String>>();
	params.add(new Pair(GlobalDataForWS.FOLLOWER_ID.toString(), followUserModel.getFollowerID()));
	params.add(new Pair(GlobalDataForWS.FOLLOWING_ID.toString(), followUserModel.getFollowingID()));
	String postData = ItbarxUtils.formattedData(params);
	// Post service CALL
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, FollowingProcessLinks.UPODATE_AS_FRIEND_LINK.toString());
	}

	// ---UPDATE_AS_BLOCKED---
	public void setUpdateAsBlocked(FollowUserModel followUserModel) {

	List<Pair<String,String>> params = new ArrayList<Pair<String,String>>();
	params.add(new Pair(GlobalDataForWS.FOLLOWER_ID.toString(), followUserModel.getFollowerID()));
	params.add(new Pair(GlobalDataForWS.FOLLOWING_ID.toString(), followUserModel.getFollowingID()));
	String postData = ItbarxUtils.formattedData(params);
	// Post service CALL
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, FollowingProcessLinks.UPODATE_AS_BLOCKED_LINK.toString());
	}

	// ---FOLLOWER COUNT (TAKİPÇİ SAYISI)---
	/**
	 * COUNTFOLLOWER USES FOLLOWINGMODEL
	 */
	public void setCountFollower(FollowingModel followingModel) {

	List<Pair<String,String>> params = new ArrayList<Pair<String,String>>();
	params.add(new Pair(GlobalDataForWS.FOLLOWING_ID.toString(), followingModel.getFollowingId()));
	String postData = ItbarxUtils.formattedData(params);
	// Post service CALL
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, FollowingProcessLinks.FOLLOWER_COUNT_LINK.toString());
	}

	// ---FOLLOWING COUNT (TAKİP EDİLENLERİN SAYISI) ---
	/**
	 * COUNTFOLLOWING USES FOLLOWERMODEL
	 */
	public void setCountFollowing(FollowerModel followerModel) {

	List<Pair<String,String>> params = new ArrayList<Pair<String,String>>();
	params.add(new Pair(GlobalDataForWS.FOLLOWER_ID.toString(), followerModel.getFollowerId()));
	String postData = ItbarxUtils.formattedData(params);
	// Post service CALL
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, FollowingProcessLinks.FOLLOWING_COUNT_LINK.toString());
	}

	// ---PENDING COUNT (CEVAP BEKLEYENLERİN SAYISI - TAKİPÇİ iSTEGİ - GELEN iSTEK SAYISI) ---
	/**
	 * COUNTPENDING USES FOLLOWINGMODEL
	 */
	public void setCountPending(FollowingModel followingModel) {

	List<Pair<String,String>> params = new ArrayList<Pair<String,String>>();
	params.add(new Pair(GlobalDataForWS.FOLLOWING_ID.toString(), followingModel.getFollowingId()));
	String postData = ItbarxUtils.formattedData(params);
	// Post service CALL
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, FollowingProcessLinks.PENDING_COUNT_LINK.toString());
	}

	// ---SEND PENDING COUNT (GÖNDERDİGİM iSTEK SAYISI - BEKLEYENLER) ---
	/**
	 * COUNTFOLLOWING USES FOLLOWERMODEL
	 */
	public void setCountSendPending(FollowerModel followerModel) {

	List<Pair<String,String>> params = new ArrayList<Pair<String,String>>();
	params.add(new Pair(GlobalDataForWS.FOLLOWING_ID.toString(), followerModel.getFollowerId()));
	String postData = ItbarxUtils.formattedData(params);
	// Post service CALL
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, FollowingProcessLinks.SEND_PENDING_COUNT_LINK.toString());
	}

	// ---DELETE FOLLOW (TAKİBİ BIRAK VEYA İSTEGİ GERİ AL İÇİN KULLANILABİLİR)---
	public void setDeleteFollow(FollowUserModel followUserModel) {

	List<Pair<String,String>> params = new ArrayList<Pair<String,String>>();
	params.add(new Pair(GlobalDataForWS.FOLLOWER_ID.toString(), followUserModel.getFollowerID()));
	params.add(new Pair(GlobalDataForWS.FOLLOWING_ID.toString(), followUserModel.getFollowingID()));
	String postData = ItbarxUtils.formattedData(params);
	// Post service CALL
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, FollowingProcessLinks.DELETE_FOLLOW.toString());
	}

	// --- FOLLOWER LIST ---
	public void setFollowerList(FollowerListModel followerListModel) {

	List<Pair<String,String>> params = new ArrayList<Pair<String,String>>();
	params.add(new Pair(GlobalDataForWS.SEARCHER_ID.toString(), followerListModel.getSearcherId()));
	params.add(new Pair(GlobalDataForWS.FOLLOWING_ID.toString(), followerListModel.getFollowingId()));
	params.add(new Pair(GlobalDataForWS.PAGE.toString(), followerListModel.getPage()));
	params.add(new Pair(GlobalDataForWS.REC_PER_PAGE.toString(), followerListModel.getRecPerPage()));
	String postData = ItbarxUtils.formattedData(params);
	// Post service CALL
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, FollowingProcessLinks.GET_FOLLOWER_LIST.toString());
	}

	// --- FOLLOWING LIST ---
	public void setFollowingList(FollowingListModel followingListModel) {

	List<Pair<String,String>> params = new ArrayList<Pair<String,String>>();
	params.add(new Pair(GlobalDataForWS.SEARCHER_ID.toString(), followingListModel.getSearcherId()));
	params.add(new Pair(GlobalDataForWS.FOLLOWER_ID.toString(), followingListModel.getFollowerId()));
	params.add(new Pair(GlobalDataForWS.PAGE.toString(), followingListModel.getPage()));
	params.add(new Pair(GlobalDataForWS.REC_PER_PAGE.toString(), followingListModel.getRecPerPage()));
	String postData = ItbarxUtils.formattedData(params);
	// Post service CALL
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, FollowingProcessLinks.GET_FOLLOWING_LIST.toString());
	}

	// --- PENDING LIST ---
	/**
	 * PENDING LIST USES FOLLOWERLISTMODEL
	 */
	public void setGetPendingList(FollowerListModel followerListModel) {

	List<Pair<String,String>> params = new ArrayList<Pair<String,String>>();
	params.add(new Pair(GlobalDataForWS.FOLLOWING_ID.toString(), followerListModel.getFollowingId()));
	params.add(new Pair(GlobalDataForWS.PAGE.toString(), followerListModel.getPage()));
	params.add(new Pair(GlobalDataForWS.REC_PER_PAGE.toString(), followerListModel.getRecPerPage()));
	String postData = ItbarxUtils.formattedData(params);
	// Post service CALL
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, FollowingProcessLinks.GET_PENDING_LIST.toString());
	}

	// --- SEND PENDING LIST ---
	/**
	 * SEND PENDING LIST USES FOLLOWINGLISTMODEL
	 */
	public void setGetSendPendingList(FollowingListModel followingListModel) {

	List<Pair<String,String>> params = new ArrayList<Pair<String,String>>();
	params.add(new Pair(GlobalDataForWS.FOLLOWER_ID.toString(), followingListModel.getFollowerId()));
	params.add(new Pair(GlobalDataForWS.PAGE.toString(), followingListModel.getPage()));
	params.add(new Pair(GlobalDataForWS.REC_PER_PAGE.toString(), followingListModel.getRecPerPage()));
	String postData = ItbarxUtils.formattedData(params);
	// Post service CALL
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, FollowingProcessLinks.GET_SEND_PENDING_LIST.toString());
	}
	// ************************//
	// ---OVERRIDED METHODS---
	// ************************//

	@Override
	public void onPOSTCommit(ResponseEventModel<String>  responseEvent) {
	String result = responseEvent.getResponseData();
	// ---ADD---
	if (responseEvent.getMethodName().equalsIgnoreCase(FollowingProcessLinks.ADD_LINK.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseModelDataKey(result);

		String isAddedResponse = null;
		if (model != null) {

		isAddedResponse = new FollowModelParserJSON().getFollowUserModelFromJSON(model.getModel());
		if (isAddedResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
			errorModel.setErrMessage(context.getString(R.string.BrokenData));
			followingProcessesServiceListener.onError(errorModel);
		} else {
			followingProcessesServiceListener.add(isAddedResponse);
		}
		}

	} // ---UPDATE_AS_FRIEND---
	else if (responseEvent.getMethodName().equalsIgnoreCase(FollowingProcessLinks.UPODATE_AS_FRIEND_LINK.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseModelDataKey(result);

		String isUpdateAsFriendResponse = null;
		if (model != null) {

		isUpdateAsFriendResponse = new FollowModelParserJSON().getFollowUserModelFromJSON(model.getModel());
		if (isUpdateAsFriendResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
			errorModel.setErrMessage(context.getString(R.string.BrokenData));
			followingProcessesServiceListener.onError(errorModel);
		} else {
			followingProcessesServiceListener.updateAsFriend(isUpdateAsFriendResponse);

		}
		}

	}
	// ---UPDATE_AS_BLOCKED---
	else if (responseEvent.getMethodName().equalsIgnoreCase(FollowingProcessLinks.UPODATE_AS_BLOCKED_LINK.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseModelDataKey(result);

		String isUpdateAsBlockedResponse = null;
		if (model != null) {

		isUpdateAsBlockedResponse = new FollowModelParserJSON().getFollowUserModelFromJSON(model.getModel());
		if (isUpdateAsBlockedResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
			errorModel.setErrMessage(context.getString(R.string.BrokenData));
			followingProcessesServiceListener.onError(errorModel);
		} else {
			followingProcessesServiceListener.updateAsBlocked(isUpdateAsBlockedResponse);
			;
		}
		}

	}
	// ---FOLLOWER COUNT---
	else if (responseEvent.getMethodName().equalsIgnoreCase(FollowingProcessLinks.FOLLOWER_COUNT_LINK.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseModelDataKey(result);

		String followerCountResponse = null;
		if (model != null) {

		followerCountResponse = new FollowModelParserJSON().getFollowUserModelFromJSON(model.getModel());
		if (followerCountResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
			errorModel.setErrMessage(context.getString(R.string.BrokenData));
			followingProcessesServiceListener.onError(errorModel);
		} else {
			followingProcessesServiceListener.countFollower(followerCountResponse);
		}
		}

	} // ---FOLLOWING COUNT---
	else if (responseEvent.getMethodName().equalsIgnoreCase(FollowingProcessLinks.FOLLOWING_COUNT_LINK.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseModelDataKey(result);

		String followingCountResponse = null;
		if (model != null) {

		followingCountResponse = new FollowModelParserJSON().getFollowUserModelFromJSON(model.getModel());
		if (followingCountResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
			errorModel.setErrMessage(context.getString(R.string.BrokenData));
			followingProcessesServiceListener.onError(errorModel);
		} else {
			followingProcessesServiceListener.countFollowing(followingCountResponse);
		}
		}

	}
	// ---PENDING COUNT---
	else if (responseEvent.getMethodName().equalsIgnoreCase(FollowingProcessLinks.PENDING_COUNT_LINK.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseModelDataKey(result);

		String pendingCountResponse = null;
		if (model != null) {

		pendingCountResponse = new FollowModelParserJSON().getFollowUserModelFromJSON(model.getModel());
		if (pendingCountResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
			errorModel.setErrMessage(context.getString(R.string.BrokenData));
			followingProcessesServiceListener.onError(errorModel);
		} else {
			followingProcessesServiceListener.countPending(pendingCountResponse);
		}
		}

	}
	// ---SEND PENDING COUNT---
	else if (responseEvent.getMethodName().equalsIgnoreCase(FollowingProcessLinks.SEND_PENDING_COUNT_LINK.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseModelDataKey(result);

		String sendPendingCountResponse = null;
		if (model != null) {

		sendPendingCountResponse = new FollowModelParserJSON().getFollowUserModelFromJSON(model.getModel());
		if (sendPendingCountResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
			errorModel.setErrMessage(context.getString(R.string.BrokenData));
			followingProcessesServiceListener.onError(errorModel);
		} else {
			followingProcessesServiceListener.countSendPending(sendPendingCountResponse);
		}
		}

	}
	// ---DELETE FOLLOW---
	else if (responseEvent.getMethodName().equalsIgnoreCase(FollowingProcessLinks.DELETE_FOLLOW.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseModelDataKey(result);

		String isDeletedResponse = null;
		if (model != null) {

		isDeletedResponse = new FollowModelParserJSON().getFollowUserModelFromJSON(model.getModel());
		if (isDeletedResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
			errorModel.setErrMessage(context.getString(R.string.BrokenData));
			followingProcessesServiceListener.onError(errorModel);
		} else {
			followingProcessesServiceListener.deleteFollow(isDeletedResponse);

		}
		}

	}
	// ---GET FOLLOWER LIST---
	else if (responseEvent.getMethodName().equalsIgnoreCase(FollowingProcessLinks.GET_FOLLOWER_LIST.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseModelDataKey(result);

		List<FollowerListByFollowingIdModel> followerListModelResponse = null;
		if (model != null) {

		followerListModelResponse = new FollowModelParserJSON().getFollowerListByFollowingIdModelFromJSON(model.getModel());
		if (followerListModelResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
			errorModel.setErrMessage(context.getString(R.string.BrokenData));
			followingProcessesServiceListener.onError(errorModel);
		} else {
			followingProcessesServiceListener.getFollowerListById(followerListModelResponse);

		}
		}

	}
	// ---GET FOLLOWING LIST---
	else if (responseEvent.getMethodName().equalsIgnoreCase(FollowingProcessLinks.GET_FOLLOWING_LIST.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseModelDataKey(result);

		List<FollowingListByFollowerIdModel> followingListByFollowerIdModelResponse = null;
		if (model != null) {

		followingListByFollowerIdModelResponse = new FollowModelParserJSON().getFollowingListByFollowerIdModelFromJSON(model.getModel());
		if (followingListByFollowerIdModelResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
			errorModel.setErrMessage(context.getString(R.string.BrokenData));
			followingProcessesServiceListener.onError(errorModel);
		} else {
			followingProcessesServiceListener.getFollowingListById(followingListByFollowerIdModelResponse);

		}
		}

	}
	// ---GET PENDING LIST---
	else if (responseEvent.getMethodName().equalsIgnoreCase(FollowingProcessLinks.GET_PENDING_LIST.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseArrayModelDataKey(result);

		List<PendingListByFollowingIdModel> getPendingListByFollowerIdModelResponse = null;
		if (model != null) {

			getPendingListByFollowerIdModelResponse = new FollowModelParserJSON().getPendingListByFollowingIdModelFromJSON(model.getModel());
			if (getPendingListByFollowerIdModelResponse == null) {
				BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
				errorModel.setErrMessage(context.getString(R.string.BrokenData));
				followingProcessesServiceListener.onError(errorModel);
			} else {
				followingProcessesServiceListener.getPendingListById(getPendingListByFollowerIdModelResponse);

			}
		}

	}
	// ---GET SEND PENDING LIST---
	else if (responseEvent.getMethodName().equalsIgnoreCase(FollowingProcessLinks.GET_SEND_PENDING_LIST.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseArrayModelDataKey(result);

		List<SendPendingListByFollowerIdModel> sendPendingListByFollowerIdModelResponse = null;
		if (model != null) {

		sendPendingListByFollowerIdModelResponse = new FollowModelParserJSON().getSendPendingListByFollowerIdModelFromJSON(model.getModel());
		if (sendPendingListByFollowerIdModelResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
			errorModel.setErrMessage(context.getString(R.string.BrokenData));
			followingProcessesServiceListener.onError(errorModel);
		} else {
			followingProcessesServiceListener.getSendPendingListById(sendPendingListByFollowerIdModelResponse);

		}
		}

	}
	}

	@Override
	public void onError(BarxErrorModel responseServiceErrorModel) {
	followingProcessesServiceListener.onError(responseServiceErrorModel);

	}

}
