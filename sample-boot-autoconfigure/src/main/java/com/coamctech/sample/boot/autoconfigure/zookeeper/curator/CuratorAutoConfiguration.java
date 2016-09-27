package com.coamctech.sample.boot.autoconfigure.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.coamctech.sample.boot.commons.zookeeper.curator.CuratorFrameworkFactoryBean;
import com.coamctech.sample.boot.commons.zookeeper.curator.CuratorOperations;
import com.coamctech.sample.boot.commons.zookeeper.curator.CuratorTemplate;
import com.coamctech.sample.boot.commons.zookeeper.curator.aop.CuratorDistributedMutexLockAop;

@Configuration
@ConditionalOnClass({CuratorFrameworkFactory.class, CuratorFramework.class, 
	CuratorOperations.class, CuratorTemplate.class})
@ConditionalOnProperty(prefix = "coamctech.zookeeper.curator", name = "enabled", 
	matchIfMissing = true)
@EnableConfigurationProperties(CuratorProperties.class)
public class CuratorAutoConfiguration {

	private final CuratorProperties curatorProperties;
	
	public CuratorAutoConfiguration(CuratorProperties curatorProperties) {
		this.curatorProperties = curatorProperties;
	}
	
	@Bean
	@ConditionalOnMissingBean(CuratorFramework.class)
	public CuratorFrameworkFactoryBean curatorFramework() {
		CuratorFrameworkFactoryBean cffb = new CuratorFrameworkFactoryBean();
		applyProperties(cffb);
		return cffb;
	}
	
	private void applyProperties(CuratorFrameworkFactoryBean cffb) {
		cffb.setConnectionString(this.curatorProperties.getConnectionString());
		cffb.setConnectionTimeout(this.curatorProperties.getConnectionTimeout());
		cffb.setSessionTimeout(this.curatorProperties.getSessionTimeout());
		cffb.setRetryPolicyType(this.curatorProperties.getRetryPolicyType());
		cffb.setRetryPolicyBaseSleepTime(
				this.curatorProperties.getRetryPolicyBaseSleepTime());
		cffb.setRetryPolicyMaxElapsedTime(
				this.curatorProperties.getRetryPolicyMaxElapsedTime());
		cffb.setRetryPolicyMaxRetries(
				this.curatorProperties.getRetryPolicyMaxRetries());
		cffb.setRetryPolicyMaxSleepTime(
				this.curatorProperties.getRetryPolicyBaseSleepTime());
		cffb.setRetryPolicySleepBetweenRetries(
				this.curatorProperties.getRetryPolicySleepBetweenRetries());
	}
	
	@Bean
	@ConditionalOnMissingBean(CuratorOperations.class)
	@ConditionalOnBean(CuratorFramework.class)
	public CuratorOperations curatorOperations() throws Exception {
		return new CuratorTemplate(curatorFramework().getObject());
	}
	
	@Bean
	@ConditionalOnBean(CuratorOperations.class)
	// 默认启动此AOP
	// 可以通过coamctech.zookeeper.curator.distributed-mutex-lock-aop.enabled=false关闭
	@ConditionalOnProperty(prefix = "coamctech.zookeeper.curator.distributed-mutex-lock-aop", 
		name = "enabled", matchIfMissing = true)
	public CuratorDistributedMutexLockAop curatorDistributedMutexLockAop() throws Exception {
		return new CuratorDistributedMutexLockAop(curatorOperations());
	}
	
}
