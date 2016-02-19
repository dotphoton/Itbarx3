package com.itbarxproject.enums;

public enum SearchProcessesLinks {

	AUTO_COMPLETE_SEARCH("search/autocompletesearch"), SEARCH_USER("search/searchuser");

	private String link;

	private SearchProcessesLinks(String value) {
	this.link = value;
	}

	@Override
	public String toString() {
	return this.link;
	}
}
