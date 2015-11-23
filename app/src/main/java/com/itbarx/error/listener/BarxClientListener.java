package com.itbarx.error.listener;

import com.itbarx.error.common.ResponseServiceModel;

import java.util.EventListener;

/**
 * TODO: Add a class header comment!
 */
public interface BarxClientListener<T>  extends EventListener {

    public void onGETReceive(ResponseServiceModel<T> responseEvent);
}
