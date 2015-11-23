package com.itbarx.listener;

import java.util.List;

import com.itbarx.error.listener.BaseServiceListener;
import com.itbarx.model.rebark.ReBarkGetPostSharedUserListModel;
import com.itbarx.model.rebark.ReBarkGetSharedPostListModel;

public interface ReBarkProcessesServiceListener<T> extends BaseServiceListener<T> {

	public void add(String isAdded);

	public void delete(String isDeleted);

	public void getSharedPostList(List<ReBarkGetSharedPostListModel> reBarkGetSharedPostListModel);

	public void getPostSharedUserList(List<ReBarkGetPostSharedUserListModel> reBarkPostSharedUserListModel);

	public void getSharedPostCount(String count);

	public void getUserCount(String count);

}
