package com.itbarx.application;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Locale;

import com.itbarx.model.account.AccountGetUserByLoginInfoModel;
import com.itbarx.model.post.PostPopularPostListModel;
import com.itbarx.utils.TextSizeUtil;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TabHost;

public class ItbarxGlobal extends Application {

	private String userToken;
	private static Bitmap googlePlusPhoto;
	private AccountGetUserByLoginInfoModel userLoginInfoModel;
	private List<PostPopularPostListModel> popularListModel;
	private static Context appContext;
	private static SharedPreferences mSharedPrefs;
	private static ItbarxGlobal instance =null;
	private static float DENSITY;
	private static float DISPLAY_PX_HEIGHT;
	private static float DISPLAY_DP_HEIGHT;
	private static float DISPLAY_PX_WIDTH;


	@Override
	public void onCreate() {
		instance =this;

		setDensity();
		setDisplayPxHeight();
		setDisplayDpHeight();
		setDisplayPxWidth();
		printHashKey();
		super.onCreate();
	}
	private void printHashKey(){
		try {
			PackageInfo info = getPackageManager().getPackageInfo(
					"com.itbarx",
					PackageManager.GET_SIGNATURES);
			for (Signature signature : info.signatures) {
				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				Log.d("KeyHash: Test APP", Base64.encodeToString(md.digest(), Base64.DEFAULT));
			}
		} catch (PackageManager.NameNotFoundException e) {

		} catch (NoSuchAlgorithmException e) {



		}}

	public static Context getContext(){
		return instance;
	}

	public static ItbarxGlobal getInstance() {
	if (instance == null) {
		instance = new ItbarxGlobal();

	}
	return instance;
	}

	public static ItbarxGlobal setInstance(Context appContext) {
	if (instance == null) {
		instance = new ItbarxGlobal();
		init(appContext);

	}
	return instance;
	}

	private static void init(Context context) {
	appContext = context;
	mSharedPrefs = appContext.getSharedPreferences("xmlFile", MODE_PRIVATE);

	}

	public static Boolean IsContexNull() {
	Boolean isNull = false;
	if (appContext == null) {
		isNull = true;
	} else {
		isNull = false;
	}
	return isNull;

	}
	public static TabHost tabHost;

	public String getUserToken() {
	return userToken;
	}

	public void setUserToken(String userToken) {
	this.userToken = userToken;
	}

	public AccountGetUserByLoginInfoModel getAccountModel() {
	return userLoginInfoModel;
	}

	public void setAccountModel(AccountGetUserByLoginInfoModel accountModel) {
	this.userLoginInfoModel = accountModel;
	}

	public  List<PostPopularPostListModel> getPopularListModel(){
		return popularListModel;
	}

	public  void setPopularListModel( List<PostPopularPostListModel> models){
		popularListModel =models;
	}

	public String getSystemLanguage() {
	return Locale.getDefault().getLanguage();
	}

	//set density

	private static void setDensity(){
	DENSITY =	getInstance().getResources().getDisplayMetrics().density;
	}

	public static  float getDENSITY(){
		return  DENSITY;
	}

	private static void setDisplayPxHeight() {
		DisplayMetrics metrics = getInstance().getResources().getDisplayMetrics();

		//float deviceTotalWidth = metrics.widthPixels;
		float deviceTotalHeight = metrics.heightPixels;
		DISPLAY_PX_HEIGHT = deviceTotalHeight;


	}

	public static float getDisplayPxHeight() {
		return DISPLAY_PX_HEIGHT;
	}

	private static void setDisplayDpHeight() {
		DisplayMetrics metrics = getInstance().getResources().getDisplayMetrics();
		float deviceTotalHeight = metrics.heightPixels;
		DISPLAY_DP_HEIGHT = deviceTotalHeight / getInstance().getResources().getDisplayMetrics().density;


	}

	private static void setDisplayPxWidth(){
		DisplayMetrics metrics = getInstance().getResources().getDisplayMetrics();
		DISPLAY_PX_WIDTH = metrics.widthPixels;

	}
	public static float getDisplayPxWidth(){
		return  DISPLAY_PX_WIDTH;
	}

	public static  float getDisplayDpHeight(){
		return DISPLAY_DP_HEIGHT;
	}

	public static Bitmap getGooglePlusPhoto() {
		return googlePlusPhoto;
	}

	public static void setGooglePlusPhoto(Bitmap sPlusPhoto) {
		ItbarxGlobal.googlePlusPhoto = sPlusPhoto;
	}


}
