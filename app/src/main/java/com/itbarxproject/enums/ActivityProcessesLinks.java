package com.itbarxproject.enums;

public enum ActivityProcessesLinks {
	
	
	ACTIVITY_LIST("activity/list");
	
	private String link;
	private ActivityProcessesLinks(String value){
		this.link=value;
	
	}

	@Override
	public String toString() {
	
	return this.link;
	}
}
