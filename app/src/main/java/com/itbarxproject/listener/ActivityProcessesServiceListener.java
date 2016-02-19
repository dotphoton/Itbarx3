package com.itbarxproject.listener;

import java.util.List;

import com.itbarxproject.service.BaseServiceListener;
import com.itbarxproject.model.activity.ActivityListModel;

public interface ActivityProcessesServiceListener<T> extends BaseServiceListener<T> {

	public void getActivityList(List<ActivityListModel> activityListModel);

}
