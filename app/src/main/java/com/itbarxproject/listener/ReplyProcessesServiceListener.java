package com.itbarxproject.listener;

import java.util.List;

import com.itbarxproject.service.BaseServiceListener;
import com.itbarxproject.model.reply.ReplyListModel;

public interface ReplyProcessesServiceListener<T> extends BaseServiceListener<T> {

	public void deleteReply(String idDeleted);

	public void getPostRepliesList(List<ReplyListModel> replyListModel);

	public void addReply(String isAdded);

}
