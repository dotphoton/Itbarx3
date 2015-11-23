package com.itbarx.listener;

import java.util.List;

import com.itbarx.error.listener.BaseServiceListener;
import com.itbarx.model.reply.ReplyListModel;

public interface ReplyProcessesServiceListener<T> extends BaseServiceListener<T> {

	public void deleteReply(String idDeleted);

	public void getPostRepliesList(List<ReplyListModel> replyListModel);

	public void addReply(String isAdded);

}
