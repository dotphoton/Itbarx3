package com.itbarxproject.model.like;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LikeCountUserModel {

	@SerializedName("UserId") @Expose private String UserId;

	/**
	 * No args constructor for use in serialization
	 */
	public LikeCountUserModel() {
	}

	/**
	 * @param UserId
	 */
	public LikeCountUserModel(String UserId) {
		this.UserId = UserId;
	}

	/**
	 * @return The UserId
	 */
	public String getUserId() {
		return UserId;
	}

	/**
	 * @param UserId The UserId
	 */
	public void setUserId(String UserId) {
		this.UserId = UserId;
	}

}
