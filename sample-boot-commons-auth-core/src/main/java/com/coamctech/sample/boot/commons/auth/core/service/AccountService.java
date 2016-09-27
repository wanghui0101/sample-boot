package com.coamctech.sample.boot.commons.auth.core.service;

import com.coamctech.sample.boot.commons.auth.core.Account;

/**
 * 账户服务接口。由业务系统提供实现。
 * 
 * @author wh
 * @lastModified 2016-6-7 15:50:22
 */
public interface AccountService {

	/**
	 * 获取散列算法
	 */
	String getHashAlgorithm();

	/**
	 * 获取加密次数
	 */
	int getHashInterations();

	/**
	 * 通过账户名称获取账户
	 * @param accountName
	 * @return
	 */
	Account findByAccountName(String accountName);
}
