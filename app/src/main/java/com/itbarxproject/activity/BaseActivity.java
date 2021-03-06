package com.itbarxproject.activity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Random;



import com.itbarxproject.R;
import com.itbarxproject.common.UserSharedPrefrences;

import android.app.Activity;
import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

public abstract class BaseActivity extends ActivityGroup {

	protected abstract int getLayoutResourceId();

	protected abstract Context getContext();

	protected abstract void initViews();

	protected abstract void exceptionHandler();

	private ProgressDialog mProgressDialog;

	private  static String userIdOtherProfileScreen;

	/**
	 * holds the map of callbacks
	 */
	protected HashMap<Integer, ResultCallbackIF> _callbackMap = new HashMap<Integer, ResultCallbackIF>();
	protected HashMap<Integer, Class> _liveChildActivity = new HashMap<Integer, Class>();

	@Override public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//exceptionHandler();
		setContentView(getLayoutResourceId());
		initViews();
	}
	public void   setInitFaceBookSdk(boolean isFaceBookInit)
	{

	}
	@Override public void onActivityResult(int correlationId, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		try {
			ResultCallbackIF callback = _callbackMap.get(correlationId);

			switch (resultCode) {
				case Activity.RESULT_CANCELED:
					callback.resultCancel(data);
					_liveChildActivity.remove(correlationId);
					_callbackMap.remove(correlationId);
					break;
				case Activity.RESULT_OK:
					callback.resultOk(data);
					_liveChildActivity.remove(correlationId);
					_callbackMap.remove(correlationId);
					break;
				default:
					writeLog("Base Activity onActivityResult", "correlationId bulunamadı");

			}
		} catch (Exception e) {
			writeLog("Base Activity onActivityResult", "Problem processing result from sub-activity");
		}
	}

	public void showAlert(String msg) {
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(getResources().getString(R.string.app_name)).setMessage(msg).setCancelable(false).setIcon(android.R.drawable.ic_dialog_alert).setPositiveButton(getString(R.string.Ok), new DialogInterface.OnClickListener() {
			@Override public void onClick(DialogInterface dialogInterface, int i) {
				dialogInterface.dismiss();
			}
		}).create().show();
	}

	public Dialog showAlert(String msg, String PositiveBtnText, DialogInterface.OnClickListener positiveListener) {

		Dialog dialog = null;
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		dialog = builder.setTitle(getResources().getString(R.string.app_name)).setMessage(msg).setCancelable(false).setIcon(android.R.drawable.ic_dialog_alert).setPositiveButton(PositiveBtnText, positiveListener).create();
		return dialog;

	}

	public Dialog showAlert(String msg, String PositiveBtnText, DialogInterface.OnClickListener positiveListener, String NegativeText, DialogInterface.OnClickListener negativeListener) {

		Dialog dialog = null;
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		//dialog = builder.setTitle(getResources().getString(R.string.app_name)).
				//setMessage(msg).setCancelable(false).setIcon(android.R.drawable.ic_dialog_alert)
			//	.setNegativeButton(NegativeText, negativeListener).
			//			setPositiveButton(PositiveBtnText, positiveListener).create();
		dialog = builder.setTitle(null)
				.setMessage(msg).setCancelable(false).setIcon(android.R.drawable.ic_dialog_alert)
				.setNegativeButton(NegativeText, negativeListener)
				.setPositiveButton(PositiveBtnText, positiveListener).create();
		return dialog;

	}

	protected void showToast(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}

	public void showProgress(String msg) {
		if (mProgressDialog != null && mProgressDialog.isShowing()) dismissProgress();
		mProgressDialog = ProgressDialog.show(this, null, msg);
		//mProgressDialog = ProgressDialog.show(this, getResources().getString(R.string.app_name), msg);
/*
		new Handler().postDelayed(new Runnable() {
			public void run() {
				dismissProgress();
			}
		}, 45000);
*/

	}

	public void dismissProgress() {
		if (mProgressDialog != null) {
			mProgressDialog.dismiss();
			mProgressDialog = null;
		}
	}

	protected void writeLog(String key, String value) {
		Log.d(key, value);
	}

	public void launchSubActivity(Class subActivityClass) {
		Intent i = new Intent(this, subActivityClass);
		Random rand = new Random();
		int correlationId = rand.nextInt();

		ResultCallbackIF resultCallbackIF = new ResultCallbackIF() {
			@Override public void resultOk(Intent data) {
				// TODO Auto-generated method stub
				Log.i("launchSubActivity", "subactivity completed successfully, result=");
			}

			@Override public void resultCancel(Intent data) {
				// TODO Auto-generated method stub
				Log.i("launchSubActivity", "subactivity was cancelled, result=");
			}
		};

		_liveChildActivity.put(correlationId, subActivityClass);
		_callbackMap.put(correlationId, resultCallbackIF);
		startActivity(i);
	}
	public void launchSubActivityAddString(Class subActivityClass,String key,String value) {
		Intent i = new Intent(this, subActivityClass);
		i.putExtra(key,value);
		Random rand = new Random();
		int correlationId = rand.nextInt();

		ResultCallbackIF resultCallbackIF = new ResultCallbackIF() {
			@Override public void resultOk(Intent data) {
				// TODO Auto-generated method stub
				Log.i("launchSubActivity", "subactivity completed successfully, result=");
			}

			@Override public void resultCancel(Intent data) {
				// TODO Auto-generated method stub
				Log.i("launchSubActivity", "subactivity was cancelled, result=");
			}
		};

		_liveChildActivity.put(correlationId, subActivityClass);
		_callbackMap.put(correlationId, resultCallbackIF);
		startActivity(i);
	}
	public void launchSubActivityAddExtra(Class subActivityClass, String key, Serializable serializableObje) {
		Intent i = new Intent(this, subActivityClass);
		i.putExtra(key, serializableObje);
		Random rand = new Random();
		int correlationId = rand.nextInt();

		ResultCallbackIF resultCallbackIF = new ResultCallbackIF() {
			@Override public void resultOk(Intent data) {
				// TODO Auto-generated method stub
				Log.i("launchSubActivity", "subactivity completed successfully, result=");
			}

			@Override public void resultCancel(Intent data) {
				// TODO Auto-generated method stub
				Log.i("launchSubActivity", "subactivity was cancelled, result=");
			}
		};

		_liveChildActivity.put(correlationId, subActivityClass);
		_callbackMap.put(correlationId, resultCallbackIF);
		startActivity(i);
	}

	public void launchSubActivityAddStringExtra(Class subActivityClass, List<Pair<String,String>> extraDictionary) {
		Intent i = new Intent(this, subActivityClass);
		for (Pair<String,String> nameValue : extraDictionary) {
			i.putExtra(nameValue.first, nameValue.second);
		}

		Random rand = new Random();
		int correlationId = rand.nextInt();

		ResultCallbackIF resultCallbackIF = new ResultCallbackIF() {
			@Override public void resultOk(Intent data) {
				// TODO Auto-generated method stub
				Log.i("launchSubActivity", "subactivity completed successfully, result=");
			}

			@Override public void resultCancel(Intent data) {
				// TODO Auto-generated method stub
				Log.i("launchSubActivity", "subactivity was cancelled, result=");
			}
		};

		_liveChildActivity.put(correlationId, subActivityClass);
		_callbackMap.put(correlationId, resultCallbackIF);
		startActivity(i);
	}

	public void launchSubActivityForResult(Class subActivityClass, ResultCallbackIF callback) {

		Intent i = new Intent(this, subActivityClass);
		Random rand = new Random();
		int correlationId = rand.nextInt();
		_liveChildActivity.put(correlationId, subActivityClass);
		_callbackMap.put(correlationId, callback);
		startActivityForResult(i, correlationId);

	}

	public void launchSubActivityForResult(Class subActivityClass) {

		Intent i = new Intent(this, subActivityClass);
		Random rand = new Random();
		int correlationId = rand.nextInt();

		ResultCallbackIF resultCallbackIF = new ResultCallbackIF() {
			@Override public void resultOk(Intent data) {
				// TODO Auto-generated method stub
				Log.i("launchSubActivity", "subactivity completed successfully, result=");
			}

			@Override public void resultCancel(Intent data) {
				// TODO Auto-generated method stub
				Log.i("launchSubActivity", "subactivity was cancelled, result=");
			}
		};

		_liveChildActivity.put(correlationId, subActivityClass);
		_callbackMap.put(correlationId, resultCallbackIF);
		startActivityForResult(i, correlationId);

	}
	protected void closeKeyboard() {
		View view = this.getCurrentFocus();
		InputMethodManager imm = (InputMethodManager) getSystemService(Context
				.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}
	public  interface ResultCallbackIF {

		public void resultOk(Intent data);

		public void resultCancel(Intent data);

	}

	public void areYoulogOffAccount()
	{
		Dialog dialog = showAlert(getString(R.string.are_you_logout),getString(R.string.Yes),new Dialog.OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				UserSharedPrefrences.saveLogInClear(getContext());
				UserSharedPrefrences.clearLoginData(getContext());
				Intent intent = new Intent(getContext(), SplashActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

				startActivity(intent);
				finish();
			}
		},getString(R.string.No),null);
		dialog.show();
	}
	public void closeApp()
	{
		Intent intent = new Intent(getContext(), SplashActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("Exit me", true);
		startActivity(intent);
		finish();
	}
	public void areYouExitApp()
	{
		Dialog dialog = showAlert(getString(R.string.are_you_exit),getString(R.string.Yes),new Dialog.OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int which) {
				//android.os.Process.killProcess(android.os.Process.myPid());

				closeApp();
			}
		},getString(R.string.No),null);
		dialog.show();
	}
	protected interface ConfigurationChangedListener {
		void ConfigurationChanged(Configuration config);
	}

	public static String getUserIdOtherProfileScreen() {
		return userIdOtherProfileScreen;
	}

	public static void setUserIdOtherProfileScreen(String userIdOtherProfileScreen) {
		BaseActivity.userIdOtherProfileScreen = userIdOtherProfileScreen;
	}
}
