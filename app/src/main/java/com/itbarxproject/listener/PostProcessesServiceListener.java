package com.itbarxproject.listener;

import java.util.List;

import com.itbarxproject.service.BaseServiceListener;
import com.itbarxproject.model.post.PostGetPostDetailModel;
import com.itbarxproject.model.post.PostGetWallInfoModel;
import com.itbarxproject.model.post.PostNewPostListModel;
import com.itbarxproject.model.post.PostPopularPostListModel;
import com.itbarxproject.model.post.PostTimelineListForUserModel;
import com.itbarxproject.model.post.PostWallListForUserModel;

public interface PostProcessesServiceListener<T> extends BaseServiceListener<T> {

	public abstract void getTimelineListForUser(List<PostTimelineListForUserModel> postTimelineListForUserModel);

	public void getWallListForUser(List<PostWallListForUserModel> postWallListForUserModel);

	public void getPopularPostList(List<PostPopularPostListModel> popularPostListModel);

	public void getNewPostList(List<PostNewPostListModel> postNewPostListModels);

	public void getWallInfo(PostGetWallInfoModel postGetWallInfoModel);

	public void getPostDetail(PostGetPostDetailModel postDetailModel);

	public void isAdded(String isAdded);

}
