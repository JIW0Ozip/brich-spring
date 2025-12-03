package com.ktdsuniversity.edu.domain.user.dao;

import com.ktdsuniversity.edu.domain.user.vo.response.ResponseUserSubscriptionInfoVO;

public interface SubscriptionPaymentDao {

	ResponseUserSubscriptionInfoVO selectSubscriptionInfoByUserId(String usrId);

}