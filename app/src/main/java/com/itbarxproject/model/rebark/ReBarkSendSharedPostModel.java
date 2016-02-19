package com.itbarxproject.model.rebark;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReBarkSendSharedPostModel {

	@SerializedName("UserId")
	@Expose
	private String UserId;
	@SerializedName("Page")
	@Expose
	private String Page;
	@SerializedName("RecPerPage")
	@Expose
	private String RecPerPage;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public ReBarkSendSharedPostModel() {
	}

	/**
	 *
	 * @param RecPerPage
	 * @param Page
	 * @param UserId
	 */
	public ReBarkSendSharedPostModel(String UserId, String Page, String RecPerPage) {
		this.UserId = UserId;
		this.Page = Page;
		this.RecPerPage = RecPerPage;
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
	 * The RecPerPage
	 */
	public String getRecPerPage() {
		return RecPerPage;
	}

	/**
	 *
	 * @param RecPerPage
	 * The RecPerPage
	 */
	public void setRecPerPage(String RecPerPage) {
		this.RecPerPage = RecPerPage;
	}

}