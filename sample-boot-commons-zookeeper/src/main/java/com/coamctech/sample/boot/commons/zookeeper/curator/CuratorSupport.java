package com.coamctech.sample.boot.commons.zookeeper.curator;

/**
 * Curator支持类
 * 
 * @author wh
 * @lastModified 2016-4-18 11:39:48
 */
public abstract class CuratorSupport {

	/**
	 * 将异常都转换为CuratorException
	 * @param t
	 * @return
	 */
	public CuratorException convertToCuratorException(Throwable t) {
		return new CuratorException(t);
	}
}
