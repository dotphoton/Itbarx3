package com.itbarxproject.service;

import java.util.EventListener;


import com.itbarxproject.service.error.BarxErrorModel;


public interface BaseServiceListener<TModel> extends EventListener {
	
	public void onComplete(ResponseEventModel<TModel> onComplete);
	public void onError(BarxErrorModel onError);

}
