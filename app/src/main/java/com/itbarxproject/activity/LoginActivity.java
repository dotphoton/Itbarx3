package com.itbarxproject.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;


import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import com.itbarxproject.R;
import com.itbarxproject.application.ItbarxGlobal;
import com.itbarxproject.common.UserSharedPrefrences;
import com.itbarxproject.custom.component.ButtonBold;
import com.itbarxproject.custom.component.EditTextRegular;
import com.itbarxproject.custom.component.TextViewBold;
import com.itbarxproject.custom.component.TextViewRegular;
import com.itbarxproject.service.ResponseEventModel;
import com.itbarxproject.service.error.BarxErrorModel;
import com.itbarxproject.listener.AccountProcessesServiceListener;
import com.itbarxproject.listener.OneShotOnClickListener;
import com.itbarxproject.model.account.AccountForgotSendMailModel;
import com.itbarxproject.model.account.AccountGetUserByLoginInfoModel;
import com.itbarxproject.model.account.AccountSendEmailCodeModel;
import com.itbarxproject.model.account.AccountSignUpModel;
import com.itbarxproject.model.account.DeleteProfileModel;
import com.itbarxproject.model.account.EditProfileModel;
import com.itbarxproject.model.account.GetEditProfileIdModel;
import com.itbarxproject.model.account.GetEditProfileModel;
import com.itbarxproject.model.account.LoginModel;
import com.itbarxproject.sl.AccountProcessesServiceSL;
import com.itbarxproject.utils.TextSizeUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.Arrays;
import java.util.Random;

public class LoginActivity extends Activity {

    private ProgressDialog mProgressDialog;

    private static final int RC_SIGN_IN = 1000;
    private CallbackManager callbackManager;
    ProfileTracker profileTracker;

    private boolean mIntentInProgress;
    private boolean signedInUser;
    private String dt;
    private ButtonBold btnLogIn, btnCreateNewAcc, btnForgotPwd;
    //, btnFacebook, btnGooglePlus;
    private TextViewRegular txtToolbar;
    private TextViewBold txtViewOr;//txtViewRembMe,
    private EditTextRegular edtUserName, edtPassword;
    private String strUserName, strPassword;

   // private ImageView unCheckedRemMeImageV, checkedRemMeImageV;
   // private SignInButton btnSignInButton;
    private LoginButton btnfaceLoginButton;
    private String facebookEmail;

    private ImageView unCheckedRemMeImageV, checkedRemMeImageV;

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_login_screen);
        btnfaceLoginButton = (LoginButton) findViewById(R.id.facebook_login_button);
        btnfaceLoginButton.setReadPermissions(Arrays.asList("email"));

        if(UserSharedPrefrences.getToken(getContext())!=null && !UserSharedPrefrences.getToken(getContext()).equalsIgnoreCase(""))
        {
            LoginManager.getInstance().logOut();
            UserSharedPrefrences.clearLoginData(getContext());
        }
        edtUserName = (EditTextRegular) findViewById(R.id.login_activity_screen_username_edittext);
        edtPassword = (EditTextRegular) findViewById(R.id.login_activity_screen_password_edittext);
        btnLogIn = (ButtonBold) findViewById(R.id.login_activity_screen_login_button);
        btnForgotPwd = (ButtonBold) findViewById(R.id.login_activity_screen_forgotpassword_button);
        btnCreateNewAcc = (ButtonBold) findViewById(R.id
                .login_activity_screen_createnewaccount_button);

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.itbarxproject",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
              String data = Base64.encodeToString(md.digest(), Base64.DEFAULT);
                Log.d("KeyHash:",data );
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }

        //btnfaceLoginButton = (LoginButton) findViewById(R.id
        // .login_activity_screen_facebook_signIn_button);
        txtViewOr = (TextViewBold) findViewById(R.id.login_activity_screen_or_plaintext);

        txtToolbar = (TextViewRegular) findViewById(R.id.login_activity_screen_toolbar_textView);



        strUserName = "";
        strPassword = "";

        //SET FONTS
        setCompFonts();
        logInStart();
        forgotStart();
        createNewAccStart();

        btnfaceLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                String accessToken = loginResult.getAccessToken().getToken();
                    UserSharedPrefrences.saveToken(getContext(),accessToken);
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                    @Override
                    public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {
                        Bundle bFacebookData = getFacebookData(jsonObject);
                        UserSharedPrefrences.saveBundle(getContext(),bFacebookData);
                        setOtherSignup(bFacebookData.getString("email"), bFacebookData.getString("first_name"),
                                UserSharedPrefrences.getToken(getContext())
                                , "2", bFacebookData.getString("idFacebook"));
                        showProgress(getString(R.string.ItbarxConnecting));
                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, first_name, last_name, email,gender, birthday, location"); // Parámetros que pedimos a facebook
                request.setParameters(parameters);
                request.executeAsync();

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException e) {

            }
        });
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(
                    Profile oldProfile,
                    Profile currentProfile) {
                // App code
            }
        };
    }



    protected Context getContext() {
        // TODO Auto-generated method stub
        return LoginActivity.this;
    }


    public void showProgress(String msg) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) dismissProgress();

        mProgressDialog = ProgressDialog.show(getContext(), null, msg);
    }

    public void dismissProgress() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }


    //----------------------------------------------------------------------------------------------------------------------
    //LOGIN COMPONENTS SETUP METHODS
    private void setCompFonts() {
        btnLogIn.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getButtonTextSize());
        btnForgotPwd.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getButtonTextSize());
        btnCreateNewAcc.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getButtonTextSize());
        /*
        btnFacebook.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getButtonTextSize());
        btnGooglePlus.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getButtonTextSize());
        txtViewOr.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getLoginOrTextSize());
        txtViewRembMe.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil
				.getLoginRememberMeTextSize());
        */
        //txtToolbar.setTextSize(TypedValue.COMPLEX_UNIT_SP, TextSizeUtil.getToolbarTextSize());
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


    }

    // FACEBOOK BUTTON EVENTS
    private void facebookStart() {

       // btnFacebook.setOnClickListener(facebookClickListener);
    }

    private void checkedRemMeStart() {
       // checkedRemMeImageV.setOnClickListener(checkedClickListener);
    }

    private void unCheckedRemMeStart() {
        //unCheckedRemMeImageV.setOnClickListener(unCheckedClickListener);
    }

    // ************************//
    // ---FACEBOOK LISTENERS---
    // ************************//

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        callbackManager.onActivityResult(requestCode, resultCode, data);
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
  /*
    OneShotOnClickListener unCheckedClickListener = new OneShotOnClickListener(500) {
        @Override
        public void onOneShotClick(View v) {
            unCheckedRemMeImageV.setVisibility(View.INVISIBLE);
            unCheckedRemMeImageV.setVisibility(View.GONE);
            checkedRemMeImageV.setVisibility(View.VISIBLE);

        }
    };
*/
    // REMEMBER ME UNCHECKED LISTENER
  /*
    OneShotOnClickListener checkedClickListener = new OneShotOnClickListener(500) {
        @Override
        public void onOneShotClick(View v) {


            checkedRemMeImageV.setVisibility(View.INVISIBLE);
            checkedRemMeImageV.setVisibility(View.GONE);
            unCheckedRemMeImageV.setVisibility(View.VISIBLE);
        }
    };
*/

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

    // --- Other signup---
    protected void setOtherSignup(String Email,String Name, String Token,String LoginCategory,String IdInfo) {
        AccountProcessesServiceSL accountServiceSL = new AccountProcessesServiceSL(getContext(),
                accountProcessesServiceListener, R.string.root_service_url);
        accountServiceSL.setOtherSignup(Email,Name,Token,LoginCategory,IdInfo);


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
            ItbarxGlobal.setUserId(loginModelResponse.getUserID());


            UserSharedPrefrences.saveLogOnModel(getContext(),loginModelResponse);
            UserSharedPrefrences.saveUserName(getContext(), strUserName);
            UserSharedPrefrences.savePassword(getContext(),strPassword);

            UserSharedPrefrences.saveLogIn(getContext());

            closeKeyboard();
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
    public void showAlert(String msg) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(null).setMessage(msg).setCancelable(false).setIcon(android.R.drawable.ic_dialog_alert).setPositiveButton(getString(R.string.Ok), new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).create().show();
    }
    public void launchSubActivity(Class subActivityClass) {
        Intent i = new Intent(this, subActivityClass);
        startActivity(i);
    }
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

    protected void closeKeyboard() {
        View view = this.getCurrentFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context
                .INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(getContext(), SplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("Exit me", true);
            startActivity(intent);
            finish();
            return  true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private Bundle getFacebookData(JSONObject object) {

    try {
        Bundle bundle = new Bundle();
        String id = object.getString("id");

        try {
            URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=150");
            Log.i("profile_pic", profile_pic + "");
            bundle.putString("profile_pic", profile_pic.toString());

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }

        bundle.putString("idFacebook", id);
        if (object.has("first_name"))
            bundle.putString("first_name", object.getString("first_name"));
        if (object.has("last_name"))
            bundle.putString("last_name", object.getString("last_name"));
        if (object.has("email"))
            bundle.putString("email", object.getString("email"));
        if (object.has("gender"))
            bundle.putString("gender", object.getString("gender"));
        if (object.has("birthday"))
            bundle.putString("birthday", object.getString("birthday"));
        if (object.has("location"))
            bundle.putString("location", object.getJSONObject("location").getString("name"));

        return bundle;
    } catch (JSONException e) {
        e.printStackTrace();
    }
    return  null;
}
    @Override
public void onDestroy() {
    super.onDestroy();
    profileTracker.stopTracking();
}
}
