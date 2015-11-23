package com.itbarx.activity;

import com.itbarx.R;
import com.itbarx.application.ItbarxGlobal;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

public class TabContainer extends BaseActivity {

	@Override protected int getLayoutResourceId() {
		return R.layout.tab_activity;
	}

	@Override protected Context getContext() {
		return TabContainer.this;
	}

	@Override protected void initViews() {
		setTabs();
	}

	TabHost tabHost;

	private void setTabs() {
		tabHost = (TabHost) findViewById(android.R.id.tabhost);
		tabHost.setup(this.getLocalActivityManager());
		tabHost.setOnTabChangedListener(tabChangeListener);
		addTabs();

	}

	static int counter = 0;

	void addTabs() {
		addTab("HOME", R.drawable.tab_home, T_HomeActivity.class);
		addTab("ACTIVITY", R.drawable.tab_activity, T_SecondActivity.class);
		addTab("RECORD", R.drawable.tab_record, T_RecordActivity.class);
		addTab("SEARCH", R.drawable.tab_search, T_SearchActivity.class);
		addTab("PROFILE", R.drawable.tab_profile, T_ProfileActivity.class);
		ItbarxGlobal.getInstance().tabHost = tabHost;
	}

	private void addTab(String labelId, int drawableId, Class<?> c) {
		Intent intent = new Intent(this, c);
		TabHost.TabSpec spec = tabHost.newTabSpec(labelId);
		View tabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_indicator, tabHost.getTabWidget(), false);
		ImageView icon = (ImageView) tabIndicator.findViewById(R.id.icon);
		icon.setImageResource(drawableId);
		spec.setContent(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
		spec.setIndicator(tabIndicator);
		spec.setContent(intent);
		tabHost.addTab(spec);
	}

	OnTabChangeListener tabChangeListener = new OnTabChangeListener() {
		@Override public void onTabChanged(String tabId) {
			if (counter == 0) {
				counter++;
				return;
			}

			//tabHost.clearAllTabs();
			//addTabs();
		}
	};




}
