package com.itbarx.service;

import java.util.EventListener;


import com.itbarx.service.error.BarxErrorModel;


public interface BaseServiceListener<TModel> extends EventListener {
	
	public void onComplete(ResponseEventModel<TModel> onComplete);
	public void onError(BarxErrorModel onError);

}
