package com.itbarx.error.listener;

import java.util.EventListener;

import com.itbarx.error.common.ResponseServiceModel;

import com.itbarx.error.model.BarxErrorModel;


public interface BaseServiceListener<TModel> extends EventListener {
	
	public void onComplete(ResponseServiceModel<TModel> onComplete);
	public void onError(BarxErrorModel onError);

}
