package com.itbarx.service;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.itbarx.common.ServiceUtil;
import com.itbarx.service.error.BarxErrorHelper;
import com.itbarx.service.error.BarxErrorListener;
import com.itbarx.service.error.BarxErrorModel;
import com.itbarx.service.BarxPostListener;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Add a class header comment!
 */
public class BasePostAsyncTask<T> extends AsyncTask<String, Void, String> {

	private List<BarxPostListener<T>> postListeners;
	private List<BarxErrorListener> errorlisteners;
	private Context context;
	private String serviceName;
	private String errorMessage;
	private String methodName;
	private String sendData;
	private InputStream inputStream;
	boolean isArrived;
	boolean serviceStatus;

	private String requestBody;


	{
		postListeners = new ArrayList<>();
		errorlisteners = new ArrayList<>();
		serviceName = "";
		methodName = "";
		sendData = "";

	}

	public BasePostAsyncTask(Context contextInstance, String serviceName, String requestBody) {

		this.context = contextInstance;
		this.serviceName = serviceName;
		this.requestBody = requestBody;

	}

	@Override protected String doInBackground(String... params) {
		String result = "";
		try {

			result = readPostFeed(params[0], params[1]);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override protected void onPostExecute(String result) {
		this.fireResponseEvent(result);
	}

	public String readPostFeed(String URL, String uriParam) throws Exception {

		String uri = URL;
		String responseData = "";
		if (uriParam != null && !uriParam.equalsIgnoreCase("")) {

			uri += "/" + uriParam;
			this.methodName = uriParam;
		}

		String sendJson = this.requestBody;
		URL url = new URL(uri);
		HttpURLConnection connection=null;
		int code = -1;
		try {
			connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(60000);
			connection.setReadTimeout(60000);
			connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.connect();
			OutputStream os = connection.getOutputStream();
			os.write(sendJson.getBytes("UTF-8"));
			os.close();
			code = connection.getResponseCode();

			if (code == 200 || code == 400) {
				isArrived = true;
				try {
					inputStream = new BufferedInputStream(connection.getInputStream());
					responseData = org.apache.commons.io.IOUtils.toString(inputStream, "UTF-8");

					//responseData = IOUtils.toString(inputStream, "UTF-8");
					//responseData = ServiceUtil.EncodeUTF8InputStream(inputStream);
					serviceStatus = true;
				} catch (Exception e) {
					Log.e("status code", code + " " + e.getMessage() +" service status : "+serviceStatus);
					errorMessage += code + " " + e.getMessage()+" service status : "+serviceStatus;

				}
							}

		} catch (Exception e) {
			Log.e("PostAsyncTask", code + " " + e.getMessage()+" service status : "+serviceStatus );
			errorMessage += code + " " + e.getMessage() +" service status : "+serviceStatus;
		}
		if(null!=inputStream)
			inputStream.close();
		if(null!=connection)
			connection.disconnect();


		return responseData;

	}

	private synchronized void fireResponseEvent(String responseData) {

		ResponseEventModel<T> responseEvent = new ResponseEventModel<T>(this);
		responseEvent.setServiceName(this.serviceName);
		responseEvent.setSendData(this.sendData);
		responseEvent.setErrorMessage(this.errorMessage);
		responseEvent.setStream(this.inputStream);
		responseEvent.setIsArrived(this.isArrived);
		responseEvent.setServiceStatus(this.serviceStatus);
		responseEvent.setMethodName(this.methodName);
		responseEvent.setResponseData(responseData);

		if (serviceStatus) {

			BarxErrorHelper errorHelper = new BarxErrorHelper();
			BarxErrorModel errorModel = errorHelper.getServiceErrorModel(responseEvent.getResponseData());
			if (errorModel == null || errorModel.getIsError()) {
				for (BarxErrorListener errorlistener : errorlisteners) {
					errorlistener.onError(errorModel);
				}
			} else {
				for (BarxPostListener<T> listener : postListeners) {
					listener.onPOSTCommit(responseEvent);
				}
			}
		}
		else {
			BarxErrorModel errModel = new BarxErrorModel();
			errModel.setErrMessage(responseEvent.getErrorMessage());
			for (BarxErrorListener errlistener : errorlisteners) {
				errlistener.onError(errModel);
			}

		}


	}


	public synchronized void addErrorErrorServiceClientListener(BarxErrorListener errorListener) {
		this.errorlisteners.add(errorListener);
	}


	public synchronized void addServiceClientListener(BarxPostListener<T> postListener) {
		this.postListeners.add(postListener);
	}
}