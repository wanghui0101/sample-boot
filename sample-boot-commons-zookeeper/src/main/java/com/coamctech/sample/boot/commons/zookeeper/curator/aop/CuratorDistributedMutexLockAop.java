package com.coamctech.sample.boot.commons.zookeeper.curator.aop;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;

import com.coamctech.sample.boot.commons.zookeeper.curator.CuratorOperations;

@Aspect
public class CuratorDistributedMutexLockAop implements InitializingBean, Ordered {
	
	private static final Logger logger = LoggerFactory.getLogger(CuratorDistributedMutexLockAop.class);
	
	private CuratorOperations curatorOperations;
	private CuratorFramework client;
	
	public CuratorDistributedMutexLockAop() {
	}
	
	public CuratorDistributedMutexLockAop(CuratorOperations curatorOperations) {
		this.curatorOperations = curatorOperations;
	}
	
	public void setCuratorOperations(CuratorOperations curatorOperations) {
		this.curatorOperations = curatorOperations;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(curatorOperations);
		this.client = curatorOperations.getClient();
	}
	
	@Override
	public int getOrder() {
		return 1;
	}

	@Pointcut("@annotation(com.coamctech.sample.boot.commons.zookeeper.curator.aop.DistributedMethod)")
	public void pointCut() {
	}
	
	@Around("pointCut()")
	public Object doAround(ProceedingJoinPoint pjp) {
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method distributedMethod = signature.getMethod();
		DistributedMethod annoDistributed = AnnotationUtils.findAnnotation(distributedMethod, 
				DistributedMethod.class);
		String lockPath = annoDistributed.lockPath();
		
		long annoExecutionTimeConsumingEvaluation = annoDistributed.executionTimeConsumingEvaluation();
		TimeUnit annoExecutionTimeConsumingEvaluationTimeUnit = annoDistributed
				.executionTimeConsumingEvaluationTimeUnit();
		
		Assert.isTrue(annoExecutionTimeConsumingEvaluation >= DistributedMethod.WAIT_UNIT_LOCK_ACQUIRE);
		Assert.notNull(annoExecutionTimeConsumingEvaluationTimeUnit);
		
		InterProcessMutex mutex = new InterProcessMutex(client, lockPath); // 创建分布式锁节点
		
		try {
			if (annoExecutionTimeConsumingEvaluation == DistributedMethod.WAIT_UNIT_LOCK_ACQUIRE) {
				mutex.acquire(); // 在获取到锁之前保持一直阻塞的状态
			} else if (!mutex.acquire(annoExecutionTimeConsumingEvaluation, 
					annoExecutionTimeConsumingEvaluationTimeUnit)) {
				// 若获取锁超时，则抛出异常
				throw new AcquireLockTimeoutException("执行分布式方法" + signature.toLongString() + "超时");
			}
			return pjp.proceed(); // 执行业务方法
		} catch (Throwable e) {
			logger.error(e.getMessage());
			throw new DistributedMethodExecutionException(e);
		} finally {
			try {
				if (mutex.isAcquiredInThisProcess()) {
					mutex.release();
		        }
			} catch (Exception e) {
				logger.error(e.getMessage());
				throw new DistributedMethodExecutionException(e);
			}
		}
		
	}

}
