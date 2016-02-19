package com.itbarxproject.model.reply;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReplyListModel {



		@SerializedName("RowNum")
		@Expose
		private String RowNum;
		@SerializedName("PostReplyID")
		@Expose
		private String PostReplyID;
		@SerializedName("PostID")
		@Expose
		private String PostID;
		@SerializedName("PostSenderUserId")
		@Expose
		private String PostSenderUserId;
		@SerializedName("itBarxUserName")
		@Expose
		private String itBarxUserName;
		@SerializedName("userProfilePhoto")
		@Expose
		private String userProfilePhoto;
		@SerializedName("AddedDate")
		@Expose
		private String AddedDate;
		@SerializedName("PostText")
		@Expose
		private String PostText;
		@SerializedName("PostURL")
		@Expose
		private String PostURL;
		@SerializedName("PostPictureURL")
		@Expose
		private String PostPictureURL;

		/**
		 * No args constructor for use in serialization
		 *
		 */
		public ReplyListModel() {
		}

		/**
		 *
		 * @param PostPictureURL
		 * @param PostReplyID
		 * @param RowNum
		 * @param itBarxUserName
		 * @param PostText
		 * @param PostURL
		 * @param userProfilePhoto
		 * @param PostSenderUserId
		 * @param PostID
		 * @param AddedDate
		 */
		public ReplyListModel(String RowNum, String PostReplyID, String PostID, String PostSenderUserId, String itBarxUserName, String userProfilePhoto, String AddedDate, String PostText, String PostURL, String PostPictureURL) {
			this.RowNum = RowNum;
			this.PostReplyID = PostReplyID;
			this.PostID = PostID;
			this.PostSenderUserId = PostSenderUserId;
			this.itBarxUserName = itBarxUserName;
			this.userProfilePhoto = userProfilePhoto;
			this.AddedDate = AddedDate;
			this.PostText = PostText;
			this.PostURL = PostURL;
			this.PostPictureURL = PostPictureURL;
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
		 * The PostReplyID
		 */
		public String getPostReplyID() {
			return PostReplyID;
		}

		/**
		 *
		 * @param PostReplyID
		 * The PostReplyID
		 */
		public void setPostReplyID(String PostReplyID) {
			this.PostReplyID = PostReplyID;
		}

		/**
		 *
		 * @return
		 * The PostID
		 */
		public String getPostID() {
			return PostID;
		}

		/**
		 *
		 * @param PostID
		 * The PostID
		 */
		public void setPostID(String PostID) {
			this.PostID = PostID;
		}

		/**
		 *
		 * @return
		 * The PostSenderUserId
		 */
		public String getPostSenderUserId() {
			return PostSenderUserId;
		}

		/**
		 *
		 * @param PostSenderUserId
		 * The PostSenderUserId
		 */
		public void setPostSenderUserId(String PostSenderUserId) {
			this.PostSenderUserId = PostSenderUserId;
		}

		/**
		 *
		 * @return
		 * The itBarxUserName
		 */
		public String getItBarxUserName() {
			return itBarxUserName;
		}

		/**
		 *
		 * @param itBarxUserName
		 * The itBarxUserName
		 */
		public void setItBarxUserName(String itBarxUserName) {
			this.itBarxUserName = itBarxUserName;
		}

		/**
		 *
		 * @return
		 * The userProfilePhoto
		 */
		public String getUserProfilePhoto() {
			return userProfilePhoto;
		}

		/**
		 *
		 * @param userProfilePhoto
		 * The userProfilePhoto
		 */
		public void setUserProfilePhoto(String userProfilePhoto) {
			this.userProfilePhoto = userProfilePhoto;
		}

		/**
		 *
		 * @return
		 * The AddedDate
		 */
		public String getAddedDate() {
			return AddedDate;
		}

		/**
		 *
		 * @param AddedDate
		 * The AddedDate
		 */
		public void setAddedDate(String AddedDate) {
			this.AddedDate = AddedDate;
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

		/**
		 *
		 * @return
		 * The PostPictureURL
		 */
		public String getPostPictureURL() {
			return PostPictureURL;
		}

		/**
		 *
		 * @param PostPictureURL
		 * The PostPictureURL
		 */
		public void setPostPictureURL(String PostPictureURL) {
			this.PostPictureURL = PostPictureURL;
		}

	}