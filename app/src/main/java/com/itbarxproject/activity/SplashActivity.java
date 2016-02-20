package com.itbarxproject.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;

import com.itbarxproject.R;
import com.itbarxproject.application.ItbarxGlobal;
import com.itbarxproject.common.UserSharedPrefrences;
import com.itbarxproject.listener.AccountProcessesServiceListener;
import com.itbarxproject.model.account.AccountGetUserByLoginInfoModel;
import com.itbarxproject.model.account.EditProfileModel;
import com.itbarxproject.model.account.GetEditProfileModel;
import com.itbarxproject.model.account.LoginModel;
import com.itbarxproject.service.ResponseEventModel;
import com.itbarxproject.service.error.BarxErrorModel;
import com.itbarxproject.sl.AccountProcessesServiceSL;

/**
 * Created by 02483564 on 20.2.2016.
 */
public class SplashActivity extends BaseActivity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 1000;


    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_splash_screen;
    }

    @Override
    protected Context getContext() {
        return SplashActivity.this;
    }

    @Override
    protected void initViews() {
 /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if( getIntent().getBooleanExtra("Exit me", false)){
                    finish();
                    android.os.Process.killProcess(android.os.Process.myPid());
                }

                /* Create an Intent that will start the Menu-Activity. */
                //manuel stringte chache temizlenmesi istenirse temizler
                UserSharedPrefrences.lookClearStatus(getContext());
                //daha evvelden kullanıcı login yapmış ise
                boolean didLogon = UserSharedPrefrences.didLogOn(getContext());

                if(didLogon)
                {

                    if(UserSharedPrefrences.getToken(getContext())!=null&&!UserSharedPrefrences.getToken(getContext()).equalsIgnoreCase(""))
                    {
                        //facebookUser
                        setLogInAccountForFace();
                    }
                    else
                    {
                        //normal user
                        ItbarxGlobal global = ItbarxGlobal.setInstance(SplashActivity.this);
                        AccountGetUserByLoginInfoModel model = UserSharedPrefrences.getLogOnModel(getContext());
                        if(model==null)
                        {
                            launchSubActivity(LoginActivity.class);
                        }
                        else
                        {
                            global.setAccountModel(model);
                            LoginModel lmodel = new LoginModel();
                            lmodel.setPassword(UserSharedPrefrences.getPassword(getContext()));
                            lmodel.setUsername(UserSharedPrefrences.getUserName(getContext()));
                            setLogInAccount(lmodel);
                        }

                    }




                }
                else
                {
                    launchSubActivity(LoginActivity.class);

                }
                // finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
    protected void setLogInAccount(LoginModel loginModel) {
        AccountProcessesServiceSL accountServiceSL = new AccountProcessesServiceSL(getContext(),
                accountProcessesServiceListener, R.string.root_service_url);
        accountServiceSL.setLogInAccount(loginModel);
        showProgress(getString(R.string.ItbarxConnecting));
    }

    protected void setLogInAccountForFace() {
        AccountProcessesServiceSL accountServiceSL = new AccountProcessesServiceSL(getContext(),
                accountProcessesServiceListener, R.string.root_service_url);
        String email = UserSharedPrefrences.getEmail(getContext());
        String faceId = UserSharedPrefrences.getFacebookId(getContext());
        String token = UserSharedPrefrences.getToken(getContext());
        String name = UserSharedPrefrences.getUserName(getContext());
        accountServiceSL.setOtherSignup(email,name,token,"2",faceId);
        showProgress(getString(R.string.ItbarxConnecting));
    }
    @Override
    protected void exceptionHandler() {

    }

    // **************************************//
    // ---ACCOUNT SERVICE LISTENER METHODS---
    // **************************************//
    AccountProcessesServiceListener<String> accountProcessesServiceListener = new
            AccountProcessesServiceListener<String>() {

                @Override
                public void onError(BarxErrorModel onError) {

                    dismissProgress();
                    showAlert(onError.getErrMessage());
                    launchSubActivity(LoginActivity.class);

                }

                @Override
                public void onComplete(ResponseEventModel<String> onComplete) {
                    dismissProgress();

                }

                @Override
                public void logInAccount(AccountGetUserByLoginInfoModel loginModelResponse) {
                    dismissProgress();

                    ItbarxGlobal global = ItbarxGlobal.setInstance(SplashActivity.this);
                    global.setAccountModel(loginModelResponse);
                    UserSharedPrefrences.saveLogIn(getContext());
                    UserSharedPrefrences.saveLogOnModel(getContext(), loginModelResponse);

                    launchSubActivity(TabContainer.class);

                }

                @Override
                public void signUpAccount(AccountGetUserByLoginInfoModel loginModelResponse) {
                    dismissProgress();

                }

                @Override
                public void forgotAccount(String ok) {
                    dismissProgress();
                }

                @Override
                public void changePassByCode(AccountGetUserByLoginInfoModel loginModelResponse) {
                    dismissProgress();
                }

                @Override
                public void getEditProfileAccount(GetEditProfileModel getEditProfileModel) {
                    dismissProgress();

                }

                @Override
                public void editProfileAccount(EditProfileModel editProfileModel) {
                    dismissProgress();
                }

                @Override
                public void deleteProfileAccount(String isDeleted) {
                    dismissProgress();

                }

            };
}
