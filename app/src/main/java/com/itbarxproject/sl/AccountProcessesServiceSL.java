package com.itbarxproject.sl;

import java.util.ArrayList;
import java.util.List;


import com.itbarxproject.R;

import com.itbarxproject.application.ItbarxGlobal;
import com.itbarxproject.service.ResponseEventModel;
import com.itbarxproject.service.ServiceResponseModel;
import com.itbarxproject.enums.AccountProcessesLinks;
import com.itbarxproject.enums.GlobalDataForWS;
import com.itbarxproject.service.error.BarxErrorModel;
import com.itbarxproject.service.BasePostServiceSL;
import com.itbarxproject.error.sl.BaseServicePostClientSL;
import com.itbarxproject.json.AccountModelParserJSON;
import com.itbarxproject.listener.AccountProcessesServiceListener;
import com.itbarxproject.model.account.AccountForgotSendMailModel;
import com.itbarxproject.model.account.AccountGetUserByLoginInfoModel;
import com.itbarxproject.model.account.AccountSendEmailCodeModel;
import com.itbarxproject.model.account.AccountSignUpModel;
import com.itbarxproject.model.account.DeleteProfileModel;
import com.itbarxproject.model.account.EditProfileModel;
import com.itbarxproject.model.account.GetEditProfileIdModel;
import com.itbarxproject.model.account.GetEditProfileModel;
import com.itbarxproject.model.account.LoginModel;
import com.itbarxproject.utils.ItbarxUtils;

import android.content.Context;
import android.util.Pair;

public class AccountProcessesServiceSL extends BasePostServiceSL<String> {

	// private final String NAME_OF_THE_CLASS = this.getClass().getSimpleName();
	private static final String NAME_OF_THE_CLASS = AccountProcessesServiceSL.class.getSimpleName();
	AccountProcessesServiceListener<String> accountServiceListener;

	public AccountProcessesServiceSL(Context appContext, AccountProcessesServiceListener<String> listener, int serviceResUriId) {
	super(appContext,listener,serviceResUriId);
	accountServiceListener = listener;
	setOnServiceErrorClientListener(this);

	}

	// **********************************//
	// ---SEND DATA WEBSERVICE METHODS---
	// **********************************//

	// ---LOGIN---KULLANICI GİRİŞİ
	public void setLogInAccount(LoginModel loginModel) {

		List<Pair<String,String>> params = new ArrayList<>();
	params.add(new Pair(GlobalDataForWS.USERNAME.toString(), loginModel.getUsername()));
	params.add(new Pair(GlobalDataForWS.PASSWORD.toString(), loginModel.getPassword()));
	String postData = ItbarxUtils.formattedData(params);
	// Post service CALL
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, AccountProcessesLinks.LOGIN_LINK.toString());
	}

	// ---SIGNUP---KULLANICI KAYIT
	public void setSignUpAccount(AccountSignUpModel signupModel) {

		List<Pair<String,String>> params = new ArrayList<>();
	params.add(new Pair(GlobalDataForWS.USERNAME.toString(), signupModel.getUsername()));
	params.add(new Pair(GlobalDataForWS.PASSWORD.toString(), signupModel.getPassword()));
	params.add(new Pair(GlobalDataForWS.PASSWORD_CONFIRM.toString(), signupModel.getPasswordConfirm()));
	params.add(new Pair(GlobalDataForWS.EMAIL.toString(), signupModel.getEmail()));
	params.add(new Pair(GlobalDataForWS.PHOTO_BASE64_STRING.toString(), signupModel.getPhoto()));
	String postData = ItbarxUtils.formattedData(params);
	// Post service CALL
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, AccountProcessesLinks.SIGNUP_LINK.toString());
	}

	// ---FORGOT---KULLANICI ŞİFRE UNUTTUM
	public void setForgotAccount(AccountForgotSendMailModel accountForgotSendMailModel) {

		List<Pair<String,String>> params = new ArrayList<>();
	params.add(new Pair(GlobalDataForWS.EMAIL.toString(), accountForgotSendMailModel.getEmail()));
	String postData = ItbarxUtils.formattedData(params);
	// Post service CALL
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, AccountProcessesLinks.FORGOT_PASSWORD_LINK.toString());
	}

	// ---CHANGE BY CODE---KULLANICI SİFRE DEĞİŞTİRME
	public void setChangePassByCodeAccount(AccountSendEmailCodeModel accountSendEmailCodeModel) {

		List<Pair<String,String>> params = new ArrayList<>();
	params.add(new Pair(GlobalDataForWS.EMAIL_CODE.toString(), accountSendEmailCodeModel.getEmailCode()));
	params.add(new Pair(GlobalDataForWS.PASSWORD.toString(), accountSendEmailCodeModel.getPassword()));
	params.add(new Pair(GlobalDataForWS.PASSWORD_CONFIRM.toString(), accountSendEmailCodeModel.getPasswordConfirm()));
	String postData = ItbarxUtils.formattedData(params);
	// Post service CALL
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, AccountProcessesLinks.CHANGE_PASS_BY_CODE.toString());
	}

	// ---GET_EDIT_PROFILE---KULLANICI EDİT SAYFASI BİLGİLERİ AL
	public void setGetEditProfile(GetEditProfileIdModel gEditProfileIdModel) {

		List<Pair<String,String>> params = new ArrayList<>();
	params.add(new Pair(GlobalDataForWS.USER_ID.toString(), gEditProfileIdModel.getUserID()));
	String postData = ItbarxUtils.formattedData(params);
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, AccountProcessesLinks.GET_EDIT_PROFILE_LINK.toString());
	}

	// ---GET_EDIT_PROFILE---KULLANICI EDİT SAYFASI BİLGİLERİ AL
	public void setOtherSignup(String Email,String Name, String Token,String LoginCategory,String IdInfo) {
/*
facebook icin = 2
google plus icin = 3
Token:"my token",
Name :"my name",
LoginCategory : "2",
IdInfo : "my id"
}
 */
		List<Pair<String,String>> params = new ArrayList<>();
		params.add(new Pair(GlobalDataForWS.EMAIL.toString(),Email));
		params.add(new Pair(GlobalDataForWS.TOKEN.toString(),Token));
		params.add(new Pair(GlobalDataForWS.NAME.toString(),Name));
		params.add(new Pair(GlobalDataForWS.LOGIN_CATEGORY.toString(),LoginCategory));
		params.add(new Pair(GlobalDataForWS.ID_INFO.toString(),IdInfo));

		String postData = ItbarxUtils.formattedData(params);
		BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
		postClient.addServiceClientListener(this);
		postClient.addErrorErrorServiceClientListener(this);
		postClient.setBasicHttpBinding(true);
		String uri = getServiceUri();
		postClient.execute(uri, AccountProcessesLinks.OTHER_SIGNUP.toString());
	}


	// ---EDIT_PROFILE---
	public void setEditProfile(EditProfileModel epm) {

		List<Pair<String,String>>  params = new ArrayList<>();
	params.add(new Pair(GlobalDataForWS.USER_ID.toString(), epm.getUserId()));
	params.add(new Pair(GlobalDataForWS.NAME.toString(), epm.getName()==null?"":epm.getName()));
	params.add(new Pair(GlobalDataForWS.USERNAME.toString(), epm.getUserName()==null?"":epm.getUserName()));
	//params.add(new Pair(GlobalDataForWS.LOCATION.toString(), epm.getLocation()==null?"":epm.getLocation()));
	params.add(new Pair(GlobalDataForWS.WEBSITE.toString(), epm.getWebSite()==null?"":epm.getWebSite()));
	params.add(new Pair(GlobalDataForWS.OLD_PASSWORD.toString(), epm.getOldPassword()==null?"":epm.getOldPassword()));
	params.add(new Pair(GlobalDataForWS.NEW_PASSWORD.toString(), epm.getNewPassword()==null?"":epm.getNewPassword()));
	params.add(new Pair(GlobalDataForWS.CONFIRM_PASSWORD.toString(), epm.getConfirmPassword()==null?"":epm.getConfirmPassword()));
	//params.add(new Pair(GlobalDataForWS.OLD_PHOTO_URL.toString(), epm.getOldPhotoUrl()==null?"":epm.getOldPhotoUrl()));
	params.add(new Pair(GlobalDataForWS.NEW_PHOTO_BASE64_STRING.toString(), epm.getNewPhotoBase64String()==null?"":epm.getNewPhotoBase64String()));
	params.add(new Pair(GlobalDataForWS.IS_NOTIFICATION_ACTIVE.toString(), epm.getIsNotificationActive()==null?"":epm.getIsNotificationActive()));
	params.add(new Pair(GlobalDataForWS.USER_BIO.toString(), epm.getUserBio()==null?"":epm.getUserBio()));
	params.add(new Pair(GlobalDataForWS.EMAIL.toString(), ItbarxGlobal.getInstance().getAccountModel().getUserEmail()));

	String postData = ItbarxUtils.formattedData(params);
	// Post service CALL
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, AccountProcessesLinks.EDIT_PROFILE_LINK.toString());
	}

	// ---DELETE_PROFILE---
	public void setDeleteProfile(DeleteProfileModel deleteProfileModel) {

		List<Pair<String,String>> params = new ArrayList<>();
	params.add(new Pair(GlobalDataForWS.USER_ID.toString(), deleteProfileModel.getUserID()));
	String postData = ItbarxUtils.formattedData(params);
	BaseServicePostClientSL<String> postClient = new BaseServicePostClientSL<String>(context, NAME_OF_THE_CLASS, postData);
	postClient.addServiceClientListener(this);
	postClient.addErrorErrorServiceClientListener(this);
	postClient.setBasicHttpBinding(true);
	String uri = getServiceUri();
	postClient.execute(uri, AccountProcessesLinks.DELETE_PROFILE_LINK.toString());
	}

	// ************************//
	// ---OVERRIDED METHODS---
	// ************************//

	@Override
	public void onPOSTCommit(ResponseEventModel<String> responseEvent) {

	String result = responseEvent.getResponseData();
	// ---LOGIN---
	if (responseEvent.getMethodName().equalsIgnoreCase(AccountProcessesLinks.LOGIN_LINK.toString())) {
		ServiceResponseModel model = ItbarxUtils.getServiceResponseModel(result, "SpUserGetUserByLoginInfosResult");

		if (model != null) {

		AccountModelParserJSON accountModelParserJSON = new AccountModelParserJSON();
		AccountGetUserByLoginInfoModel loginModelResponse = accountModelParserJSON.getUserLoginInfoModelFromJSON(model.getModel());
		if (loginModelResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
			errorModel.setErrMessage(context.getString(R.string.BrokenData));
			accountServiceListener.onError(errorModel);
		} else {
			accountServiceListener.logInAccount(loginModelResponse);
		}

		}
	}
else if(responseEvent.getMethodName().equalsIgnoreCase(AccountProcessesLinks.OTHER_SIGNUP.toString()))
	{

		ServiceResponseModel model = ItbarxUtils.getServiceResponseModel(result, "SpUserGetUserByOtherLoginInfosResult");
		if (model != null) {

			AccountModelParserJSON accountModelParserJSON = new AccountModelParserJSON();
			AccountGetUserByLoginInfoModel loginModelResponse = accountModelParserJSON.getUserLoginInfoModelFromJSON(model.getModel());
			if (loginModelResponse == null) {
				BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
				errorModel.setErrMessage(context.getString(R.string.BrokenData));
				accountServiceListener.onError(errorModel);
			} else {
				accountServiceListener.logInAccount(loginModelResponse);
			}

		}
	}
	// ---SIGNUP---
	else if (responseEvent.getMethodName().equalsIgnoreCase(AccountProcessesLinks.SIGNUP_LINK.toString()))

	{
		ServiceResponseModel model = ItbarxUtils.getServiceResponseModel(result, "SpUserGetUserByLoginInfosResult");

		if (model != null) {
		AccountModelParserJSON accountModelParserJSON = new AccountModelParserJSON();
		AccountGetUserByLoginInfoModel loginModelResponse = accountModelParserJSON.getUserLoginInfoModelFromJSON(model.getModel());
		if (loginModelResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
			errorModel.setErrMessage(context.getString(R.string.BrokenData));
			accountServiceListener.onError(errorModel);
		} else {
			accountServiceListener.signUpAccount(loginModelResponse);
		}
		}

	}
	// ---FORGOT---
	else if (responseEvent.getMethodName().equalsIgnoreCase(AccountProcessesLinks.FORGOT_PASSWORD_LINK.toString()))

	{
		ServiceResponseModel model = ItbarxUtils.getServiceResponseModelDataKey(result);

		if (model != null) {
		AccountModelParserJSON accountModelParserJSON = new AccountModelParserJSON();
		String forgotResponse = accountModelParserJSON.getForgotProfileModelFromJSON(model.getModel());
		if (forgotResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
			errorModel.setErrMessage(context.getString(R.string.BrokenData));
			accountServiceListener.onError(errorModel);
		} else {
			accountServiceListener.forgotAccount(forgotResponse);
		}
		}

	}

	// ---CHANGE PASS BY CODE---
	else if (responseEvent.getMethodName().equalsIgnoreCase(AccountProcessesLinks.CHANGE_PASS_BY_CODE.toString()))

	{
		ServiceResponseModel model = ItbarxUtils.getServiceResponseModel(result, "SpUserGetUserByLoginInfosResult");

		if (model != null) {
		AccountModelParserJSON accountModelParserJSON = new AccountModelParserJSON();
		AccountGetUserByLoginInfoModel loginModelResponse = accountModelParserJSON.getUserLoginInfoModelFromJSON(model.getModel());
		if (loginModelResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
			errorModel.setErrMessage(context.getString(R.string.BrokenData));
			accountServiceListener.onError(errorModel);
		} else {
			accountServiceListener.changePassByCode(loginModelResponse);
		}
		}

	}
	// ---GETEDITPROFILE---
	else if (responseEvent.getMethodName().equalsIgnoreCase(AccountProcessesLinks.GET_EDIT_PROFILE_LINK.toString()))

	{
		ServiceResponseModel model = ItbarxUtils.getServiceResponseModelDataKey(result);

		if (model != null) {
		AccountModelParserJSON accountModelParserJSON = new AccountModelParserJSON();
		GetEditProfileModel getEditProfileModelResponse = accountModelParserJSON.getGetEditProfileModelFromJSON(model.getModel());
		if (getEditProfileModelResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
			errorModel.setErrMessage(context.getString(R.string.BrokenData));
			accountServiceListener.onError(errorModel);
		} else {
			accountServiceListener.getEditProfileAccount(getEditProfileModelResponse);
		}

		}
	} // ---EDIT_PROFILE---
	else if (responseEvent.getMethodName().equalsIgnoreCase(AccountProcessesLinks.EDIT_PROFILE_LINK.toString()))

	{
		ServiceResponseModel model = ItbarxUtils.getServiceResponseModelDataKey(result);

		if (model != null) {
		AccountModelParserJSON accountModelParserJSON = new AccountModelParserJSON();

			AccountGetUserByLoginInfoModel editProfileModelResponse = accountModelParserJSON.getUserLoginInfoModelFromJSON(model.getModel());
			//EditProfileModel editProfileModelResponse = accountModelParserJSON.getEditProfileModelFromJSON(model.getModel());


			if (editProfileModelResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
			errorModel.setErrMessage(context.getString(R.string.BrokenData));
			accountServiceListener.onError(errorModel);
		} else {
			accountServiceListener.editProfileAccount(editProfileModelResponse);
		}

		}
	} // ---DELETE_PROFILE---
	else if (responseEvent.getMethodName().equalsIgnoreCase(AccountProcessesLinks.DELETE_PROFILE_LINK.toString()))

	{
		ServiceResponseModel model = ItbarxUtils.getServiceResponseModelDataKey(result);
		String isDeletedResponse = null;
		if (model != null) {
		AccountModelParserJSON accountModelParserJSON = new AccountModelParserJSON();
		isDeletedResponse = accountModelParserJSON.getDeleteProfileModelFromJSON(model.getModel());
		if (isDeletedResponse == null) {
			BarxErrorModel<String> errorModel = new BarxErrorModel<String>();
			errorModel.setErrMessage(context.getString(R.string.BrokenData));
			accountServiceListener.onError(errorModel);
		} else {
			accountServiceListener.deleteProfileAccount(isDeletedResponse);
		}

		}
	}

	}



	@Override
	public void onError(BarxErrorModel errorModel) {

	accountServiceListener.onError(errorModel);

	}

}
