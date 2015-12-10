package com.itbarx.service;

import java.util.EventListener;

/**
 * TODO: Add a class header comment!
 */
public interface BarxPostListener<T> extends EventListener {

    public void onPOSTCommit(ResponseEventModel<T> responseEvent);
}
