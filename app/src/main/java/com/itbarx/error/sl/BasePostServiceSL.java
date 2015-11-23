package com.itbarx.error.sl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.itbarx.error.listener.BarxClientListener;
import com.itbarx.error.listener.BarxErrorListener;
import com.itbarx.error.listener.BarxPostErrorListener;
import com.itbarx.error.listener.BaseServiceListener;

import android.content.Context;

public abstract class BasePostServiceSL<T> implements BarxPostErrorListener<T>,  BarxErrorListener {

protected Context context;
	private String serviceUri = "";
	private String serviceGetUri = "";
	private BaseServiceListener<T> serviceListener;
	private BarxErrorListener errorListener = null;

	// Base Constructor
	public BasePostServiceSL(Context appContext, BaseServiceListener<T> listener, int serviceResUriId) {
	// Base Constructor
	context=appContext;
	serviceUri = context.getString(serviceResUriId);
	serviceListener = listener;
	addListener(serviceListener);

	}

	public void setOnServiceErrorClientListener(BarxErrorListener onServiceErrorClientListener) {
		errorListener = onServiceErrorClientListener;
	addErrorListener(errorListener);
	}


	private List<BarxErrorListener> errorlisteners = new CopyOnWriteArrayList<BarxErrorListener>();

	public synchronized void addErrorListener(BarxErrorListener errorlistener) {
	errorlisteners.add(errorlistener);
	}

	public synchronized void removeErrorListener(BarxErrorListener errorlistener) {
	errorlisteners.remove(errorlistener);
	}

	private List<BaseServiceListener<T>> listeners = new CopyOnWriteArrayList<BaseServiceListener<T>>();

	public synchronized void addListener(BaseServiceListener<T> listener) {
	listeners.add(listener);
	}

	public synchronized void removeListener(BaseServiceListener<T> listener) {
	listeners.remove(listener);
	}

	public String getServiceUri() {
	return serviceUri;
	}

	void setServiceUri(String serviceUri) {
	this.serviceUri = serviceUri;
	}

	public String getServiceGETUri() {
	return serviceUri;
	}

	public void setServiceGETUri(int serviceResUriId) {
	this.serviceUri = context.getString(serviceResUriId);
	}


}
