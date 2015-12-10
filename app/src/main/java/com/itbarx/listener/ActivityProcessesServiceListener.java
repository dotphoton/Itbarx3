package com.itbarx.listener;

import java.util.List;

import com.itbarx.service.BaseServiceListener;
import com.itbarx.model.activity.ActivityListModel;

public interface ActivityProcessesServiceListener<T> extends BaseServiceListener<T> {

	public void getActivityList(List<ActivityListModel> activityListModel);

}
