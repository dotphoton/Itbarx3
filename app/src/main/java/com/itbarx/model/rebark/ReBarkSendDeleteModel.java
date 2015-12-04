package com.itbarx.model.rebark;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ReBarkSendDeleteModel {

	@SerializedName("ShareId")
	@Expose
	private String ShareId;
	@SerializedName("PostId")
	@Expose
	private String PostId;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public ReBarkSendDeleteModel() {
	}

	/**
	 *
	 * @param ShareId
	 * @param PostId
	 */
	public ReBarkSendDeleteModel(String ShareId, String PostId) {
		this.ShareId = ShareId;
		this.PostId = PostId;
	}

	/**
	 *
	 * @return
	 * The ShareId
	 */
	public String getShareId() {
		return ShareId;
	}

	/**
	 *
	 * @param ShareId
	 * The ShareId
	 */
	public void setShareId(String ShareId) {
		this.ShareId = ShareId;
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
