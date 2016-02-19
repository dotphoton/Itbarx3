package com.itbarxproject.listener;

import java.util.List;

import com.itbarxproject.service.BaseServiceListener;
import com.itbarxproject.model.follow.FollowerListByFollowingIdModel;
import com.itbarxproject.model.follow.FollowingListByFollowerIdModel;
import com.itbarxproject.model.follow.PendingListByFollowingIdModel;
import com.itbarxproject.model.follow.SendPendingListByFollowerIdModel;

public interface FollowingProcessesServiceListener<T> extends BaseServiceListener<T> {

	public void add(String isAdded);

	public void updateAsFriend(String isUpdateAsFriend);

	public void updateAsBlocked(String isUpdateAsBlocked);

	public void countFollower(String count);

	public void countFollowing(String count);

	public void countPending(String count);

	public void countSendPending(String count);

	public void deleteFollow(String isDeleted);

	public void getFollowerListById(List<FollowerListByFollowingIdModel> followerListByFollowingIdModel);

	public void getFollowingListById(List<FollowingListByFollowerIdModel> followingListByFollowerIdModel);

	public void getPendingListById(List<PendingListByFollowingIdModel> pendingListByFollowingIdModel);

	public void getSendPendingListById(List<SendPendingListByFollowerIdModel> sendPendingListByFollowerIdModel);
}
