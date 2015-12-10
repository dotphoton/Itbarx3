package com.itbarx.listener;

import java.util.List;

import com.itbarx.service.BaseServiceListener;
import com.itbarx.model.like.LikePostsByUserIdModel;
import com.itbarx.model.like.LikeUsersByPostIdModel;

public interface LikeProcessesServiceListener<T> extends BaseServiceListener<T> {

	public void addLike(String isAdded);

	public void deleteLike(String isDeleted);

	public void countLikeByUser(String count);

	public void countLikeByPost(String count);

	public void getLikePostByUserId(List<LikePostsByUserIdModel> likePostsByUserIdModel);

	public void getLikeUsersByPostId(List<LikeUsersByPostIdModel> likeUsersByPostIdModel);

}
