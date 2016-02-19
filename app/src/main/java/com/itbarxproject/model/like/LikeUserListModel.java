package com.itbarxproject.model.like;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LikeUserListModel {

	@SerializedName("PostId")
	@Expose
	private String PostId;
	@SerializedName("Page")
	@Expose
	private String Page;
	@SerializedName("RecPerPage")
	@Expose
	private String RecPerPage;


	public LikeUserListModel() {
		super();
	}

	public LikeUserListModel(String postId, String page, String recPerPage) {
		PostId = postId;
		Page = page;
		RecPerPage = recPerPage;
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
