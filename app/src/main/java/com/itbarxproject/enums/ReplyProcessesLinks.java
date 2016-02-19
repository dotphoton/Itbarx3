package com.itbarxproject.enums;

public enum ReplyProcessesLinks{

	POSTREPLY_DELETE("postreply/delete"), GET_POST_REPLIES("postreply/getpostreplies"),	POSTREPLY_ADD("postreply/add");

	private String link;

	private ReplyProcessesLinks(String value) {
	this.link = value;
	}

	@Override
	public String toString() {
	return this.link;
	}}