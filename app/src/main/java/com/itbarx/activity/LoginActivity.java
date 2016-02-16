package com.itbarx.activity;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import com.itbarx.R;
import com.itbarx.application.ItbarxGlobal;
import com.itbarx.custom.component.ButtonBold;
import com.itbarx.custom.component.EditTextRegular;
import com.itbarx.custom.component.TextViewBold;
import com.itbarx.custom.component.TextViewRegular;
import com.itbarx.exception.ExceptionHandler;
import com.itbarx.service.ResponseEventModel;
import com.itbarx.service.error.BarxErrorModel;
import com.itbarx.listener.AccountProcessesServiceListener;
import com.itbarx.listener.OneShotOnClickListener;
import com.itbarx.model.account.AccountForgotSendMailModel;
import com.itbarx.model.account.AccountGetUserByLoginInfoModel;
import com.itbarx.model.account.AccountSendEmailCodeModel;
import com.itbarx.model.account.AccountSignUpModel;
import com.itbarx.model.account.DeleteProfileModel;
import com.itbarx.model.account.EditProfileModel;
import com.itbarx.model.account.GetEditProfileIdModel;
import com.itbarx.model.account.GetEditProfileModel;
import com.itbarx.model.account.LoginModel;
import com.itbarx.sl.AccountProcessesServiceSL;
import com.itbarx.utils.TextSizeUtil;

import java.io.InputStream;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {

    private static final int RC_SIGN_IN = 1000;

    private static GoogleApiClient mGoogleApiClient;
    private ConnectionResult mConnectionResult;
    private boolean mIntentInProgress;
    private boolean signedInUser;
    private String dt;
    private ButtonBold btnLogIn, btnCreateNewAcc, btnForgotPwd, btnFacebook, btnGooglePlus;
    private TextViewRegular txtToolbar;
    private TextViewBold txtViewRembMe, txtViewOr;
    private EditTextRegular edtUserName, edtPassword;
    private String strUserName, strPassword;
    private ImageView unCheckedRemMeImageV, checkedRemMeImageV;
    private SignInButton btnSignInButton;
    private LoginButton btnfaceLoginButton;


    @Override
    protected int getLayoutResourceId() {

        return R.layout.activity_login_screen;
    }

    @Override
    protected Context getContext() {
        // TODO Auto-generated method stub
        return LoginActivity.this;
    }

    @Override
    protected void exceptionHandler() {
        //	Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
    }

    @Override
    protected void initViews() {
        //	ButterKnife.bind(this);
        edtUserName = (EditTextRegular) findViewById(R.id.login_activity_screen_username_edittext);
        edtPassword = (EditTextRegular) findViewById(R.id.login_activity_screen_password_edittext);
        btnLogIn = (ButtonBold) findViewById(R.id.login_activity_screen_login_button);
        btnForgotPwd = (ButtonBold) findViewById(R.id.login_activity_screen_forgotpassword_button);
        btnCreateNewAcc = (ButtonBold) findViewById(R.id
                .login_activity_screen_createnewaccount_button);
        btnGooglePlus = (ButtonBold) findViewById(R.id.login_activity_screen_googlePlus_button);
        btnSignInButton = (SignInButton) findViewById(R.id
				.login_activity_screen_googlePlus_signIn_button);
        btnFacebook = (ButtonBold) findViewById(R.id.login_activity_screen_facebook_button);
        //btnfaceLoginButton = (LoginButton) findViewById(R.id
        // .login_activity_screen_facebook_signIn_button);
        txtViewOr = (TextViewBold) findViewById(R.id.login_activity_screen_or_plaintext);
        txtViewRembMe = (TextViewBold) findViewById(R.id
				.login_activity_screen_rememberMe_plaintext);
        txtToolbar = (TextViewRegular) findViewById(R.id.login_activity_screen_toolbar_textView);
        unCheckedRemMeImageV = (ImageView) findViewById(R.id
				.login_activity_screen_rememberMe_unchecked_icon);
        checkedRemMeImageV = (ImageView) findViewById(R.id
				.login_activity_screen_rememberMe_checked_icon);

        strUserName = "";
        strPassword = "";

        //SET FONTS
        setCompFonts();

        //PLUS LOGIN START
        //	mGoogleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks
        // (connectionCallbacks).
        //			addOnConnectionFailedListener(failedListener).addApi(Drive.API, Drive
        // .PlusOptions.builder().build()).addScope(Drive.SCOPE_PLUS_LOGIN).build();

        //START OTHER ACTIVITIES SET LISTENERS
        logInStart();
        forgotStart();
        createNewAccStart();
        //	googlePlusStart();
        //	facebookStart();
      /*
        btnLogIn.setOnClickListener(new OneShotOnClickListener(500) {
            @Override
            public void onOneShotClick(View v) {
                btnSignInButton.performClick();
            }
        });
        */
        //REMEMBER ME METHODS
        checkedRemMeStart();
        unCheckedRemMeStart();


    }

    //----------------------------------------------------------------------------------------------------------------------
    //LOGIN COMPONENTS SETUP METHODS
    private void setCompFonts() {
        btnLogIn.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getButtonTextSize());
        btnForgotPwd.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getButtonTextSize());
        btnCreateNewAcc.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getButtonTextSize());
        btnFacebook.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getButtonTextSize());
        btnGooglePlus.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getButtonTextSize());
        txtViewOr.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getLoginOrTextSize());
        txtViewRembMe.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil
				.getLoginRememberMeTextSize());
        txtToolbar.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getToolbarTextSize());
        edtUserName.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getEditBoxTextSize());
        edtPassword.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getEditBoxTextSize());
    }

    // *************************************//
    // ---LOGIN ACTIVITY ACTION METHODS---
    // *************************************//

    // LOGIN BUTTON EVENTS
    private void logInStart() {

        btnLogIn.setOnClickListener(logInClickListener);
    }

    // FORGOT BUTTON EVENTS
    private void forgotStart() {

        btnForgotPwd.setOnClickListener(forgotPwdCreateClickListener);
    }

    // CREATE NEW ACCOUNT BUTTON EVENTS
    private void createNewAccStart() {

        btnCreateNewAcc.setOnClickListener(newUserCreateClickListener);
    }

    // GOOGLE+ BUTTON EVENTS
    private void googlePlusStart() {

        btnSignInButton.setOnClickListener(googlePlusClickListener);
    }

    // FACEBOOK BUTTON EVENTS
    private void facebookStart() {

        btnFacebook.setOnClickListener(facebookClickListener);
    }

    private void checkedRemMeStart() {
        checkedRemMeImageV.setOnClickListener(checkedClickListener);
    }

    private void unCheckedRemMeStart() {
        unCheckedRemMeImageV.setOnClickListener(unCheckedClickListener);
    }


    // ************************//
    // ---EVENT LISTENERS---
    // ************************//

    // LOGIN BUTTON LISTENER
    OneShotOnClickListener logInClickListener = new OneShotOnClickListener(500) {

        @Override
        public void onOneShotClick(View v) {


            strUserName = edtUserName.getText().toString();
            strPassword = edtPassword.getText().toString();
            if (strUserName.equals("")) {
                Toast.makeText(getApplicationContext(), "Kullanıcı adınızı giriniz!", Toast
						.LENGTH_LONG).show();
                return;
            }
            if (strPassword.equals("")) {
                Toast.makeText(getApplicationContext(), "Şifrenizi giriniz!", Toast.LENGTH_LONG)
						.show();
                return;
            }

            setLogInAccount(new LoginModel(strUserName, strPassword));
        }
    };

    // FORGOT BUTTON LISTENER
    OneShotOnClickListener forgotPwdCreateClickListener = new OneShotOnClickListener(500) {

        @Override
        public void onOneShotClick(View v) {
            launchSubActivity(ForgotPasswordActivity.class);

        }
    };

    // CREATE NEW ACCOUNT BUTTON LISTENER
    OneShotOnClickListener newUserCreateClickListener = new OneShotOnClickListener(500) {

        @Override
        public void onOneShotClick(View v) {

            launchSubActivity(CreateNewAccountActivity.class);
        }
    };

    // FACEBOOK BUTTON LISTENER
    OneShotOnClickListener facebookClickListener = new OneShotOnClickListener(500) {

        @Override
        public void onOneShotClick(View v) {

        }
    };

    // GOOGLE PLUS BUTTON LISTENER
    OneShotOnClickListener googlePlusClickListener = new OneShotOnClickListener(500) {

        @Override
        public void onOneShotClick(View v) {
			/*
			SharedPreferences sp1=getContext().getSharedPreferences("Login", 0);
			String unm=sp1.getString("Unm", null);
			String pass = sp1.getString("Psw", null);
			Toast.makeText(getApplicationContext(), unm+" "+pass, Toast.LENGTH_LONG).show();
*/
            //	googlePlusLogin();


        }
    };
    // REMEMBER ME UNCHECKED LISTENER
    OneShotOnClickListener unCheckedClickListener = new OneShotOnClickListener(500) {
        @Override
        public void onOneShotClick(View v) {
            unCheckedRemMeImageV.setVisibility(View.INVISIBLE);
            unCheckedRemMeImageV.setVisibility(View.GONE);
            checkedRemMeImageV.setVisibility(View.VISIBLE);

        }
    };

    // REMEMBER ME UNCHECKED LISTENER
    OneShotOnClickListener checkedClickListener = new OneShotOnClickListener(500) {
        @Override
        public void onOneShotClick(View v) {


            checkedRemMeImageV.setVisibility(View.INVISIBLE);
            checkedRemMeImageV.setVisibility(View.GONE);
            unCheckedRemMeImageV.setVisibility(View.VISIBLE);
        }
    };

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context
				.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    // REMEMBER ME CHECKED LISTENER

    // *************************************//
    // ---WEB SERVICES METHODS---
    // *************************************//

    // ************************************************************************************************************************************************//

    // ****************************************//
    // ---ACCOUNT SEND DATA SERVICE METHODS----//
    // ****************************************//

    // ---LOGIN---
    protected void setLogInAccount(LoginModel loginModel) {
        AccountProcessesServiceSL accountServiceSL = new AccountProcessesServiceSL(getContext(),
				accountProcessesServiceListener, R.string.root_service_url);
        accountServiceSL.setLogInAccount(loginModel);
        showProgress(getString(R.string.ItbarxConnecting));
    }

    // ---SIGNUP---
    protected void setSignUpAccount(AccountSignUpModel signUpModel) {
        AccountProcessesServiceSL accountServiceSL = new AccountProcessesServiceSL(getContext(),
				accountProcessesServiceListener, R.string.root_service_url);
        accountServiceSL.setSignUpAccount(signUpModel);
        showProgress(getString(R.string.ItbarxConnecting));

    }

    // ---FORGOT---
    protected void setForgotAccount(AccountForgotSendMailModel accountForgotSendMailModel) {
        AccountProcessesServiceSL accountServiceSL = new AccountProcessesServiceSL(getContext(),
				accountProcessesServiceListener, R.string.root_service_url);
        accountServiceSL.setForgotAccount(accountForgotSendMailModel);

    }

    // ---FORGOT---
    protected void setChangePassByCodeAccount(AccountSendEmailCodeModel accountSendEmailCodeModel) {
        AccountProcessesServiceSL accountServiceSL = new AccountProcessesServiceSL(getContext(),
				accountProcessesServiceListener, R.string.root_service_url);
        accountServiceSL.setChangePassByCodeAccount(accountSendEmailCodeModel);

    }

    // ---GET EDIT PROFILE---
    protected void setGetEditProfile(GetEditProfileIdModel getEditProfileIdModel) {
        AccountProcessesServiceSL accountServiceSL = new AccountProcessesServiceSL(getContext(),
				accountProcessesServiceListener, R.string.root_service_url);
        accountServiceSL.setGetEditProfile(getEditProfileIdModel);

    }

    // ---EDIT PROFILE---
    protected void setEditProfile(EditProfileModel editProfileModel) {
        AccountProcessesServiceSL accountServiceSL = new AccountProcessesServiceSL(getContext(),
				accountProcessesServiceListener, R.string.root_service_url);
        accountServiceSL.setEditProfile(editProfileModel);

    }

    // --- DELETE PROFILE---
    protected void setDeleteProfile(DeleteProfileModel deleteProfileModel) {
        AccountProcessesServiceSL accountServiceSL = new AccountProcessesServiceSL(getContext(),
				accountProcessesServiceListener, R.string.root_service_url);
        accountServiceSL.setDeleteProfile(deleteProfileModel);


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
        }

        @Override
        public void onComplete(ResponseEventModel<String> onComplete) {
            dismissProgress();

        }

        @Override
        public void logInAccount(AccountGetUserByLoginInfoModel loginModelResponse) {
            dismissProgress();

            ItbarxGlobal global = ItbarxGlobal.setInstance(LoginActivity.this);
            global.setAccountModel(loginModelResponse);
            SharedPreferences sp = getSharedPreferences("Login", 0);
            SharedPreferences.Editor Ed = sp.edit();
            Ed.putString("Unm", strUserName);
            Ed.putString("Psw", strPassword);
            Ed.commit();
            closeKeyboard();
            launchSubActivity(TabContainer.class);
            // cağırmak
            // ItbarxGlobal.getInstance().getAccountModel().

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

    //********************************************************************************************//
    //
    //					--	FACEBOOK LOGIN LISTENERS AND CODES --
    //
    //********************************************************************************************//


    //********************************************************************************************//
    //
    //					--	GOOGLE PLUS LOGIN LISTENERS AND CODES --
    //
    //********************************************************************************************//


/*

	protected void onStart() {
		Log.e("*TEST*","onStart"+"");
		super.onStart();
		mGoogleApiClient.connect();
	}

	protected void onStop() {
		Log.e("*TEST*","onStop"+"");
		super.onStop();
		if (mGoogleApiClient.isConnected()) {
			mGoogleApiClient.disconnect();
		}
	}

	private void resolveSignInError() {
		Log.e("*TEST*","resolveSignInError"+"");
		if (mConnectionResult.hasResolution()) {
			try {
				mIntentInProgress = true;
				mConnectionResult.startResolutionForResult(this, RC_SIGN_IN);
			} catch (IntentSender.SendIntentException e) {
				mIntentInProgress = false;
				mGoogleApiClient.connect();
			}
		}
	}

	@Override
	public void onActivityResult(int correlationId, int resultCode, Intent data) {
		Log.e("*TEST*","onActivityResult"+"");
		super.onActivityResult(correlationId, resultCode, data);
		Log.d("TEST ", correlationId + " regCode " + resultCode + " resultCode . !!");

		switch (resultCode) {
			case RC_SIGN_IN:
				if (resultCode == RESULT_OK) {
					signedInUser = false;

				}
				mIntentInProgress = false;
				if (!mGoogleApiClient.isConnecting()) {
					mGoogleApiClient.connect();
				}
				break;
		}
	}





/*
	private void updateProfile(boolean isSignedIn) {
		if (isSignedIn) {
			signinFrame.setVisibility(View.GONE);
			profileFrame.setVisibility(View.VISIBLE);

		} else {
			signinFrame.setVisibility(View.VISIBLE);
			profileFrame.setVisibility(View.GONE);
		}
	}
	*/
/*
	private void getProfileInformation() {
		AccountGetUserByLoginInfoModel accouuntModel=new AccountGetUserByLoginInfoModel();

		Log.e("*TEST*","getProfileInformation"+"");
		try {
			if (Plus.PeopleApi.getCurrentPerson(mGoogleApiClient) != null) {
				Person currentPerson = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
				String personName = currentPerson.getDisplayName();
				String personPhotoUrl = currentPerson.getImage().getUrl();
				String email = Plus.AccountApi.getAccountName(mGoogleApiClient);

				//username.setText(personName);
				//emailLabel.setText(email);

				new LoadProfileImage().execute(personPhotoUrl);

				// update profile frame with new info about Google Account
				// profile
				//updateProfile(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ItbarxGlobal global = ItbarxGlobal.setInstance(LoginActivity.this);

	//	global.setAccountModel(accouuntModel);
		launchSubActivity(TabContainer.class);

	}






	private void googlePlusLogin() {
		Log.e("*TEST*","googlePlusLogin"+"");
		if (!mGoogleApiClient.isConnecting()) {
			signedInUser = true;
			resolveSignInError();
		}
	}




	GoogleApiClient.OnConnectionFailedListener failedListener = new GoogleApiClient
	.OnConnectionFailedListener() {


		@Override
		public void onConnectionFailed(ConnectionResult result) {
			Log.e("*TEST*","onConnectionFailed"+"");
			if (!result.hasResolution()) {
				GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(), LoginActivity.this,
				0).show();
				return;
			}

			if (!mIntentInProgress) {
				// store mConnectionResult
				mConnectionResult = result;

				if (signedInUser) {
					resolveSignInError();
				}
			}
		}
	};

	GoogleApiClient.ConnectionCallbacks connectionCallbacks = new GoogleApiClient.ConnectionCallbacks() {
		@Override
		public void onConnected(Bundle arg0) {
			Log.e("*TEST*","onConnected"+"");
			signedInUser = false;
			Toast.makeText(LoginActivity.this, "Connected", Toast.LENGTH_LONG).show();
			getProfileInformation();
		}

		@Override
		public void onConnectionSuspended(int cause) {
			Log.e("*TEST*","onConnectionSuspended"+"");
			mGoogleApiClient.connect();
		//	updateProfile(false);
		}
	};

	// download Google Account profile image, to complete profile
	private class LoadProfileImage extends AsyncTask<String, Void, Bitmap> {

		protected Bitmap doInBackground(String... urls) {
			Log.e("*TEST*","LoadProfileImage doInBackground"+"");
			String urldisplay = urls[0];
			Bitmap mIcon11 = null;
			try {
				InputStream in = new java.net.URL(urldisplay).openStream();
				mIcon11 = BitmapFactory.decodeStream(in);
			} catch (Exception e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			return mIcon11;
		}

		protected void onPostExecute(Bitmap result) {
			//bmImage.setImageBitmap(result);
			Log.e("*TEST*","LoadProfileImage onPostExecute"+"");
			ItbarxGlobal.setGooglePlusPhoto(result);
		}
	}


	public static void disConnect(){
		Log.e("*TEST*","disConnect"+"");
			Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
			mGoogleApiClient.disconnect();

	}

	public static boolean isConnect(){
		return mGoogleApiClient.isConnected();

	}
*/

}
