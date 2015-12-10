package com.itbarx.listener;

import java.util.List;

import com.itbarx.service.BaseServiceListener;
import com.itbarx.model.post.PostGetPostDetailModel;
import com.itbarx.model.post.PostGetWallInfoModel;
import com.itbarx.model.post.PostNewPostListModel;
import com.itbarx.model.post.PostPopularPostListModel;
import com.itbarx.model.post.PostTimelineListForUserModel;
import com.itbarx.model.post.PostWallListForUserModel;

public interface PostProcessesServiceListener<T> extends BaseServiceListener<T> {

	public abstract void getTimelineListForUser(List<PostTimelineListForUserModel> postTimelineListForUserModel);

	public void getWallListForUser(List<PostWallListForUserModel> postWallListForUserModel);

	public void getPopularPostList(List<PostPopularPostListModel> popularPostListModel);

	public void getNewPostList(List<PostNewPostListModel> postNewPostListModels);

	public void getWallInfo(PostGetWallInfoModel postGetWallInfoModel);

	public void getPostDetail(PostGetPostDetailModel postDetailModel);

	public void isAdded(String isAdded);

}
