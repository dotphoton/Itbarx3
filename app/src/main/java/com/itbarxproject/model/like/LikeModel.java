package com.itbarxproject.model.like;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LikeModel {

	@SerializedName("UserId")
	@Expose
	private String UserId;
	@SerializedName("PostId")
	@Expose
	private String PostId;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public LikeModel() {
	}

	/**
	 *
	 * @param UserId
	 * @param PostId
	 */
	public LikeModel(String UserId, String PostId) {
		this.UserId = UserId;
		this.PostId = PostId;
	}

	/**
	 *
	 * @return
	 * The UserId
	 */
	public String getUserId() {
		return UserId;
	}

	/**
	 *
	 * @param UserId
	 * The UserId
	 */
	public void setUserId(String UserId) {
		this.UserId = UserId;
	}

	/**
	 *
	 * @return
	 * The PostId
	 */
	public String getPostId() {
		return PostId;
	}

	/**
	 *
	 * @param PostId
	 * The PostId
	 */
	public void setPostId(String PostId) {
		this.PostId = PostId;
	}

}

