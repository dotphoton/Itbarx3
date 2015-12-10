package com.itbarx.sl;

import java.util.ArrayList;
import java.util.List;

import android.util.Pair;

import com.itbarx.R;

import com.itbarx.service.ResponseEventModel;
import com.itbarx.service.ServiceResponseModel;
import com.itbarx.enums.ActivityProcessesLinks;
import com.itbarx.enums.GlobalDataForWS;
import com.itbarx.service.error.BarxErrorModel;
import com.itbarx.service.BasePostServiceSL;
import com.itbarx.error.sl.BaseServicePostClientSL;
import com.itbarx.json.ActivityModelParserJSON;
import com.itbarx.listener.ActivityProcessesServiceListener;
import com.itbarx.model.activity.ActivityListModel;
import com.itbarx.model.activity.ActivityModel;
import com.itbarx.utils.ItbarxUtils;

import android.content.Context;

public class ActivityProcessesServiceSL extends BasePostServiceSL<String> {

	private static final String NAME_OF_THE_CLASS = ActivityProcessesServiceSL.class.getSimpleName();
	ActivityProcessesServiceListener<String> activityProcessesServiceListener;

	public ActivityProcessesServiceSL(Context appContext, ActivityProcessesServiceListener<String> listener, int serviceResUriId) {
	super(appContext, listener, serviceResUriId);
	this.activityProcessesServiceListener = listener;
	setOnServiceErrorClientListener(this);
	}

	// **********************************//
	// ---SEND DATA WEBSERVICE METHODS---
	// **********************************//

	// SEARCHAUTOCOMPLETE
	public void setActivityList(ActivityModel activityModel) {
//List<Pair<String,String>>
	List<Pair<String,String>> params = new ArrayList<>();
	params.add(new Pair(GlobalDataForWS.USER_ID.toString(), activityModel.getUserID()));
	params.add(new Pair(GlobalDataForWS.PAGE.toString(), activityModel.getPage()));
	params.add(new Pair(GlobalDataForWS.REC_PER_PAGE.toString(), activityModel.getRecPerPage()));
	String postData = ItbarxUtils.formattedData(params);
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, ActivityProcessesLinks.ACTIVITY_LIST.toString());
	}

	// ************************//
	// ---OVERRIDED METHODS---
	// ************************//

	@Override
	public void onPOSTCommit(ResponseEventModel responseEvent) {
	String result = responseEvent.getResponseData();

	// ---GET SEARCHAUTOCOMPLETE LIST ---
	if (responseEvent.getMethodName().equalsIgnoreCase(ActivityProcessesLinks.ACTIVITY_LIST.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseArrayModelDataKey(result);
		List<ActivityListModel> activityListModelResponse = null;
		if (model != null) {
		activityListModelResponse = new ActivityModelParserJSON().getActivityListModelFromJSON(model.getModel());
		}
		if (activityListModelResponse == null) {
		BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
		errorModel.setErrMessage(context.getString(R.string.BrokenData));
		activityProcessesServiceListener.onError(errorModel);
		} else {
		activityProcessesServiceListener.getActivityList(activityListModelResponse);
		}
	}
	}

	@Override
	public void onError(BarxErrorModel responseServiceErrorModel) {
	// TODO Auto-generated method stub
		activityProcessesServiceListener.onError(responseServiceErrorModel);
	}

}
