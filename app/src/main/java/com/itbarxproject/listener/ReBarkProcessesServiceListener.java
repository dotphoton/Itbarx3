package com.itbarxproject.listener;

import java.util.List;

import com.itbarxproject.service.BaseServiceListener;
import com.itbarxproject.model.rebark.ReBarkGetPostSharedUserListByPostIdModel;
import com.itbarxproject.model.rebark.ReBarkGetSharedPostListByUserIdModel;

public interface ReBarkProcessesServiceListener<T> extends BaseServiceListener<T> {

	public void add(String isAdded);

	public void delete(String isDeleted);

	public void getSharedPostList(List<ReBarkGetSharedPostListByUserIdModel> reBarkGetSharedPostListByUserIdModel);

	public void getPostSharedUserList(List<ReBarkGetPostSharedUserListByPostIdModel> reBarkPostSharedUserListModel);

	public void getSharedPostCount(String count);

	public void getUserCount(String count);

}
