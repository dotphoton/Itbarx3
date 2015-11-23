package com.itbarx.model.search;

public class SearchModel {

	private String userID;
	private String searchText;
	private String page;
	private String recPerPage;

	public SearchModel() {
	super();
	}

	public SearchModel(String userID, String searchText, String page, String recPerPage) {
	super();
	this.userID = userID;
	this.searchText = searchText;
	this.page = page;
	this.recPerPage = recPerPage;
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

	public String getPage() {
	return page;
	}

	public void setPage(String page) {
	this.page = page;
	}

	public String getRecPerPage() {
	return recPerPage;
	}

	public void setRecPerPage(String recPerPage) {
	this.recPerPage = recPerPage;
	}

}
