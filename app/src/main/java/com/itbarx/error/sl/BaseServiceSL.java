package com.itbarx.error.sl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.itbarx.error.listener.BarxClientListener;
import com.itbarx.error.listener.BarxErrorListener;
import com.itbarx.error.listener.BaseServiceListener;


import android.content.Context;




public abstract class BaseServiceSL<T> implements BarxClientListener<T> {
	

	Context context;
	private String serviceUri = "";
	private BaseServiceListener<T> serviceListener;
	private BarxErrorListener serviceErrorClientListener = null;
	
	//Base Constructor
	public BaseServiceSL(Context appContext,BaseServiceListener<T> listener,int serviceResUriId)
	{
		//Base Constructor
		context = appContext;
		serviceUri=	context.getString(serviceResUriId);
		serviceListener = listener;
		addListener(serviceListener);
		
	}
	public void setOnServiceErrorClientListener(BarxErrorListener onServiceErrorClientListener)
	{
		serviceErrorClientListener = onServiceErrorClientListener;
		addErrorListener(serviceErrorClientListener);
	}
	  private List<BarxErrorListener> errorlisteners = new CopyOnWriteArrayList<BarxErrorListener>();

	    public synchronized void addErrorListener(BarxErrorListener errorlistener) {
	    	errorlisteners.add(errorlistener);
	    }    

	    public synchronized  void removeErrorListener(BarxErrorListener errorlistener) {
	    	errorlisteners.remove(errorlistener);
	    }

	
	  private List<BaseServiceListener<T>> listeners = new CopyOnWriteArrayList<BaseServiceListener<T>>();

	    public synchronized void addListener(BaseServiceListener<T> listener) {
	      listeners.add(listener);
	    }    

	    public synchronized  void removeListener(BaseServiceListener<T> listener) {
	      listeners.remove(listener);
	    }

	  

	public	String getServiceUri() {
			return serviceUri;
		}

		void setServiceUri(String serviceUri) {
			this.serviceUri = serviceUri;
		}

	
		
}
