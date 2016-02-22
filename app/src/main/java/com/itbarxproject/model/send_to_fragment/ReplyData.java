package com.itbarxproject.model.send_to_fragment;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * TODO: Add a class header comment!
 */
public class ReplyData implements Parcelable {

	private String profilePhoto;
	private String itBarxUserName;
	private String timeAgo;
	private String place;
	private String replyPostText;
	private String thumbnail;
	private String videoURI;
	private String postId;


	/**
	 * Constructs a Question from values
	 */
	public ReplyData(String profilePhoto, String videoURI, String thumbnail, String replyPostText, String place, String timeAgo, String itBarxUserName) {
		this.profilePhoto = profilePhoto;
		this.videoURI = videoURI;
		this.thumbnail = thumbnail;
		this.replyPostText = replyPostText;
		this.place = place;
		this.timeAgo = timeAgo;
		this.itBarxUserName = itBarxUserName;
	}

	/**
	 * Constructs a Question from a Parcel
	 * @param parcel Source Parcel
	 */
	public ReplyData (Parcel parcel) {
		this.profilePhoto = parcel.readString();
		this.itBarxUserName = parcel.readString();
		this.timeAgo = parcel.readString();
		this.place = parcel.readString();
		this.replyPostText = parcel.readString();
		this.thumbnail = parcel.readString();
		this.videoURI = parcel.readString();
		this.postId = parcel.readString();
	}

	@Override public int describeContents() {
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(profilePhoto);
		dest.writeString(itBarxUserName);
		dest.writeString(timeAgo);
		dest.writeString(place);
		dest.writeString(replyPostText);
		dest.writeString(thumbnail);
		dest.writeString(videoURI);
		dest.writeString(postId);
	}


	public static Creator<ReplyData> CREATOR = new Creator<ReplyData>() {

		@Override
		public ReplyData createFromParcel(Parcel source) {
			return new ReplyData(source);
		}

		@Override
		public ReplyData[] newArray(int size) {
			return new ReplyData[size];
		}

	};

	public String getProfilePhoto() {
		return profilePhoto;
	}

	public ReplyData setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
		return this;
	}

	public String getItBarxUserName() {
		return itBarxUserName;
	}

	public ReplyData setItBarxUserName(String itBarxUserName) {
		this.itBarxUserName = itBarxUserName;
		return this;
	}

	public String getTimeAgo() {
		return timeAgo;
	}

	public ReplyData setName(String timeAgo) {
		this.timeAgo = timeAgo;
		return this;
	}

	public String getPlace() {
		return place;
	}

	public ReplyData setPlace(String place) {
		this.place = place;
		return this;
	}
	public String getReplyPostText() {
		return replyPostText;
	}

	public ReplyData setReplyPostText(String replyPostText) {
		this.replyPostText = replyPostText;
		return this;
	}
	public String getThumbnail() {
		return thumbnail;
	}

	public ReplyData setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
		return this;
	}
	public String getVideoURI() {
		return videoURI;
	}

	public ReplyData setVideoURI(String videoURI) {
		this.videoURI = videoURI;
		return this;
	}
	public String getPostId() {
		return postId;
	}

	public ReplyData setPostId(String PostId) {
		this.postId = PostId;
		return this;
	}
}
