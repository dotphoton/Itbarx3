package com.itbarxproject.enums;

public enum FollowingProcessLinks {

	ADD_LINK("follow/add"), UPODATE_AS_FRIEND_LINK("follow/updateasfriend"), UPODATE_AS_BLOCKED_LINK("follow/updateasblocked"), FOLLOWING_COUNT_LINK("follow/followingcount"), FOLLOWER_COUNT_LINK(
			"follow/followercount"), PENDING_COUNT_LINK("follow/pendingcount"), SEND_PENDING_COUNT_LINK("follow/sendpendingcount"), DELETE_FOLLOW("follow/deletefollow"), GET_FOLLOWER_LIST(
			"follow/getfollowerlist"), GET_FOLLOWING_LIST("follow/getfollowinglist"), GET_PENDING_LIST("follow/getpendinglist"), GET_SEND_PENDING_LIST("follow/getsendpendinglist");

	private final String link;

	private FollowingProcessLinks(String value) {
	this.link = value;
	}

	@Override
	public String toString() {

	return link;
	}

}

