package com.itbarx.error.sl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;

import com.itbarx.R;
import com.itbarx.error.common.ResponseServiceModel;
import com.itbarx.common.ServiceUtil;
import com.itbarx.error.listener.BarxClientListener;
import com.itbarx.error.listener.BarxErrorListener;
import com.itbarx.error.model.BarxErrorModel;
import com.itbarx.error.util.BarxErrorHelper;

public class BaseGetServiceClient<T> extends AsyncTask<String, Void, String> {

	/* LOCK CONFIG */
	private final ReentrantLock lock = new ReentrantLock();
	private final Condition tryAgain = lock.newCondition();
	private volatile boolean finished = false;

	private ArrayList<BarxClientListener<T>> _listeners = new ArrayList<BarxClientListener<T>>();
	private ArrayList<BarxErrorListener> errorlisteners = new ArrayList<BarxErrorListener>();
	Boolean serviceStatus = false;
	Context context = null;
	String _serviceName = "";
	String errorMessage = "";
	InputStream inputStream = null;
	String _methodName = null;
	boolean isArrived = false;
	private boolean isQueryString = false;
	private String RequestBody = null;
	private boolean IsGzipEncode = false;
	private String queryString;
	private List<NameValuePair> nameValueParams;

	public BaseGetServiceClient(Context contextInstance, String serviceName) {

		context = contextInstance;
		_serviceName = serviceName;

	}

	public BaseGetServiceClient(Context contextInstance, String serviceName,
			List<NameValuePair> params) {

		context = contextInstance;
		_serviceName = serviceName;
		nameValueParams = params;

	}

	public String readJSONFeed(String URL, String methodName) throws Exception {

		String uri = URL;
		serviceStatus = false;
		errorMessage = "";
		String responseString = "";
		try {

			if (methodName != null && !methodName.equalsIgnoreCase("")) {
				_methodName = methodName;
				uri += "/" + methodName;
			}

			HttpParams httpParameters = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParameters, Integer
					.parseInt(context.getString(R.string.httpCallTimeout)));
			HttpConnectionParams.setSoTimeout(httpParameters, Integer
					.parseInt(context.getString(R.string.httpCallTimeout)));

			DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);

			if (isQueryString()) {
				uri += getQueryString();
			} else {
				if (nameValueParams != null && nameValueParams.size() > 0) {
					String urlParams = "";

					for (NameValuePair iter : nameValueParams) {

						if (iter.getValue() == null
								|| iter.getValue().equals("") == true) {
							urlParams += "/%20";
						} else {
							urlParams += "/" + iter.getValue();
						}
					}

					uri += urlParams;

				}
			}

			errorMessage += uri;

			HttpGet httpGet = new HttpGet(uri);
			httpGet.setHeader("Content-Type", "application/json; charset=utf-8");

			HttpResponse response = httpClient.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();

			if (statusCode == 200) {
				isArrived = true;
				HttpEntity entity = response.getEntity();
				inputStream = entity.getContent();

				try {
					responseString = ServiceUtil
							.EncodeUTF8InputStream(inputStream);
					serviceStatus = true;
				} catch (Exception ex) {
					errorMessage += "Web servisden gelen data problemi";
					// throw new KSException(");
				}

			} else {
				errorMessage += "http Status Code : " + statusCode;
			}
		} catch (Exception ex) {
			errorMessage = "Web servis hatası";
			// throw new
			// KSException("Web servis ï¿½aï¿½rï¿½sï¿½ sï¿½rasï¿½nda hata oluï¿½tu",
			// ex, ErrorType.getJSONFromUrl_HTTP);
		}

		// temizle json disi datayi

		int findHtmlIndex = responseString.indexOf("<!DOCTYPE html>");
		if (findHtmlIndex != -1)
			responseString = responseString.substring(0, findHtmlIndex);
		return responseString;
	}

	protected String doInBackground(String... urls) {

		String result = "";
		try {
			result = readJSONFeed(urls[0], urls[1]);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	protected void onPostExecute(String result) {

		_fireResponseEvent(result);

	}

	public String getServiceMethodName() {
		return this._methodName;
	}

	public String getServiceName() {
		return this._serviceName;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	/* Listener members */

	/* ERROr Listener Memebers */

	public synchronized void removeServiceClientListener(
			BarxErrorListener l) {
		errorlisteners.remove(l);
	}

	public synchronized void addErrorErrorServiceClientListener(
			BarxErrorListener l) {
		errorlisteners.add(l);
	}

	public synchronized void removeServiceClientListener(
			BarxClientListener<T> l) {
		_listeners.remove(l);
	}

	public synchronized void addServiceClientListener(BarxClientListener<T> l) {
		_listeners.add(l);
	}

	private synchronized void _fireResponseEvent(String responseData) {
		ResponseServiceModel<T> responseEvent = new ResponseServiceModel<T>(
				this, this._serviceName, responseData, serviceStatus,
				errorMessage);
		responseEvent.setMethodName(getServiceMethodName());
		responseEvent.setIsArrived(isArrived);
		responseEvent.setSentParams(nameValueParams);
		if (serviceStatus) {
			BarxErrorHelper helper = new BarxErrorHelper();
			BarxErrorModel serviceErrorModel = helper
					.getServiceErrorModel(responseEvent.getResponseData());
			// TODO:Diyet uzmani hatası
			if (serviceErrorModel != null) {
				for (BarxErrorListener errorlistener : errorlisteners) {
					errorlistener.onError(serviceErrorModel);
				}

			}
			/*
			else {
				for (BarxClientListener<T> listener : _listeners) {
					listener.onGETReceive(responseEvent);
				}
			}
*/
		} else {
			BarxErrorModel serviceErrorModel = new BarxErrorModel();
			serviceErrorModel.setErrMessage(responseEvent.getErrorMessage());
			for (BarxErrorListener errorlistener : errorlisteners) {
				errorlistener.onError(serviceErrorModel);
			}
		}

	}

	public boolean isQueryString() {
		return isQueryString;
	}

	public void setIsQueryString(boolean isQueryString) {
		this.isQueryString = isQueryString;
	}

	public boolean isIsGzipEncode() {
		return IsGzipEncode;
	}

	public void setIsGzipEncode(boolean isGzipEncode) {
		IsGzipEncode = isGzipEncode;
	}

}
