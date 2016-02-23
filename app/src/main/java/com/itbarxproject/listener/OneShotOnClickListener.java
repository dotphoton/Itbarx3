package com.itbarxproject.listener;

import java.util.Map;
import java.util.WeakHashMap;

import android.content.Context;
import android.os.SystemClock;
import android.view.View;

public abstract class OneShotOnClickListener implements View.OnClickListener {

	private final long minimumInterval;
	private Map<View, Long> lastClickMap;;
	private Context context;



	/**
	 * Click islemlerimizi artik buraya yazacagiz implements
	 * 
	 * @param v
	 *          The view that was clicked
	 */
	public abstract void onOneShotClick(View v);

	/**
	 * Bu constructor sayesinde iki click arası min bekleme zamanı bırakıyoruz.
	 * 
	 * @param minimumIntervalMsec
	 *          izin vereceğimiz min click suresidir
	 */
	public OneShotOnClickListener(long minimumIntervalMsec) {
	this.minimumInterval = minimumIntervalMsec;
	this.lastClickMap = new WeakHashMap<View, Long>();
	}

	public OneShotOnClickListener(long minimumIntervalMsec, Context pContext) {
	this.minimumInterval = minimumIntervalMsec;
	this.lastClickMap = new WeakHashMap<View, Long>();
	this.context = pContext;
	}

	@Override
	public void onClick(View clickedView) {
	Long previousClickTimestamp = lastClickMap.get(clickedView);
	long currentTimestamp = SystemClock.uptimeMillis();

	lastClickMap.put(clickedView, currentTimestamp);
	if (previousClickTimestamp == null || (currentTimestamp - previousClickTimestamp.longValue() > minimumInterval)) {

		// clickedView.setAnimation(ViewEffector.getFadeEffect());
		onOneShotClick(clickedView);

	}
	}

}
