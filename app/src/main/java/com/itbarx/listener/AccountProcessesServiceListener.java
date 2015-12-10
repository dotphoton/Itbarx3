package com.itbarx.listener;

import com.itbarx.service.BaseServiceListener;
import com.itbarx.model.account.AccountGetUserByLoginInfoModel;
import com.itbarx.model.account.EditProfileModel;
import com.itbarx.model.account.GetEditProfileModel;

public interface AccountProcessesServiceListener<T> extends BaseServiceListener<T> {

	public void logInAccount(AccountGetUserByLoginInfoModel loginModelResponse);

	public void signUpAccount(AccountGetUserByLoginInfoModel loginModelResponse);
	// public void ForgotPassword(String message);

	public void forgotAccount(String forgotResponse);

	public void changePassByCode(AccountGetUserByLoginInfoModel loginModelResponse);

	public void getEditProfileAccount(GetEditProfileModel getEditProfileModel);

	public void editProfileAccount(EditProfileModel editProfileModel);

	public void deleteProfileAccount(String isDeleted);
}
