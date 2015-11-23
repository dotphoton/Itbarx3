package com.itbarx.enums;

public enum LikeProcessesLinks {

	ADD("like/add"), DELETE("like/delete"), LIKE_COUNT_BY_USER("like/likecountbyuser"), LIKE_COUNT_BY_POST("like/likecountbypost"), GET_LIKE_POSTS_BY_USER("like/getlikepostsbyuser"), GET_LIKE_USERS_BY_POST("like/getlikeusersbypost");

	private String link;

	private LikeProcessesLinks(String value) {
	this.link = value;
	}

	@Override
	public String toString() {
	return this.link;
	}

}
