package com.itbarx.model.rebark;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReBarkSendPostSharedUserModel {

	@SerializedName("PostId")
	@Expose
	private String PostId;
	@SerializedName("Page")
	@Expose
	private String Page;
	@SerializedName("recPerPage")
	@Expose
	private String recPerPage;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public ReBarkSendPostSharedUserModel() {
	}

	/**
	 *
	 * @param Page
	 * @param recPerPage
	 * @param PostId
	 */
	public ReBarkSendPostSharedUserModel(String PostId, String Page, String recPerPage) {
		this.PostId = PostId;
		this.Page = Page;
		this.recPerPage = recPerPage;
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
	 * The Page
	 */
	public String getPage() {
		return Page;
	}

	/**
	 *
	 * @param Page
	 * The Page
	 */
	public void setPage(String Page) {
		this.Page = Page;
	}

	/**
	 *
	 * @return
	 * The recPerPage
	 */
	public String getRecPerPage() {
		return recPerPage;
	}

	/**
	 *
	 * @param recPerPage
	 * The recPerPage
	 */
	public void setRecPerPage(String recPerPage) {
		this.recPerPage = recPerPage;
	}

}