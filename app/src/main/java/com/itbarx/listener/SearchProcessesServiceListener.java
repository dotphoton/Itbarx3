package com.itbarx.listener;

import java.util.List;

import com.itbarx.error.listener.BaseServiceListener;
import com.itbarx.model.search.SearchUserForAutoCompleteResultModel;
import com.itbarx.model.search.SearchUserListResultModel;

public interface SearchProcessesServiceListener<T> extends BaseServiceListener<T> {

	public void getSearchUserForAutoCompleteList(List<SearchUserForAutoCompleteResultModel> searchUserForAutoCompleteResultModel);

	public void getSearchUserList(List<SearchUserListResultModel> searchUserListResultModel);

}
