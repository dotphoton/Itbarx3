package com.itbarx.error.sl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

import com.itbarx.R;
import com.itbarx.error.common.ResponseServiceModel;
import com.itbarx.common.ServiceUtil;
import com.itbarx.error.listener.BarxErrorListener;
import com.itbarx.error.listener.BarxPostErrorListener;
import com.itbarx.error.model.BarxErrorModel;
import com.itbarx.error.util.BarxErrorHelper;


import android.content.Context;
import android.os.AsyncTask;

public class BaseServicePostClientSL<T> extends AsyncTask<String, Void, String> {

    /* LOCK CONFIG */
    /*
	 * private final ReentrantLock lock = new ReentrantLock(); private final
	 * Condition tryAgain = lock.newCondition(); private volatile boolean
	 * finished = false;
	 * 
	 */
    private List<NameValuePair> nameValuePair;

    //	private ArrayList<ServicePostClientListener<T>> _listeners = new ArrayList<ServicePostClientListener<T>>();
//	private ArrayList<ServiceErrorClientListener> errorlisteners = new ArrayList<ServiceErrorClientListener>();
    private ArrayList<BarxPostErrorListener<T>> _listeners = new ArrayList<BarxPostErrorListener<T>>();
    private ArrayList<BarxErrorListener> errorlisteners = new ArrayList<BarxErrorListener>();
    Boolean serviceStatus = false;
    Context context = null;
    String _serviceName = "";
    String errorMessage = "";
    InputStream inputStream = null;
    String soapAction = "";
    String soapInterface = "";
    boolean isArrived = false;
    private boolean isBasicHttpBinding = false;
    private String RequestBody = null;
    private boolean IsGzipEncode = false;

    // private boolean IS_LARGE_DATA=false;

    private String methodName = "";

    private String sendData = "";

    public BaseServicePostClientSL(Context contextInstance, String serviceName, String requestBody) {
        context = contextInstance;
        _serviceName = serviceName;
        setRequestBody(requestBody);

    }

    public BaseServicePostClientSL(Context contextInstance, String serviceName, String SOAPInterface, String soapMethodName,
                                   List<NameValuePair> soapParams) {
        context = contextInstance;
        _serviceName = serviceName;
        soapInterface = SOAPInterface;
        methodName = soapMethodName;
        nameValuePair = soapParams;
    }

    public String readPostFeed(String URL, String uriParam) throws Exception {

        String uri = URL;
        String responseData = "";
        serviceStatus = false;
        errorMessage = "";

        try {

            if (uriParam != null && !uriParam.equalsIgnoreCase("")) {

                uri += "/" + uriParam;
            }

            HttpParams httpParameters = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters, Integer.parseInt(context.getString(R.string.httpCallTimeout)));
            HttpConnectionParams.setSoTimeout(httpParameters, Integer.parseInt(context.getString(R.string.httpCallTimeout)));

            DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
            HttpPost httpPost = new HttpPost(uri);
            if (!isBasicHttpBinding()) {
                String SOAPRequestXML = "<s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\">\n";
                SOAPRequestXML += "<s:Body>\n";
                SOAPRequestXML += "<" + methodName + " xmlns=\"http://tempuri.org/\">";

                if (nameValuePair != null && nameValuePair.size() > 0) {
                    for (NameValuePair iter : nameValuePair) {
                        SOAPRequestXML += "\n<" + iter.getName() + ">";
                        SOAPRequestXML += iter.getValue();
                        SOAPRequestXML += "</" + iter.getName() + ">";
                    }
                }
                SOAPRequestXML += "\n</" + methodName + ">";
                SOAPRequestXML += "</s:Body>\n";
                SOAPRequestXML += "</s:Envelope>";

                sendData = SOAPRequestXML;

                httpPost.setHeader("SOAPAction", "http://tempuri.org/" + soapInterface + "/" + methodName);
                httpPost.setHeader("Content-Type", "text/xml; charset=utf-8");
                httpPost.setHeader("Accept-Encoding", "gzip, deflate");

                errorMessage += uri;

                StringEntity se = new StringEntity(SOAPRequestXML, HTTP.UTF_8);
                se.setContentType("text/xml");
                httpPost.setEntity(se);
            } else {
                // httpPost.setHeader("Content-Type",
                // "application/x-www-form-urlencoded");
                // httpPost.setHeader("Cache-Control", "no-cache");
                // httpPost.setHeader("Accept-Encoding", "gzip, deflate");

                StringEntity se = new StringEntity(getRequestBody(), HTTP.UTF_8);
                se.setContentType("application/json");
                httpPost.setEntity(se);
                methodName = uriParam;

            }

            HttpResponse response = httpClient.execute(httpPost);

            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();

            if (statusCode == 200 || statusCode == 400) {
                isArrived = true;
                HttpEntity entity = response.getEntity();

                try {

                    inputStream = entity.getContent();
			/*
			 * if(getIS_LARGE_DATA()) { responseData
			 * =ServiceUtil.EncodeGZipStreamLargeData(inputStream); } else
			 */
                    if (isIsGzipEncode()) {
                        responseData = ServiceUtil.EncodeGZipStream(inputStream);
                    } else {
                        responseData = ServiceUtil.EncodeUTF8InputStream(inputStream);
                    }

                    serviceStatus = true;

                } catch (Exception ex) {

                    errorMessage += "Web servisden gelen data problemi";
                }

            } else {
                errorMessage = "http Status Code : " + statusCode;

            }
        } catch (Exception ex) {

            errorMessage = "internet bağlantı problemi";

        }
        int findHtmlIndex = responseData.indexOf("<!DOCTYPE html>");
        if (findHtmlIndex != -1)
            responseData = responseData.substring(0, findHtmlIndex);

        return responseData;
    }

    @Override
    protected String doInBackground(String... urls) {

        String result = "";
        try {

            result = readPostFeed(urls[0], urls[1]);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(String result) {

        this.fireResponseEvent(result);

    }

    public String getServiceName() {
        return this._serviceName;
    }

	/* ERROr Listener Memebers */

    /*
    public synchronized void removeServiceClientListener(ServiceErrorClientListener l) {
    errorlisteners.remove(l);
    }

    public synchronized void addErrorErrorServiceClientListener(ServiceErrorClientListener l) {
    errorlisteners.add(l);
    }
*/
	/* Listener members */
	/*
	public synchronized void removeServiceClientListener(ServicePostClientListener<T> l) {
	_listeners.remove(l);
	}

	public synchronized void addServiceClientListener(ServicePostClientListener<T> l) {
	_listeners.add(l);
	}

*/
    public synchronized void removeServiceClientListener(BarxErrorListener l) {
        errorlisteners.remove(l);
    }

    public synchronized void addErrorErrorServiceClientListener(BarxErrorListener l) {
        errorlisteners.add(l);
    }

    public synchronized void removeServiceClientListener(BarxPostErrorListener<T> l) {
        _listeners.remove(l);
    }

    public synchronized void addServiceClientListener(BarxPostErrorListener<T> l) {
        _listeners.add(l);
    }

    private synchronized void fireResponseEvent(String responseData) {

        ResponseServiceModel<T> responseEvent = new ResponseServiceModel<T>(this, this._serviceName, responseData, serviceStatus, errorMessage);
        responseEvent.setStream(inputStream);
        responseEvent.setSendData(sendData);
        responseEvent.setIsArrived(isArrived);
        responseEvent.setSentParams(nameValuePair);
        responseEvent.setMethodName(methodName);
        if (serviceStatus) {

            //	ServiceErrorHelper helper = new ServiceErrorHelper();
            //	ServiceErrorModel serviceErrorModel = helper.getServiceErrorModel(responseEvent.getResponseData());

            BarxErrorHelper helper = new BarxErrorHelper();
            BarxErrorModel errModel = helper.getServiceErrorModel(responseEvent.getResponseData());

            if (errModel == null|| errModel.getIsError()) {
                for (BarxErrorListener errorlistener : errorlisteners) {
                    errorlistener.onError(errModel);
                }

            } else {
                for (BarxPostErrorListener<T> listener : _listeners) {
                    listener.onPOSTCommit(responseEvent);
                }
            }

        } else {
            BarxErrorModel serviceErrorModel = new BarxErrorModel();
            serviceErrorModel.setErrMessage(responseEvent.getErrorMessage());
            for (BarxErrorListener errorlistener : errorlisteners) {
                errorlistener.onError(serviceErrorModel);
            }
        }
    }

    public boolean isBasicHttpBinding() {
        return isBasicHttpBinding;
    }

    public void setBasicHttpBinding(boolean isBasicHttpBinding) {
        this.isBasicHttpBinding = isBasicHttpBinding;
    }

    public String getRequestBody() {
        return RequestBody;
    }

    public void setRequestBody(String requestBody) {
        RequestBody = requestBody;
    }

    public boolean isIsGzipEncode() {
        return IsGzipEncode;
    }

    public void setIsGzipEncode(boolean isGzipEncode) {
        IsGzipEncode = isGzipEncode;
    }

}
