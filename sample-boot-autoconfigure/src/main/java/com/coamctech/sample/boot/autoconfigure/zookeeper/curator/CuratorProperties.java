package com.coamctech.sample.boot.autoconfigure.zookeeper.curator;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "coamctech.zookeeper.curator")
public class CuratorProperties {

	private String connectionString;
	private Integer connectionTimeout;
	private Integer sessionTimeout;

	private String retryPolicyType;
	private Integer retryPolicyBaseSleepTime;
	private Integer retryPolicyMaxElapsedTime;
	private Integer retryPolicyMaxRetries;
	private Integer retryPolicyMaxSleepTime;
	private Integer retryPolicySleepBetweenRetries;

	public String getConnectionString() {
		return connectionString;
	}

	public void setConnectionString(String connectionString) {
		this.connectionString = connectionString;
	}

	public Integer getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(Integer connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public Integer getSessionTimeout() {
		return sessionTimeout;
	}

	public void setSessionTimeout(Integer sessionTimeout) {
		this.sessionTimeout = sessionTimeout;
	}

	public String getRetryPolicyType() {
		return retryPolicyType;
	}

	public void setRetryPolicyType(String retryPolicyType) {
		this.retryPolicyType = retryPolicyType;
	}

	public Integer getRetryPolicyBaseSleepTime() {
		return retryPolicyBaseSleepTime;
	}

	public void setRetryPolicyBaseSleepTime(Integer retryPolicyBaseSleepTime) {
		this.retryPolicyBaseSleepTime = retryPolicyBaseSleepTime;
	}

	public Integer getRetryPolicyMaxElapsedTime() {
		return retryPolicyMaxElapsedTime;
	}

	public void setRetryPolicyMaxElapsedTime(Integer retryPolicyMaxElapsedTime) {
		this.retryPolicyMaxElapsedTime = retryPolicyMaxElapsedTime;
	}

	public Integer getRetryPolicyMaxRetries() {
		return retryPolicyMaxRetries;
	}

	public void setRetryPolicyMaxRetries(Integer retryPolicyMaxRetries) {
		this.retryPolicyMaxRetries = retryPolicyMaxRetries;
	}

	public Integer getRetryPolicyMaxSleepTime() {
		return retryPolicyMaxSleepTime;
	}

	public void setRetryPolicyMaxSleepTime(Integer retryPolicyMaxSleepTime) {
		this.retryPolicyMaxSleepTime = retryPolicyMaxSleepTime;
	}

	public Integer getRetryPolicySleepBetweenRetries() {
		return retryPolicySleepBetweenRetries;
	}

	public void setRetryPolicySleepBetweenRetries(
			Integer retryPolicySleepBetweenRetries) {
		this.retryPolicySleepBetweenRetries = retryPolicySleepBetweenRetries;
	}

}
