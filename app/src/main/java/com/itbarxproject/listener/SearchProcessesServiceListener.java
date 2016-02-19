package com.itbarxproject.listener;

import java.util.List;

import com.itbarxproject.service.BaseServiceListener;
import com.itbarxproject.model.search.SearchUserForAutoCompleteResultModel;
import com.itbarxproject.model.search.SearchUserListResultModel;

public interface SearchProcessesServiceListener<T> extends BaseServiceListener<T> {

	public void getSearchUserForAutoCompleteList(List<SearchUserForAutoCompleteResultModel> searchUserForAutoCompleteResultModel);

	public void getSearchUserList(List<SearchUserListResultModel> searchUserListResultModel);

}
