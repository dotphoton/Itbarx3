package com.itbarx.enums;

public enum AccountProcessesLinks {

	SIGNUP_LINK("account/signup"), LOGIN_LINK("account/login"), GET_EDIT_PROFILE_LINK("account/geteditprofile"), EDIT_PROFILE_LINK("account/editprofile"), DELETE_PROFILE_LINK(
		"account/deleteprofile"), FORGOT_PASSWORD_LINK("account/forgot"),CHANGE_PASS_BY_CODE("account/changepassbycode");

	private final String link;

	private AccountProcessesLinks(String value) {
	this.link = value;
	}

	@Override
	public String toString() {

	return link;
	}

}
