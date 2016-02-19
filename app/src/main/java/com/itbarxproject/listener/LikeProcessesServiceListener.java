package com.itbarxproject.listener;

import java.util.List;

import com.itbarxproject.service.BaseServiceListener;
import com.itbarxproject.model.like.LikePostsByUserIdModel;
import com.itbarxproject.model.like.LikeUsersByPostIdModel;

public interface LikeProcessesServiceListener<T> extends BaseServiceListener<T> {

	public void addLike(String isAdded);

	public void deleteLike(String isDeleted);

	public void countLikeByUser(String count);

	public void countLikeByPost(String count);

	public void getLikePostByUserId(List<LikePostsByUserIdModel> likePostsByUserIdModel);

	public void getLikeUsersByPostId(List<LikeUsersByPostIdModel> likeUsersByPostIdModel);

}
