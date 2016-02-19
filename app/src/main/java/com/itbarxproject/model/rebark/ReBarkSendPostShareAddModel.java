package com.itbarxproject.model.rebark;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ReBarkSendPostShareAddModel {

	@SerializedName("UserId")
	@Expose
	private String UserId;
	@SerializedName("PostId")
	@Expose
	private String PostId;
	@SerializedName("SharedText")
	@Expose
	private String SharedText;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public ReBarkSendPostShareAddModel() {
	}

	/**
	 *
	 * @param SharedText
	 * @param UserId
	 * @param PostId
	 */
	public ReBarkSendPostShareAddModel(String UserId, String PostId, String SharedText) {
		this.UserId = UserId;
		this.PostId = PostId;
		this.SharedText = SharedText;
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

	/**
	 *
	 * @return
	 * The SharedText
	 */
	public String getSharedText() {
		return SharedText;
	}

	/**
	 *
	 * @param SharedText
	 * The SharedText
	 */
	public void setSharedText(String SharedText) {
		this.SharedText = SharedText;
	}

}

