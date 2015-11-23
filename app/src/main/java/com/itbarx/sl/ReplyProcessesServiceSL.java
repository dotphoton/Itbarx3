package com.itbarx.sl;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.itbarx.R;
import com.itbarx.error.common.ResponseServiceModel;
import com.itbarx.error.common.ServiceResponseModel;
import com.itbarx.enums.GlobalDataForWS;
import com.itbarx.enums.ReplyProcessesLinks;
import com.itbarx.error.listener.BarxErrorListener;
import com.itbarx.error.model.BarxErrorModel;
import com.itbarx.error.sl.BasePostServiceSL;
import com.itbarx.error.sl.BaseServicePostClientSL;
import com.itbarx.json.ReplyModelParserJSON;
import com.itbarx.listener.ReplyProcessesServiceListener;
import com.itbarx.model.reply.ReplyAddModel;
import com.itbarx.model.reply.ReplyDeleteModel;
import com.itbarx.model.reply.ReplyListModel;
import com.itbarx.model.reply.ReplyModel;
import com.itbarx.utils.ItbarxUtils;

import android.content.Context;

public class ReplyProcessesServiceSL extends BasePostServiceSL<String> {

	private static final String NAME_OF_THE_CLASS = ReplyProcessesServiceSL.class.getSimpleName();
	ReplyProcessesServiceListener<String> replyProcessesServiceListener;

	public ReplyProcessesServiceSL(Context appContext, ReplyProcessesServiceListener<String> listener, int serviceResUriId) {
	super(appContext, listener, serviceResUriId);
	replyProcessesServiceListener = listener;
	setOnServiceErrorClientListener(this);
	}
	// **********************************//
	// ---SEND DATA WEBSERVICE METHODS---
	// **********************************//

	// DELETE POSTREPLY (POSTA VERİLEN CEVABI SİLME)
	public void setDeleteReply(ReplyDeleteModel replyDeleteModel) {
	List<NameValuePair> params = new ArrayList<NameValuePair>();
	params.add(new BasicNameValuePair(GlobalDataForWS.REPLY_ID.toString(), replyDeleteModel.getReplyID()));
	String postData = ItbarxUtils.formattedData(params);
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, ReplyProcessesLinks.POSTREPLY_DELETE.toString());
	}

	// GET POSTREPLY LIST (POST A GONDERİLEN CEVAPLAR LİSTESİ)
	public void setGetPostRepliesList(ReplyModel replyModel) {
	List<NameValuePair> params = new ArrayList<NameValuePair>();
	params.add(new BasicNameValuePair(GlobalDataForWS.POST_ID.toString(), replyModel.getPostID()));
	params.add(new BasicNameValuePair(GlobalDataForWS.PAGE.toString(), replyModel.getPage()));
	params.add(new BasicNameValuePair(GlobalDataForWS.REC_PER_PAGE.toString(), replyModel.getRecPerPage()));
	String postData = ItbarxUtils.formattedData(params);
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, ReplyProcessesLinks.GET_POST_REPLIES.toString());
	}

	// ADD POSTREPLY
	public void setAddReply(ReplyAddModel replyAddModel) {
	List<NameValuePair> params = new ArrayList<NameValuePair>();
	params.add(new BasicNameValuePair(GlobalDataForWS.POST_ID.toString(), replyAddModel.getPostID()));
	params.add(new BasicNameValuePair(GlobalDataForWS.POST_SENDER_USER_ID.toString(), replyAddModel.getPostSenderUserId()));
	params.add(new BasicNameValuePair(GlobalDataForWS.POST_SPEECH_TO_TEXT.toString(), replyAddModel.getPostSpeechToText()));
	params.add(new BasicNameValuePair(GlobalDataForWS.POST_TEXT.toString(), replyAddModel.getPostText()));
	params.add(new BasicNameValuePair(GlobalDataForWS.POST_SENDER_IP.toString(), replyAddModel.getPostSenderIp()));
	params.add(new BasicNameValuePair(GlobalDataForWS.POST_REPLY_BYTE.toString(), replyAddModel.getPostReplyByte()));
	String postData = ItbarxUtils.formattedData(params);
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, ReplyProcessesLinks.POSTREPLY_ADD.toString());
	}

	// ************************//
	// ---OVERRIDED METHODS---
	// ************************//

	@Override
	public void onPOSTCommit(ResponseServiceModel<String> responseEvent) {
	String result = responseEvent.getResponseData();
	// ---GET DELETE REPLY ---
	if (responseEvent.getMethodName().equalsIgnoreCase(ReplyProcessesLinks.POSTREPLY_DELETE.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseModelDataKey(result);
		String isDeeletedResponse = null;
		if (model != null) {
		isDeeletedResponse = new ReplyModelParserJSON().getReplyDeleteModelFromJSON(model.getModel());
		}
		if (isDeeletedResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
		errorModel.setErrMessage(context.getString(R.string.BrokenData));
		replyProcessesServiceListener.onError(errorModel);
		} else {
		replyProcessesServiceListener.deleteReply(isDeeletedResponse);
		}
	}
	// ---GET POST REPLIES LIST ---
	else if (responseEvent.getMethodName().equalsIgnoreCase(ReplyProcessesLinks.GET_POST_REPLIES.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseModelDataKey(result);
		List<ReplyListModel> replyListModelResponse = null;
		if (model != null) {
		replyListModelResponse = new ReplyModelParserJSON().getReplyListModelFromJSON(model.getModel());
		}
		if (replyListModelResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
		errorModel.setErrMessage(context.getString(R.string.BrokenData));
		replyProcessesServiceListener.onError(errorModel);
		} else {
		replyProcessesServiceListener.getPostRepliesList(replyListModelResponse);
		}
	}
	// ---GET ADD REPLY ---
	if (responseEvent.getMethodName().equalsIgnoreCase(ReplyProcessesLinks.POSTREPLY_ADD.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseModelDataKey(result);
		String isAddedResponse = null;
		if (model != null) {
		isAddedResponse = new ReplyModelParserJSON().getReplyAddModelFromJSON(model.getModel());
		}
		if (isAddedResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
		errorModel.setErrMessage(context.getString(R.string.BrokenData));
		replyProcessesServiceListener.onError(errorModel);
		} else {
		replyProcessesServiceListener.addReply(isAddedResponse);
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
	replyProcessesServiceListener.onError(responseServiceErrorModel);

	}
}
