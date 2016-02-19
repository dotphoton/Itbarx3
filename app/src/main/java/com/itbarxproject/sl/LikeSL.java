package com.itbarxproject.sl;

import android.content.Context;
import android.util.Pair;

import com.itbarxproject.R;
import com.itbarxproject.enums.GlobalDataForWS;
import com.itbarxproject.enums.LikeProcessesLinks;
import com.itbarxproject.json.LikeModelParserJSON;
import com.itbarxproject.listener.LikeProcessesServiceListener;
import com.itbarxproject.model.like.LikeCountPostModel;
import com.itbarxproject.model.like.LikeCountUserModel;
import com.itbarxproject.model.like.LikeModel;
import com.itbarxproject.model.like.LikePostListModel;
import com.itbarxproject.model.like.LikePostsByUserIdModel;
import com.itbarxproject.model.like.LikeUserListModel;
import com.itbarxproject.model.like.LikeUsersByPostIdModel;
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
public class LikeSL  extends BasePostServiceSL<String> {

	private static final String NAME_OF_THE_CLASS = LikeSL.class.getSimpleName();
	LikeProcessesServiceListener<String> likeProcessesServiceListener;

	public LikeSL(Context appContext, LikeProcessesServiceListener<String> listener, int serviceResUriId) {
		super(appContext, listener, serviceResUriId);
		this.likeProcessesServiceListener = listener;
		setOnServiceErrorClientListener(this);
	}

	// **********************************//
	// ---SEND DATA WEBSERVICE METHODS---
	// **********************************//

	// ADD LIKE
	public void setAddLike(LikeModel likeModel) {

		List<Pair<String,String>> params = new ArrayList<>();
		params.add(new Pair(GlobalDataForWS.USER_ID.toString(), likeModel.getUserId()));
		params.add(new Pair(GlobalDataForWS.POST_ID.toString(), likeModel.getPostId()));

		String postData = ItbarxUtils.formattedData(params);
		BasePostAsyncTask<String> postClient = new BasePostAsyncTask<String>(context, NAME_OF_THE_CLASS, postData);
		postClient.addServiceClientListener(this);
		postClient.addErrorErrorServiceClientListener(this);
		String uri = getServiceUri();
		postClient.execute(uri, LikeProcessesLinks.ADD.toString());
	}

	// DELETE LIKE
	public void setDeleteLike(LikeModel likeModel) {

		List<Pair<String,String>> params = new ArrayList<>();
		params.add(new Pair(GlobalDataForWS.USER_ID.toString(), likeModel.getUserId()));
		params.add(new Pair(GlobalDataForWS.POST_ID.toString(), likeModel.getPostId()));

		String postData = ItbarxUtils.formattedData(params);
		BasePostAsyncTask<String> postClient = new BasePostAsyncTask<String>(context, NAME_OF_THE_CLASS, postData);
		postClient.addServiceClientListener(this);
		postClient.addErrorErrorServiceClientListener(this);
		String uri = getServiceUri();
		postClient.execute(uri, LikeProcessesLinks.DELETE.toString());
	}

	// COUNT LIKE ACOORDING TO USER (KULLANICI KAC TANE POST BEGENMİS)
	public void setCountLikeByUser(LikeCountUserModel likeCountUserModel) {

		List<Pair<String,String>> params = new ArrayList<>();
		params.add(new Pair(GlobalDataForWS.USER_ID.toString(), likeCountUserModel.getUserId()));
		String postData = ItbarxUtils.formattedData(params);
		BasePostAsyncTask<String> postClient = new BasePostAsyncTask<String>(context, NAME_OF_THE_CLASS, postData);
		postClient.addServiceClientListener(this);
		postClient.addErrorErrorServiceClientListener(this);
		String uri = getServiceUri();
		postClient.execute(uri, LikeProcessesLinks.LIKE_COUNT_BY_USER.toString());
	}

	// COUNT LIKE ACOORDING TO POST (POST U KAC KULLANICI BEGENMİS)
	public void setCountLikeByPost(LikeCountPostModel likeCountPostModel) {

		List<Pair<String,String>> params = new ArrayList<>();
		params.add(new Pair(GlobalDataForWS.POST_ID.toString(), likeCountPostModel.getPostId()));
		String postData = ItbarxUtils.formattedData(params);
		BasePostAsyncTask<String> postClient = new BasePostAsyncTask<String>(context, NAME_OF_THE_CLASS, postData);
		postClient.addServiceClientListener(this);
		postClient.addErrorErrorServiceClientListener(this);
		String uri = getServiceUri();
		postClient.execute(uri, LikeProcessesLinks.LIKE_COUNT_BY_POST.toString());
	}

	// GET POSTLIST LIKED BY THE USER (KULLANICININ BEGENDİGİ POSTLAR)
	public void setGetLikePostsByUserId(LikePostListModel likePostListModel) {

		List<Pair<String,String>> params = new ArrayList<>();
		params.add(new Pair(GlobalDataForWS.USER_ID.toString(), likePostListModel.getUserId()));
		params.add(new Pair(GlobalDataForWS.PAGE.toString(), likePostListModel.getPage()));
		params.add(new Pair(GlobalDataForWS.REC_PER_PAGE.toString(), likePostListModel.getRecPerPage()));
		String postData = ItbarxUtils.formattedData(params);
		BasePostAsyncTask<String> postClient = new BasePostAsyncTask<String>(context, NAME_OF_THE_CLASS, postData);
		postClient.addServiceClientListener(this);
		postClient.addErrorErrorServiceClientListener(this);
		String uri = getServiceUri();
		postClient.execute(uri, LikeProcessesLinks.GET_LIKE_POSTS_BY_USER.toString());
	}
	// GET USERLIST THAT USERS LIKED THE POST (POSTU BEGENEN KULLANICI LİSTESİ)

	public void setGetLikeUsersByPostId(LikeUserListModel likeUserListModel) {

		List<Pair<String,String>> params = new ArrayList<>();
		params.add(new Pair(GlobalDataForWS.POST_ID.toString(), likeUserListModel.getPostId()));
		params.add(new Pair(GlobalDataForWS.PAGE.toString(), likeUserListModel.getPage()));
		params.add(new Pair(GlobalDataForWS.REC_PER_PAGE.toString(), likeUserListModel.getRecPerPage()));
		String postData = ItbarxUtils.formattedData(params);
		BasePostAsyncTask<String> postClient = new BasePostAsyncTask<String>(context, NAME_OF_THE_CLASS, postData);
		postClient.addServiceClientListener(this);
		postClient.addErrorErrorServiceClientListener(this);
		String uri = getServiceUri();
		postClient.execute(uri, LikeProcessesLinks.GET_LIKE_USERS_BY_POST.toString());
	}

	// ************************//
	// ---OVERRIDED METHODS---
	// ************************//

	@Override
	public void onPOSTCommit(ResponseEventModel<String> responseEvent) {
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


	@Override
	public void onError(BarxErrorModel responseServiceErrorModel) {
		likeProcessesServiceListener.onError(responseServiceErrorModel);

	}
}
