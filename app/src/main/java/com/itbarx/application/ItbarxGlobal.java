package com.itbarx.application;

import java.util.List;
import java.util.Locale;

import com.itbarx.model.account.AccountGetUserByLoginInfoModel;
import com.itbarx.model.post.PostPopularPostListModel;
import com.itbarx.utils.TextSizeUtil;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Camera;
import android.util.DisplayMetrics;
import android.widget.TabHost;

public class ItbarxGlobal extends Application {

	private String userToken;
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
		super.onCreate();
	}

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

}
