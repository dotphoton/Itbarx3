package com.itbarx.model.like;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LikeCountPostModel {

	@SerializedName("PostId")
	@Expose
	private String PostId;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public LikeCountPostModel() {
	}

	/**
	 *
	 * @param PostId
	 */
	public LikeCountPostModel(String PostId) {
		this.PostId = PostId;
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