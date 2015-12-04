package com.itbarx.sl;

import java.util.ArrayList;
import java.util.List;
import com.itbarx.R;
import com.itbarx.error.common.ResponseServiceModel;
import com.itbarx.error.common.ServiceResponseModel;
import com.itbarx.enums.GlobalDataForWS;
import com.itbarx.enums.SearchProcessesLinks;
import com.itbarx.error.model.BarxErrorModel;
import com.itbarx.error.sl.BasePostServiceSL;
import com.itbarx.error.sl.BaseServicePostClientSL;
import com.itbarx.json.SearchModelParserJSON;
import com.itbarx.listener.SearchProcessesServiceListener;
import com.itbarx.model.search.SearchAutoCompleteModel;
import com.itbarx.model.search.SearchModel;
import com.itbarx.model.search.SearchUserForAutoCompleteResultModel;
import com.itbarx.model.search.SearchUserListResultModel;
import com.itbarx.utils.ItbarxUtils;

import android.content.Context;
import android.util.Pair;

public class SearchProcessesServiceSL extends BasePostServiceSL<String> {

	private static final String NAME_OF_THE_CLASS = SearchProcessesServiceSL.class.getSimpleName();
	SearchProcessesServiceListener<String> searchProcessesServiceListener;

	public SearchProcessesServiceSL(Context appContext, SearchProcessesServiceListener<String> listener, int serviceResUriId) {
	super(appContext, listener, serviceResUriId);
	this.searchProcessesServiceListener = listener;
	setOnServiceErrorClientListener(this);
	}

	// **********************************//
	// ---SEND DATA WEBSERVICE METHODS---
	// **********************************//

	// SEARCHAUTOCOMPLETE
	public void setSearchAutoComplete(SearchAutoCompleteModel searchAutoCompleteModel) {

	List<Pair<String,String>> params = new ArrayList<Pair<String,String>>();
	params.add(new Pair(GlobalDataForWS.USER_ID.toString(), searchAutoCompleteModel.getUserID()));
	params.add(new Pair(GlobalDataForWS.SHARED_TEXT.toString(), searchAutoCompleteModel.getSearchText()));
	String postData = ItbarxUtils.formattedData(params);
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, SearchProcessesLinks.AUTO_COMPLETE_SEARCH.toString());
	}

	// SEARCH USER
	public void setSearchUser(SearchModel searchModel) {

	List<Pair<String,String>> params = new ArrayList<Pair<String,String>>();
	params.add(new Pair(GlobalDataForWS.USER_ID.toString(), searchModel.getUserID()));
	params.add(new Pair(GlobalDataForWS.SEARCH_TEXT.toString(), searchModel.getSearchText()));
	params.add(new Pair(GlobalDataForWS.PAGE.toString(), searchModel.getPage()));
	params.add(new Pair(GlobalDataForWS.REC_PER_PAGE.toString(), searchModel.getRecPerPage()));
	String postData = ItbarxUtils.formattedData(params);
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, SearchProcessesLinks.SEARCH_USER.toString());
	}

	// ************************//
	// ---OVERRIDED METHODS---
	// ************************//

	@Override
	public void onPOSTCommit(ResponseServiceModel<String> responseEvent) {
	String result = responseEvent.getResponseData();

	// ---GET SEARCHAUTOCOMPLETE LIST ---
	if (responseEvent.getMethodName().equalsIgnoreCase(SearchProcessesLinks.AUTO_COMPLETE_SEARCH.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseModelDataKey(result);
		List<SearchUserForAutoCompleteResultModel> searchUserForAutoCompleteResultModelResponse = null;
		if (model != null) {
		searchUserForAutoCompleteResultModelResponse = new SearchModelParserJSON().getSearchUserForAutoCompleteResultModelFromJSON(model.getModel());
		}
		if (searchUserForAutoCompleteResultModelResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
		errorModel.setErrMessage(context.getString(R.string.BrokenData));
		searchProcessesServiceListener.onError(errorModel);
		} else {
		searchProcessesServiceListener.getSearchUserForAutoCompleteList(searchUserForAutoCompleteResultModelResponse);
		}
	} // ---GET SEARCH USER LIST ---
	else if (responseEvent.getMethodName().equalsIgnoreCase(SearchProcessesLinks.SEARCH_USER.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseModelDataKey(result);
		List<SearchUserListResultModel> searchUserListResultModelResponse = null;
		if (model != null) {
		searchUserListResultModelResponse = new SearchModelParserJSON().getSearchUserListResultModelFromJSON(model.getModel());
		}
		if (searchUserListResultModelResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
		errorModel.setErrMessage(context.getString(R.string.BrokenData));
		searchProcessesServiceListener.onError(errorModel);
		} else {
		searchProcessesServiceListener.getSearchUserList(searchUserListResultModelResponse);
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
	// TODO Auto-generated method stub

	}
}
