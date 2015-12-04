package com.itbarx.model.rebark;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReBarkGetPostSharedUserListByPostIdModel {

	@SerializedName("RowNum")
	@Expose
	private String RowNum;
	@SerializedName("ShareId")
	@Expose
	private String ShareId;
	@SerializedName("PostId")
	@Expose
	private String PostId;
	@SerializedName("Sharer")
	@Expose
	private String Sharer;
	@SerializedName("SharedDate")
	@Expose
	private String SharedDate;
	@SerializedName("SharerName")
	@Expose
	private String SharerName;
	@SerializedName("SharerPhoto")
	@Expose
	private String SharerPhoto;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public ReBarkGetPostSharedUserListByPostIdModel() {
	}

	/**
	 *
	 * @param SharerPhoto
	 * @param Sharer
	 * @param ShareId
	 * @param RowNum
	 * @param SharedDate
	 * @param SharerName
	 * @param PostId
	 */
	public ReBarkGetPostSharedUserListByPostIdModel(String RowNum, String ShareId, String PostId, String Sharer, String SharedDate, String SharerName, String SharerPhoto) {
		this.RowNum = RowNum;
		this.ShareId = ShareId;
		this.PostId = PostId;
		this.Sharer = Sharer;
		this.SharedDate = SharedDate;
		this.SharerName = SharerName;
		this.SharerPhoto = SharerPhoto;
	}

	/**
	 *
	 * @return
	 * The RowNum
	 */
	public String getRowNum() {
		return RowNum;
	}

	/**
	 *
	 * @param RowNum
	 * The RowNum
	 */
	public void setRowNum(String RowNum) {
		this.RowNum = RowNum;
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

	/**
	 *
	 * @return
	 * The Sharer
	 */
	public String getSharer() {
		return Sharer;
	}

	/**
	 *
	 * @param Sharer
	 * The Sharer
	 */
	public void setSharer(String Sharer) {
		this.Sharer = Sharer;
	}

	/**
	 *
	 * @return
	 * The SharedDate
	 */
	public String getSharedDate() {
		return SharedDate;
	}

	/**
	 *
	 * @param SharedDate
	 * The SharedDate
	 */
	public void setSharedDate(String SharedDate) {
		this.SharedDate = SharedDate;
	}

	/**
	 *
	 * @return
	 * The SharerName
	 */
	public String getSharerName() {
		return SharerName;
	}

	/**
	 *
	 * @param SharerName
	 * The SharerName
	 */
	public void setSharerName(String SharerName) {
		this.SharerName = SharerName;
	}

	/**
	 *
	 * @return
	 * The SharerPhoto
	 */
	public String getSharerPhoto() {
		return SharerPhoto;
	}

	/**
	 *
	 * @param SharerPhoto
	 * The SharerPhoto
	 */
	public void setSharerPhoto(String SharerPhoto) {
		this.SharerPhoto = SharerPhoto;
	}

}