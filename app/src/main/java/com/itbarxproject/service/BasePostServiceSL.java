package com.itbarxproject.service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.itbarxproject.service.error.BarxErrorListener;

import android.content.Context;

public abstract class BasePostServiceSL<T> implements BarxPostListener<T>,  BarxErrorListener {

protected Context context;
	private String serviceUri;
	private List<BaseServiceListener<T>> listeners ;
	private BaseServiceListener<T> serviceListener;
	private BarxErrorListener errorListener = null;


	public BasePostServiceSL(Context appContext, BaseServiceListener<T> listener, int serviceResUriId) {
	// Base Constructor
	this.listeners = new CopyOnWriteArrayList<>();
	this.serviceUri = "";
	this.context=appContext;
	this.serviceUri = context.getString(serviceResUriId);
	this.serviceListener = listener;
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

	public synchronized void addListener(BaseServiceListener<T> listener) {
		this.listeners.add(listener);
	}



	public String getServiceUri() {
	return serviceUri;
	}





}
