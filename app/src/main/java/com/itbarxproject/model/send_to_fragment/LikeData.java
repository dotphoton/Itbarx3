package com.itbarxproject.model.send_to_fragment;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * TODO: Add a class header comment!
 */
public class LikeData implements Parcelable {

	private String profilePhoto;
	private String itBarxUserName;
	private String name;
	private String areYouFollowing;
	private String id;

	/**
	 * Constructs a Question from values
	 */
	public LikeData (String photo,String itBarxUserName, String name, String areYouFollowing,String id) {
		this.profilePhoto=photo;
		this.itBarxUserName = itBarxUserName;
		this.name = name;
		this.areYouFollowing = areYouFollowing;
		this.id = id;
	}
	/**
	 * Constructs a Question from a Parcel
	 * @param parcel Source Parcel
	 */
	public LikeData (Parcel parcel) {
		this.profilePhoto = parcel.readString();
		this.itBarxUserName = parcel.readString();
		this.name = parcel.readString();
		this.areYouFollowing = parcel.readString();
		this.id = id;
			}

	@Override public int describeContents() {
		return 0;
	}

	@Override public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(profilePhoto);
		dest.writeString(itBarxUserName);
		dest.writeString(name);
		dest.writeString(areYouFollowing);
		dest.writeString(id);

	}


	public static Creator<LikeData> CREATOR = new Creator<LikeData>() {

		@Override
		public LikeData createFromParcel(Parcel source) {
			return new LikeData(source);
		}

		@Override
		public LikeData[] newArray(int size) {
			return new LikeData[size];
		}

	};

	public String getProfilePhoto() {
		return profilePhoto;
	}

	public LikeData setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
		return this;
	}

	public String getItBarxUserName() {
		return itBarxUserName;
	}

	public LikeData setItBarxUserName(String itBarxUserName) {
		this.itBarxUserName = itBarxUserName;
		return this;
	}

	public String getName() {
		return name;
	}

	public LikeData setName(String name) {
		this.name = name;
		return this;
	}

	public String getAreYouFollowing() {
		return areYouFollowing;
	}

	public LikeData setAreYouFollowing(String areYouFollowing) {
		this.areYouFollowing = areYouFollowing;
		return this;
	}

	public String getId() {
		return id;
	}

	public LikeData setId(String id) {
		this.id = id;
		return this;
	}
}
