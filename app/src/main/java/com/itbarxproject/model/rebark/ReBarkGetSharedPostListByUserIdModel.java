package com.itbarxproject.model.rebark;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ReBarkGetSharedPostListByUserIdModel {

	@SerializedName("RowNum")
	@Expose
	private String RowNum;
	@SerializedName("ShareId")
	@Expose
	private String ShareId;
	@SerializedName("SharedPostId")
	@Expose
	private String SharedPostId;
	@SerializedName("SharedDate")
	@Expose
	private String SharedDate;
	@SerializedName("SharedText")
	@Expose
	private String SharedText;
	@SerializedName("SharedPostLikeCount")
	@Expose
	private String SharedPostLikeCount;
	@SerializedName("SharedPostReplyCount")
	@Expose
	private String SharedPostReplyCount;
	@SerializedName("SharedPostShareCount")
	@Expose
	private String SharedPostShareCount;
	@SerializedName("SharedPostCoummentCount")
	@Expose
	private String SharedPostCoummentCount;
	@SerializedName("RealOwner")
	@Expose
	private String RealOwner;
	@SerializedName("RealOwnerName")
	@Expose
	private String RealOwnerName;
	@SerializedName("PostText")
	@Expose
	private String PostText;
	@SerializedName("PostURL")
	@Expose
	private String PostURL;

	/**
	 * No args constructor for use in serialization
	 *
	 */
	public ReBarkGetSharedPostListByUserIdModel() {
	}

	/**
	 *
	 * @param SharedText
	 * @param RealOwnerName
	 * @param RowNum
	 * @param SharedPostReplyCount
	 * @param PostText
	 * @param SharedPostCoummentCount
	 * @param SharedPostLikeCount
	 * @param ShareId
	 * @param PostURL
	 * @param SharedPostShareCount
	 * @param SharedPostId
	 * @param SharedDate
	 * @param RealOwner
	 */
	public ReBarkGetSharedPostListByUserIdModel(String RowNum, String ShareId, String SharedPostId, String SharedDate, String SharedText, String SharedPostLikeCount, String SharedPostReplyCount, String SharedPostShareCount, String SharedPostCoummentCount, String RealOwner, String RealOwnerName, String PostText, String PostURL) {
		this.RowNum = RowNum;
		this.ShareId = ShareId;
		this.SharedPostId = SharedPostId;
		this.SharedDate = SharedDate;
		this.SharedText = SharedText;
		this.SharedPostLikeCount = SharedPostLikeCount;
		this.SharedPostReplyCount = SharedPostReplyCount;
		this.SharedPostShareCount = SharedPostShareCount;
		this.SharedPostCoummentCount = SharedPostCoummentCount;
		this.RealOwner = RealOwner;
		this.RealOwnerName = RealOwnerName;
		this.PostText = PostText;
		this.PostURL = PostURL;
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
	 * The SharedPostId
	 */
	public String getSharedPostId() {
		return SharedPostId;
	}

	/**
	 *
	 * @param SharedPostId
	 * The SharedPostId
	 */
	public void setSharedPostId(String SharedPostId) {
		this.SharedPostId = SharedPostId;
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

	/**
	 *
	 * @return
	 * The SharedPostLikeCount
	 */
	public String getSharedPostLikeCount() {
		return SharedPostLikeCount;
	}

	/**
	 *
	 * @param SharedPostLikeCount
	 * The SharedPostLikeCount
	 */
	public void setSharedPostLikeCount(String SharedPostLikeCount) {
		this.SharedPostLikeCount = SharedPostLikeCount;
	}

	/**
	 *
	 * @return
	 * The SharedPostReplyCount
	 */
	public String getSharedPostReplyCount() {
		return SharedPostReplyCount;
	}

	/**
	 *
	 * @param SharedPostReplyCount
	 * The SharedPostReplyCount
	 */
	public void setSharedPostReplyCount(String SharedPostReplyCount) {
		this.SharedPostReplyCount = SharedPostReplyCount;
	}

	/**
	 *
	 * @return
	 * The SharedPostShareCount
	 */
	public String getSharedPostShareCount() {
		return SharedPostShareCount;
	}

	/**
	 *
	 * @param SharedPostShareCount
	 * The SharedPostShareCount
	 */
	public void setSharedPostShareCount(String SharedPostShareCount) {
		this.SharedPostShareCount = SharedPostShareCount;
	}

	/**
	 *
	 * @return
	 * The SharedPostCoummentCount
	 */
	public String getSharedPostCoummentCount() {
		return SharedPostCoummentCount;
	}

	/**
	 *
	 * @param SharedPostCoummentCount
	 * The SharedPostCoummentCount
	 */
	public void setSharedPostCoummentCount(String SharedPostCoummentCount) {
		this.SharedPostCoummentCount = SharedPostCoummentCount;
	}

	/**
	 *
	 * @return
	 * The RealOwner
	 */
	public String getRealOwner() {
		return RealOwner;
	}

	/**
	 *
	 * @param RealOwner
	 * The RealOwner
	 */
	public void setRealOwner(String RealOwner) {
		this.RealOwner = RealOwner;
	}

	/**
	 *
	 * @return
	 * The RealOwnerName
	 */
	public String getRealOwnerName() {
		return RealOwnerName;
	}

	/**
	 *
	 * @param RealOwnerName
	 * The RealOwnerName
	 */
	public void setRealOwnerName(String RealOwnerName) {
		this.RealOwnerName = RealOwnerName;
	}

	/**
	 *
	 * @return
	 * The PostText
	 */
	public String getPostText() {
		return PostText;
	}

	/**
	 *
	 * @param PostText
	 * The PostText
	 */
	public void setPostText(String PostText) {
		this.PostText = PostText;
	}

	/**
	 *
	 * @return
	 * The PostURL
	 */
	public String getPostURL() {
		return PostURL;
	}

	/**
	 *
	 * @param PostURL
	 * The PostURL
	 */
	public void setPostURL(String PostURL) {
		this.PostURL = PostURL;
	}

}