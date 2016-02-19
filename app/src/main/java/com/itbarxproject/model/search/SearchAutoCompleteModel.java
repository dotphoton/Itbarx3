package com.itbarxproject.model.search;

public class SearchAutoCompleteModel {

	private String userID;
	private String searchText;

	public SearchAutoCompleteModel() {
	super();
	}

	public SearchAutoCompleteModel(String userID, String searchText) {
	super();
	this.userID = userID;
	this.searchText = searchText;
	}

	public String getUserID() {
	return userID;
	}

	public void setUserID(String userID) {
	this.userID = userID;
	}

	public String getSearchText() {
	return searchText;
	}

	public void setSearchText(String searchText) {
	this.searchText = searchText;
	}

}
