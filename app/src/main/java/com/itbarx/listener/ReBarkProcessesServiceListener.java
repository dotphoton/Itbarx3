package com.itbarx.listener;

import java.util.List;

import com.itbarx.service.BaseServiceListener;
import com.itbarx.model.rebark.ReBarkGetPostSharedUserListByPostIdModel;
import com.itbarx.model.rebark.ReBarkGetSharedPostListByUserIdModel;

public interface ReBarkProcessesServiceListener<T> extends BaseServiceListener<T> {

	public void add(String isAdded);

	public void delete(String isDeleted);

	public void getSharedPostList(List<ReBarkGetSharedPostListByUserIdModel> reBarkGetSharedPostListByUserIdModel);

	public void getPostSharedUserList(List<ReBarkGetPostSharedUserListByPostIdModel> reBarkPostSharedUserListModel);

	public void getSharedPostCount(String count);

	public void getUserCount(String count);

}
