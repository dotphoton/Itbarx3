package com.itbarxproject.listener;

import com.itbarxproject.service.BaseServiceListener;
import com.itbarxproject.model.account.AccountGetUserByLoginInfoModel;
import com.itbarxproject.model.account.EditProfileModel;
import com.itbarxproject.model.account.GetEditProfileModel;

public interface AccountProcessesServiceListener<T> extends BaseServiceListener<T> {

	public void logInAccount(AccountGetUserByLoginInfoModel loginModelResponse);

	public void signUpAccount(AccountGetUserByLoginInfoModel loginModelResponse);
	// public void ForgotPassword(String message);

	public void forgotAccount(String forgotResponse);

	public void changePassByCode(AccountGetUserByLoginInfoModel loginModelResponse);

	public void getEditProfileAccount(GetEditProfileModel getEditProfileModel);

	public void editProfileAccount(AccountGetUserByLoginInfoModel editProfileModel);

	public void deleteProfileAccount(String isDeleted);
}
