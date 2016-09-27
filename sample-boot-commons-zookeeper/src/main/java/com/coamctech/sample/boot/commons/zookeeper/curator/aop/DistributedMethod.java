package com.coamctech.sample.boot.commons.zookeeper.curator.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * 用来标识某个方法要在分布式锁下执行
 * 
 * @author wh
 * 
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DistributedMethod {
	
	long WAIT_UNIT_LOCK_ACQUIRE = -1L;

	/**
	 * 分布式锁路径
	 * 
	 * <p>
	 * 不同的业务逻辑应使用不同的锁路径
	 * 
	 * @return
	 */
	String lockPath();
	
	/**
	 * 方法在执行时的耗时评估值
	 * 
	 * <p>
	 * 若执行时间超过此评估值将导致方法不执行，并抛出<code>AcquireLockTimeoutException</code>。
	 * 
	 * <p>
	 * 使用默认值会造成永远的锁等待，直到此节点获取到分布式锁。
	 * 
	 * <p>
	 * 在并发不太高，执行速度较快的方法上可以使用默认值。<br/>
	 * 但在高并发，执行相对较慢的方法上，可以通过设置此值而避免分布式锁节点在ZooKeeper堆积。
	 * 
	 * <p>
	 * <b>注意：</b>请谨慎填写此值。需要在锁堆积数量和保证方法尽可能多的执行之间找到平衡。
	 * 
	 * @return
	 */
	long executionTimeConsumingEvaluation() default WAIT_UNIT_LOCK_ACQUIRE;
	
	/**
	 * 方法在执行时的耗时评估值的时间单位。默认为秒。
	 * 
	 * @return
	 */
	TimeUnit executionTimeConsumingEvaluationTimeUnit() default TimeUnit.SECONDS;
}
