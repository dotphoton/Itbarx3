package com.itbarxproject.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.itbarxproject.R;
import com.itbarxproject.activity.Communicator;
import com.itbarxproject.json.AccountModelParserJSON;
import com.itbarxproject.model.account.AccountGetUserByLoginInfoModel;

/**
 * Created by 02483564 on 20.2.2016.
 */
public class UserSharedPrefrences {

    public static void saveBundle(Context context,Bundle facebundle) {

        saveEmail(context, facebundle.getString("email"));
        saveUserName(context, facebundle.getString("first_name"));
        saveFacebookId(context,facebundle.getString("idFacebook"));

    }

   public static void saveUserName(Context context,String userName) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "LoginPreferences", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("UserName", userName);
        editor.commit();
    }
  public static   void saveId(Context context,String id) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "LoginPreferences", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("ID", id);
        editor.commit();
    }
  public static   void savePassword(Context context,String pwd) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "LoginPreferences", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Password", pwd);
        editor.commit();
    }
    public static   void saveFacebookId(Context context,String facebookId) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "LoginPreferences", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("FacebookId", facebookId);
        editor.commit();
    }
    public static void saveToken(Context context,String token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "LoginPreferences", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Token", token);
        editor.commit();
    }
    public static String getToken(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(
                "LoginPreferences", 0);
        return sharedPreferences.getString("Token", "");
    }
    public static String getId(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(
                "LoginPreferences", 0);
        return sharedPreferences.getString("ID", "");
    }
    public static String getFacebookId(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(
                "LoginPreferences", 0);
        return sharedPreferences.getString("FacebookId", "");
    }
    public static String getEmail(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(
                "LoginPreferences", 0);
        return sharedPreferences.getString("Email", "");
    }
    public static void saveEmail(Context context,String email) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "LoginPreferences", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Email", email);
        editor.commit();
    }

    public static void clearLoginData(Context context) {

        saveId(context, "");
        saveEmail(context, "");
        saveToken(context, "");
        saveUserName(context, "");
        savePassword(context, "");
        saveFacebookId(context,"");
    }
    public static void saveLogIn(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(
                "LoginPreferences", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("LogInStatus", "1");
        editor.commit();
    }


    public static String getUserName(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(
                "LoginPreferences", 0);
        return sharedPreferences.getString("UserName", "");
    }

    public static String getPassword(Context context) {
        SharedPreferences sharedPreferences =context.getApplicationContext().getSharedPreferences(
                "LoginPreferences", 0);
        return sharedPreferences.getString("Password", "");
    }

    public static String getLogInStatus(Context context)  {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(
                "LoginPreferences", 0);
        return sharedPreferences.getString("LogInStatus", "");
    }



    public static void saveLogInClear(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(
                "LoginPreferences", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("LogInStatus", "0");
        editor.commit();
    }

    public static void saveLogOnModel(Context context,AccountGetUserByLoginInfoModel loginModelResponse) {
        AccountModelParserJSON modelParserJSON = new AccountModelParserJSON();
        String data = modelParserJSON.getUserLoginInfoJSONFromModel(loginModelResponse);
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(
                "LoginPreferences", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("LogOnModel", data);
        editor.commit();
    }
    private static String getAccountGetUserByLoginInfoJSONString(Context context) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(
                "LoginPreferences", 0);
        return sharedPreferences.getString("LogOnModel", "");
    }
    public static AccountGetUserByLoginInfoModel  getLogOnModel(Context context) {
        AccountModelParserJSON modelParserJSON = new AccountModelParserJSON();
        AccountGetUserByLoginInfoModel data = modelParserJSON.getUserLoginInfoModelFromJSON(getAccountGetUserByLoginInfoJSONString(context));
        return  data;
    }

    public static void lookClearStatus(Context context)
    {
        String clearStatus = context.getString(R.string.isClearLogInStatus);
        if(clearStatus.equalsIgnoreCase("1"))
        {
            saveLogInClear(context);
            clearLoginData(context);
        }
    }

    public static boolean  didLogOn(Context context)
    {
        boolean  status =false;
        String clearStatus = context.getString(R.string.isClearLogInStatus);
        String logStatus = getLogInStatus(context);
        if (logStatus.equalsIgnoreCase("1")) {
            status=true;
        }
        return status;
    }
}
