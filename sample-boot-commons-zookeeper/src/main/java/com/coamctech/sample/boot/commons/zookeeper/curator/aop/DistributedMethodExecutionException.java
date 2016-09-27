package com.coamctech.sample.boot.commons.zookeeper.curator.aop;

public class DistributedMethodExecutionException extends RuntimeException {

	private static final long serialVersionUID = 4779930855308585940L;
	
	public DistributedMethodExecutionException(String message) {
		super(message);
	}

	public DistributedMethodExecutionException(Throwable cause) {
		super(cause);
	}

	public DistributedMethodExecutionException(String message, Throwable cause) {
		super(message, cause);
	}

}
